package solutions.others;

import util.SolutionsFacade;

import java.util.Map;
import java.util.HashMap;

public class LeetCode1  implements SolutionsFacade {

    /**
     * 2O(n)的方式：将数组所有元素以`<值——下标>`的形式存入哈希表
     * 再次遍历数组，查查target减去当前数组值后的值在不在哈希表，若在则返回当前下标及哈希表中对应下标（二者必须不同）。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum0(int[] nums, int target){

        // 复杂度: 2*O(n)
        Map<Integer, Integer> map = new HashMap<>();

        for (int j = 0;j < nums.length;j++){
            map.put(nums[j], j);
        }
        int i = 0;
        int j = 0;
        for(;i<nums.length; i++){
            if(map.containsKey(target-nums[i]) && map.get(target-nums[i])!=i){
                j = map.get(target-nums[i]);
                break;
            }
        }
        return new int[]{i,j};
    }


    /**
     * 其实本题可以一次遍历解决，即在遍历时查看当前数组元素的对应元素（target-nums[i])的值在不在哈希表，
     * 不在将当前元素加入哈希表，这样在遍历到对应元素时就可以查询到此刻加入的那个值，
     * 此种做法也避免了元素相同{3,3}-6或下标相同{3,2,4}-6的特殊情形。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target){

        // 复杂度: O(n)
        Map<Integer, Integer> map = new HashMap<>();

        for (int j = 0;j < nums.length;j++){
            if(map.containsKey(target-nums[j])){
                return new int[]{j, map.get(target-nums[j])};
            }
            else
                map.put(nums[j], j);
        }
        return new int[]{-1,-1};
    }

    @Override
    public void calculate(Object... objects) {
        twoSum((int[]) objects[0], (int) objects[1]);
    }

}
