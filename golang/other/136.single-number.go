package other

/*
用 map，遍历，如果能从 map 中找到这个数，则删除，如果不能，则存入
*/

func singleNumber(nums []int) int {
	m := make(map[int]bool)
	for _, num := range nums {
		if m[num] {
			delete(m, num)
		} else {
			m[num] = true
		}
	}
	for num := range m {
		return num
	}
	return -1
}

/*
实在想不到还可以用异或，受教了
*/
func singleNumberV2(nums []int) int {
	ans := 0
	for _, num := range nums {
		ans ^= num
	}
	return ans
}
