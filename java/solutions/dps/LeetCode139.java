package solutions.dps;

import util.SolutionsFacade;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode139 implements SolutionsFacade {

    /**
     * 动规：用dp[n]表示s内从0到n位子串被拆分的可能性。则
     * dp[n] = dp[i]（必须为true)& (s[i,n] in wd ) || dp[j]（必须是true）&(s[j,n] in wd) || ... || (s[0,n] in wd)
     *
     * 补充：
     * 本体有好多优化点 ——
     * 1.用Set加快匹配速度；2.剪枝，如当剩余子串长度大于字典最长子串长度时即可结束迭代；3.迭代时只要有1种可能成立（为true）时停止迭代。
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp  = new boolean[s.length()];
        for(int i = 0;i<s.length();i++){
            // 查询当前子串在不在wordlist
            dp[i] = wordSet.contains( s.substring(0, i+1)) ;
            if(dp[i] == false){
                for(int j = i-1;j>-1;j--){
                    if(dp[j] == true) {
                        dp[i] |= dp[j] && wordSet.contains(s.substring(j + 1, i + 1));
                        if(dp[i])
                            break;
                    }
                }
            }

        }
        return dp[s.length()-1];
    }






    /**
     * 递归写法，数据量大了就会超时
     * @param s
     * @param start
     * @param wordDict
     * @return
     */
    public boolean match(String s, int start, List<String> wordDict){
        if(start == s.length())
            return true;
        boolean res = false;
        // 从头匹配
        for(String word:wordDict){
            if(s.indexOf(word, start)==start ){
                res |= match(s, start+word.length(),wordDict);
            }
        }
        return res;
    }

    @Override
    public void calculate(Object... objects) {
        boolean res = wordBreak((String)objects[0], (List<String>)objects[1]);
        System.out.println("we stop");
    }
}
