package solutions.dps;

import util.SolutionsFacade;

public class LeetCode198 implements SolutionsFacade {


    /**
     * 思路：我们用一个2*n数组记录最优解，第一行（0）代表不偷当前节点的解，第二行（1）代表偷当前节点的解
     *      对于当前节点，如果不选它，则它的最优解是偷/不偷上一节点的最大值；如果选它，必不能选上一节点。
     * 状态方程： f(i) = max{f(1,i-1), f(0,i-1)+nums[i]}
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if(nums.length==0)
            return 0;
        //  2*n数组，第一行（0）代表不偷当前节点的最优解，第二行（1）代表偷当前节点的解
        int[][] money = new int[2][nums.length];
        money[1][0] = nums[0];
        for(int j =1 ;j<nums.length;j++)
            // 按列填
            for(int i = 0;i<2;i++){
                if(i==0)    // 不选当前节点，上一节点可选、可不选
                    money[i][j] = Math.max(money[1][j-1], money[0][j-1]);
                else        // 选当前节点，上一节点必不能选
                    money[i][j] = money[0][j-1]+nums[j];
            }
        return Math.max(money[0][nums.length-1], money[1][nums.length-1]);

    }


    @Override
    public void calculate(Object... objects) {
        rob((int[])objects[0]);
    }
}
