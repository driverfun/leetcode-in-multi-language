package other

import "testing"

func TestMergeV2(t *testing.T) {
	MergeV2([]int{4, 0, 0, 0, 0, 0}, 1, []int{1, 2, 3, 5, 6}, 5)
}
