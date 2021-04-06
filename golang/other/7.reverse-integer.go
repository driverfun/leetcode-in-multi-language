package other

import "math"

/*
题目描述：给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
如果反转后整数超过 32 位的有符号整数的范围 [−2^31, 2^31 − 1] ，就返回 0
*/

/*
题解：先写常规，再考虑边界条件.
ans*10+x%10 >= max || anx*10+x%10<= min 时就遇到了边界条件，
可以转化成 ans > (math.MaxInt32-remainder)/10 || ans < (math.MinInt32-remainder)/10
当 x<0 时，每次求余数得到的也是负数，ans 也为负数
*/
func reverseInteger(x int) int {
	ans := 0
	for x != 0 {
		remainder := x % 10
		if ans > (math.MaxInt32-remainder)/10 || ans < (math.MinInt32-remainder)/10 {
			return 0
		}
		ans = ans*10 + remainder
		x = x / 10
	}
	return ans
}
