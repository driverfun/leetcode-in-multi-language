package solutions;

import core.TreeNode;
import util.BinaryTreeFactory;
import util.SolutionsFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode144 implements SolutionsFacade {

    public List<Integer>  preorder;

    public void preOrder(TreeNode root){
        if(root == null)
            return;
        preorder.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        preorder = new ArrayList<>();
        preOrder(root);
        return preorder;
    }

    /**
     * 出/入栈版，真正的精髓在于栈中保存的是当前节点的上一节点，以及栈内元素出栈的时机。
     * 具体逻辑：每个节点先一个劲的向左走，并将每个节点的父节点记录入栈；
     *         当到达叶子节点的左子节点后（必为空），从栈中取出父节点（栈顶节点），并将自己赋值为父节点的右子节点；
     *         因为此时父节点已经如果栈了，所以无须记录。依次重复，结束条件为：栈中元素全部出栈，并且当前节点来到最后叶子节点的右节点（为空）。
     * @param node 二叉树的根节点
     * @return 前序遍历的结果列表
     */
    public List<Integer> preorderTraversal1(TreeNode node){
        preorder = new ArrayList<>();
        Stack<TreeNode> lastNode = new Stack<>();

        if (node != null){
            // 添加第一个元素
            lastNode.add(node);
            preorder.add(node.val);
            node = node.left;
            // 只有在栈为空并且当前节点亦为空（最后一个节点的右子节点为空时退出）
            while(!lastNode.empty() || node != null) {
                while (node != null) {
                    lastNode.add(node);
                    preorder.add(node.val);
                    node = node.left;
                }
                node = lastNode.pop().right;
            }
        }
        return preorder;
    }

    @Override
    public void calculate(Object... objects) {
        List<Integer> preorder = preorderTraversal1((TreeNode) objects[0]);
        System.out.println(preorder);
    }

}
