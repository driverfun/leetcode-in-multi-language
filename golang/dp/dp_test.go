package dp

import (
	"testing"

	"github.com/stretchr/testify/require"
)

func TestLongestPalindromeV2(t *testing.T) {
	tss := []struct {
		word   string
		result string
	}{
		{"ababa", "ababa"},
		{"ab", "a"},
		{"bccd", "cc"},
	}
	for _, ts := range tss {
		require.Equal(t, LongestPalindromeV2(ts.word), ts.result)
	}
}
