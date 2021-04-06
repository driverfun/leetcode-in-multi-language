package other

/*
合并两个有序数组，原地合并
先把 nums1 的部分 copy 到后边，然后再跟 nums2 比较
*/
func MergeV2(nums1 []int, m int, nums2 []int, n int) {
	if n == 0 {
		return
	}
	if m == 0 {
		copy(nums1, nums2)
		return
	}
	copy(nums1[n:], nums1[:m])
	j := 0
	k := 0
	i := n
	for i < m+n && j < n {
		if nums1[i] <= nums2[j] {
			nums1[k] = nums1[i]
			i, k = i+1, k+1
		} else {
			nums1[k] = nums2[j]
			j, k = j+1, k+1
		}
	}
	copy(nums1[k:], nums2[j:])
}
