package sort

import (
	"math/rand"
	"testing"
	"time"

	"github.com/stretchr/testify/require"
)

func randomInts(length int) []int {
	rand.Seed(time.Now().UnixNano())
	return rand.Perm(length)
}

func TestQuickSort(t *testing.T) {
	nums := randomInts(1000)
	QuickSort(nums)
	for i := 0; i < len(nums)-1; i++ {
		require.Greater(t, nums[i+1], nums[i])
	}
}

func TestInsertSort(t *testing.T) {
	nums := randomInts(1000)
	InsertSort(nums)
	for i := 0; i < len(nums)-1; i++ {
		require.Greater(t, nums[i+1], nums[i])
	}
}

func TestBubbleSort(t *testing.T) {
	nums := randomInts(1000)
	BubbleSort(nums)
	for i := 0; i < len(nums)-1; i++ {
		require.Greater(t, nums[i+1], nums[i])
	}
}

func TestSelectSort(t *testing.T) {
	nums := randomInts(1000)
	SelectSort(nums)
	for i := 0; i < len(nums)-1; i++ {
		require.Greater(t, nums[i+1], nums[i])
	}
}

func TestMergeSort(t *testing.T) {
	nums := randomInts(1000)
	nums = MergeSort(nums)
	for i := 0; i < len(nums)-1; i++ {
		require.Greater(t, nums[i+1], nums[i])
	}
}
