package sort

/*
从右向左，依次比较，如果顺序相反就交换，一轮就可以把最小 or 最大的冒泡到左端
*/
func BubbleSort(nums []int) {
	for i := 0; i < len(nums); i++ {
		for j := len(nums) - 1; j > i; j-- {
			if nums[j] < nums[j-1] {
				nums[j], nums[j-1] = nums[j-1], nums[j]
			}
		}
	}
}
