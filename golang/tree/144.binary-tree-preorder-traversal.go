package _tree

// 前序遍历，递归 没什么好说的
func preorderTraversalV1(root *TreeNode) []int {
	if root == nil {
		return nil
	}
	ret := []int{root.Val}
	ret = append(ret, preorderTraversalV1(root.Left)...)
	ret = append(ret, preorderTraversalV1(root.Right)...)
	return ret
}

func preorderTraversalV2(root *TreeNode) []int {
	var stack []*TreeNode
	var ret []int
	node := root
	for node != nil || len(stack) > 0 {
		for node != nil {
			ret = append(ret, node.Val)
			stack = append(stack, node)
			node = node.Left
		}
		node = stack[len(stack)-1].Right
		stack = stack[:len(stack)-1]
	}
	return ret
}
