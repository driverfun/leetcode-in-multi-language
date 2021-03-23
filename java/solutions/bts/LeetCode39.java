package solutions.bts;

import util.SolutionsFacade;

import java.util.*;

public class LeetCode39 implements SolutionsFacade {

    /**
     * 用这种方式算出来的结果是重复的，如：[2,3,6,7]的解为 [2,2,3]、[2,3,2]、[3,2,2]、[7]
     * 解决方案：去重的关键在于如果两个解组成元素的个数完全相同，则认为重复。
     *         因此，我们在记录path时换一种方式，改成记录所用元素的个数。
     * @param cand
     * @param target
     * @param path
     * @param sum
     * @param tmp
     */
//    public void backtrack(int[] cand, int target, List<Integer> path, int sum,  List<List<Integer>> res){
//        if(sum>target)
//            return ;
//        if(sum == target)
//            res.add(new ArrayList<>(path));
//        for(int i = 0;i<cand.length;i++){
//            sum +=cand[i];
//            path.add(cand[i]);
//            backtrack(cand, target, path, sum, res);
//            path.remove(path.size()-1);
//            sum -= cand[i];
//        }
//    }


    public Map<Integer, Integer> map;       // map 用来完成cand数组值-下标的映射
    public int pathID;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> tmp = new HashSet<>();
        map = new HashMap<>();
        List<Integer> current = new ArrayList<>();
        for(int i=0;i<30;i++)           // candidates最多30个元素，current这里记录它们的下标对应元素的使用次数
            current.add(0);
        for(int i=0;i<candidates.length;i++){
            map.put(candidates[i], i);
        }
        backtrack(candidates, target, current, 0, tmp);

        // 最后一步，完成向真正结果集的转化
        for(List<Integer> arr: tmp){
            List<Integer> mafan = new ArrayList<>();
            for(int i=0;i<arr.size();i++){
                int val = arr.get(i);
                while(val>0){
                    mafan.add(candidates[i]);
                    val --;
                }
            }
            res.add(new ArrayList<>(mafan));
        }
        return res;
    }

    public void backtrack(int[] cand, int target, List<Integer> path, int sum,  Set<List<Integer>> tmp){
        if(sum>target)
            return ;
        if(sum == target)
            tmp.add(new ArrayList<>(path));
        for(int i = 0;i<cand.length;i++){
            sum +=cand[i];
            path.set(map.get(cand[i]) ,path.get(map.get(cand[i]))+1);
            backtrack(cand, target, path, sum, tmp);
            path.set(map.get(cand[i]) ,path.get(map.get(cand[i]))-1);
            sum -= cand[i];
        }
    }


    /**
     * 思路二：
     * 策略：原地踏步或前进，对应决策树是一颗n叉树
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack1(candidates, target, 0,  new ArrayList<>(), res);
        return res;
    }

    public void backtrack1(int[] nums, int target, int idx, List<Integer> path, List<List<Integer>> res){
        if(target<0){
            pathID++;
            System.out.println("PathID"+pathID+": "+path);
            return;
        }
        if(target==0){
            pathID++;
            System.out.println("PathID"+pathID+": "+path);
            res.add(new ArrayList<>(path));
            return;                 // 引入return也是一种剪枝，虽然后面的代码马上会有idx长度的判断，但少了好多其他节点的测试
        }

        for(int i = idx;i<nums.length;i++){
            if(target-nums[i]>=0){
                path.add(nums[i]);
                backtrack1(nums, target-nums[i], i, path, res);
                path.remove(path.size()-1);
            }
        }
    }


    /**
     * 思路三：
     * 每到一个元素有两种选择：选 or 不选，所以这种回溯的决策树是棵二叉树！
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack2(candidates, target, 0,  new ArrayList<>(), res);
        return res;
    }

    public void backtrack2(int[] nums, int target, int idx, List<Integer> path, List<List<Integer>> res){
        if( idx> nums.length-1){
            pathID++;
            System.out.println("PathID"+pathID+": "+path);
            return;
        }
        if(target==0){
            pathID++;
            System.out.println("PathID"+pathID+": "+path);
            res.add(new ArrayList<>(path));
            return;             // 如果没有return，比如在[2,2,3]路径的基础上要再递归一遍剩余节点才会返回，这就是为啥答案变成了4次。
        }
        // 情况一，选当前节点
        if(target-nums[idx]>=0){
            path.add(nums[idx]);
            backtrack2(nums, target-nums[idx], idx, path, res);
            path.remove(path.size()-1);
        }

        // 情况二，不选当前节点
        backtrack2(nums, target, idx+1, path, res);
    }


    @Override
    public void calculate(Object... objects) {
        List<List<Integer>> res = combinationSum2((int[])objects[0],(int)objects[1]);

        int s = 2;
    }
}
