package _tree

/*
判断一个树是不是另一个树的子结构
*/

/*
思路：一开始想的是用中序遍历，然后判断数组是不是另一个的子结构，但是中序遍历相同是必要条件而非充分条件
*/

/*
题解：
若树 B 是树 A 的子结构，则子结构的根节点可能为树 A 的任意一个节点
*/

func isSubStructure(A *TreeNode, B *TreeNode) bool {
	if A == nil || B == nil {
		return false
	}
	return cmp(A, B) || isSubStructure(A.Left, B) || isSubStructure(A.Right, B)
}

func cmp(A *TreeNode, B *TreeNode) bool {
	if B == nil {
		return true
	}
	if A == nil || A.Val != B.Val {
		return false
	}
	return cmp(A.Left, B.Left) && cmp(A.Right, B.Right)
}
