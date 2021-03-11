package solutions.dps;

import util.SolutionsFacade;

public class LeetCode322 implements SolutionsFacade {

    /**
     * 思路：
     *  这题和139很像，状态转移方程 dp[i]= min[dp(nums[i]-coins[x])+1], x =0,1,...
     *
     * 注意（以下这些地方和139不同之处）：
     *  1. 输入的值无法分解时返回-1。我的处理方法是将dp数组全部初始化为-1，如果所有的dp(nums[i]-coins[x])为-1，则dp(i)也是-1；
     *
     *  2. 输入为0的情况，结果肯定是0，所以直接返回0。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if(amount == 0)
            return 0;
        int[] dp = new int[amount+1];
        for(int i = 0;i<dp.length;i++)
            dp[i]= -1;

        for(int i =1;i<=amount;i++){
            int minimum = 100000;
            boolean skip = false;
            for(int j = 0;j<coins.length;j++){
                if(coins[j]== i){
                    dp[i] =1;
                    skip=true;
                    break;
                }else{
                    if(i>coins[j] && dp[i-coins[j]] !=-1)
                        minimum = Math.min(minimum, dp[i-coins[j]]+1) ;
                }
            }
            if(skip)
                continue;
            if(minimum!=100000) // 真实有效
                dp[i]= minimum;
        }
        return dp[amount];
    }


    /**
     *  精简版本：！！
     *  对于情形1、2，官方题解写的更为精简：
     *  2. dp[0] = 0，直接解决
     *  1. nums[i]==coins[j]时，相当于dp[0]+1=1，符合题意；
     *     如果dp[i]不满足任何转移方程，则将它的值变为minimum的初始值；
     *     最后对dp[amount]比较，如果是minimum初始值，说明不可分解，返回-1。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {

        int[] dp = new int[amount+1];

        for(int i =1;i<=amount;i++){
            int minimum = 100000;

            for(int j = 0;j<coins.length;j++){

                if(i>=coins[j])
                    minimum = Math.min(minimum, dp[i-coins[j]]+1) ;

            }

            dp[i]= minimum;
        }
        return dp[amount]==100000?-1:dp[amount];
    }


    @Override
    public void calculate(Object... objects) {
        int res = coinChange2((int[])objects[0], (int)objects[1]);
        int two = 2;
    }
}
