package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;

public class LeetCode543 implements SolutionsFacade {

    /**
     * 思路：没想到吧，这道题目104的DFS解法极为相似
     * 我们已知左、右子树存在时，肯定经过当前节点，将二者联通后路径最长，但题目中提示：最长直径可能不经过根节点（意为其子节点中的某个从左到右的路径更长）
     * 因此，本题可对每个节点分别计算左右子树高度并累加比较的过程，故使用后序遍历。
     * 如果当前节点的左右子树高度和大于已知path，则更新；注意递归返回时要将高度加一，因为当前节点会使高度增加。
     */
    public int path = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        postOrder(root);
        return path;
    }


    public int postOrder(TreeNode root){
        if (root == null)
            return 0;
        int left = postOrder(root.left);
        int right = postOrder(root.right);
        if ( left+right > path)
            path = left + right;
        return Math.max(left+1, right+1);
    }

    @Override
    public void calculate(Object... objects) {
        diameterOfBinaryTree((TreeNode)objects[0]);
    }
}
