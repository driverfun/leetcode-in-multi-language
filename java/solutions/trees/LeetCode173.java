package solutions.trees;

import com.sun.source.tree.Tree;
import core.TreeNode;
import util.SolutionsFacade;

import java.util.Stack;

public class LeetCode173 implements SolutionsFacade {

    class BSTIterator {
        /**
         * 要求：next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
         * 所以中序时应当保留该节点的父节点，用栈保存即栈顶为其父节点，栈内元素保留父子关系
         * @param root
         */

        Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            // 获得第一个元素
            pushToStack(root);
        }

        // 压栈函数
        public void pushToStack(TreeNode node){
            while(node!= null & node.left!=null){
                stack.push(node);
                node = node.left;
            }
            stack.push(node);
        }

        public int next() {
            // update  nextNode
            int val= 0;
            if(stack.size()>0){
                // 取出父节点，如果父节点有右子节点，则压入栈
                 TreeNode tmp = stack.pop();
                 val = tmp.val;
                if(tmp.right!=null){
                    pushToStack(tmp.right);
                }
            }

            return val;
        }

        public boolean hasNext() {
            return stack.size()>0;
        }
    }

    @Override
    public void calculate(Object... objects) {
        // get Iterator
        BSTIterator iterator = new BSTIterator((TreeNode) objects[0]);
        System.out.println(iterator.hasNext() + " :"+iterator.next());
        System.out.println(iterator.hasNext() + " :"+iterator.next());
        System.out.println(iterator.hasNext() + " :"+iterator.next());
        System.out.println(iterator.hasNext() + " :"+iterator.next());
        System.out.println(iterator.hasNext() + " :"+iterator.next());
        System.out.println(iterator.hasNext() + " :"+iterator.next());
        System.out.println(iterator.hasNext() + " :"+iterator.next());
        System.out.println(iterator.hasNext() + " :"+iterator.next());

    }
}
