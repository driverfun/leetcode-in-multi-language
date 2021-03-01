package dp

import "math"

/*
贪心
*/
func maxSubArrayV1(nums []int) int {
	maxSum, preSum := math.MinInt32, math.MinInt32
	for _, num := range nums {
		if preSum < 0 {
			preSum = num
		} else {
			preSum += num
		}
		if maxSum < preSum {
			maxSum = preSum
		}
	}
	return maxSum
}

/*
动态规划, f(i)=max{f(i−1)+nums[i],nums[i]}，把 nums[i]改成f(i)
*/
func maxSubArrayV2(nums []int) int {
	max := nums[0]
	for i := 1; i < len(nums); i++ {
		if nums[i] < nums[i]+nums[i-1] {
			nums[i] = nums[i] + nums[i-1]
		}
		if max < nums[i] {
			max = nums[i]
		}
	}
	return max
}
