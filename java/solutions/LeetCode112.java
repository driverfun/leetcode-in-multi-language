package solutions;

import com.sun.source.tree.Tree;
import core.TreeNode;
import util.BinaryTreeFactory;
import util.SolutionsFacade;

public class LeetCode112 implements SolutionsFacade {

    public boolean state = false;
    /**
     * 简单题，思路：就是个DFS搜索，递归就完事了
     * 官方题解还提供了BFS，这种解法不易出错，但要开辟一个额外的队列维护到达当前节点的路径和
     * @param root 输入的二叉树
     * @param sum 所求路径和的目标
     * @return 当某条路径和为目标值时为真，否则为假
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null)
            return this.state;
        searchPath(root, sum, 0);
        return this.state;
    }

    public void searchPath(TreeNode node, int target, int current){
        // 先做值的累加，因为代码在递归前已经做了非空判断，所以不担心报NullPointer异常
        current += node.val;
        // 到达叶子节点后，判断路径是否符合要求
        if(node.left== null && node.right==null ){
            if (current == target)
                this.state = true;
            return ;
        }
        // 利用状态值可以做一些剪枝
        if(node.left!=null && !this.state)
            searchPath(node.left, target, current);
        if(node.right!=null && !this.state)
            searchPath(node.right, target, current);
    }


    @Override
    public void calculate(Object... objects) {
        hasPathSum((TreeNode) objects[0], (int) objects[1]);
        System.out.println("for debug");
    }

}
