package binary_search

import "sort"

func findDuplicateV1(nums []int) int {
	sort.Ints(nums)
	for i := 1; i < len(nums); i++ {
		if nums[i] == nums[i-1] {
			return nums[i]
		}
	}
	return -1
}

func FindDuplicateV2(nums []int) int {
	m := make(map[int]bool)
	for _, num := range nums {
		if m[num] {
			return num
		}
		m[num] = true
	}
	return -1
}

// 二分搜索
func findDuplicateV3(nums []int) int {
	left, right := 1, len(nums)-1
	ans := 0
	for left <= right {
		mid := (left + right) / 2
		count := 0
		for i := 0; i < len(nums); i++ {
			if nums[i] <= mid {
				count++
			}
		}
		if count <= mid {
			left = mid + 1
		} else {
			right = mid - 1
			ans = mid
		}
	}
	return ans
}
