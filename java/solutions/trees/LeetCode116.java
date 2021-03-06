package solutions.trees;

import core.Node1;
import util.SolutionsFacade;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode116 implements SolutionsFacade {

    /**
     * 普通解法：层序遍历，每层的每个节点的next都连向其队首节点
     * @param root
     * @return
     */
    public Node1 connect(Node1 root) {
        Queue<Node1> queue = new LinkedList<>();
        if (root == null)
            return null;
        queue.add(root);

        Node1 tmp;
        int size = queue.size();
        while (!queue.isEmpty()){

            tmp = queue.poll();
            size -= 1;

            if(tmp.left!=null)
                queue.add(tmp.left);
            if(tmp.right!=null)
                queue.add(tmp.right);
            if(size == 0){
                tmp.next =null;
                size = queue.size();
            }
            else{
                tmp.next = queue.peek();
            }
        }
        return  root;

    }

    @Override
    public void calculate(Object... objects) {
        connect((Node1) objects[0]);
        System.out.println("stop for debug!");
    }
}
