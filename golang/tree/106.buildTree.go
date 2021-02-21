package _tree

func buildTreeV2(inorder []int, postorder []int) *TreeNode {
	length := len(postorder)
	if length == 0 {
		return nil
	}
	root := &TreeNode{Val: postorder[length-1]}
	i := 0
	for ; i < len(inorder); i++ {
		if inorder[i] == postorder[length-1] {
			break
		}
	}
	root.Left = buildTreeV2(inorder[:i], postorder[:i])
	root.Right = buildTreeV2(inorder[i+1:], postorder[i:length-1])
	return root
}
