package list

/*
遍历一个链表，不改变链表结构
*/
func TraverseList(node *ListNode) {
	m := make(map[*ListNode]bool)
	curr := node
	for curr != nil {
		m[curr] = true
		curr = curr.Next
	}
}

/*
用一个 map 来存已经遍历过的 headA 的节点，时间复杂度和空间复杂度都比较高
*/
func getIntersectionNode(headA, headB *ListNode) *ListNode {
	m := make(map[*ListNode]bool)
	curr := headA
	for curr != nil {
		m[curr] = true
		curr = curr.Next
	}
	curr = headB
	for curr != nil {
		if m[curr] {
			return curr
		}
		curr = curr.Next
	}
	return nil
}

/*
双指针，分别指向 headA 和 headB
如果两个人分别都做过 A 和 B ，且有交点，则最终一定有一段路是同样的，如果没有交点，则最终都会走到 nil
如何让两个指针走同样长的路呢？分别走 A、B，走到尽头则走对方的路
*/
func getIntersectionNodeV2(headA, headB *ListNode) *ListNode {
	currA, currB := headA, headB
	for currA != currB {
		if currA != nil {
			currA = currA.Next
		} else {
			currA = headB
		}
		if currB != nil {
			currB = currB.Next
		} else {
			currB = headA
		}
	}
	return currA
}

/*
比 V2 更快一点，但实际上只是赋值的方法不同而已
*/
func getIntersectionNodeV3(headA, headB *ListNode) *ListNode {
	currA, currB := headA, headB
	for currA != currB {
		if currA != nil && currB != nil {
			currA, currB = currA.Next, currB.Next
		}
		if currA == nil && currB != nil {
			currA, currB = headB, currB.Next
		}
		if currA != nil && currB == nil {
			currA, currB = currA.Next, headA
		}
	}
	return currA
}
