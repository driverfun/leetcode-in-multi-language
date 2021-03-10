package dp

func lengthOfLIS(nums []int) int {
	length := len(nums)
	if length < 2 {
		return length
	}
	dp := make([]int, length)
	result := 1
	for i := 0; i < length; i++ {
		dp[i] = 1
		for j := 0; j < i; j++ {
			if nums[j] < nums[i] {
				dp[i] = max(dp[i], dp[j]+1)
			}
		}
		result = max(result, dp[i])
	}
	return result
}
