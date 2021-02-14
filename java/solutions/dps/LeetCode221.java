package solutions.dps;

import util.SolutionsFacade;

public class LeetCode221 implements SolutionsFacade {

    public int maximalSquare(char[][] matrix) {
        // 1. 复制原数组第一行、第一列
        int hang = matrix.length;
        int lie = matrix[0].length;

        int[][] dp = new int[hang][lie];

        for(int j=0;j<lie;j++)
            dp[0][j] = matrix[0][j]=='1'?1:0;

        for(int i=0;i<hang;i++)
            dp[i][0] = matrix[i][0]=='1'?1:0;

        // 2. 开始算值，dp[i][j] = min(dp[i-1][j], dp[i-1][j-1], dp[i][j-1])+1;

        for(int i=1;i<hang;i++)
            for(int j = 1;j<lie;j++)
                if(matrix[i][j]=='1')
                    dp[i][j] = min(dp[i - 1][j], dp[i - 1][j - 1], dp[i][j - 1]) + 1;

        int maxium = dp[0][0];
        // 3. 获取最大值
        for(int i =0;i<hang;i++)
            for(int j = 0;j<lie;j++)
                if(dp[i][j]>maxium)
                    maxium = dp[i][j];
        return maxium*maxium;
    }

    public int min(int a, int b , int c){
        return Math.min(Math.min(a,b),c);
    }


    @Override
    public void calculate(Object... objects) {
        maximalSquare((char[][])objects[0]);
    }
}
