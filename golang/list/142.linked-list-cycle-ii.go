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

/*
还是用快慢指针，当快慢指针第一次相遇时，慢指针走了 k 步，快指针走了 2k 步，则环周长为 k
设起始点到入环点长度为 m，则入环点到相遇点的距离为 k-m，从相遇点再走到入环点的距离也是 m
所以令 slow = head，然后快慢指针同时一步步走，走到的时候必然相遇在入环点
*/
func detectCycleV2(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}
	fast, slow := head, head
	hasCycle := false
	for fast != nil && fast.Next != nil {
		fast, slow = fast.Next.Next, slow.Next
		if fast == slow {
			hasCycle = true
			break
		}
	}
	if !hasCycle {
		return nil
	}
	slow = head
	for slow != fast {
		slow, fast = slow.Next, fast.Next
	}
	return slow
}
