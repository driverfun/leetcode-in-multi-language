package solutions.dps;

import util.SolutionsFacade;

public class LeetCode5 implements SolutionsFacade {

    /**
     * 一种比较笨的解法（中心扩散思想）：
     * 观察发现，所有的回文串分两种，奇数长度的中间点为一个不重复值；偶数长度的中间两值相等。
     * 故从头开始遍历字符串，对每个下标做以上两种方式的判断，当满足时则开始扩大搜寻回文串的范围，直到下标越界或者不是回文。
     * 再用一个数组记录这些下标组合，每当获得更大的回文长度（上界-下界）时更新这组值，最后对原字符串做截取就是所求回文串。
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int[] res = {0, 1};
        if (s.length() == 1)
            return s;
        else{ // >=2 则可能有奇数回文和偶数回文
            int j = 0;
            int k = 0;
            for ( int i = 1;i< s.length();i++){
                if (s.charAt(i) == s.charAt(i-1)){
                    // 寻找上下界，
                    j = i-1;
                    k = i;
                    while(j>=0 && k<s.length() && s.charAt(j)==s.charAt(k)){
                        j -=1;
                        k +=1;
                    }
                    j += 1;
                    k -= 1;
                    // 并计算长度
                    if (k- j >= res[1]- res[0]){
                        res[0] = j;         // res0 记录小值
                        res[1] = k+1;         // res1 记录大值
                    }
                }
                // 不判断右边界
                if(i+1<s.length() && s.charAt(i-1) == s.charAt(i+1)){
                    j = i-1;
                    k = i+1;
                    while(j>=0 && k<s.length() && s.charAt(j)==s.charAt(k)){
                        j -=1;
                        k +=1;
                    }
                    j += 1;
                    k -= 1;
                    // 并计算长度
                    if (k- j >= res[1]- res[0]){
                        res[0] = j;         // res0 记录小值
                        res[1] = k+1;         // res1 记录大值
                    }
                }


            }
            return s.substring(res[0], res[1]);
        }
    }


    @Override
    public void calculate(Object... objects) {
        String res = longestPalindrome((String)objects[0]);
        int s = 0;
    }
}
