package solutions.others;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class LeetCode146 {

    public int base;
    Queue<Integer> queue;


    /**
     * 思路：
     * 一、 两个map，一个map保存key的值，另一个map保存这些key的计数，每次get、put对非key做减一操作，主key赋值为容量大小，
     * 当到达容量后put时，将计数最小的值删掉。
     *
     */
//    Map<Integer, Integer> freqs;
//
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
     * 二、 一个map加一个单向队列，队列种元素是key出现的先后顺序，如：[1,2,3,4]，右边是新出现，左边是已出现，当重新get(2)之后，移动队
     * 列中元素，将队列中元素从左侧出，再从右边进队，如此移动队列长度减一步（跳过2自身），最后将2补到末尾，由此变成[1,3,4,2]。这样便用单
     * 向队列维护其了LRU。
     *
     * @param capacity
     */
//    public LeetCode146(int capacity) {
//        base = capacity;
//        queue = new LinkedList<>();
//        values = new HashMap<>();
//    }
//
//    public int get(int key) {
//        if(!values.containsKey(key))
//            return -1;
//        // move element, because key exists in queue.
//        adjust(key);
//        return values.get(key);
//    }
//
//    public void put(int key, int value) {
//        if(!values.containsKey(key)){
//            // 添加到队列末尾，根据队列长度决定是否删除元素
//            if(queue.size()==base){
//                int t =queue.poll();
//                values.remove(t);
//            }
//            values.put(key, value);
//            queue.add(key);
//        }
//        else{
//            // 调整队列
//            adjust(key);
//            values.put(key, value);
//        }
//    }
//
//    public void adjust(int key){
//        if(queue.size()>0){
//            int step = queue.size();
//            while(step>=1){
//                step-=1;
//                int t = queue.poll();
//                if(t==key)
//                    continue;
//                queue.add(t);
//            }
//            queue.add(key);
//        }
//    }


    /**
     * 思路：
     * 三、 一个map加一个双向队列：（官方解法）
     *     因为官方提供的deque没有从队列中间直接删除的API，这里我们手写一个双端队列，实际就是LinkedHashMap源码。
     *
     * 注意：
     *     哈希表的结构设计，必须方便删除操作，Deque中我们保存key， HashMap的value中我们保存以引用做key的真实值。
     *
     * 补充：
     *     说实话，有点担心put、remove的key正好是哑节点对应的key。
     * @上一条：
     *     实际上不必担心哑节点，因为map中只会存真实节点，没必要存哑节点，故不存在覆盖问题。
     *     因此可以设计成：Map{key - Deque}; Deque{ key - value}（方便删除）
     *
     */

    class MyDeque{
        public int key;
        public int value;
        public MyDeque next;
        public MyDeque front;

        public MyDeque( int k, int v){
            key = k;
            value = v;
            next = null;
            front = null;
        }

        // 本例只给head用
        void add(MyDeque s){
            next.front = s;
            s.next = next;
            next = s;
            s.front = this;
        }

        // 本例只给tail用
        MyDeque poll(){
            MyDeque item = front;
            front.front.next = this;
            this.front = front.front;
            return item;
        }
    }

    private Map<Integer, MyDeque> map;
    MyDeque head,tail;
    int length;

    public LeetCode146(int capacity) {
        base = capacity;
        head = new MyDeque(-1, 0);
        tail = new MyDeque(-1, 0);
        head.next = tail;
        tail.front = head;
        length = 2;
        map = new HashMap<>();
    }

    // get之后将元素移到队头
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        else{
            moveToHead(key);
            return map.get(key).value;
        }
    }

    // put时key存在则覆盖，并移动；不存在看队列长度，已经满了就先从尾部出队（并从map删除），再往头部加入新元素
    public void put(int key, int value) {
        if(map.containsKey(key)){
            moveToHead(key);
            map.get(key).value = value;
        }else{
            if(length-2 == base){
                MyDeque item = tail.poll();
                map.remove(item.key);
                length -= 1;
            }
            MyDeque item = new MyDeque(key, value);
            head.add(item);
            map.put(key, item);
            length += 1;
        }
    }

    public void moveToHead(int key){
        MyDeque item = map.get(key);
        // 建立新连接
        item.front.next = item.next;
        item.next.front = item.front;
        // 移动到头
        head.next.front = item;
        item.next = head.next;

        head.next = item;
        item.front = head;
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
        res = -1;
    }

}
