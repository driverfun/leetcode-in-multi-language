package unionfind

func findCircleNum(isConnected [][]int) int {
	uf := newUnionFind(len(isConnected))
	for i, row := range isConnected {
		for j, connected := range row {
			if connected == 1 && i != j {
				uf.union(i, j)
			}
		}
	}
	return uf.setCount
}
