package _tree

import "math"

/*
任何一个二叉树的最大路径和等于该节点的值 + 左子树的最大贡献 + 右子树的最大贡献
节点的最大贡献 = 该节点的值 + max（左子树的最大贡献，右子树的最大贡献）
实际上是一个后序遍历
*/
func maxPathSum(root *TreeNode) int {
	if root == nil {
		return 0
	}
	maxSum := math.MinInt32
	var maxGain func(node *TreeNode) int
	maxGain = func(node *TreeNode) int {
		if node == nil {
			return 0
		}
		leftGain := max(maxGain(node.Left), 0)
		rightGain := max(maxGain(node.Right), 0)
		current := leftGain + rightGain + node.Val
		maxSum = max(maxSum, current)
		return node.Val + max(leftGain, rightGain)
	}
	maxGain(root)
	return maxSum
}
