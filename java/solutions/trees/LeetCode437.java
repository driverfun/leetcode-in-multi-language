package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;

public class LeetCode437 implements SolutionsFacade {

    public int res=0;

    public void preorder(TreeNode  root, int path, int target){
        if (root == null)
            return ;
        path += root.val;
        if( path == target){
            res += 1;
        }
        preorder(root.left, 0, target);
        preorder(root.right, 0, target);
        preorder(root.left, path, target);
        preorder(root.right, path, target);

    }


    public int pathSum(TreeNode root, int sum) {
        preorder(root, 0, sum);
        return res;
    }


    @Override
    public void calculate(Object ...objects){
        int res =pathSum((TreeNode) objects[0], (int) objects[1]);
        System.out.println(res);
    }

}
