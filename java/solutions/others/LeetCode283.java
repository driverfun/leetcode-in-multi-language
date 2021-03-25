package solutions.others;

public class LeetCode283 {


    /**
     * 思路：
     * 从尾向前移动，碰到0时，将其后的所有元素挨个前移。
     *
     * WDNMD，这个中规中矩的速度只打败了10%
     * @param nums
     */
    public void moveZeroes0(int[] nums) {
        for(int j = nums.length-1;j>=0;j--){
            if(nums[j]==0){
                // 移动其后所有元素
                int  x= j+1;
                for(;x<nums.length && nums[x]!=0;x++){
                    nums[x-1] = nums[x];
                }
                nums[x-1]= 0;
            }
        }
    }


    /**
     * 思路：（该解法类似快排的移位法）
     * 粗暴一点，从前向后走，维护一个指针，碰到一个非零元素就移动到指针位，同时指针加一
     * 当原数组遍历完后，将指针后的所有元素填充0。
     *
     * 这一版速度击败100%，淦！
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int p = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[p] = nums[i];
                p++;
            }
        }
        for(;p<nums.length;p++){
            nums[p] = 0;
        }
    }

}
