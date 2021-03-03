##并查集基本用途
- 查联通数量、节点之间的联通关系等

## 基本思想
- `finx(x)` 查找x的根节点.
- `union(x, y)` 合并两个集合.
- 优化：
1. find 的过程中进行 path compression
![image]()
![image]()
2. union 的过程将 size 小的集合的根节点指向 size 较大的根节点

## 代码结构
```
type unionFind struct {
    // 每一个节点的父节点
	parent   []int
    // 每一个根节点下连接的节点数量
	size     []int
    // union 的数量，有些题目用不到
	setCount int
}

// 初始化 unionFind
func newUnionFind(n int) *unionFind {
	parent := make([]int, n)
	size := make([]int, n)
	for i := range parent {
        // 每一个节点的父节点初始化时都指向自己
		parent[i] = i
		size[i] = 1
	}
	return &unionFind{parent: parent, size: size, setCount: n}
}

// find 并做 path compression
// 如果x 的父节点不等于x，则找父节点的父节点，并修改父节点的值，一直指向根节点，使得父节点更加扁平
// 最终使得并集内所有的节点都指向根节点
func (uf *unionFind) find(x int) int {
	if x != uf.parent[x] {
		uf.parent[x] = uf.find(uf.parent[x])
	}
    // 返回 x 的父节点
	return uf.parent[x]
}
// 
func (uf *unionFind) union(x, y int) bool {
	fx, fy := uf.find(x), uf.find(y)
    // 如果查到的根节点相同，说明他们是联通的
	if fx == fy {
		return true
	}
    // 将 size 较小的并集合并到 size 较大的并集
	if uf.size[fx] < uf.size[fy] {
		fx, fy = fy, fx
	}
    // 将 fy 指向 fx 即可
	uf.parent[fy] = fx
	uf.size[fx] += uf.size[fy]
	uf.setCount--
	return false
}
```


## 相关题型

- LeetCode 684 冗余连接
- LeetCode 547 查省份数量