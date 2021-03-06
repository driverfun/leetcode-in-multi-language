package other

func FindDisappearedNumbers(nums []int) []int {
	n := len(nums)
	for _, v := range nums {
		nums[(v-1)%n] += n
	}
	var ret []int
	for i, v := range nums {
		if v <= n {
			ret = append(ret, i+1)
		}
	}
	return ret
}
