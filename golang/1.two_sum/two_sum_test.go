package two_sum

import (
	"github.com/stretchr/testify/require"
	"math/rand"
	"testing"
)

var (
	Nums = []int{2, 7, 9, 11, 15}
	N    = 10000
)

func TestTwoSumV1(t *testing.T) {
	res := TwoSumV1(Nums, 9)
	require.Equal(t, res, []int{0, 1})
	res = TwoSumV1(Nums, 19)
	require.Equal(t, res, []int{-1, -2})
}

func TestTwoSumV2(t *testing.T) {
	res := TwoSumV2(Nums, 9)
	require.Equal(t, res, []int{0, 1})
	res = TwoSumV2(Nums, 19)
	require.Equal(t, res, []int{-1, -2})
}

func BenchmarkTwoSumV1(b *testing.B) {
	nums := make([]int, 0, N)
	for i := 0; i < N; i++ {
		nums = append(nums, rand.Int())
	}
	nums = append(nums, 2, 7)
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		TwoSumV1(nums, 9)
	}
}

func BenchmarkTwoSumV2(b *testing.B) {
	nums := make([]int, 0, N)
	for i := 0; i < N; i++ {
		nums = append(nums, rand.Int())
	}
	nums = append(nums, 2, 7)
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		TwoSumV2(nums, 9)
	}
}
