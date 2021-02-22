package _tree

/*
算法和 94 题中序遍历的 Morris 算法有些神似
1. 将左子树插入到右子树的地方
2. 将原来的右子树接到左子树的最右边节点
3. 考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
*/

func flattenV1(root *TreeNode) {
	for root != nil {
		// 左子树为 nil，考虑下一个节点
		if root.Left == nil {
			root = root.Right
		} else {
			// 找到左子树最右边的节点
			pre := root.Left
			for pre.Right != nil {
				pre = pre.Right
			}
			// 将右子树接到左子树最右边的节点的右节点
			pre.Right = root.Right
			// 左子树插入到右子树的地方
			root.Right = root.Left
			root.Left = nil
			// 考虑下一个节点
			root = root.Right
		}
	}
}

/*
利用后序遍历，把前一个遍历的节点接到后一个遍历的节点的右子树上
*/
var pre *TreeNode

func flattenV2(root *TreeNode) {
	if root == nil {
		return
	}
	flattenV2(root.Right)
	flattenV2(root.Left)
	root.Right = pre
	root.Left = nil
	pre = root
}
