package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode222 implements SolutionsFacade {

    /**
     * 广度优先，用队列保存每层树的结构
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root !=null){
            queue.add(root);
            int size =1;
            int nums =1;
            while (queue.size()>0){
                TreeNode tmp = queue.poll();
                size -=1;
                if (tmp.left!=null)
                    queue.add(tmp.left);
                if (tmp.right!=null)
                    queue.add(tmp.right);
                if(size == 0){
                    size = queue.size();
                    nums += size;
                }
            }
            return nums;
        }

        return 0;
    }


    /**
     * 判断节点
     * @param index
     * @param root
     * @param depth 二叉树上一层的高度，用来推算当前层的中间分叉点
     * @return
     */
    public boolean exists(int index, TreeNode root, int depth){
        while(depth > -1){
            int mid = 1<<depth;
            if (index> mid){    // right child tree
                index -= mid;
                root = root.right;
                if(root == null)
                    return false;
            }

            else {  // left child tree
                root = root.left;
                if(root == null)
                    return false;
            }

            depth -= 1;
        }
        return true;
    }

    // 定义：树的高度为h，从0开始的，故每层节点数为2^h个
    // 对最后一层编号，范围为： 1~2^h （因为已经算过高度，所以第h层的最左节点一定存在）
    // 最重要的是：通过观察发现，最后一层的节点个数是start -1
    public int countNodes1(TreeNode root) {
        // fix counting height bug
        int height = 0;
        TreeNode tmp = root;
        while (tmp!=null){
            tmp = tmp.left;
            height +=1;
        }
        if(root == null)
            return 0;
        height -= 1;


        // 最后一层节点计算
        int start = 1;
        int end = 1<<height;
        int mid;
        while(start <= end){
            mid = (start+ end)/2;
            if(exists(mid, root, height-1))
                start = mid +1;
            else
                end =mid -1;
        }
        // !!! start - 1为最后一层节点数
        int res = (1<< (height)) -1 + start -1;
        return res;
    }

    @Override
    public void calculate(Object... objects) {
        System.out.println(countNodes1((TreeNode)objects[0]));
    }
}
