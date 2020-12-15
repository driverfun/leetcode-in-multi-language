package solutions;

import core.TreeNode;
import util.SolutionsFacade;

import java.util.*;

public class LeetCode113 implements SolutionsFacade {

    List<List<Integer>> result;

    /**
     * 思路：与上一题类似，观察可知一棵二叉树有多少叶子节点就有几条从根节点到叶子结点的路径。
     * 本题需要遍历所有路径，但无需为每条路径维护一个列表（记录走过节点），
     * 只需利用一个栈，与递归顺序相匹配，如：再遍历完左子节点后pop掉当前节点，重新入栈，从而改变路径方向。
     * @param root 输入的二叉树根节点
     * @param sum 所求路径和的目标
     * @return 满足条件的所有路径
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        this.result = new ArrayList<>();
        if(root==null) {
            return this.result;
        }
        Stack<Integer> path = new Stack<>();
        searchPath(root, sum, 0, path);
        return this.result;
    }


    public void searchPath(TreeNode node, int target, int current, Stack<Integer> path){
        // 先做值的累加，因为代码在递归前已经做了非空判断，所以不担心报NullPointer异常
        current += node.val;
        path.add(node.val);
        // 到达叶子节点后，判断路径是否符合要求
        if(node.left== null && node.right==null ){
            if (current == target) {
                List<Integer> tmp = new ArrayList<>(path);
                this.result.add(tmp);
            }
            return ;
        }
        // 利用状态值可以做一些剪枝
        if(node.left!=null) {
            searchPath(node.left, target, current, path);
            path.pop();
        }
        if(node.right!=null ) {
            searchPath(node.right, target, current, path);
            path.pop();
        }
    }

    @Override
    public void calculate(Object... objects) {
        List<List<Integer>>  paths = pathSum((TreeNode) objects[0], (int) objects[1]);
        System.out.println("for debug: "+ paths);
    }
}
