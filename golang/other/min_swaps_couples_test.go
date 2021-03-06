package other

import (
	"testing"

	"github.com/stretchr/testify/require"
)

func TestMinSwapsCouplesV1(t *testing.T) {
	var tss []struct {
		array []int
		ret   int
	}
	initTss := func() {
		tss = []struct {
			array []int
			ret   int
		}{
			{
				array: []int{2, 0, 5, 4, 3, 1},
				ret:   1,
			},
			{
				array: []int{0, 2, 1, 3},
				ret:   1,
			},
			{
				array: []int{3, 2, 0, 1},
				ret:   0,
			},
		}
	}
	initTss()
	for _, ts := range tss {
		require.Equal(t, ts.ret, MinSwapsCouplesV1(ts.array))
	}
	initTss()
	for _, ts := range tss {
		require.Equal(t, ts.ret, MinSwapsCouplesV2(ts.array))
	}
	initTss()
	for _, ts := range tss {
		require.Equal(t, ts.ret, MinSwapsCouplesV3(ts.array))
	}
	initTss()
	for _, ts := range tss {
		require.Equal(t, ts.ret, MinSwapsCouplesV4(ts.array))
	}
}
