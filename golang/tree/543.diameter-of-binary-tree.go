package _tree

/*
想了一会，经过某节点的最长路径 = 左节点的最大贡献值 + 右节点的最大贡献值，贡献值定义为左子树 or 右子树最大深度 +1
在 dfs 遍历节点的过程中寻找最大的直径即可
*/
func diameterOfBinaryTree(root *TreeNode) int {
	maxDiameter := 0
	max := func(a, b int) int {
		if a > b {
			return a
		}
		return b
	}
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		if root.Left == nil && root.Right == nil {
			return 1
		}
		left := dfs(root.Left)
		right := dfs(root.Right)
		maxDiameter = max(maxDiameter, left+right)
		return max(left, right) + 1
	}
	dfs(root)
	return maxDiameter
}
