package _tree

func pathSum(root *TreeNode, targetSum int) [][]int {
	if root == nil {
		return nil
	}
	ret := [][]int{}
	stack := []int{}
	var searchPath func(root *TreeNode, current int)
	searchPath = func(root *TreeNode, current int) {
		current += root.Val
		stack = append(stack, root.Val)
		if root.Left == nil && root.Right == nil && current == targetSum {
			tmp := make([]int, len(stack))
			copy(tmp, stack)
			ret = append(ret, tmp)
		}
		if root.Left != nil {
			searchPath(root.Left, current)
			stack = stack[:len(stack)-1]
		}
		if root.Right != nil {
			searchPath(root.Right, current)
			stack = stack[:len(stack)-1]
		}
	}
	searchPath(root, 0)
	return ret
}
