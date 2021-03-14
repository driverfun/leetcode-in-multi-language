package dp

/*
常规 dp，没什么太多好说的
*/
func minPathSum(grid [][]int) int {
	m := len(grid)
	n := len(grid[0])
	dp := make([][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([]int, n)
		for j := 0; j < n; j++ {
			if i == 0 {
				if j == 0 {
					dp[i][j] = grid[i][j]
				} else {
					dp[i][j] = grid[i][j] + dp[i][j-1]
				}
			} else {
				if j == 0 {
					dp[i][j] = grid[i][j] + dp[i-1][j]
				} else {
					dp[i][j] = min(dp[i][j-1], dp[i-1][j]) + grid[i][j]
				}
			}
		}
	}
	return dp[m-1][n-1]
}
