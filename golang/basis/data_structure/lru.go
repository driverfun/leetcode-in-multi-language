package data_structure

// 双向链表
type DLinkedNode struct {
	Key   int
	Value int
	Prev  *DLinkedNode
	Next  *DLinkedNode
}

func newDLinkedNode(key, value int) *DLinkedNode {
	return &DLinkedNode{
		Key:   key,
		Value: value,
	}
}

// 用一个辅助的头结点和尾结点
type LRUCache struct {
	Capacity int
	Length   int
	Head     *DLinkedNode
	Tail     *DLinkedNode
	Nodes    map[int]*DLinkedNode
}

func Constructor(capacity int) LRUCache {
	lru := LRUCache{
		Capacity: capacity,
		Length:   0,
		Head:     newDLinkedNode(0, 0),
		Tail:     newDLinkedNode(0, 0),
		Nodes:    make(map[int]*DLinkedNode, 0),
	}
	lru.Head.Next = lru.Tail
	lru.Tail.Prev = lru.Head
	return lru
}

func (this *LRUCache) AddToHead(node *DLinkedNode) {
	node.Prev = this.Head
	node.Next = this.Head.Next
	this.Head.Next.Prev = node
	this.Head.Next = node
}

// 移除节点
func (this *LRUCache) RemoveNode(node *DLinkedNode) {
	node.Prev.Next, node.Next.Prev = node.Next, node.Prev
}

// 移到头部
func (this *LRUCache) MoveToHead(node *DLinkedNode) {
	this.RemoveNode(node)
	this.AddToHead(node)
}

// 移除尾结点
func (this *LRUCache) RemoveTail() *DLinkedNode {
	node := this.Tail.Prev
	this.RemoveNode(node)
	return node
}

func (this *LRUCache) Get(key int) int {
	node, ok := this.Nodes[key]
	if !ok {
		return -1
	}
	this.MoveToHead(node)
	return node.Value
}

func (this *LRUCache) Put(key int, value int) {
	if node, ok := this.Nodes[key]; !ok {
		node = newDLinkedNode(key, value)
		this.Nodes[key] = node
		this.AddToHead(node)
		this.Length++
		if this.Length > this.Capacity {
			tail := this.RemoveTail()
			delete(this.Nodes, tail.Key)
			this.Length--
		}
	} else {
		node.Value = value
		this.MoveToHead(node)
	}
}
