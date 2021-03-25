package sort

// 归并排序，就是二分，分到最后，然后 merge
func MergeSort(args []int) []int {
	if len(args) < 2 {
		return args
	}
	mid := len(args) / 2
	left := MergeSort(args[:mid])
	right := MergeSort(args[mid:])
	return merge(left, right)
}

// 合并两个有序数组
func merge(left, right []int) []int {
	l, r := 0, 0
	res := make([]int, 0, len(left)+len(right))
	for l < len(left) && r < len(right) {
		if left[l] < right[r] {
			res = append(res, left[l])
			l++
		} else {
			res = append(res, right[r])
			r++
		}
	}
	res = append(res, left[l:]...)
	res = append(res, right[r:]...)
	return res
}
