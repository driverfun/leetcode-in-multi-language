package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;
import java.util.Map;
import java.util.HashMap;

public class LeetCode437 implements SolutionsFacade {

    public int res=0;

    public void pathCalculate(TreeNode  root, int path, int target){
        if (root == null)
            return ;
        path += root.val;
        if( path == target){
            res += 1;
        }
        pathCalculate(root.left, path, target);
        pathCalculate(root.right, path, target);
    }

    public void preorder(TreeNode root, int sum){
        if(root== null)
            return;
        pathCalculate(root, 0, sum);
        preorder(root.left, sum);
        preorder(root.right, sum);
    }


    /**
     * 解题思路：因为所求路径可能是全部长度（从根节点到叶子节点）的一部分，所以最笨的办法就是将树中每个节点都当作根节点，向子节点做累加求路径。
     * 因此，涉及到两个嵌套的前序遍历，外面一层用于遍历树的所有节点，里面一层用于分别计算左、右子树路径和。
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        preorder(root, sum);
        return res;
    }

    /**
     * 前缀和算法，具体请见algorithms目录下的解释。前缀和与节点建立等价关系（存在与否），并通过加减后另一节点的存在确定路径的存在。
     * 注意：根节点是0时的特殊处理！
     * @param root
     * @param sum
     * @return
     */
    public int pathSum1(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 前缀和为0 已出现
        helper(root, map, 0, sum);
        return res;
    }

    public void helper(TreeNode root, Map<Integer, Integer> map, int cur, int sum){
        if (root == null)
            return;
        cur += root.val; // 前缀和
        if( map.getOrDefault(cur-sum, 0)>0 )
            res+=map.getOrDefault(cur-sum, 0);
        map.put(cur, map.getOrDefault(cur, 0)+1);
        helper(root.left, map, cur, sum);
        helper(root.right, map, cur, sum);
        map.put(cur, map.getOrDefault(cur, 0)-1);
    }


    /**
     * 注意：改写法无法应对根节点是0的情形。
     *  public void helper(TreeNode root, Map<Integer, Integer> map, int cur, int sum){
     *      if (root == null)
     *         return;
     *      cur += root.val; // 前缀和
     *      if( map.getOrDefault(cur-sum, 0)>0 )
     *         res += 1;
     *      map.put(cur, 1);
     *      helper(root.left, map, cur, sum);
     *      helper(root.right, map, cur, sum);
     *      map.put(cur, 0);
     *  }
     */


    @Override
    public void calculate(Object ...objects){
        int res =pathSum1((TreeNode) objects[0], (int) objects[1]);
        System.out.println(res);
    }

}
