package _tree

/*
	遍历一遍，记录下每一个节点的父节点，然后寻找 p、q 的父节点，寻找的过程中记录下遍历过的节点。
*/
func lowestCommonAncestorV3(root, p, q *TreeNode) *TreeNode {
	parents := make(map[int]*TreeNode, 0)
	visited := make(map[int]bool, 0)
	var dsp func(root, parent *TreeNode)
	dsp = func(root, parent *TreeNode) {
		if root == nil {
			return
		}
		parents[root.Val] = parent
		dsp(root.Left, root)
		dsp(root.Right, root)
	}
	dsp(root, nil)
	for p != nil {
		visited[p.Val] = true
		p = parents[p.Val]
	}
	for q != nil {
		if visited[q.Val] {
			return q
		}
		q = parents[q.Val]
	}
	return root
}
