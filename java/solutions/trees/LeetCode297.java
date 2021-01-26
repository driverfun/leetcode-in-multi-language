package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;

import java.util.*;

public class LeetCode297 implements SolutionsFacade {


    /**
     * 一道开放性题目，剑指offer里也有，值得收藏！
     * 注意：DFS时序列/反序列化不同的遍历顺序
     *      以及括号编码法等新奇解法
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
        // 删除不必要的null节点
        while(res.size()-1>0 && res.get(res.size()-1).equals("null")){
            res.remove(res.size()-1);
        }
        return String.join(",", res);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        TreeNode root = nodes[0].equals("null")?  null:new TreeNode(Integer.parseInt(nodes[0]));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        for (int j = 1;j< nodes.length;){
            TreeNode tmp = queue.poll();
            if(tmp==null)continue;

            TreeNode left = nodes[j].equals("null")?  null:new TreeNode(Integer.parseInt(nodes[j]));
            tmp.left = left;
            queue.add(left);

            j+=1;
            if(j<nodes.length){
                TreeNode right = nodes[j].equals("null")?  null:new TreeNode(Integer.parseInt(nodes[j]));
                tmp.right = right;
                queue.add(right);
            }
            j+=1;
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
