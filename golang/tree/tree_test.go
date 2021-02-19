package _tree

import (
	"testing"

	"github.com/stretchr/testify/require"
)

func TestIsValidBSTV2(t *testing.T) {
	tn1 := &TreeNode{Val: 1}
	tn3 := &TreeNode{Val: 3}
	tn6 := &TreeNode{Val: 6}
	tn4 := &TreeNode{Val: 4, Left: tn3, Right: tn6}
	tn5 := &TreeNode{Val: 5, Left: tn1, Right: tn4}
	IsValidBSTV2(tn5)
	require.False(t, IsValidBSTV2(tn5))
}

func TestZigzagLevelOrder(t *testing.T) {
	tn9 := &TreeNode{Val: 9}
	tn15 := &TreeNode{Val: 15}
	tn7 := &TreeNode{Val: 7}
	tn20 := &TreeNode{Val: 20, Left: tn15, Right: tn7}
	tn3 := &TreeNode{Val: 3, Left: tn9, Right: tn20}
	ZigzagLevelOrder(tn3)
}
