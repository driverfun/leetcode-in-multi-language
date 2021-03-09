package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;

public class LeetCode538 implements SolutionsFacade {


    /**
     * 思路：
     * 二叉搜索树的中序恰好是个升序序列，我们观察累加树就可发现，从右向左走的话，就是将路径上的节点值累加，
     * 再让下一个出现的节点加上这个累计值。因此，它其实是反过来的中序遍历，即右-中-左，直到最左节点。
     *
     * 发现了这条规律，就可以很快写出代码了。
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if(root==null)
            return null;
        int[] accum = new int[]{0};
        newInOrder(root, accum);
        return root;
    }


    public void newInOrder(TreeNode root, int[] nums){

        // right child
        if(root.right!=null)
           newInOrder(root.right, nums);
        root.val += nums[0];
        // yourself
        nums[0] = root.val;
        // left child
        if(root.left!=null)
            newInOrder(root.left, nums);
    }


    @Override
    public void calculate(Object... objects) {
        TreeNode root = convertBST((TreeNode)objects[0]);
        System.out.println("debug");
    }
}
