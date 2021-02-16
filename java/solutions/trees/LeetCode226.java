package solutions.trees;

import core.TreeNode;

public class LeetCode226{

    /**
     * 后序遍历：交换左、右子节点，逐次往上
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root != null){
            TreeNode left = invertTree(root.left);
            TreeNode right = invertTree(root.right);
            root.left = right;
            root.right = left;
        }
        return root;
    }

}
