import core.TreeNode;
import util.BinaryTreeFactory;

import java.util.ArrayList;
import java.util.List;

public class LeetCode99 {

    /**
     * 思路：因为BST的中序是个从左到右递增的数组，而题目中表明只有2个值位置错了
     * 所以它们在数组中表现为数字逆序了，如：[1, 3, 4, 5, 6, 7, 11]变成了 [1, 7, 4, 5, 6, 3, 7]，则交换节点3和7就行
     * 找法为：从数组左找第一个 右侧比自己小的数， 从数组右找第一个左侧比自己大的数
     * 记住这两个值，并在树遍历时修改二者节点的值即可
     * @param root 调整后二叉树的根节点
     */
    public void recoverTree(TreeNode root) {

        // 1. get inorder matrix
        List<Integer> order = new ArrayList<>();
        inorder(root, order);
        // 2. search two inappropriate numbers
        int i = 0;
        int j = order.size()-1;
        while (i < order.size()-1){
            if(order.get(i) > order.get(i+1))
                break;
            i += 1;
        }
        int val1 = order.get(i);
        while (j> 0){
            if (order.get(j)< order.get(j-1))
                break;
            j -=1;
        }
        int val2 = order.get(j);
        // 3. change tree node
        preorder(root, val1, val2);

    }

    /**
     *  获得一棵树的中序数组
     */
    void inorder(TreeNode node, List<Integer> array){
        if(node.left != null)
            inorder(node.left, array);
        array.add(node.val);
        if(node.right != null)
            inorder(node.right, array);
    }

    /**
     *  前序遍历一棵树，并修改二者结点的值
     */
    public void preorder(TreeNode node, int val1, int val2){
        if (node.val == val1)
            node.val = val2;
        else if (node.val == val2)
            node.val = val1;
        if (node.left != null)
            preorder(node.left, val1, val2);
        if (node.right != null)
            preorder(node.right, val1, val2);
    }

    public static void main(String[] args){

        LeetCode99 solution = new LeetCode99();
        String[] inputs = {"1", "3", "null", "null", "2"};
        TreeNode root = BinaryTreeFactory.getBinaryTreeFromStringArray(inputs);
        solution.recoverTree(root);
        System.out.println("debug.");
    }

}
