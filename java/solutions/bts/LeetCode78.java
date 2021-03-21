package solutions.bts;

import util.SolutionsFacade;

import java.util.ArrayList;
import java.util.List;

public class LeetCode78 implements SolutionsFacade {

    /**
     * 思路：（只前进不后退策略）
     * 1. 空集是所有集合的子集，故可以初始化时加入；
     * 2. 每次递归时，从当前位置开始做一轮结果记录，将当前节点加入路径，记录至结果集，再进入下一轮递归（同时步长加一），
     * 返回后再将当前节点移除路径；然后，改变当前位置，重新加入路径。
     * 以[1,2,3]为例：找寻结果为：
     * [1] -> [1,2] -> [1,2,3]
     * [2] -> [2,3]
     * [3]
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>(){{this.add(new ArrayList<>());}};
        backtrack(nums, 0, new ArrayList<>(), res);
        return  res;
    }

    public void backtrack(int[] nums, int idx, List<Integer> path,  List<List<Integer>> res){
        if(idx == nums.length)return;
        for(int i=idx;i<nums.length;i++){
            path.add(nums[i]);
            res.add(new ArrayList<>(path));
            backtrack(nums, i+1, path, res);
            path.remove(path.size()-1);
        }
    }


    /**
     * 官方解法二：
     * 迭代法：妙呀！！！
     * 数组长度个数已知后可以将数组看成一个布尔数组，
     * true代表元素入选，false为不如选。
     * 所以：以[1,2,3]为例，长度为3，故有2^3=8中结果：
     * 000, 001, 010, 011, 100, 101, 110, 111
     * 我们按照每个数字里1的位置对应添加数组中元素。
     *
     * @param objects 可变参数对象
     */
    @Override
    public void calculate(Object... objects) {
        List<List<Integer>> res = subsets((int[]) objects[0]);
        int s = 3;
    }




    /**
     * 又：
     * python目录下的一个字符串的所有字母的组合可以看作是本问题的特例（排除了空集的情况），所以将其合并入本体，不再保留独立代码！
     */
}
