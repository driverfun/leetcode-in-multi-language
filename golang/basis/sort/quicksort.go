package sort

/*
快排的基本思想，选取一个基准值，将比基准值大的放在左边，比基准值小的放在右边，然后递归
具体的做法：每次随机选择基准值，比如选最右边那个，然后左索引选最左，右索引选择右起第二个
左边向右移，右边向左移，遇到左边比基准值大，左边停止移动，右边比基准值小，右边停止移动，左右都停止时，交换左右
左右相遇时停止移动，这个时候如果左比基准值大，交换
注意是否交换基准值，决定了子问题的规模
*/
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
