package _tree

// dfs 求解
func hasPathSumV1(root *TreeNode, targetSum int) bool {
	if root == nil {
		return false
	}
	return dfs(root, targetSum, 0)
}

func dfs(root *TreeNode, target, current int) bool {
	current += root.Val
	if root.Left == nil && root.Right == nil && current == target {
		return true
	}
	if root.Left != nil && dfs(root.Left, target, current) {
		return true
	}
	if root.Right != nil && dfs(root.Right, target, current) {
		return true
	}
	return false
}

func hasPathSumV2(root *TreeNode, targetSum int) bool {
	if root == nil {
		return false
	}
	if root.Left == nil && root.Right == nil {
		return root.Val == targetSum
	}
	return hasPathSumV2(root.Left, targetSum-root.Val) || hasPathSumV2(root.Right, targetSum-root.Val)
}

/* BFS 用一个队列维护要遍历的节点，一个队列维护要根节点到这些节点的路径
每次取出一个，判断到叶子节点的长度已经满足则返回 true，否则把非空子节点入队
*/
func hasPathSumV3(root *TreeNode, targetSum int) bool {
	if root == nil {
		return false
	}
	qNode := []*TreeNode{root}
	qVal := []int{root.Val}
	for len(qNode) > 0 {
		node := qNode[0]
		qNode = qNode[1:]
		val := qVal[0]
		qVal = qVal[1:]
		if node.Left == nil && node.Right == nil {
			if val == targetSum {
				return true
			}
			continue
		}
		if node.Left != nil {
			qNode = append(qNode, node.Left)
			qVal = append(qVal, val+node.Left.Val)
		}
		if node.Right != nil {
			qNode = append(qNode, node.Right)
			qVal = append(qVal, val+node.Right.Val)
		}
	}
	return false
}
