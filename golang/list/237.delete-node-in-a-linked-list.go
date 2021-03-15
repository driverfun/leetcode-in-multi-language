package list

/*
脑筋急转弯的题，节点的值更改为 next 的值，然后 next 指向 next 的 next
*/
func deleteNode(node *ListNode) {
	node.Val, node.Next = node.Next.Val, node.Next.Next
}
