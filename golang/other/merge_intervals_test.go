package other

import "testing"

func TestMerge(t *testing.T) {
	merge([][]int{
		{1, 3},
		{8, 10},
		{2, 6},
		{15, 18},
	})
}
