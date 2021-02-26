package _tree

/*
一开始想的是 dfs，先遍历右节点，再遍历左节点，遇到第一个左右子节点都为 0 的即认为是最后一个节点
但是忽略了最后一个节点可能在左子树上
*/

/*
	正确的思路，先求得完全二叉树的深度 n，则完全二叉树的个数应该就在 2^(n-1) ~ 2^n-1 之间
	然后通过二分确定个数，比如 8~15 之间，假设个 12，然后确定路径是否存在
	路径又可以通过位运算确定
*/
func countNodes(root *TreeNode) int {
	if root == nil {
		return 0
	}
	depth := 0
	// 得到深度
	for node := root; node.Left != nil; node = node.Left {
		depth++
	}
	// 最小
	left := 1 << depth
	right := 1<<(depth+1) - 1
	for left < right {
		mid := (right-left+1)/2 + left
		if exists(root, mid, depth) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}

func exists(root *TreeNode, k, depth int) bool {
	bits := 1 << (depth - 1)
	var node *TreeNode
	node = root
	for node != nil && bits > 0 {
		if bits&k == 0 {
			node = node.Left
		} else {
			node = node.Right
		}
		bits >>= 1
	}
	return node != nil
}
