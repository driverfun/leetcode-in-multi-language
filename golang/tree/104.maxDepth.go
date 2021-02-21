package _tree

// 深度优先，当前树的最大深度等于 max(左子树的最大深度，右子树的最大深度) +1
func maxDepthV1(root *TreeNode) int {
	if root == nil {
		return 0
	}
	return max(maxDepthV1(root.Left), maxDepthV1(root.Right)) + 1
}

func max(a, b int) int {
	if a >= b {
		return a
	}
	return b
}

// 广度优先，维持一个队列，每次取队列中所有的子节点入队，然后把老的节点出队
func maxDepthV2(root *TreeNode) int {
	if root == nil {
		return 0
	}
	nodes := []*TreeNode{root}
	ans := 0
	for len(nodes) > 0 {
		length := len(nodes)
		for _, node := range nodes {
			if node.Left != nil {
				nodes = append(nodes, node.Left)
			}
			if node.Right != nil {
				nodes = append(nodes, node.Right)
			}
		}
		nodes = nodes[length:]
		ans++
	}
	return ans
}
