package solutions.dps;

import util.SolutionsFacade;

public class LeetCode494  implements SolutionsFacade {


    /**
     * 思路：（未优化版本）
     * 打算用笨办法：dp二维数组，横向为第i号数组元素，纵向为值域（带偏移映射）
     * 因为下标不能是负数，所以我们做一个映射：
     *       target = original + offset
     *  纵向长度为：负数+0+正数
     * 状态转移方程：
     *  dp[j][i] = dp(target+nums[i-1])(i-1) + dp(target-nums[i-1])(i-1)
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        // 先计算最大、最小值
        int max = 0, min = 0;
        for(int i =0;i< nums.length;i++){
            max+=nums[i];
            min-=nums[i];
        }
        if(S>max || S< min)
            return 0;

        int[][] dp= new int[min*(-1)+1+max][nums.length];
        int offset = -min;
        // 上届与下届初值
        int upper = offset+nums[0], lower = offset-nums[0];
        dp[upper][0]+= 1;
        dp[lower][0]+= 1;
        for(int j = 1;j<nums.length;j++){
            // 开始迭代
            upper += nums[j];
            lower -= nums[j];
            for(int x = lower; x<=upper ;x++){
                dp[x][j]= 0;
                if(x-nums[j]>=0)
                    dp[x][j]+= dp[x-nums[j]][j-1];
                if( (x+nums[j])<dp.length)
                    dp[x][j] += dp[x+nums[j]][j-1];
            }

        }
        return dp[S+offset][nums.length-1];
    }


    /**
     * 思路：优化版本（自想版本）
     * 集成上一版本思路：发现是个对称矩阵，关于中间值0对称，所以可只算一半。
     * 在计算过程中还发现：下一列的结果只与上一列的非0元素有关，则可进一步简化！
     *
     * 效果： 16ms -> 7ms
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays1(int[] nums, int S) {
        int max= 0;
        for(int i =0;i< nums.length;i++)
            max+=nums[i];

        if(S<0 )
            S *=-1;
        if(S>max)
            return 0;
        // 省一半数组
        int[][] dp= new int[1+max][nums.length];
        int upper = nums[0];
        dp[upper][0] += 1;
        if(upper==0)
            dp[upper][0] *= 2;

        for(int j=1;j<nums.length;j++) {

            for (int x = 0; x <= upper; x++) {
                // 特别关注：
                // x=0时 0+nums[j]与-0+nums[j]相同
                // x=0时 0-nums[j]与-0-nums[j]相同
                // 为避免重复计算，以上二者只保留一个就行
                if (dp[x][j-1]>0){
                    dp[x+nums[j]][j] += dp[x][j-1];
                    if(x-nums[j]>=0)
                        dp[x-nums[j]][j] +=dp[x][j-1];
                    if(-x+nums[j]>=0 && x!=0)
                        dp[-x+nums[j]][j] +=dp[x][j-1];
                    if(-x-nums[j]>=0 && x!=0)
                        dp[-x-nums[j]][j] +=dp[x][j-1];
                }
            }
            upper += nums[j];
        }
        return dp[S][nums.length-1];
    }


    /**
     * 官方优化：
     * dp[i][...]只和 dp[i - 1][...]有关，因此只需要使用两个一维数组即可。
     * 相当于对原数组每个元素，计算完可能和的数目后用新的dp数组替换旧的数组，直到结束。
     * @param objects 可变参数对象
     */
    @Override
    public void calculate(Object... objects) {
        int res = findTargetSumWays1((int[])objects[0], (int)objects[1]);
        System.out.println("fthink you again.");
    }
}
