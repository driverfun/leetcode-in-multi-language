package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;

import java.util.ArrayList;
import java.util.List;

public class LeetCode114 implements SolutionsFacade {


    // 根据测试数据可发现变为单链表即左节点置空
    // 而链表顺序正好是前序遍历
    // 因此，方法一：利用额外的空间O(n)，n为二叉树节点个数
    //             按前序将节点填入列表，再依次重组他们的关系
    //
    //      方法二：这题目与昨天做的98题解法相同，都要记录其上一节点（注意设置为全局变量）
    //             不同之处在于，在进行前序遍历时，需要保存上一节点的右子节点（否则返回后找不到右子树，注意：这里用局部变量，防止覆盖）
    //              而左子树因为在遍历时必定是根节点的下一节点，所以可不保留。
    //              如此，在每次进入一个节点后先将last节点左子节点置空，右子节点指向当前节点，再更新last为当前节点，并保存其右子树
    public void preorder0(List<TreeNode>list, TreeNode root){
        if(root == null)
            return;

        list.add(root);
        preorder0(list, root.left);
        preorder0(list, root.right);
    }


    public void flatten0(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        preorder0(nodeList, root);
        if (nodeList.size() > 0) {
            for (int j = 0; j < nodeList.size() - 1; j++) {
                nodeList.get(j).left = null;
                nodeList.get(j).right = nodeList.get(j + 1);
            }
            // 最后一个节点就免了，因为是叶子节点，所以左右节点比为空
        }
    }
    public TreeNode last;

    public void inorder(TreeNode origin){
        if(last == null)
            last = origin;
        else{
            last.right = origin;
            last.left = null;
        }
        TreeNode right = origin.right;
        last = origin;
        if(origin.left!= null)
            inorder(origin.left);
        if(right != null)
            inorder(right);
    }

    public void flatten(TreeNode root){
        if(root==null)
            return ;
        inorder(root);
    }



    @Override
    public void calculate(Object... objects) {
        flatten((TreeNode) objects[0]);
        System.out.println("stop!");
    }
}
