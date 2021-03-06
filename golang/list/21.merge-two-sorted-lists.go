package list

func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
	if l1 == nil {
		return l2
	}
	if l2 == nil {
		return l1
	}
	var ret *ListNode
	if l1.Val < l2.Val {
		ret = l1
		ret.Next = mergeTwoLists(l1.Next, l2)
	} else {
		ret = l2
		ret.Next = mergeTwoLists(l1, l2.Next)
	}
	return ret
}
