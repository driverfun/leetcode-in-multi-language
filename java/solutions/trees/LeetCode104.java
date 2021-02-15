package solutions.trees;

import core.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode104 {

    /**
     * 同其他，应当有BFS和DFS两种解法
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        // BFS solution
        int depth = 0;
        if(root!=null){
            Queue<TreeNode>  queue = new LinkedList<>();
            queue.add(root);
            int size = 1;
            while(!queue.isEmpty()){
                TreeNode tmp = queue.poll();
                size -=1;

                if(tmp.left!=null)
                    queue.add(tmp.left);
                if(tmp.right!=null)
                    queue.add(tmp.right);
                if(size==0){
                    depth +=1;
                    size = queue.size();
                }
            }
        }
        return depth;
    }

    // 2. DFS solution
    // 有两种方式： 1.前序遍历（判断是否抵达叶子节点并更新高度）；2.后序遍历（每个节点的深度是其左、右子树最大高度+1）

}
