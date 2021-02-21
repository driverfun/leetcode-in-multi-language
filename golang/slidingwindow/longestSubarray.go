package slidingwindow

func LongestSubarray(nums []int, limit int) int {
	var ans int
	var minQ, maxQ []int
	left := 0
	for right, v := range nums {
		// 保证 minQ 单调递增
		for len(minQ) > 0 && minQ[len(minQ)-1] > v {
			minQ = minQ[:len(minQ)-1]
		}
		// maxQ 单调递减
		minQ = append(minQ, v)
		for len(maxQ) > 0 && maxQ[len(maxQ)-1] < v {
			maxQ = maxQ[:len(maxQ)-1]
		}
		maxQ = append(maxQ, v)
		// 首尾差值即为窗口大小
		for len(minQ) > 0 && len(maxQ) > 0 && maxQ[0]-minQ[0] > limit {
			/*
				调整 left 使得窗口满足条件, 如果遇到跟最小值或最大值相等的元素，则将其删去，这样窗口的大小就发生了变化
			*/
			if nums[left] == minQ[0] {
				minQ = minQ[1:]
			}
			if nums[left] == maxQ[0] {
				maxQ = maxQ[1:]
			}
			left++
		}
		ans = max(ans, right-left+1)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
