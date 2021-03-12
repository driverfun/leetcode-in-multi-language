package list

// 遍历的过程中用一个 map 记录遍历的节点
func removeNthFromEnd(head *ListNode, n int) *ListNode {
	m := make(map[int]*ListNode, 0)
	total := 0
	for head != nil {
		m[total] = head
		total++
		head = head.Next
	}
	del := total - n
	if del == 0 {
		return m[1]
	}
	m[del-1].Next = m[del].Next
	return m[0]
}

// 快慢指针，快指针先走 n 个
// 用一个辅助的头结点
func removeNthFromEndV2(head *ListNode, n int) *ListNode {
	dummy := &ListNode{Val: 0, Next: head}
	first, second := head, dummy
	for i := 0; i < n; i++ {
		first = first.Next
	}
	for first != nil {
		second, first = second.Next, first.Next
	}
	second.Next = second.Next.Next
	return dummy.Next
}
