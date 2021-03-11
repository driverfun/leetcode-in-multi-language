package solutions.others;

import java.security.Key;
import java.util.*;

public class LeetCode146 {

    public int base;
    Map<Integer, Integer> values;
    Map<Integer, Integer> freqs;
    Queue<Integer> queue;

//    public LeetCode146(int capacity) {
//        values = new HashMap<>();
//        freqs = new HashMap<>();
//        base = capacity;
//    }
//
//    // get之后对所有的其他key减一。
//    public int get(int key) {
//        if(!values.containsKey(key))
//            return -1;
//        else{
//            updateMap(key, false);
//            return values.get(key);
//        }
//    }
//
//    void updateMap(int key, boolean delete){
//        int min = key;     // 代表最小值的key
//        for(Integer k: freqs.keySet()){
//            if(k==key){
//                freqs.put(k, base);
//                continue;
//            }
//            int f = freqs.get(k);
//            f -= 1;
//            if(freqs.get(k)<= freqs.getOrDefault(min, base))
//                min =k;
//            freqs.put(k, f);
//        }
//        if(delete){
//            values.remove(min);
//            freqs.remove(min);
//        }
//    }
//
//    // put之前对所有其他的key减一。
//    public void put(int key, int value) {
//        if(!values.containsKey(key) && values.keySet().size()==base)
//            updateMap(key, true);
//        else
//            updateMap(key, false);
//        values.put(key,value);
//        freqs.put(key, base);
//    }

    /**
     * 思路：
     * 一、 两个map，如上注释，一个map保存key的值，另一个map保存这些key的计数，每次get、put对非key做减一操作，主key赋值为容量大小，
     * 当到达容量后put时，将计数最小的值删掉。
     * 二、 一个map加一个单向队列，队列种元素是key出现的先后顺序，如：[1,2,3,4]，右边是新出现，左边是已出现，当重新get(2)之后，移动队
     * 列中元素，将队列中元素从左侧出，再从右边进队，如此移动队列长度减一步（跳过2自身），最后将2补到末尾，由此变成[1,3,4,2]。这样便用单
     * 向队列维护其了LRU。
     *
     * 三、 一个map加一个双向队列：（官方解法）
     *
     * @param capacity
     */
    public LeetCode146(int capacity) {
        base = capacity;
        queue = new LinkedList<>();
        values = new HashMap<>();
    }

    public int get(int key) {
        if(!values.containsKey(key))
            return -1;
        // move element, because key exists in queue.
        adjust(key);
        return values.get(key);
    }

    public void put(int key, int value) {
        if(!values.containsKey(key)){
            // 添加到队列末尾，根据队列长度决定是否删除元素
            if(queue.size()==base){
                int t =queue.poll();
                values.remove(t);
            }
            values.put(key, value);
            queue.add(key);
        }
        else{
            // 调整队列
            adjust(key);
            values.put(key, value);
        }
    }

    public void adjust(int key){
        if(queue.size()>0){
            int step = queue.size();
            while(step>=1){
                step-=1;
                int t = queue.poll();
                if(t==key)
                    continue;
                queue.add(t);
            }
            queue.add(key);
        }
    }


    public static void main(String[] args){
        int res = 0;
        LeetCode146 lRUCache = new LeetCode146(2);

        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        res = lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        res = lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        res = lRUCache.get(1);    // 返回 -1 (未找到)
        res =lRUCache.get(3);    // 返回 3
        res =lRUCache.get(4);    // 返回 4
//        LeetCode146 lRUCache = new LeetCode146(1);
//        lRUCache.put(2,1);
//        res = lRUCache.get(2);
//
//        res = -1;
    }

}
