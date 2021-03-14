package other

func Exist(board [][]byte, word string) bool {
	length := len(board)
	width := len(board[0])
	// 用一个二维数组保存访问的记录
	vis := make([][]bool, length)
	for i := range vis {
		vis[i] = make([]bool, width)
	}
	var dfs func(i, j int, word string) bool
	dfs = func(i, j int, word string) bool {
		if word == "" {
			return true
		}
		if i >= length || j >= width || i < 0 || j < 0 {
			return false
		}
		if board[i][j] != word[0] || vis[i][j] {
			return false
		}
		vis[i][j] = true
		// 用的巧妙啊，返回的时候依次还原访问
		defer func() {
			vis[i][j] = false
		}()
		word = word[1:]
		return dfs(i+1, j, word) || dfs(i, j+1, word) || dfs(i-1, j, word) || dfs(i, j-1, word)
	}
	for i, b := range board {
		for j := range b {
			if dfs(i, j, word) {
				return true
			}
		}
	}
	return false
}
