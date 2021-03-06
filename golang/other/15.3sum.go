package other

import "sort"

/*
要求满足三数之和的所有组合，而且不能重复，去重就是一件很麻烦的事。
一个做法就是保证遍历的顺序是不会产生重复组合的，需要排序
*/
func threeSum(nums []int) [][]int {
	length := len(nums)
	if length < 3 {
		return nil
	}
	var ret [][]int
	sort.Ints(nums)
	// 外层遍历 n1
	for i := 0; i < length-2; i++ {
		if nums[i] > 0 {
			break
		}
		// 连续 n1 相等的情况，这种找出来的很定会重复，跳过
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		// 内层用左右双指针
		left, right := i+1, length-1
		for left < right {
			n2, n3 := nums[left], nums[right]
			sum := nums[i] + n3 + n2
			// 如果和小于 0，则需要 left 指针向右移，右边的数更大
			if sum < 0 {
				left++
				// 反之向左移
			} else if sum > 0 {
				right--
			} else {
				ret = append(ret, []int{nums[i], n2, n3})
				// left 指针继续右移，直到不再重复
				for left < right && nums[left] == n2 {
					left++
				}
				// right 指针继续左移，直到不再重复
				for left < right && nums[right] == n3 {
					right--
				}
			}
		}
	}
	return ret
}
