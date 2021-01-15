package solutions.trees;

import core.TreeNode;
import util.BinaryTreeFactory;
import util.SolutionsFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode145 implements SolutionsFacade {

    public List<Integer>  postorder = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        postOrder(root);
        return postorder;
    }

    public void postOrder(TreeNode node){
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        postorder.add(node.val);
    }

    /**
     * 栈版本。跟其他遍历一样，栈中保存上当前节点的上一节点（或父节点），最重要的一点：出栈时机——当前节点的相邻右子节点为空时
     * 具体逻辑：先迭代地添加左子节点，在左子节点为空后，再看相邻右子节点，如果有值说明树还在继续，则将当前节点变为父节点的右子节点`stack.peek().right`
     *         如果其右子节点为空，说明已经位于叶子节点，此时可以出栈。
     *         但需判断出栈者是栈顶节点的左、右子节点，若是右子节点，意味着当前处于右子树，需不断的出栈（+登记值），直到非右子节点或栈列为空。
     *         以此循环，也不知道我说明白了没，反正我感觉应该有更清楚的思路、写法。
     * @param node 二叉树的根节点
     * @return 二叉树的后序遍历列表
     */
    public List<Integer> postorderTraversal1(TreeNode node) {

        if(node != null){
            Stack<TreeNode> stack = new Stack<>();
            TreeNode goAway;
            while (!stack.empty() || node!=null) {
                while (node != null) {
                    stack.add(node);
                    node = node.left;
                }
                // root变为相邻右子节点
                node = stack.peek().right;
                // 右子节点为空则出栈，并加以判断
                if (node == null) {
                    goAway = stack.pop();
                    postorder.add(goAway.val);
                    while(!stack.empty() && stack.peek().right==goAway){
                        //持续出栈直到非右节点或栈为空
                        goAway = stack.pop();
                        postorder.add(goAway.val);
                    }
                    if(stack.empty())
                        break;
                    node = stack.peek().right;
                }
            }
        }

        return postorder;
    }

    @Override
    public void calculate(Object... objects) {
        List<Integer> preorder = postorderTraversal1((TreeNode) objects[0]);
        System.out.println(preorder);
    }


}
