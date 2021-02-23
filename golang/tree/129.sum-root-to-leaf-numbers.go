package _tree

/*
	层序遍历，每次将根节点的值 * 10 加到左右子树上，求所有叶子节点的和
*/
func sumNumbers(root *TreeNode) int {
	if root == nil {
		return 0
	}
	sum := 0
	stack := []*TreeNode{root}
	for len(stack) != 0 {
		length := len(stack)
		for i := 0; i < length; i++ {
			node := stack[i]
			if node.Left == nil && node.Right == nil {
				sum += node.Val
			}
			if node.Left != nil {
				node.Left.Val = node.Val*10 + node.Left.Val
				stack = append(stack, node.Left)
			}
			if node.Right != nil {
				node.Right.Val = node.Val*10 + node.Right.Val
				stack = append(stack, node.Right)
			}
		}
		stack = stack[length:]
	}
	return sum
}
