package solutions.trees;

import core.TreeNode;

import java.util.Stack;

public class LeetCode236 {

    // 控制递归是否继续进行
    private boolean flag = true;
    // 保存最终结果
    private TreeNode res = null;

    public boolean DFS(TreeNode root, TreeNode q){
        if(root == null)
            return false;
        if(root ==q)
            return true;
        boolean stat = false;
        if(root.left != null)
             stat |= DFS(root.left, q);
        if(root.right != null)
             stat |= DFS(root.right, q);
        return stat;
    }

    public void preorderFind(TreeNode root, TreeNode p, TreeNode q, Stack<TreeNode> stack){
        if(root == p ||  root ==q ){

            if(root == p){
                //  在递归的过程中发现了p节点，先查找它的子树中是否存在q
                if (DFS(root, q)){
                    // 如果存在，则p就是最近祖先
                    res = p;
                    flag = false;
                }
                else{
                    // 如果不存在，则在其某个父节点的右子树中
                    while (stack.size()>0){
                        TreeNode t = stack.pop();
                        // 对每个父节点的右子树再次查找
                        if(DFS(t.right, q)){
                            res  = t;
                            flag = false;
                            break;
                        }

                    }
                }
            }else{
                //  在递归的过程中发现了q节点，先查找它的子树中是否存在p
                if (DFS(root, p)){
                    // 如果存在，则q就是最近祖先
                    res = q;
                    flag = false;
                }
                else{
                    // 如果不存在，则在其某个父节点的右子树中
                    while (stack.size()>0){
                        TreeNode t = stack.pop();
                        if(DFS(t.right, p)){
                            res  = t;
                            flag = false;
                            break;
                        }
                    }
                }
            }
        }
        // 在进入下次递归前，将当前节点加入父节点栈
        stack.add(root);
        if(root.left!=null && flag)
            preorderFind(root.left, p, q, stack);
        if(root.right!=null && flag)
            preorderFind(root.right, p, q, stack);
        if(flag)
            // 如果递归继续进行，则弹出当前节点
            stack.pop();

    }


    /**
     * 思路：想的倒是清楚，写的时候废了半天劲儿。
     * 我们在DFS（实际上中序也行）的过程中，总会先碰到所找节点的其中一个（假设是p）。那么剩下的另一个节点（假设是q），情况一：要么在当前节点p的子树中，
     * 即以p为根节点DFS查找q；情况二：在它父节点的右子树中（因为前中序实际上都是从左到右、从上到下的方式），至于是哪个父节点，需要用一个栈记录父节点，并
     * 依次弹出查找q（这里存在重复查找，应该能优化）。
     *
     * 对于情况一，最近公共祖先就是p本身；对于情况二，是弹出的父元素中最先找到q的那个父节点。
     * @param root 根节点（当前节点）
     * @param p 所找节点1
     * @param q 所找节点2
     * @return 最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        preorderFind(root, p, q, stack);
        return res;
    }



    public static void main(String[]  args){
        TreeNode node1 = new TreeNode(-1);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(-2);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(1);
        TreeNode node8 = new TreeNode(0);
        TreeNode node9 = new TreeNode(8);
        node1.left = node2 ;
        node1.right = node3;
        node2.left = node5;
        node2.right = node6;

        node5.left = node9;
//        node4.left =  node5;
//        node4.right = node6;

        LeetCode236 solution = new LeetCode236();
        TreeNode res = solution.lowestCommonAncestor(node1, node9, node3);
        System.out.println(res.val);
    }

}
