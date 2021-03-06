package other

/*
一开始写了一个比较复杂的，感觉复杂度是 n^2 的，还不如先排序呢
*/
func longestConsecutive(nums []int) int {
	if len(nums) == 0 {
		return 0
	}
	m := make(map[int]bool, len(nums))
	for _, num := range nums {
		m[num] = true
	}
	ret := 1
	for _, num := range nums {
		num++
		currentMax := 1
		for ; m[num]; num++ {
			currentMax++
			if currentMax > ret {
				ret = currentMax
			}
		}
	}
	return ret
}

func LongestConsecutiveV2(nums []int) int {
	if len(nums) == 0 {
		return 0
	}
	m := make(map[int]bool, len(nums))
	for _, num := range nums {
		m[num] = true
	}
	ret := 1
	for _, num := range nums {
		// 假设已经遍历了一个序列, x,x+1, x+2, x+3...x+n，那么从 x+1后面的数开始遍历都会重复
		// 相比于上面的例子多了一个很简单的优化，就是排除了 x-1 就可以保证不会重复遍历了
		if m[num-1] {
			continue
		}
		num++
		currentMax := 1
		for ; m[num]; num++ {
			currentMax++
			if currentMax > ret {
				ret = currentMax
			}
		}
	}
	return ret
}

/*
并查集，然后看索引之间的连接关系，对于 value 相差 1 的 index，将它们连起来，需要一个 map 来保存 value 和 index 的关系
*/
func longestConsecutiveV3(nums []int) int {
	uf := NewUnionFindV2(len(nums))
	m := make(map[int]int, len(nums))
	for i, num := range nums {
		if _, ok := m[num]; ok {
			continue
		}
		m[num] = i
		if idx, ok := m[num-1]; ok {
			uf.union(i, idx)
		}
		if idx, ok := m[num+1]; ok {
			uf.union(i, idx)
		}
	}
	res := 0
	for _, size := range uf.size {
		if res < size {
			res = size
		}
	}
	return res
}

type unionFindV2 struct {
	parent []int
	size   []int
}

func NewUnionFindV2(n int) *unionFindV2 {
	uf := &unionFindV2{}
	uf.parent = make([]int, n)
	uf.size = make([]int, n)
	for i := range uf.parent {
		uf.parent[i] = i
	}
	for i := range uf.size {
		uf.size[i] = 1
	}
	return uf
}

func (uf *unionFindV2) find(x int) int {
	if uf.parent[x] != x {
		uf.parent[x] = uf.parent[uf.parent[x]]
	}
	return uf.parent[x]
}

func (uf *unionFindV2) union(x, y int) {
	fx, fy := uf.find(x), uf.find(y)
	if fx == fy {
		return
	}
	if uf.size[fx] > uf.size[fy] {
		uf.parent[fy] = fx
		uf.size[fx] += uf.size[fy]
	} else {
		uf.parent[fx] = fy
		uf.size[fy] += uf.size[fx]
	}
}
