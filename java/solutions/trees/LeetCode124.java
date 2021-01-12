package solutions.trees;

import core.TreeNode;
import util.BinaryTreeFactory;
import util.SolutionsFacade;

public class LeetCode124 implements SolutionsFacade {

    /**
     * 原始思路：
     *  1. 因为是二叉树，遍历时每个节点可选方向（父节点、左子节点、右子节点），但要加判断，不要走来的那条路（所以记录下上一节点）。
     *  2. 最大路径可能是个中间结果，而非走到叶节点才算。
     *  3. 但最大路径必须将所有结果纳入考虑，例如：[10,7,3]-> 20, [10,7,3,-1] -> 19,看似减小，但有可能在下一节点补回来[10,7,3,-1,6] ->25
     *  4. 针对上两条，可以用@max变量（全局唯一）记录最大值，@current记录当前路径上的路径和，当@current>@max时更新
     */

//    public void travese(TreeNode node, int current, TreeNode lastNode){
//        if(node==null)
//            return ;
//        current += node.val;
//        if (current> this.max)
//            this.max = current;
//
//        if(node.left != lastNode)
//            travese(node.left, current, node);
//        if(node.right != lastNode)
//            travese(node.right, current, node);
//    }

    public int max = -99999999;


    /**
     * 题解思路：
     *  以后序方式（左-右-中）遍历树，为啥是后序方式？因为后序是一种由底向上的构建方式，在考虑根节点时其左右节点都已分析完毕。
     *  对于二叉树中的每个节点，到分析他的那一刻，假设左右子树皆以抽象为节点：
     *  1. 其左子树为负，则舍弃它，即left=0，否则保留；
     *  2. 同理，右子树为负则舍弃，负责保留；
     *  3. 此时，更新最大路径时考虑：是否连同左右子树，即left+right+rootVal，如果比已知最大路径大则联通，否则放弃；
     *  4. 在向上一级返回时，只返回其左、右子树的一条，因为最大路径不可能两条路都走，择大而选。
     */
    public int travese(TreeNode node){
        if(node == null)
            return 0;
        int left = Math.max(0, travese(node.left));
        int right = Math.max(0, travese(node.right));
        // update this.max value
        this.max = Math.max(this.max, left+right+node.val);

        return Math.max(left,right)+node.val;
    }

    public int maxPathSum(TreeNode root) {
        travese(root);
        return this.max;
    }

    @Override
    public void calculate(Object... objects) {
        int res = maxPathSum((TreeNode) objects[0]);
        System.out.println(res);
    }

}
