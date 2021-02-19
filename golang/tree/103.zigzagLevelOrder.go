package _tree

func ZigzagLevelOrder(root *TreeNode) [][]int {
	if root == nil {
		return nil
	}
	nodes := []*TreeNode{root}
	ret := [][]int{}
	fromLeft := false
	for len(nodes) > 0 {
		levelVals := []int{}
		length := len(nodes)
		for i, node := range nodes {
			n := nodes[length-1-i]
			if fromLeft {
				if n.Left != nil {
					nodes = append(nodes, n.Left)
				}
				if n.Right != nil {
					nodes = append(nodes, n.Right)
				}
			} else {
				if n.Right != nil {
					nodes = append(nodes, n.Right)
				}
				if n.Left != nil {
					nodes = append(nodes, n.Left)
				}
			}
			levelVals = append(levelVals, node.Val)
		}
		if fromLeft {
			fromLeft = false
		} else {
			fromLeft = true
		}
		nodes = nodes[length:]
		ret = append(ret, levelVals)
	}
	return ret
}
