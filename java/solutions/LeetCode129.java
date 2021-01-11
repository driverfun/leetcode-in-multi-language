package solutions;

import com.sun.source.tree.Tree;
import core.TreeNode;
import util.SolutionsFacade;

import java.util.List;
import java.util.ArrayList;

/**
 * 题目描述：
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 *
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 *
 * 计算从根到叶子节点生成的所有数字之和。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode129  implements SolutionsFacade {

    public int sumNumbers(TreeNode root) {
        // 用先序遍历查找到叶子结点的所有路径
        // 跟112、113两道题十分类似
        List<Integer> sums  = new ArrayList<>();
        int val = 0;
        if(root!=null)
            preOrder(sums, root, val);
        for (int item: sums){
            val += item;
        }
        return val;
    }

    void preOrder(List<Integer> list, TreeNode root, int val) {
        val  = val *10+ root.val;
        if(root.left != null)
            preOrder(list, root.left, val);
        if(root.right != null)
            preOrder(list, root.right, val);
        if(root.left == null & root.right == null){
            list.add(val);
        }
    }

    @Override
    public void calculate(Object... objects) {
        System.out.println(sumNumbers((TreeNode)objects[0]));
    }
}
