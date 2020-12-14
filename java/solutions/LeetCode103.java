package solutions;

import core.TreeNode;
import util.BinaryTreeFactory;
import util.SolutionsFacade;

import java.util.*;

public class LeetCode103 implements SolutionsFacade {

    /**
     * 和102题目很像，所以这里只说说不同之处：
     * 因为打印顺序会按照层次的不同而变化，简化逻辑的最简单方法就是浪费空间。
     * 我们在偶数层维护一个栈，将出队的元素先入栈再加入结果集。值得注意的是：
     * 用偶数层先入右节点、后入左节点的方式无法得出正确的顺序，
     * 因为在紧接的一层只有反过来出队顺序是反的，只有以反方向出队才能得到正确顺序，这无疑增加了问题复杂性。
     *
     * ！！！另外，省空间的解法比如官方解法：
     * 用双向队列，在奇数层从屁股添加节点，在偶数层从头添加节点，依次保持顺序的正确性。
     *
     * @param root 二叉树的根节点
     * @return 层序遍历结果
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> results = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<>();

        if(root!=null){
            queue.add(root);
            int level = 1;
            while (!queue.isEmpty()){
                TreeNode tmpNode;
                List<Integer> tmpList = new ArrayList<>();
                Stack<Integer> stack = new Stack<>();
                int times = queue.size();
                // 清理掉这批节点
                while (times>0) {
                    tmpNode = queue.poll();
                    // 奇数层直接添加
                    if (level %2 !=0)
                        tmpList.add(tmpNode.val);
                    else{// 偶数层先入栈
                        stack.add(tmpNode.val);
                    }

                    if(tmpNode.left!= null)
                        queue.add(tmpNode.left);
                    if(tmpNode.right!= null)
                        queue.add(tmpNode.right);
                    times -= 1;
                }
                // 偶数层先把栈里的元素导倒出来
                if(level %2 ==0 )
                    while(!stack.empty())
                        tmpList.add(stack.pop());
                results.add(tmpList);
                level += 1;
            }
        }
        return results;
    }


    @Override
    public void calculate(Object... objects) {
        List<List<Integer>>  res = zigzagLevelOrder((TreeNode) objects[0]);
        System.out.print(res);
    }
}
