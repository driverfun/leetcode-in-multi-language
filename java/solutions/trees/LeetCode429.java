package solutions.trees;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import core.Node;

public class LeetCode429 {

    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> res= new ArrayList<>();
        if (root !=null){
            List<Integer> tmp = new ArrayList<>();
            queue.add(root);
            int size = queue.size();
            while(queue.size()>0){
                Node node = queue.poll();
                size -= 1;
                tmp.add(node.val);
                if (node.children!=null){
                    for(Node child: node.children){
                        queue.add(child);
                    }
                }

                if(size == 0){
                    res.add(tmp);
                    tmp = new ArrayList<>();
                    size = queue.size();
                }
            }
        }
        return  res;
    }

    public static void main(String[] args){

        List<Node> children = new ArrayList<>();
        children.add(new Node(2));
        children.add(new Node(1));
        Node root = new Node(3, children);

        LeetCode429 solution = new LeetCode429();
        solution.levelOrder(root);
    }

}
