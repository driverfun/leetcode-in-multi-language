package other

// use map. O(n)
func TwoSumV1(nums []int, target int) []int {
	numMap := make(map[int]int, len(nums))
	for i, v := range nums {
		if j, ok := numMap[target-v]; ok {
			return []int{j, i}
		} else {
			numMap[v] = i
		}
	}
	return []int{-1, -2}
}

// scan in circle violently
func TwoSumV2(nums []int, target int) []int {
	n := len(nums)
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			if nums[i]+nums[j] == target {
				return []int{i, j}
			}
		}
	}
	return []int{-1, -2}
}
