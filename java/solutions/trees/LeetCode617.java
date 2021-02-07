package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;

public class LeetCode617 implements SolutionsFacade {


    /**
     * 方法一：原地拷贝法（一道面试题里要求的，其实完全没必要）
     * 任意挑一棵树作为基准，用合并后的值更新当前节点值，当左、右子树皆为空时，返回空指针；当基准树对应节点为空时，创建之并从另一方拷贝进来；继续遍历。
     * 繁琐耗时，优点是节省了空间，超过了75%的提交。
     *
     *
     * 方法二：DFS/BFS
     * 新建一棵树跟随原来的树一起遍历，遍历过程中，当有一方（左、右子树）不存在时，则直接嫁接存在的一方，即跟原来的树共享它的子树。
     * 空间上超过了15%的提交。
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 以第一棵树作为头号节点，原地拷贝
        if(root1== null && root2== null)
            return null;
        // 二者必有一个存在
        int val = 0;
        if (root1 !=null)
            val+= root1.val;
        if (root2 !=null)
            val += root2.val;
        if(root1 == null)
            root1 = new TreeNode(0);
        root1.val = val;
        root1.left  = root2!=null? mergeTrees(root1.left, root2.left):mergeTrees(root1.left, null) ;
        root1.right = root2!=null? mergeTrees(root1.right, root2.right):mergeTrees(root1.right, null) ;
        return root1;
    }

    public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {

        if(root1 == null)
            return root2;
        if(root2 == null)
            return root1;
        // 二者皆不为空
        TreeNode root = new TreeNode(root1.val+root2.val);
        root.left = mergeTrees1(root1.left, root2.left);
        root.right = mergeTrees1(root1.right, root2.right);
        return root;
    }

    @Override
    public void calculate(Object... objects) {
        mergeTrees((TreeNode)objects[0], (TreeNode)objects[1]);
    }
}
