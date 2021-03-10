package dp

/*
dp，长度为 7 时，根节点可以为1 ~ 7
当根节点为 3 时，组合数为 g(2)*g(4)
因此 7 的组合数为 g(7) = g(0)*g(6) + g(1)*g(5) +...+ g(6)*g(0)
*/
func numTrees(n int) int {
	g := make([]int, n+1)
	g[0], g[1] = 1, 1
	for i := 2; i <= n; i++ {
		for j := 1; j <= n; j++ {
			g[i] += g[j-1] * g[i-j]
		}
	}
	return g[n]
}
