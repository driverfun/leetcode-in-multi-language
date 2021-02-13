package median_of_two_sorted_arrays

import (
	"testing"

	"github.com/stretchr/testify/require"
)

func TestFindMedianSortedArrays(t *testing.T) {
	ts := []struct {
		nums1 []int
		nums2 []int
		ret   float64
	}{
		{
			nums1: []int{3},
			nums2: []int{-2, -1},
			ret:   -1.0,
		},
	}
	for _, s := range ts {
		require.Equal(t, FindMedianSortedArrays(s.nums1, s.nums2), s.ret)
	}
}
