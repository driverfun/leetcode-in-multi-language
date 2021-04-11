package binary_search

/*
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性
每行的元素从左到右升序排列
每列的元素从上到下升序排列
*/

/*
题解: 能想到两种方法，1. 二分搜索 2. 剪枝搜索
剪枝搜索，先指向 matrix 的左下端，遇到比 target 小的就向右搜索，遇到比 target 打的就向上搜索
*/

func searchMatrix(matrix [][]int, target int) bool {
	m, n := len(matrix), len(matrix[0])
	i, j := m-1, 0
	for i >= 0 && j <= n-1 {
		if matrix[i][j] == target {
			return true
		}
		if matrix[i][j] > target {
			i--
		} else {
			j++
		}
	}
	return false
}
