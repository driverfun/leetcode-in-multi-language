import com.sun.source.tree.Tree;
import core.TreeNode;

public class LeetCode105 {

    public int preIter = 0;
    /**
     * 官方例题：
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 规律早已明晰：每个前序的元素都将中序列表进行分割，元素左边为左子树、右边右子树，依次递归。
     * 我们可以利用数组的下标进行分割，值得注意的是一些临界点的考虑，当左/右子树只有一个节点时意味着来到了叶子节点，可以return了。
     *
     * @param preorder 前序列表
     * @param inorder 中序列表
     * @return 二叉树的根节点
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // 特殊情形处理，如：[], []
        if(preorder.length!=inorder.length || preorder.length==0)
            return null;
        TreeNode root = constructTree(0, inorder.length -1, preorder, inorder);
        return root;
    }

    public int findElementInorder(int value, int[] inorder){
        int  j=0;
        for(; j<inorder.length; j++){
            if(inorder[j]==value){
                break;
            }
        }
        return j;
    }

    public TreeNode constructTree(int firstPos, int lastPos, int[] preorder, int[] inorder){

        if(firstPos == lastPos)
            return new TreeNode(inorder[firstPos]);
        // 在中序数组中找到前序数组的下标
        int inIter = findElementInorder(preorder[preIter], inorder);
        // 将中序数组按下标分割
        TreeNode root = new TreeNode(inorder[inIter]);
        if(inIter>firstPos){
            this.preIter += 1;
            root.left = constructTree(firstPos, inIter-1, preorder, inorder);
        }
        if(inIter<lastPos){
            this.preIter += 1;
            root.right = constructTree(inIter+1, lastPos, preorder, inorder);
        }
        return root;
    }


    public static void main(String[] args){
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        LeetCode105 solution = new LeetCode105();
//        int pos = solution.findElementInorder(preorder[2], inorder);
        TreeNode tree = solution.buildTree(preorder, inorder);
        System.out.println("for debug");
    }

}
