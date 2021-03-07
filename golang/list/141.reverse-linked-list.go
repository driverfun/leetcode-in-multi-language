package list

/*
注意修改指针指向的值是否会影响到其他变量，
所以需要一个 prev 保存之前的指针，一个 next 保存当前指针的 Next
*/
func ReverseList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	curr := head
	var prev, next *ListNode
	for curr != nil {
		next, curr.Next = curr.Next, prev
		prev, curr = curr, next
	}
	return prev
}

/*
对于 ... -> k -> k+1 -> ... ->m
反转 k 的时候，假设 k+1~m 已经反转，则需要把 k+1 的 next 指向 m，k 的 next 指向 nil
*/
func ReverseListV2(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	newHead := ReverseListV2(head.Next)
	// head 在之前没有变化，所以 head 的 next 还是指向 k+1
	head.Next.Next, head.Next = head, nil
	return newHead
}
