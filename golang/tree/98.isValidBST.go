package _tree

/* 在左序遍历二叉搜索树的过程中，节点值是递增的
二叉搜索树性质：
	节点的左子树只包含小于当前节点的数。
	节点的右子树只包含大于当前节点的数。
	所有左子树和右子树自身必须也是二叉搜索树。
*/

func IsValidBST(root *TreeNode) bool {
	if root == nil {
		return true
	}
	stack := make([]*TreeNode, 0)
	var pred *TreeNode
	for root != nil || len(stack) > 0 {
		for root != nil {
			stack = append(stack, root)
			root = root.Left
		}
		root = stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		// 等于也不行
		if pred != nil && pred.Val >= root.Val {
			return false
		}
		pred = root
		root = root.Right
	}
	return true
}
