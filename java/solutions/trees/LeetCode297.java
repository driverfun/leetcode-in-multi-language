package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;

import java.util.*;

public class LeetCode297 implements SolutionsFacade {


    /**
     * 一道开放性题目，剑指offer里也有，值得收藏！
     * 不说更合理的序列化方式，但就这个版本依然有可优化的地方（节省空间的方法）！
     */

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        // 层序遍历，并添加左右子节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<String> res = new ArrayList<>();

        while(queue.size()>0){
            TreeNode tmp = queue.poll();
            if(tmp == null){
                res.add("null");
            }
            else{
                res.add(Integer.toString(tmp.val));
                queue.add(tmp.left);
                queue.add(tmp.right);
            }

        }
        return String.join(",", res);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        TreeNode root = nodes[0].equals("null")?  null:new TreeNode(Integer.parseInt(nodes[0]));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        for (int j = 1;j< nodes.length;j+=2){
            TreeNode tmp = queue.poll();
            if(tmp==null){
                j-=2;   // 抵消自增过程
                continue;
            }
            TreeNode left = nodes[j].equals("null")?  null:new TreeNode(Integer.parseInt(nodes[j]));
            TreeNode right = nodes[j+1].equals("null")?  null:new TreeNode(Integer.parseInt(nodes[j+1]));
            tmp.left = left;
            tmp.right = right;
            queue.add(left);
            queue.add(right);
        }
        return root;
    }


    @Override
    public void calculate(Object... objects) {
        String res = serialize((TreeNode)objects[0]);
        TreeNode tree = deserialize(res);
        System.out.println("stop for debug!");
    }
}
