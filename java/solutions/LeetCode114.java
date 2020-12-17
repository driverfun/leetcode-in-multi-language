package solutions;

import core.TreeNode;
import util.SolutionsFacade;

import java.util.ArrayList;
import java.util.List;

public class LeetCode114 implements SolutionsFacade {


    // 根据测试数据可发现变为单链表即左节点置空
    // 而链表顺序正好是前序遍历
    // 因此，方法一：利用额外的空间O(n)，n为二叉树节点个数
    //             按前序将节点填入列表，再依次重组他们的关系

    public void preorder(List<TreeNode>list, TreeNode root){
        if(root == null)
            return;

        list.add(root);
        preorder(list, root.left);
        preorder(list, root.right);
    }


    public void flatten0(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        preorder(nodeList, root);
        if (nodeList.size() > 0) {
            for (int j = 0; j < nodeList.size() - 1; j++) {
                nodeList.get(j).left = null;
                nodeList.get(j).right = nodeList.get(j + 1);
            }
            // 最后一个节点就免了，因为是叶子节点，所以左右节点比为空
        }
    }


    public void inorder(TreeNode origin, TreeNode last){
        if(last == null)
            last = origin;
        else{
            last.right = origin;
            last.left = null;
        }
        TreeNode right = origin.right;
        if(origin.left!= null)
            inorder(origin.left, origin);
        if(right != null)
            inorder(right, origin);
    }

    public void flatten(TreeNode root){


    }



    @Override
    public void calculate(Object... objects) {
        flatten((TreeNode) objects[0]);
        System.out.println("stop!");
    }
}
