package list

func ReverseKGroup(head *ListNode, k int) *ListNode {
	if head == nil || head.Next == nil || k == 1 {
		return head
	}
	temp := head
	tempK := k
	for (temp != nil && temp.Next != nil) && tempK > 1 {
		temp = temp.Next
		tempK--
	}
	if tempK > 1 {
		return head
	}
	curr := head
	var prev, next *ListNode
	for curr != nil && prev != temp {
		next, curr.Next = curr.Next, prev
		prev, curr = curr, next
	}
	head.Next = ReverseKGroup(next, k)
	return prev
}
