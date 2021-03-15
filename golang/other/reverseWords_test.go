package other

import (
	"testing"

	"github.com/stretchr/testify/require"
)

func TestReverseWords(t *testing.T) {
	tss := []struct {
		input  string
		output string
	}{
		{
			input:  "Let's take LeetCode contest",
			output: "s'teL ekat edoCteeL tsetnoc",
		},
	}

	for _, s := range tss {
		require.Equal(t, s.output, ReverseWords(s.input))
	}
}
