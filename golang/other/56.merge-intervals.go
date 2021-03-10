package other

import "sort"

/*
先排序，然后判断已经合并的最后一个序列[x1,y1] 的 y1 元素是否大于接下来第二个序列的 y2 元素，然后 y1 = max(y1, y2)
*/
func merge(intervals [][]int) [][]int {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})
	ans := [][]int{}
	for _, interval := range intervals {
		length := len(ans)
		if length == 0 {
			ans = append(ans, interval)
			continue
		}
		if ans[length-1][1] >= interval[0] {
			ans[length-1][1] = max(ans[length-1][1], interval[1])
		} else {
			ans = append(ans, interval)
		}
	}
	return ans
}
