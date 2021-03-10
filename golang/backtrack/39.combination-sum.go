package backtrack

func combinationSum(candidates []int, target int) [][]int {
	ans := [][]int{}
	comb := []int{}
	var dfs func(target, idx int)
	dfs = func(target, idx int) {
		if idx == len(candidates) {
			return
		}
		if target == 0 {
			// 或者用 copy
			ans = append(ans, append([]int{}, comb...))
			return
		}
		// 选择当前数
		if target-candidates[idx] >= 0 {
			comb = append(comb, candidates[idx])
			dfs(target-candidates[idx], idx)
			// append 对应着删除一个元素，保证 comb 元素为空
			comb = comb[:len(comb)-1]
		}
		// 跳过当前节点，开始下一个
		dfs(target, idx+1)
	}
	dfs(target, 0)
	return ans
}
