package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;

public class LeetCode106 implements SolutionsFacade {

    /**
     * 思路：
     * 此题解法与105如出一辙，后序遍历可看作是前序遍历反着来，也就是说，当期后序遍历数组的最后一个节点是当前子树的根节点。
     * 因此，我们每次从postorder中取末尾节点，找到它在inorder中的位置，以该点为基准将中序分成左右子数组（对应左右子树），再以左右子数组的元素数量，将postorder做左右分割（postorder格式为[ 左子树| 右子树 | 根节点]）。
     * 以此递归，直到左右子树不存在。
     */


    /**
     * 本函数可以用哈希表优化（即以数组值为key，下标做value），但懒得写了
     * @param value 寻找的目标值
     * @param inorder 中序数组
     * @return 目标值在中序数组中的下标
     */
    public int findElementInorder(int value, int[] inorder){
        int  j=0;
        for(; j<inorder.length; j++){
            if(inorder[j]==value){
                break;
            }
        }
        return j;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder, int inx, int iny, int postx, int posty){
        // 1. 以postorder末尾元素作基准元素，将inroder分成左右数组
        // 2. 再按照inorder左右数组的个数，将postorder剩余元素做左右分割
        int base = findElementInorder( postorder[posty], inorder );
        TreeNode root = new TreeNode(inorder[base]);
        // 左节点存在的条件
        if (base > inx)
            root.left = buildTree(inorder, postorder, inx, base-1, postx , postx+(base-inx)-1);
        // 右节点存在的条件
        if (base < iny)
            root.right = buildTree(inorder, postorder, base+1, iny, posty-(iny-base), posty-1);
        return  root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length != postorder.length || inorder.length == 0 || postorder.length == 0)
            return null;
        TreeNode root = buildTree(inorder, postorder, 0, inorder.length-1, 0, postorder.length-1);
        return root;
    }

    @Override
    public void calculate(Object... objects) {
        TreeNode tree = buildTree((int[]) objects[0], (int[]) objects[1]);

        System.out.println("for debug");
    }

}
