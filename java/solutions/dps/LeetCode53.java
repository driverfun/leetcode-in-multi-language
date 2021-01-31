package solutions.dps;

import util.SolutionsFacade;

import java.util.HashMap;
import java.util.Map;

public class LeetCode53 implements SolutionsFacade {

    /**
     * 写一个O(n^2)的动规解法：
     * 用一个数组记录每加入一个元素后，所有子序列和的变化，我们以[8, -19, 5]为例：
     * 加入8时：    8                                 8
     * 加入-19时：  8                       加入5时：  8 -19
     *            8 -19                             8 -19 5
     *              -19                               -19 5
     *                                                    5
     * 因为在加入数据时，最长字串增长前的所有可能已经通过max记录，所以辅助数组只记录增长后的变化结果，
     * 则它必会在对应下标出添加新元素，并对0-i位累加该数字，其中0必为最长字串，1为最长字串截断第1位的串，依次类推。
     * 时间复杂度：1+2+3+...+n = n*(n+1)/2 , O(n^2)
     * 空间复杂度： O(n)
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        int[] accum = new int[nums.length];
        int max = nums[0];
        for (int i= 0; i< nums.length;i++)
            for(int j = 0;j<=i;j++) {
                accum[j] += nums[i];
                if(accum[j]>max)
                    max = accum[j];
            }

        return max;
    }


    /**
     * 使用了O(n^2)的辅助空间，大数据情况下内存超限！
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums){
        int len = nums.length;
        int[][] matrix = new int [len][len];
        int max = 0;
        for( int i=0;i<len;i++) {
            matrix[i][i] = nums[i];
            if(max < nums[i])
                max = nums[i];
        }

        for( int i= 0;i< len-1 ;i++)
            for( int j = i+1;j<len; j++){
                matrix[i][j] = nums[j]+ matrix[i][j-1];
                if(max < matrix[i][j])
                    max = matrix[i][j];
            }
        return max;
    }



    @Override
    public void calculate(Object... objects) {
        maxSubArray((int[])objects[0]);
    }
}
