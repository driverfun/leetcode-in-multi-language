package _tree

type BSTIterator struct {
	Vals []int
	Last bool
}

// 二叉搜索树迭代器, 最简单的就是构造一个中序遍历
func Constructor(root *TreeNode) BSTIterator {
	var inorder func(node *TreeNode) []int
	inorder = func(node *TreeNode) []int {
		if node == nil {
			return nil
		}
		var ret []int
		ret = append(ret, inorder(node.Left)...)
		ret = append(ret, node.Val)
		ret = append(ret, inorder(node.Right)...)
		return ret
	}
	ret := inorder(root)
	return BSTIterator{Vals: ret}
}

func (this *BSTIterator) Next() int {
	a := this.Vals[0]
	if len(this.Vals) == 1 {
		this.Last = true
	} else {
		this.Vals = this.Vals[1:]
	}
	return a
}

func (this *BSTIterator) HasNext() bool {
	return !this.Last
}
