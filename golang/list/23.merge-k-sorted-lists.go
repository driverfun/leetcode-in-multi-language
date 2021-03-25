package list

// 合并 k 个升序链表，分治，相当于两两合并，然后 merge
func mergeKLists(lists []*ListNode) *ListNode {
	if len(lists) == 0 {
		return nil
	}
	if len(lists) == 1 {
		return lists[0]
	}
	mid := len(lists) / 2
	left := mergeKLists(lists[:mid])
	right := mergeKLists(lists[mid:])
	dummyHead := &ListNode{}
	temp := dummyHead
	for left != nil && right != nil {
		if left.Val <= right.Val {
			temp.Next = left
			left = left.Next
		} else {
			temp.Next = right
			right = right.Next
		}
		temp = temp.Next
	}
	if left != nil {
		temp.Next = left
	}
	if right != nil {
		temp.Next = right
	}
	return dummyHead.Next
}
