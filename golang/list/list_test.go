package list

import (
	"fmt"
	"testing"
)

func listNodeFactory(nodes []int) *ListNode {
	var res *ListNode
	for i := len(nodes) - 1; i >= 0; i-- {
		curr := &ListNode{
			Val:  nodes[i],
			Next: res,
		}
		res = curr
	}
	return res
}

func traverseList(node *ListNode) {
	for node != nil {
		fmt.Println(node.Val)
		node = node.Next
	}
}

func listNodeFactoryV2(nodes []int) *ListNode {
	if len(nodes) == 0 {
		return nil
	}
	return &ListNode{Val: nodes[0], Next: listNodeFactoryV2(nodes[1:])}
}

func TestReverseList(t *testing.T) {
	ReverseList(listNodeFactoryV2([]int{1, 2, 3, 4, 5}))
}

func TestIsPalindrome(t *testing.T) {
	IsPalindrome(listNodeFactoryV2([]int{1, 2, 2, 1}))
}

func TestIsPalindromeV2(t *testing.T) {
	IsPalindromeV2(listNodeFactoryV2([]int{1, 2, 2, 1}))
}

func TestReverseKGroup(t *testing.T) {
	traverseList(ReverseKGroup(listNodeFactoryV2([]int{1, 2, 3, 4, 5}), 3))
}
