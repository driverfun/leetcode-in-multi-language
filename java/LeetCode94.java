import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import core.TreeNode;
import util.BinaryTreeFactory;

public class LeetCode94 {

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

    public static void main(String[] args){

        LeetCode94 solution =  new LeetCode94();
        String[] inputs0 = {"1", "null" ,"2", "3"};
        String[] inputs1 = {"2", "3" ,"null", "1"};
        TreeNode root = BinaryTreeFactory.getBinaryTreeFromStringArray(inputs1);
        List<Integer> inroder = solution.inorderTraversal1(root);
        System.out.println(inroder);

    }

}
