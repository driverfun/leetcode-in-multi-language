package unionfind

type unionFind struct {
	parent   []int
	size     []int
	setCount int
}

func newUnionFind(n int) *unionFind {
	parent := make([]int, n)
	size := make([]int, n)
	for i := range parent {
		parent[i] = i
		size[i] = 1
	}
	return &unionFind{parent: parent, size: size, setCount: n}
}

func (uf *unionFind) find(x int) int {
	if uf.parent[x] != x {
		uf.parent[x] = uf.find(uf.parent[x])
	}
	return uf.parent[x]
}

func (uf *unionFind) union(x, y int) bool {
	fx, fy := uf.find(x), uf.find(y)
	if fx == fy {
		return true
	}
	if uf.size[fx] < uf.size[y] {
		fx, fy = fy, fx
	}
	uf.parent[fy] = fx
	uf.size[fx] += uf.size[fy]
	uf.setCount--
	return false
}

func FindRedundantConnection(edges [][]int) []int {
	uf := newUnionFind(len(edges))
	for _, edge := range edges {
		if uf.union(edge[0]-1, edge[1]-1) {
			return edge
		}
	}
	return nil
}
