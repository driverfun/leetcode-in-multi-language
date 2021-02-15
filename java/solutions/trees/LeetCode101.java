package solutions.trees;


import core.TreeNode;
import util.SolutionsFacade;

public class LeetCode101 implements SolutionsFacade {

    /**
     * 设置两个指针，分别遍历左、右子树，其顺序正好相反
     *
     * 具体为：左指针向左子树移动时，右指针向右子树移动；
     *       左指针向右子树移动时，右指针向左子树移动。
     *
     * 规律：当我们判断两棵树是否对称时，就会发现A树每个节点的右子树都与B树每个节点的左子树对称；同理，A树的左子树与B树的右子树对称。
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(root==null)
            return true;
        boolean[] res = {true};
        traverse(root.left, root.right, res);
        return res[0];
    }

    public void traverse(TreeNode left, TreeNode right, boolean[] res){
        if(left!=null && right!=null){          // 左右指针皆不为空
            if(left.val!=right.val){
                res[0] = false;
                return;
            }
            traverse(left.left, right.right, res);
            traverse(left.right, right.left, res);
        }
        else if(left==null && right==null){
            return;
        }else{
            res[0] = false;
            return;
        }
    }

    @Override
    public void calculate(Object... objects) {
        isSymmetric((TreeNode)objects[0]);
    }
}
