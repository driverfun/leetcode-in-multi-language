package solutions.dps;

public class LeetCode64 {


    /**
     * 因为每个到达每个元素的来源只有两个，所以dp[i][j]= min(dp[i-1][j], dp[i][j-1])+ val
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int hang = grid.length;
        int lie = grid[0].length;
        int[][]  dp = new int[hang][lie];

        // 先赋值原点
        dp[0][0] = grid[0][0];
        // 初始化第1行、第1列
        for(int i =1 ;i<hang;i++)
            dp[i][0]= grid[i][0]+dp[i-1][0];
        for(int j = 1;j<lie;j++)
            dp[0][j] =grid[0][j]+dp[0][j-1];


        for(int i=1;i<hang;i++)
            for(int j=1;j<lie;j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }

        return dp[hang-1][lie-1];
    }

}
