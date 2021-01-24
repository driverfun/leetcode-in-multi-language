package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;

public class LeetCode337 implements SolutionsFacade {


    /**
     * 思路：在递归过程中，每个节点都有抢、不抢两种情况，但它的状态还和父节点有没有被抢有关：
     * 当父节点被抢时直接进入子节点（因为不能连续抢两家）；
     * 当父节点未被抢时有两种选择，要么抢这家、要么不抢，二者比较后选择结果更大的策略。
     * @param root
     * @return
     */

    public int rob(TreeNode root){
        if(root == null)
            return 0;
        // root 选入和未选两种
        int one = root.val + preorder(root.left, true)+ preorder(root.right, true);
        int two = preorder(root.left, false)+ preorder(root.right, false);
        return one>two?one:two;
    }


    public int preorder(TreeNode root, boolean stat){
        if(root == null)
            return 0;
        if (stat == true)  // 父节点被选入
            return preorder(root.left,false)+ preorder(root.right,false);
        else{
            int one = root.val + preorder(root.left, true)+ preorder(root.right, true);
            int two = preorder(root.left, false)+ preorder(root.right, false);
            return one>two?one:two;
        }
    }


    /**
     * 使用后序的方式遍历，对每棵子树来说，有两种选择：不选当前节点，从最左、右子树选最大值；选当前节点，放弃左、右子树。
     * @param root
     * @return
     */
    public int rob1(TreeNode root) {
        boolean[] stat = {false};
        return postOrder(root, stat);
    }

    /**
     * 注：当下层节点为false时（即未选择root时可以累加），为true时意为选了root，此时当前节点要么舍弃下层，要么不加入当前节点。
     * @param root
     * @param stat 选择状态
     * @return
     */
    public int postOrder(TreeNode root, boolean[] stat){
        if(root == null){
            stat[0] = false;
            return 0;
        }

        int left = postOrder(root.left, stat) ;
        boolean lflag = stat[0];
        int right = postOrder(root.right, stat);
        boolean rflag = stat[0];
        int res = root.val;
        if (lflag == false && left > 0)
            res += left;
        if (rflag == false && right > 0)
            res += right;

        return res;
    }


    @Override
    public void calculate(Object... objects) {
        int res = rob((TreeNode) objects[0]);
        System.out.println(res);
    }
}
