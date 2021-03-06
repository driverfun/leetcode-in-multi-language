package other

func buildArray(target []int, n int) []string {
	push, pop := "Push", "Pop"
	i := 1
	ret := make([]string, 0, len(target))
	cur := 0
	for cur < len(target) {
		ret = append(ret, push)
		if target[cur] != i {
			ret = append(ret, pop)
		} else {
			cur++
		}
		i++
	}
	return ret
}
