package solutions.dps;


import util.SolutionsFacade;

/**
 * 题目描述：一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 *
 * 要点：只能向右/向下移动，则说明终点位于起点同一行、同一列时仅有1条路径（对于路径总数无增加）
 *      只有在不同行列时才有额外的路径产生。
 */
public class LeetCode62 implements SolutionsFacade {

    /**
     * 本题的dp数组代表i*j矩阵的路径数，到每个终点的数目是起点右移一步和下移一步的路径和
     * 每次起点变化后，相当于原矩阵缩小成已知矩阵了；且对称矩阵具有对称性
     *
     * 动规题目最重要的就是找规律么：我们从小矩阵的情况向大矩阵去看，比如`3*2`的矩阵有三条路，这一眼就可看出；
     * 到了`3*3`的矩阵，我们将起点右移一步，此时当前节点到终点变成一个`3*2`的矩阵，因为新起点和旧起点在同一行，所以路径总数不会增加，就是这个`3*2`的矩阵的路径总数；
     * 同理，原起点下移一步，变成一个`2*3`的矩阵，因为新起点和旧起点在同一列，所以路径总数不会增加，因此，总路径是两个矩阵之和，3+3=6。
     * 以此类推，我们可以得出`dp[i][j]= dp[i-1][j]+dp[i][j-1]`，注意限定下i、j减1后不能为负。
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][]  dp =  new int[m][n];
        dp[0][0] = 1;
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++){
                if(i>=1)
                    dp[i][j] += dp[i-1][j];
                if(j>=1)
                    dp[i][j] += dp[i][j-1];

            }

        return dp[m-1][n-1];
    }

    @Override
    public void calculate(Object... objects) {
        uniquePaths((int)objects[0], (int)objects[1]);
    }
}
