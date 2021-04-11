package other

/*
在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字
*/

/*
题解：很容易想到用 map，但是如果对内存空间有要求怎么办？
看到体重数字都在 0～n-1 范围，因此一个萝卜一个坑，有的萝卜占了不止一个坑，那肯定就是重复的
nums[i] == nums[nums[i]] 就是占了两个坑的
*/

func findRepeatNumber(nums []int) int {
	for i := range nums {
		for i != nums[i] {
			if nums[i] == nums[nums[i]] {
				return nums[i]
			}
			nums[i], nums[nums[i]] = nums[nums[i]], nums[i]
		}
	}
	return -1
}
