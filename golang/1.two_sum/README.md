## two sum

### 1. 问题描述

- 给定一数组如[2,7,11,15]和一目标值9，求数组中两个元素为之和为目标值，这两个元素的下标。

### 2. 解题思路

- 暴力法。两层循环，第一层循环遍历数组取数m，第二遍循环遍历剩下的数组取数n，如果n = traget - m则返回下标即可。
- map法。定义一个以数组元素为key、下标为value的map，遍历数组取数m，查询map中是否有traget - m为key的元素，如果有，返回下边，如果没有，则将数m作为key，下标作为value存入map，继续遍历。

### 3. 性能测试

map法为TwoSum1，暴力法为TwoSum2，详见two_sum.go

- 当数组中元素为1W时，benchmark测试结果对比：

```
➜  1.two_sum git:(feat/golang) ✗ go test -bench .
goos: darwin
goarch: amd64
pkg: github.com/driverfun/leetcode-in-multi-language/golang/1.two_sum
BenchmarkTwoSumV1-12                3000            443212 ns/op
BenchmarkTwoSumV2-12                  50          25868747 ns/op
PASS
ok      github.com/driverfun/leetcode-in-multi-language/golang/1.two_sum        2.721s
```

