package data_structure

import "fmt"

type ListNode struct {
	Key  interface{}
	Val  interface{}
	Next *ListNode
}

func (l *ListNode) Insert(key, val interface{}) {
	node := &ListNode{
		Key:  key,
		Val:  val,
		Next: l.Next,
	}
	l.Next = node
}

func (l *ListNode) Del(key interface{}) {
	temp := l.Next
	last := l
	for temp != nil {
		if temp.Key == key {
			last.Next = temp.Next
		}
		last = temp
		temp = temp.Next
	}
}

func (l *ListNode) Get(key interface{}) *ListNode {
	temp := l.Next
	for temp != nil {
		if temp.Key == key {
			return temp
		}
		temp = temp.Next
	}
	return nil
}

type Map struct {
	Nodes []*ListNode
}

func NewMap(n int) *Map {
	return &Map{
		Nodes: make([]*ListNode, n),
	}
}

func hash(key interface{}, n int) int {
	k := fmt.Sprintf("%v", key)
	h := 0
	// ASCII 码 * 31 依次相加
	for i := 0; i < len(k); i++ {
		h = 31*h + int(k[i])
	}
	return h % n
}

func (m *Map) Store(key, val interface{}) {
	h := hash(key, len(m.Nodes))
	if h < 0 {
		panic(h)
	}
	if m.Nodes[h] == nil {
		m.Nodes[h] = &ListNode{}
		m.Nodes[h].Insert(key, val)
		return
	}
	if node := m.Nodes[h].Get(key); node == nil {
		m.Nodes[h].Insert(key, val)
	} else {
		node.Val = val
	}
}

func (m *Map) Get(key interface{}) interface{} {
	h := hash(key, len(m.Nodes))
	if m.Nodes[h] == nil {
		return nil
	}
	node := m.Nodes[h].Get(key)
	if node == nil {
		return nil
	}
	return node.Val
}

func (m *Map) Del(key interface{}) {
	h := hash(key, len(m.Nodes))
	if m.Nodes[h] == nil {
		return
	}
	m.Nodes[h].Del(key)
}
