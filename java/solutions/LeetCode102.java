package solutions;

import core.TreeNode;
import util.BinaryTreeFactory;
import util.SolutionsFacade;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode102 implements SolutionsFacade {

    /**
     * 经典题目，因为比较简单，就简单说说：
     * 维护一个队列（FIFO），每个入队的节点在出队时将它的子节点也加进来。
     * 为了方便分层，可以在每次出队前统计队列的大小（意为每层节点的数目），按这个数字来完成节点的分组。
     * @param root 二叉树的根节点
     * @return 层序遍历结果
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> results = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<>();

        if(root!=null){
            queue.add(root);
            while (!queue.isEmpty()){
                TreeNode tmpNode;
                List<Integer> tmpList = new ArrayList<>();
                int times = queue.size();
                // 清理掉这批节点
                while (times>0) {
                    tmpNode = queue.poll();
                    tmpList.add(tmpNode.val);
                    if(tmpNode.left!= null)
                        queue.add(tmpNode.left);
                    if(tmpNode.right!= null)
                        queue.add(tmpNode.right);
                    times -= 1;
                }
                results.add(tmpList);
            }
        }
        return results;
    }

    @Override
    public void calculate(Object... objects) {
        List<List<Integer>>  res = levelOrder((TreeNode) objects[0]);
        System.out.print(res);
    }
}
