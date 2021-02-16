package _61_arrayPairSum

import (
	"container/heap"
	"sort"
)

// 排序
func arrayPairSumV1(nums []int) int {
	sort.Ints(nums)
	ret := 0
	for i := 0; i < len(nums); i += 2 {
		ret += nums[i]
	}
	return ret
}

type Heap []int // 定义一个类型

func (h Heap) Len() int { return len(h) } // 绑定len方法,返回长度
func (h Heap) Less(i, j int) bool { // 绑定less方法
	return h[i] < h[j] // 如果h[i]<h[j]生成的就是小根堆，如果h[i]>h[j]生成的就是大根堆
}
func (h Heap) Swap(i, j int) { // 绑定swap方法，交换两个元素位置
	h[i], h[j] = h[j], h[i]
}

func (h *Heap) Pop() interface{} { // 绑定pop方法，从最后拿出一个元素并返回
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func (h *Heap) Push(x interface{}) { // 绑定push方法，插入新元素
	*h = append(*h, x.(int))
}

func ArrayPairSumV2(nums []int) int {
	h := (*Heap)(&nums)
	heap.Init(h)
	ret := 0
	for h.Len() != 0 {
		i := heap.Pop(h).(int)
		ret += i
		heap.Pop(h)
	}
	return ret
}
