package _65_minSwapsCouples

// 利用map，时间复杂度 O(n)
func MinSwapsCouplesV1(row []int) int {
	m := make(map[int]int, len(row))
	for i, v := range row {
		m[v] = i
	}
	count := 0
	for i := 0; i < len(row); i += 2 {
		if row[i]/2 != row[i+1]/2 {
			temp := 0
			if row[i]%2 == 0 {
				temp = row[i] + 1
			} else {
				temp = row[i] - 1
			}
			index := m[temp]
			m[row[i+1]] = index
			m[temp] = i + 1
			row[i+1], row[index] = row[index], row[i+1]
			count++
		}
	}
	return count
}

// 相比于 v1, 用一个数组表示原 row 的 value 的索引关系。
// 因为 row 是序列 0...len(row)-1 的一个全排列，所以可以考虑用一个数组表示
func MinSwapsCouplesV2(row []int) int {
	m := make([]int, len(row))
	for i, v := range row {
		m[v] = i
	}
	count := 0
	for i := 0; i < len(row); i += 2 {
		if row[i]/2 != row[i+1]/2 {
			temp := row[i] ^ 1
			index := m[temp]
			m[row[i+1]] = index
			m[temp] = i + 1
			row[i+1], row[index] = row[index], row[i+1]
			count++
		}
	}
	return count
}
