package _tree

/*
昨天没想出来，实际上看规律就是右根左的遍历顺序，然后依次将节点值相加即可
*/
func convertBST(root *TreeNode) *TreeNode {
	curr := 0
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Right)
		curr, root.Val = curr+root.Val, curr+root.Val
		dfs(root.Left)
	}
	dfs(root)
	return root
}
