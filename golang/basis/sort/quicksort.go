package sort

import "math/rand"

/*
快排的基本思想，选取一个基准值，将比基准值大的放在左边，比基准值小的放在右边，然后递归
具体的做法：每次随机选择基准值，比如选最右边那个，然后左索引选最左，右索引选择右起第二个
左边向右移，右边向左移，遇到左边比基准值大，左边停止移动，右边比基准值小，右边停止移动，左右都停止时，交换左右
左右相遇时停止移动，这个时候如果左比基准值大，交换
注意是否交换基准值，决定了子问题的规模
*/
func QuickSort(nums []int) {
	length := len(nums)
	if length < 2 {
		return
	}
	// 增加一个随机 pivot 的逻辑，避免退化
	// 随机选个值做基准，然后把它和最右侧元素交换
	randomPivot := rand.Intn(length - 1)
	nums[length-1], nums[randomPivot] = nums[randomPivot], nums[length-1]
	pivot := nums[length-1]
	left, right := 0, length-2
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
		nums[left], nums[length-1] = nums[length-1], nums[left]
		// 如果交换了基准值，则不需要对基准值进行排序
		QuickSort(nums[:left])
		QuickSort(nums[right+1:])
	} else {
		QuickSort(nums[:left+1])
		QuickSort(nums[right+1:])
	}
}
