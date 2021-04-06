package _tree

func inorderTraversalV1(root *TreeNode) []int {
	if root == nil {
		return nil
	}
	var ret []int
	ret = append(ret, inorderTraversalV1(root.Left)...)
	ret = append(ret, root.Val)
	ret = append(ret, inorderTraversalV1(root.Right)...)
	return ret
}

// 迭代方式
func inorderTraversalV2(root *TreeNode) []int {
	if root == nil {
		return nil
	}
	var ret []int
	stack := []*TreeNode{}
	for root != nil || len(stack) > 0 {
		for root != nil {
			// 从根到左一次入栈
			stack = append(stack, root)
			root = root.Left
		}
		// 出栈的就是左根右的顺序
		root = stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		ret = append(ret, root.Val)
		// 移到右节点
		root = root.Right
	}
	return ret
}
