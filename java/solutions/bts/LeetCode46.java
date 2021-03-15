package solutions.bts;

import util.SolutionsFacade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode46 implements SolutionsFacade {

    /**
     * 思路：
     * 因为直接操作nums，会导致回溯过程中元素的乱序，造成错误结果，这里使用一张布尔表代表是否选取。
     *
     * 注意：
     * 经测试，发现优化点在于结果深拷贝过程，手写的比初始化赋值转换的慢了好多，也浪费了更多空间！
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] choose = new boolean[nums.length];
        Arrays.fill(choose, true);
        backtrack(choose, nums, res, new ArrayList<>());
        return res;
    }


    public void backtrack( boolean[] original, int[] nums, List<List<Integer>> res, List<Integer> tmp){
        if(tmp.size()==nums.length){
//            List<Integer> mafan = new ArrayList<>() {{      // 手动copy
//                    for(Integer s : tmp)
//                        this.add(s);
//            }};
//            res.add(mafan);
            res.add(new ArrayList<>(tmp));
            return ;
        }
        for(int x = 0 ;x< original.length ;x++){
            if(original[x] == true){
                // 取值，并删除
                Integer s = nums[x];
                tmp.add(s);
                original[x] = false;
                backtrack(original, nums, res, tmp);
                tmp.remove(s);
                original[x] = true;
            }
        }
    }


    @Override
    public void calculate(Object... objects) {
        List res = permute((int[])objects[0]);
        int s = 0;
    }

}
