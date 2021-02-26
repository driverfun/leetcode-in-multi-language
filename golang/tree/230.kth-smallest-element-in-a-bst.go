package _tree

/*
中序遍历到第 k 个停止，即为第 k 小元素
*/
func kthSmallest(root *TreeNode, k int) int {
	stack := []*TreeNode{}
	var count int
	for root != nil || len(stack) > 0 {
		for root != nil {
			stack = append(stack, root)
			root = root.Left
		}
		root = stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		count++
		if count == k {
			return root.Val
		}
		root = root.Right
	}
	return -1
}
