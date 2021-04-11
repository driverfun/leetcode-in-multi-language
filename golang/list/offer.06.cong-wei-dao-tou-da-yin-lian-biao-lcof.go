package list

/*
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
*/
/*
题解: 先依次放入一个数组，然后反转数组
*/
func reversePrint(head *ListNode) []int {
	ans := make([]int, 0)
	for head != nil {
		ans = append(ans, head.Val)
		head = head.Next
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return ans
}
