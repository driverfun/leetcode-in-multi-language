package other

/*
总体思路，就是 "快速幂算法", x^17 = x^8*x^8*x, x^8 = x^4 *x^4，
然后有迭代和递归两种方法
但是需要注意边界条件，有点复杂
*/
func myPow(x float64, n int) float64 {
	if n >= 0 {
		return quickMult(x, n)
	} else {
		return 1.0 / quickMult(x, -n)
	}
}

func quickMult(x float64, n int) float64 {
	if n == 0 {
		return 1.0
	}
	y := quickMult(x, n/2)
	if n%2 == 0 {
		return y * y
	} else {
		return y * y * x
	}
}

/*
迭代法
*/

func myPowV2(x float64, n int) float64 {
	if n >= 0 {
		return quickMultV2(x, n)
	} else {
		return 1.0 / quickMultV2(x, -n)
	}
}

func quickMultV2(x float64, n int) float64 {
	ans := 1.0
	for n > 0 {
		if n%2 != 0 {
			ans = ans * x
		}
		x *= x
		n /= 2
	}
	return ans
}
