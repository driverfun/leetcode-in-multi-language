package solutions.bts;

import util.SolutionsFacade;

import java.util.*;

public class LeetCode39 implements SolutionsFacade {

    public Map<Integer, Integer> map;       // map 用来完成cand数组值-下标的映射

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


    @Override
    public void calculate(Object... objects) {
        List<List<Integer>> res = combinationSum((int[])objects[0],(int)objects[1]);
        int s = 2;
    }
}
