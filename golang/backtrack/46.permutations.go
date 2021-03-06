package backtrack

/*
全排列，回溯算法，在一颗决策树上不断做选择
*/
func permute(nums []int) [][]int {
	ret := make([][]int, 0, len(nums))
	var backTrack func(nums []int, track []int)
	backTrack = func(nums []int, track []int) {
		// 当路径长度为 nums 长度，即为要求的结果
		if len(nums) == len(track) {
			// 务必 copy 一份再 append
			tmp := make([]int, len(track))
			copy(tmp, track)
			ret = append(ret, tmp)
		}
		m := make(map[int]bool, len(track))
		for _, i := range track {
			m[i] = true
		}
		for _, num := range nums {
			if m[num] {
				continue
			}
			// 做选择
			track = append(track, num)
			// 回溯
			backTrack(nums, track)
			// 去掉选择
			track = track[:len(track)-1]
		}
	}
	backTrack(nums, []int{})
	return ret
}
