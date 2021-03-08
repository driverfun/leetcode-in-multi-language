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
