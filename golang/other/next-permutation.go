package other

func NextPermutation(nums []int) {
	if len(nums) < 2 {
		return
	}
	i := len(nums) - 2
	// 找到右边右数升序的序列
	for i >= 0 && nums[i] >= nums[i+1] {
		i--
	}
	// 找到刚好大于 nums[i] 的
	if i >= 0 {
		j := len(nums) - 1
		for nums[i] >= nums[j] && j >= 0 {
			j--
		}
		// 交换
		nums[i], nums[j] = nums[j], nums[i]
	}
	// 反转
	reverse(nums[i+1:])
}

func reverse(nums []int) {
	for i, j := 0, len(nums)-1; i < j; i, j = i+1, j-1 {
		nums[i], nums[j] = nums[j], nums[i]
	}
}
