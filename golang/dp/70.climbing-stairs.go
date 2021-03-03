package dp

/*
f(n) = f(n-1) + f(n-2), 每次只需记住 f(n-1) 和 f(n-2) 两个变量即可
*/
func climbStairs(n int) int {
	if n <= 3 {
		return n
	}
	first, second, ret := 2, 3, 0
	for i := 4; i <= n; i++ {
		ret = first + second
		first, second = second, ret
	}
	return ret
}
