package _tree

func rightSideView(root *TreeNode) []int {
	if root == nil {
		return nil
	}
	stack := []*TreeNode{root}
	var ret []int
	for len(stack) > 0 {
		length := len(stack)
		for i := 0; i < length; i++ {
			node := stack[i]
			if i == length-1 {
				ret = append(ret, node.Val)
			}
			if node.Left != nil {
				stack = append(stack, node.Left)
			}
			if node.Right != nil {
				stack = append(stack, node.Right)
			}
		}
		stack = stack[length:]
	}
	return ret
}

func rightSideViewV2(root *TreeNode) []int {
	var res []int
	var dfs func(root *TreeNode, depth int)
	dfs = func(root *TreeNode, depth int) {
		if root == nil {
			return
		}
		if depth == len(res) {
			res = append(res, root.Val)
		}
		depth++
		dfs(root.Right, depth)
		dfs(root.Left, depth)
	}
	dfs(root, 0)
	return res
}
