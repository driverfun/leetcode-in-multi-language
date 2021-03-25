package other

func searchRange(nums []int, target int) []int {
	if len(nums) == 0 {
		return []int{-1, -1}
	}
	left, right := 0, len(nums)-1
	index := -1
	for left <= right {
		mid := left + (right-left)/2
		if nums[mid] == target {
			index = mid
			break
		}
		if target > nums[mid] {
			left = mid + 1
		}
		if target < nums[mid] {
			right = mid - 1
		}

	}
	if index == -1 {
		return []int{-1, -1}
	}
	i, j := index, index
	for i >= 0 && nums[i] == nums[index] {
		i--
	}
	for j <= len(nums)-1 && nums[j] == nums[index] {
		j++
	}
	return []int{i + 1, j - 1}
}
