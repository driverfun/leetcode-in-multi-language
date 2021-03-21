package other

// 时间复杂度为O(n*sizeof(integer))
func countBits(num int) []int {
	ans := make([]int, 0, num+1)
	for i := 0; i <= num; i++ {
		ans = append(ans, countBit(i))
	}
	return ans
}

func countBit(i int) int {
	count := 0
	for i != 0 {
		count += i % 2
		i /= 2
	}
	return count
}

// 动态规划 + 最高有效位
/*
对于正整数 x，如果可以知道最大的正整数 y，使得 y≤x 且 y 是 2 的整数次幂，则 y 的二进制中只有最高位是 1，其余都是 0，因此 y&(y-1) = 0
*/
func countBitsV2(num int) []int {
	bits := make([]int, num+1)
	highBit := 0
	for i := 1; i <= num; i++ {
		// 判断 y 是 2 的整数次幂
		if i&(i-1) == 0 {
			highBit = i
		}
		// i 比 i - highBit 的 1 比特数多 1
		// 比方说， 7 为 0111 比 7-4 = 3 0011 的 比特数多 1，就多在 3 上了
		// 其实是 i = i-highBit + highBit
		bits[i] = bits[i-highBit] + 1
	}
	return bits
}
