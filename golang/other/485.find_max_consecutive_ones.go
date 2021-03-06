package other

func findMaxConsecutiveOnes(nums []int) int {
	x, y := 0, 0
	for _, num := range nums {
		if num != 0 {
			y++
		} else {
			x = max(x, y)
			y = 0
		}
	}
	return max(x, y)
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
