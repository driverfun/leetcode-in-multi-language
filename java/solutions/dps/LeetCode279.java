package solutions.dps;

import util.SolutionsFacade;

public class LeetCode279 implements SolutionsFacade {

    /**
     * 状态方程： dp[n] = min(dp[n-1]+1, dp[n-4]+1, dp[n-9]+1 ...)
     * 这是另一个换零钱问题，只不过随着n的增大，可用于换得纸币种类在增多，我们称这些币种为基数。
     * 可以看到，在dp数组迭代的过程中：
     * 1.每迈过整数的平方就会多一种基数，此时要多加一个基数分解目标数；
     * 2.每到整数平方的值为1，因为新基数1个就可以换它。
     * @param n
     * @return
     */
    public int numSquares(int n) {

        int[] dp = new int[n+1];
        int base = 1;
        for(int i = 1;i<=n;i++){
            if(i== base*base){// 新基数诞生！
                dp[i] = 1;
                base+=1;
            }else{
                // 用每个基数算一次
                int min = 9999999;
                for(int j = 1;j<=base && i>=j*j; j++){
                    min = Math.min(min, dp[i-j*j]+1);
                }
                dp[i] = min;
            }
        }
        return dp[n];

    }


    @Override
    public void calculate(Object... objects) {
        numSquares((int)objects[0]);
    }
}
