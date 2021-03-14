package other

import "testing"

func TestExist(t *testing.T) {
	Exist([][]byte{
		{'A', 'B', 'C', 'E'},
		{'S', 'F', 'E', 'S'},
		{'A', 'D', 'E', 'E'},
	}, "ABCESEEEFS")
}
