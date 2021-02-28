package _tree

/*
	二分查找，记录下路径，然后比较
*/
func lowestCommonAncestorV1(root, p, q *TreeNode) *TreeNode {
	pPath := binarySearch(root, p)
	qPath := binarySearch(root, q)
	minLength := min(len(pPath), len(qPath))
	for i := 0; i < minLength; i++ {
		if pPath[i].Val != qPath[i].Val {
			return pPath[i-1]
		}
	}
	return pPath[minLength-1]
}

func min(a, b int) int {
	if a > b {
		return b
	}
	return a
}

func binarySearch(root, p *TreeNode) []*TreeNode {
	ret := []*TreeNode{root}
	if root.Val == p.Val {
		return ret
	}
	if p.Val > root.Val {
		ret = append(ret, binarySearch(root.Right, p)...)
	} else {
		ret = append(ret, binarySearch(root.Left, p)...)
	}
	return ret
}

/*
	如果 p、q 在根节点两侧，则祖先为根节点，
	如果 p、q 在根节点同侧，则祖先亦为该侧
*/

func lowestCommonAncestor(root, p, q *TreeNode) *TreeNode {
	ancestor := root
	for {
		if p.Val > ancestor.Val && q.Val > ancestor.Val {
			ancestor = ancestor.Right
		} else if p.Val < ancestor.Val && q.Val < ancestor.Val {
			ancestor = ancestor.Left
		} else {
			return ancestor
		}
	}
}
