package _004_longestOnes

func longestOnes(A []int, K int) int {
	var res, left, right, zeros int
	N := len(A)
	for ; right < N; right++ {
		if A[right] == 0 {
			zeros++
		}
		for zeros > K {
			if A[left] == 0 {
				zeros--
			}
			left++
		}
		if right-left+1 > res {
			res = right - left + 1
		}
	}
	return res
}
