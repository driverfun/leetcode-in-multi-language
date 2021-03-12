package other

import "sort"

func findUnsortedSubarray(nums []int) int {
	if len(nums) == 1 {
		return 0
	}
	a := make([]int, len(nums))
	copy(a, nums)
	sort.Ints(nums)
	i, j := 0, len(nums)-1
	for i <= j {
		if i == j {
			return 0
		}
		if a[i] == nums[i] {
			i++
		}
		if a[j] == nums[j] {
			j--
		}
		if a[i] != nums[i] && a[j] != nums[j] {
			break
		}
	}
	return j - i + 1
}
