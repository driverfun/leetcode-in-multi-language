package list

/*
快慢指针，没什么好说的
*/
func hasCycle(head *ListNode) bool {
	if head == nil {
		return false
	}
	fast, slow := head, head
	for fast != nil && fast.Next != nil {
		// 尽量写成这种形式，会比分开的两条语句快一点
		fast, slow = fast.Next.Next, slow.Next
		if fast == slow {
			return true
		}
	}
	return false
}
