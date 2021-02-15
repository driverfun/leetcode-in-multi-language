package _tree

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

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
			stack = append(stack, root)
			root = root.Left
		}
		root = stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		ret = append(ret, root.Val)
		root = root.Right
	}
	return ret
}
