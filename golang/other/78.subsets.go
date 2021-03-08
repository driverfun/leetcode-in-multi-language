package other

func subsets(nums []int) [][]int {
	length := len(nums)
	if length == 0 {
		return nil
	}
	var res [][]int
	for _, num := range nums {
		resLength := len(res)
		for i := 0; i < resLength; i++ {
			res = append(res, append([]int{num}, res[i]...))
		}
		res = append(res, []int{num})
	}
	return append(res, []int{})
}

/*
位运算，列举[0, 2^n-1] 内的所有数字，如果对应的二进制位为 1，则将该数字放入子集
*/
func subsetsV2(nums []int) [][]int {
	n := len(nums)
	var ans [][]int
	for mask := 0; mask < 1<<n; mask++ {
		var set []int
		for i, num := range nums {
			// 判断二进制位是否为 1
			if mask>>i&1 > 0 {
				set = append(set, num)
			}
		}
		ans = append(ans, set)
	}
	return ans
}
