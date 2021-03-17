package dp

// dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j+nums[i]]
func findTargetSumWays(nums []int, S int) int {
	dp := make([]int, 2001)
	// 1000 只是用来区分正负
	dp[1000+nums[0]] += 1
	dp[1000-nums[0]] += 1
	for i := 1; i < len(nums); i++ {
		next := make([]int, 2001)
		for sum := -1000; sum <= 1000; sum++ {
			if dp[sum+1000] > 0 {
				next[sum+1000+nums[i]] += dp[sum+1000]
				next[sum+1000-nums[i]] += dp[sum+1000]
			}
		}
		dp = next
	}
	if S > 1000 {
		return 0
	}
	return dp[S+1000]
}
