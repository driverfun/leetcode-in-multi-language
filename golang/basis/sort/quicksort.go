package sort

func QuickSort(nums []int) {
	if len(nums) < 2 {
		return
	}
	pivot := nums[len(nums)-1]
	left, right := 0, len(nums)-2
	for left != right {
		if nums[left] < pivot {
			left++
		} else if nums[right] > pivot {
			right--
		} else {
			nums[left], nums[right] = nums[right], nums[left]
		}
	}
	if nums[left] > pivot {
		nums[left], nums[len(nums)-1] = nums[len(nums)-1], nums[left]
		QuickSort(nums[:left])
		QuickSort(nums[right+1:])
	} else {
		QuickSort(nums[:left+1])
		QuickSort(nums[right+1:])
	}
}
