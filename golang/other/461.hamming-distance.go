package other

/*
求两个数字对应二进制位不同的位置的数目,只需要求两个数字的异或的二进制位个数即可
*/
func hammingDistance(x int, y int) int {
	n := x ^ y
	ret := 0
	for n > 0 {
		if n%2 == 1 {
			ret++
		}
		n /= 2
	}
	return ret
}
