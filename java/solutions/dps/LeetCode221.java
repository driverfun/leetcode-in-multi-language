package solutions.dps;

import util.SolutionsFacade;

/**
 * 思路（分析过程）：
 * 首先，当一块区域是最大正方形时，其内所有元素都是1。
 * 因此我们结合动规思想，斜对角线元素每次加一，所在行和列都是1，因此，于是就可以分析动规矩阵元素间的规律。
 * 通过观察，我们设计dp每个元素代表最大正方形的边长，例如：当它为2时，它的左临元素值1，上临元素值1；
 * 当它为3时，它的左邻元素值为2，上临元素也为2；当上临元素为1时，它无法达到边长为3的正方形，因此为2（上临、左邻、左上）
 * 于是便有了状态转移方程：dp[i][j] = min(dp[i-1][j], dp[i-1][j-1], dp[i][j-1])+1
 */
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
