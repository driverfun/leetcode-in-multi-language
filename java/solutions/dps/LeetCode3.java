package solutions.dps;

import util.SolutionsFacade;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode3 implements SolutionsFacade {

    /**
     * 思路：用一个Set集合记录当前字串中的字符，每次新添加一个字符时去查是否重复，如果重复则停止搜索，记录下当前字串的长度；
     *       清空Set，下一下标继续开始这一循环。这样的时间复杂度O(n^2), 空间复杂度O(n)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        int max = 0;
        int j = 0;
        Set<Character> set =  new HashSet<>();
        for (int i = 0;i<s.length();i++){

            for (j = i;j< s.length();j++){
                if(set.contains(s.charAt(j)))
                    break;
                set.add(s.charAt(j));
            }
            max = max>(j-i)?max:(j-i);
            set.clear();
        }
        return max;
    }

    /**
     * 滑动窗口法：
     * 从左到右遍历字符串，当新出现的字符在前面已经出现过时，说明一个不重复子串已经终结
     * 先计算长度并更新max（最大子串长度）值，再移动左侧指针到重复元素的下一个。注意，此时
     * 可依次移动并删除掉辅助空间中记录的字符元素。因为整个过程只前进不后退，时间复杂度为 2*O(n)， 空间复杂度O(n)。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        int max = 0;
        int k = 0;
        int i = 0;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (i = 0;i< s.length();i++){
            if(map.containsKey(s.charAt(i))){
                // do stuff
                max = max>(i-start)?max:(i-start);
                k = map.get(s.charAt(i))+1;
                while (start<k){
                    // 按顺序清掉其他元素
                    map.remove(s.charAt(start));
                    start += 1;
                }
                // 结束后start定位完毕
            }
            map.put(s.charAt(i), i);
        }
        //i = s.length()
        max = max>(i-start)?max:(i-start);
        return max;
    }



    @Override
    public void calculate(Object[] objects){
        lengthOfLongestSubstring((String) objects[0]);
    }

}
