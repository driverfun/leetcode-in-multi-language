package solutions.trees;

import core.TreeNode;
import util.SolutionsFacade;

import java.util.*;

public class LeetCode95  implements SolutionsFacade {

    /**
     * 方法一：
     *      暴力搜索法，相当于每次从剩下的节点里选一个点加入一棵二叉搜索树；可以将模型想象成不放回的盒内抽球模型（但每次构造完一棵树后必须将球放回盒中）
     * 方法二：
     *      插入节点法（递推式思考），这个问题可看作每次给前一组树添加新节点，如n=4相当于对n=3的那组树加入新节点：
     *                  3           ----    第1层
     *                /   \
     *               2     5        ---     第2层
     *              /     / \
     *             1     4   6      ---     第3层
     *      因为新节点大于它的所有节点，所以只会添加到它的右子树，规律如下：
     *      我们对右子树分层后发现，其实就是将每一层的右子树变成新节点的左子树，并将新节点连到原最右子节点的右子树（第1层不用）
     * P.S.:
     *      如果是问种类：可以动态规划
     * @param n 整数个数
     * @return 树的根节点列表
     */

    public List<TreeNode> generateTrees(int n) {

        // 创建一个HashMap，其key是n，值是一个List，其中包括了n的所有树
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        List<TreeNode> result = new ArrayList<>(){{this.add(new TreeNode(1));}};
        map.put(1, result);
        // 开始递推，对每个n做，取出n-1所有的树，并改造！
        if(n>1)
            for(int i =2;i<=n;i++){
                List<TreeNode> trees = buildTree(map.get(i-1), i);
                map.put(i, trees);
            }

        return map.get(n);
    }


    /**
     * 传入一个原始节点，拷贝整棵树并传出新的根节点。
     * @param origin
     * @return
     */
    public TreeNode copy(TreeNode origin){

        TreeNode dest = new TreeNode(origin.val);
        preorder(origin.left, origin, dest);
        preorder(origin.right, origin, dest);
        return dest;
    }

    public void preorder(TreeNode origin, TreeNode originLast, TreeNode destLast){
        if(origin == null)
            return ;
        TreeNode node = new TreeNode(origin.val);
        if(origin == originLast.left)
            destLast.left = node;
        else
            destLast.right = node;
        preorder(origin.left, origin, node);
        preorder(origin.right, origin, node);

    }


    /**
     *
     * @param origin n-1层的所有树
     * @param n 最后一个节点
     * @return
     */
    public List<TreeNode> buildTree(List<TreeNode> origin, int n){
        List<TreeNode> trees = new ArrayList<>();

        for(int i = 1;i<n; i++){

            for (TreeNode root: origin){
                // 先拷贝树
                TreeNode nRoot = copy(root);
                int j = i-1;
                TreeNode iter = nRoot;
                while(j >0 && iter.right !=null ) {
                    iter = iter.right;
                    j -= 1;
                }
                if(j> 0)    // 说明root右子树层数不够
                    continue;

                TreeNode node = new TreeNode( n);
                TreeNode tmp = iter.right;
                iter.right = node;
                node.left = tmp;
                trees.add(nRoot);
            }

        }

        // 按层数添加节点，0~n-1，对原来数组的每棵树添加节点
        // 最后对第0层统一处理
        for (TreeNode root: origin){
            // 先拷贝树
            TreeNode nRoot = copy(root);
            TreeNode node = new TreeNode( n);
            node.left  = nRoot;
            trees.add(node);
        }
        return trees;
    }


    @Override
    public void calculate(Object... objects) {
        List<TreeNode> list = generateTrees((int)objects[0]);
        System.out.println("stop for debug.");
    }



}
