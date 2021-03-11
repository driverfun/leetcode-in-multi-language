package slidingwindow

import (
	"container/heap"
	"math"
	"sort"
)

/*
取每一个窗口的最大值，耗时很大，显然是不行的
*/
func maxSlidingWindow(nums []int, k int) []int {
	if k == 1 {
		return nums
	}
	res := []int{}
	if len(nums) <= k {
		return append(res, maxInSlice(nums))
	}
	for left, right := 0, k; right <= len(nums); left, right = left+1, right+1 {
		res = append(res, maxInSlice(nums[left:right]))
	}
	return res
}

func maxInSlice(nums []int) int {
	max := math.MinInt32
	for _, num := range nums {
		if num > max {
			max = num
		}
	}
	return max
}

var a []int

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return a[h.IntSlice[i]] > a[h.IntSlice[j]] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{}   { a := h.IntSlice; v := a[len(a)-1]; h.IntSlice = a[:len(a)-1]; return v }

func maxSlidingWindowV2(nums []int, k int) []int {
	a = nums
	q := &hp{make([]int, k)}
	for i := 0; i < k; i++ {
		q.IntSlice[i] = i
	}
	heap.Init(q)

	n := len(nums)
	ans := make([]int, 1, n-k+1)
	ans[0] = nums[q.IntSlice[0]]
	for i := k; i < n; i++ {
		heap.Push(q, i)
		for q.IntSlice[0] <= i-k {
			heap.Pop(q)
		}
		ans = append(ans, nums[q.IntSlice[0]])
	}
	return ans
}

/*
用单调队列, 武器库里面应该多一个单调队列了
*/
func maxSlidingWindowV3(nums []int, k int) []int {
	var deQueue []int
	var ans []int
	for i := 0; i < len(nums); i++ {
		// 单调递减队列
		for len(deQueue) != 0 && nums[deQueue[len(deQueue)-1]] <= nums[i] {
			//弹出队尾的值
			deQueue = deQueue[:len(deQueue)-1]
		}
		deQueue = append(deQueue, i)
		if deQueue[0] <= i-k {
			deQueue = deQueue[1:]
		}
		if i+1 >= k {
			ans = append(ans, nums[deQueue[0]])
		}
	}
	return ans
}
