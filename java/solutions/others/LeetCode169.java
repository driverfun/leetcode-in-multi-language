package solutions.others;

import util.SolutionsFacade;

public class LeetCode169 implements SolutionsFacade {

    /**
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int k = 0, res = nums[0];
        for(int i=0;i<nums.length;i++){
            if(k==0){
                res = nums[i];
                k ++;
                continue;
            }

            if(nums[i]!=res)
                k--;
            else
                k++;
        }
        return res;
    }


    @Override
    public void calculate(Object... objects) {
        majorityElement((int[]) objects[0]);
    }
}
