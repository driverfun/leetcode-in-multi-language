package solutions.trees;

import com.sun.source.tree.Tree;
import core.TreeNode;
import util.SolutionsFacade;

public class LeetCode98 implements SolutionsFacade {


    public boolean res = true;
    // 为了避免出栈时last被重新赋值，这里将其定义为全局变量
    public TreeNode last;

    /**
     * 中序遍历
     * 注意：上一节点的变化时刻，规律就是：当前节点永远大于上一节点
     * @param node 当前节点
     */
    public void inorder(TreeNode node){

        if (node.left!=null){
            inorder(node.left);
        }

        if (last!=null && last.val>= node.val)
            res = false;
        last = node;
        if (node.right!=null){
            inorder(node.right);
        }

    }

    public boolean isValidBST(TreeNode root) {

        // 方法一：打印中序：查看是否递增序列（此处省略）
        //
        // 方法二：递归：根据特性，当前节点必须小于右子树的最小节点；大于左子树的最大节点
        //              而这正好是中序的特性，即遍历时当前结点的上一点必是左子树最大节点，下一点必是右子树最小节点
        //              而在递归过程中的表现为：当前节点永远大于上一节点。
        inorder(root);
        return res;
    }


    @Override
    public void calculate(Object... objects) {
        boolean res = isValidBST((TreeNode)objects[0]);
        System.out.println("for test : "+ res);
    }
}
