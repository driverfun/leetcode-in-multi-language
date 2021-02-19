package _tree

/*
始化时我们把根节点入队两次。每次提取两个结点并比较它们的值
（队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像）
然后将两个结点的左右子结点按相反的顺序插入队列中。
*/
func IsSymmetricV1(root *TreeNode) bool {
	u, v := root, root
	q := make([]*TreeNode, 0)
	q = append(q, u)
	q = append(q, v)
	for len(q) > 0 {
		u, v = q[0], q[1]
		q = q[2:]
		if u == nil && v == nil {
			continue
		}
		if u == nil || v == nil {
			return false
		}
		if u.Val != v.Val {
			return false
		}
		q = append(q, u.Left)
		q = append(q, v.Right)
		q = append(q, u.Right)
		q = append(q, v.Left)
	}
	return true
}

/*
通过「同步移动」两个指针的方法来遍历这棵树，p 指针和 q 指针一开始都指向这棵树的根
随后 p 右移时，q 左移，p 左移时，q 右移
每次检查当前 p 和 q 节点的值是否相等，如果相等再判断左右子树是否对称。
*/
func IsSymmetricV2(root *TreeNode) bool {
	return check(root, root)
}

func check(p, q *TreeNode) bool {
	if p == nil && q == nil {
		return true
	}
	if p == nil || q == nil {
		return false
	}
	return p.Val == q.Val && check(p.Left, q.Right) && check(p.Right, q.Left)
}
