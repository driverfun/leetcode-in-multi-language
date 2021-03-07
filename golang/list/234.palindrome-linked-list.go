package list

/*
没想到什么好的办法，先把链表的节点存入一个 slice，然后双指针从 slice 两头向中间遍历，遇到不同则返回 false，效率很差
*/
func IsPalindrome(head *ListNode) bool {
	var lists []int
	for head != nil {
		lists = append(lists, head.Val)
		head = head.Next
	}
	for i, j := 0, len(lists)-1; i < j; i, j = i+1, j-1 {
		if lists[i] != lists[j] {
			return false
		}
	}
	return true
}

// 反转链表后半部分
func IsPalindromeV2(head *ListNode) bool {
	slow, fast := head, head
	for fast != nil && fast.Next.Next != nil {
		slow, fast = slow.Next, fast.Next.Next
	}
	reversed := ReverseListV2(slow.Next)
	for reversed != nil {
		if reversed.Val != head.Val {
			return false
		}
		reversed, head = reversed.Next, head.Next
	}
	return true
}
