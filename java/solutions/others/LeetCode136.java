package solutions.others;

public class LeetCode136 {
    /**
     * 没啥好说的，剑指offer经典位运算题目。
     * 规律： a^a = 0
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for(int i = 1;i<nums.length;i++)
            res ^= nums[i];

        return res;
    }
}
