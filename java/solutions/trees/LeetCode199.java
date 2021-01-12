package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode199 implements SolutionsFacade {

    /**
     * 所求结果为层序遍历的最后一个节点的组合
     * @param root 根节点
     * @return 结果列表
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null)
            return res;
        queue.add(root);
        int size = queue.size();
        while (queue.size() > 0) {
            TreeNode tmp = queue.poll();
            size -= 1;
            // 弹出后为空，说明是当前层最后一个节点
            if (size == 0) {
                res.add(tmp.val);
            }
            if (tmp.left != null)
                queue.add(tmp.left);
            if (tmp.right != null)
                queue.add(tmp.right);
            // 更新每层节点数量值
            if (size == 0) {
                size = queue.size();
            }
        }
        return res;
    }

    @Override
    public void calculate(Object... objects) {
        System.out.println(rightSideView((TreeNode)objects[0]));
    }
}
