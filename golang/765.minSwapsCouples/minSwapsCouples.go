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

// 广度优先搜索
func MinSwapsCouplesV3(row []int) int {
	n := len(row)
	graph := make([][]int, n/2)
	for i := 0; i < n; i += 2 {
		l, r := row[i]/2, row[i+1]/2
		if l != r {
			graph[l] = append(graph[l], r)
			graph[r] = append(graph[r], l)
		}
	}
	res := 0
	vis := make([]bool, n/2)
	for i, vs := range vis {
		if !vs {
			vis[i] = true
			count := 0
			q := []int{i}
			for len(q) > 0 {
				count++
				v := q[0]
				q = q[1:]
				for _, w := range graph[v] {
					if !vis[w] {
						vis[w] = true
						q = append(q, w)
					}
				}
			}
			res += count - 1
		}
	}
	return res
}

// 并查集
type unionFind struct {
	parent, size []int
	setCount     int // 当前连通分量数目
}

func newUnionFind(n int) *unionFind {
	parent := make([]int, n)
	size := make([]int, n)
	for i := range parent {
		// 每个 parent 都是自己，每个 set 的 size 都是 1，初始 set 个数 n
		parent[i] = i
		size[i] = 1
	}
	return &unionFind{parent: parent, size: size, setCount: n}
}

func (uf *unionFind) find(x int) int {
	if uf.parent[x] != x {
		// 1. path compression, 所有父都指向根节点
		uf.parent[x] = uf.find(uf.parent[x])
	}
	return uf.parent[x]
}

func (uf *unionFind) union(x, y int) {
	fx, fy := uf.find(x), uf.find(y)
	if fx == fy {
		return
	}
	// 2. union by size 把 size 小的树 union 到 rank 比较大的树
	if uf.size[x] < uf.size[y] {
		fx, fy = fy, fx
	}
	uf.size[fx] += uf.size[fy]
	uf.parent[fy] = fx
	uf.setCount--
}

func MinSwapsCouplesV4(row []int) int {
	n := len(row)
	uf := newUnionFind(n / 2)
	for i := 0; i < n; i += 2 {
		uf.union(row[i]/2, row[i+1]/2)
	}
	return n/2 - uf.setCount
}
