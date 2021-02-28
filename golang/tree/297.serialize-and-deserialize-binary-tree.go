package _tree

import (
	"strconv"
	"strings"
)

/*
序列化和反序列化一个二叉树，官方是按照层序遍历，每层按顺序如队，以逗号分隔
*/

type Codec struct {
}

func ConstructorV2() Codec {
	return Codec{}
}

// Serializes a tree to a single string.
func (this *Codec) serialize(root *TreeNode) string {
	if root == nil {
		return ""
	}
	stack := []*TreeNode{root}
	ret := strconv.Itoa(root.Val)
	for len(stack) > 0 {
		length := len(stack)
		for i := 0; i < length; i++ {
			if stack[i].Left != nil {
				ret = ret + "," + strconv.Itoa(stack[i].Left.Val)
				stack = append(stack, stack[i].Left)
			} else {
				ret = ret + "," + "nil"
			}
			if stack[i].Right != nil {
				ret = ret + "," + strconv.Itoa(stack[i].Right.Val)
				stack = append(stack, stack[i].Right)
			} else {
				ret = ret + "," + "nil"
			}
		}
		stack = stack[length:]
	}
	return ret
}

// Deserializes your encoded data to tree.
func (this *Codec) deserialize(data string) *TreeNode {
	if data == "" {
		return nil
	}
	items := strings.Split(data, ",")
	root := GetNode(items[0])
	items = items[1:]
	stack := []*TreeNode{root}
	for len(items) > 0 {
		length := len(stack)
		var children []string
		if len(items) > length*2 {
			children = items[:length*2]
			items = items[length*2:]
		} else {
			children = items
			items = nil
		}
		for i := 0; i < len(children); i++ {
			node := GetNode(children[i])
			if node == nil {
				continue
			}
			if i%2 == 0 {
				stack[i/2].Left = node
			} else {
				stack[i/2].Right = node
			}
			stack = append(stack, node)
		}
		stack = stack[length:]
	}
	return root
}

func GetNode(str string) *TreeNode {
	if str == "nil" {
		return nil
	}
	i, _ := strconv.Atoi(str)
	return &TreeNode{Val: i}
}
