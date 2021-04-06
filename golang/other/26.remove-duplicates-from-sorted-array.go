package other

/*
给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度
*/

/*
对于一个有序数组，可以使用快慢指针 i，j，当 nums[i] 和 nums[j] 不同时，就把 nums[j] 的值复制到 i+1 上去
*/
func removeDuplicates(nums []int) int {
	i := 0
	for j := 1; j < len(nums); j++ {
		if nums[i] != nums[j] {
			nums[i+1] = nums[j]
			i++
		}
	}
	return i + 1
}
