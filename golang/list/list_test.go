package list

import "testing"

func TestReverseList(t *testing.T) {
	list5 := &ListNode{Val: 5}
	list4 := &ListNode{Val: 4, Next: list5}
	list3 := &ListNode{Val: 3, Next: list4}
	list2 := &ListNode{Val: 2, Next: list3}
	list1 := &ListNode{Val: 1, Next: list2}
	//ReverseList(list1)
	TraverseList(list1)
}
