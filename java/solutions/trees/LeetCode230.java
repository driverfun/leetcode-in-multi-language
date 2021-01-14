package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;

public class LeetCode230 implements SolutionsFacade {

    int res;
    boolean flag;
    int k;

    /**
     * 对二叉搜索树中序的递归过程加以控制，用3个全局变量完成剪枝
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        if(root == null)
            return 0;
        this.k = k;
        flag = false;
        inorder (root);
        return this.res;
    }

    public void inorder(TreeNode root){
        if(root.left!=null && flag== false)
            inorder(root.left);
        k -= 1;
        if (k == 0){
            res = root.val;
            flag = true;
            return ;
        }

        if(root.right!= null&& flag== false)
            inorder(root.right);
    }

    @Override
    public void calculate(Object... objects) {
        System.out.println(kthSmallest((TreeNode) objects[0], (int) objects[1]));
    }
}
