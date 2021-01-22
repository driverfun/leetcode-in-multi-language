package solutions.trees;

import core.TreeNode;

public class LeetCode235 {


    public TreeNode res = null;
    /**
     * 这道题乍看和236十分类似，但实际可以利用BST的特性简化：
     * 1. 当p、q任一节点与当前节点值相等时即为最近祖先，因为我们遍历顺序时从上至下；
     * 2. 当p、q的值比当前节点一大一小时，则当前节点就是最近祖先；
     * 3. 当p、q都在一侧时，就去对应的子树（如全都大去右子树，全都小去左子树），再套用1、2规则直到找到答案。
     * @param root 根节点（当前节点）
     * @param p 所找节点1
     * @param q 所找节点2
     * @return 最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val == root.val || q.val == root.val){
            res = p.val==root.val? p: q;
        }
        if ((p.val<root.val && q.val> root.val) || (p.val>root.val && q.val< root.val)){
            res = root;
        }
        if(p.val<root.val && q.val < root.val && root.left!= null){
            lowestCommonAncestor(root.left, p, q);
        }
        if(q.val > root.val && p.val> root.val && root.right!= null){
            lowestCommonAncestor(root.right, p, q);
        }
        return res;
    }

}
