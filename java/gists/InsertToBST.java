package gists;

import core.TreeNode;

public class InsertToBST {

    /**
     * 将节点插入树中，位置唯一确定？
     * @param root
     * @param val
     */
    public  void insert(TreeNode root, int val){
        if(root.val< val) {
            // val大于当前节点的值，向右子树添加
            if (root.right == null) {
                TreeNode node = new TreeNode(val);
                root.right = node;
            } else {
                insert(root.right, val);
            }
        }
        else{
            // 因为是有序数组，不存在相等情况，所以这里是 root.val> val
            if (root.left == null) {
                TreeNode node = new TreeNode(val);
                root.left = node;
            } else {
                insert(root.left, val);
            }
        }
    }


}
