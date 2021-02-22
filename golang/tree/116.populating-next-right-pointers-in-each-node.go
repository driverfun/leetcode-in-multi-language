package _tree

func connect(root *Node) *Node {
	if root == nil {
		return nil
	}
	stack := []*Node{root}
	for len(stack) > 0 {
		length := len(stack)
		for i := 0; i < length; i++ {
			node := stack[i]
			if i <= length-2 {
				node.Next = stack[i+1]
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
	return root
}
