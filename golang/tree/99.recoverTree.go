package _tree

/*
	在左序遍历二叉搜索树的过程中，节点值是递增的，记录下两个符合这种情况的节点，即可 break
	遍历的过程采用了迭代法，借助栈
	pred 节点记录前一个遍历的节点
*/
func recoverTree(root *TreeNode) {
	stack := []*TreeNode{}
	var x, y, pred *TreeNode
	for root != nil || len(stack) != 0 {
		for root != nil {
			stack = append(stack, root)
			root = root.Left
		}
		root = stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		if pred != nil && pred.Val > root.Val {
			y = root
			if x == nil {
				x = pred
			} else {
				break
			}
		}
		pred = root
		root = root.Right
	}
	x.Val, y.Val = y.Val, x.Val
}
