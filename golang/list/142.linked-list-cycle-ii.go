package list

/*
维持一个 map 保留到过的节点，比较偷懒
*/
func detectCycle(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}
	m := map[*ListNode]bool{head: true}
	fast := head
	for fast != nil {
		fast = fast.Next
		if m[fast] {
			return fast
		}
		m[fast] = true
	}
	return nil
}
