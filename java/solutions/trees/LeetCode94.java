package solutions.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import core.TreeNode;
import util.BinaryTreeFactory;
import util.SolutionsFacade;

public class LeetCode94 implements SolutionsFacade {

    public List<Integer> inorder = new ArrayList<>();
    /**
     * 没啥好说的，基础知识：
     * 有两种写法：1. 递归； 2. 迭代（循环/栈）
     * 注意特殊情况：空节点等。
     *
     * @param root 二叉树的根节点
     * @return 包含了中序遍历结果的列表
     */
    public List<Integer> inorderTraversal0(TreeNode root) {
        if (root == null)
            return this.inorder;
        if (root.left!=null)
            inorderTraversal0(root.left);
        this.inorder.add(root.val);
        if (root.right!=null)
            inorderTraversal0(root.right);
        return this.inorder;
    }


    /**
     * 思路：用栈的方式实现：
     * 如果当前节点有左子节点，则依次在栈内添加它们，并移动当前指针（！为了避免后期重复出栈，要抹掉已入栈节点的左子节点！）
     * 当没有左子节点后，开始出栈，要求每个弹出的栈检查是否存在右子节点，如有则将它入栈
     * 并移动指针到新入的节点，进行下一轮的入栈、出栈
     * @param root 二叉树的根节点
     * @return 包含了中序遍历结果的列表
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        if (root == null)
            return this.inorder;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode last;
        stack.add(root);
        while (!stack.empty()) {
            while(root.left!=null){
                stack.add(root.left);
                last = root;
                root = root.left;
                // 这里用last指针抹掉已入栈节点的左子节点！！！
                last.left = null;
            }
            root = stack.pop();
            this.inorder.add(root.val);
            if(root.right!=null){
                root= root.right;
                stack.add(root);
            }
        }

        return this.inorder;
    }

    /**
     * 尝试morris版的中序遍历，即寻找前驱节点，并将其右子节点指向当前。
     * @param root 二叉树根节点
     * @return 遍历顺序中各节点的值
     */
    public List<Integer> morrisInorder(TreeNode root) {


        TreeNode pre;
        // 大于一个节点情况
        while(root!=null) {

            //1. 寻找左子树的最右节点
            if (root.left == null) {
                this.inorder.add(root.val);
                root = root.right;
            }else{
                pre = root.left;
                while(pre.right!=null && pre.right!=root){
                    pre = pre.right;
                }
                // 2.找到前驱节点后做判断
                //   右子节点未设置，
                if(pre.right!=root){
                    pre.right = root;
                    root = root.left;
                }
                else{
                //    右子节点已设置，该情况只发生在左子树已遍历完毕，所以记录前驱节点值，并恢复其右子节点为null
                    pre.right = null;
                    // 中节点
                    this.inorder.add(root.val);
                    root = root.right;

                }
            }
        }

        return  this.inorder;
    }

    @Override
    public void calculate(Object... objects) {

        TreeNode root = (TreeNode) objects[0];

        List<Integer> res = morrisInorder(root);

        System.out.println(res);
    }


}
