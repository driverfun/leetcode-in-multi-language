package data_structure

import "math/rand"

type node struct {
	ch       [2]*node
	priority int
	key      int
	count    int
}

func (o *node) cmp(b int) int {
	switch {
	case b < o.key:
		return 0
	case b > o.key:
		return 1
	default:
		return -1
	}
}

func (o *node) rotate(d int) *node {
	x := o.ch[d^1]
	o.ch[d^1] = x.ch[d]
	x.ch[d] = o
	return x
}

type treap struct {
	root *node
}

func (t *treap) ins(o *node, key int) *node {
	if o == nil {
		return &node{priority: rand.Int(), key: key, count: 1}
	}
	if d := o.cmp(key); d >= 0 {
		o.ch[d] = t.ins(o.ch[d], key)
		if o.ch[d].priority > o.priority {
			o = o.rotate(d ^ 1)
		}
	} else {
		o.count++
	}
	return o
}

func (t *treap) del(o *node, key int) *node {
	if o == nil {
		return nil
	}
	if d := o.cmp(key); d >= 0 {
		o.ch[d] = t.del(o.ch[d], key)
	} else {
		if o.count > 1 {
			o.count--
		} else {
			if o.ch[1] == nil {
				return o.ch[0]
			}
			if o.ch[0] == nil {
				return o.ch[1]
			}
			d = 0
			if o.ch[0].priority > o.ch[1].priority {
				d = 1
			}
			o = o.rotate(d)
			o.ch[d] = t.del(o.ch[d], key)
		}
	}
	return o
}

func (t *treap) insert(key int) {
	t.root = t.ins(t.root, key)
}

func (t *treap) delete(key int) {
	t.root = t.del(t.root, key)
}

func (t *treap) min() (min *node) {
	for o := t.root; o != nil; o = o.ch[0] {
		min = o
	}
	return
}

func (t *treap) max() (max *node) {
	for o := t.root; o != nil; o = o.ch[1] {
		max = o
	}
	return
}

func longestSubarray(nums []int, limit int) (ans int) {
	t := &treap{}
	left := 0
	for right, v := range nums {
		t.insert(v)
		for t.max().key-t.min().key > limit {
			t.delete(nums[left])
			left++
		}
		ans = max(ans, right-left+1)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
