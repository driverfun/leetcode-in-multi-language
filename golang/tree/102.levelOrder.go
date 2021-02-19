package _tree

/*
	层序遍历，用一个队列，初始只有根节点，每次遍历读取队列中节点的值，并将左右子节点依次入队
	每层的节点一起出队列
*/
func levelOrder(root *TreeNode) [][]int {
	if root == nil {
		return nil
	}
	nodes := []*TreeNode{root}
	ret := [][]int{}
	for len(nodes) > 0 {
		levelVals := []int{}
		length := len(nodes)
		for _, node := range nodes {
			if node.Left != nil {
				nodes = append(nodes, node.Left)
			}
			if node.Right != nil {
				nodes = append(nodes, node.Right)
			}
			levelVals = append(levelVals, node.Val)
		}
		nodes = nodes[length:]
		ret = append(ret, levelVals)
	}
	return ret
}
