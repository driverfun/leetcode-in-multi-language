package slidingwindow

/*
 重点：题意转换。把「最多可以把 K 个 0 变成 1，求仅包含 1 的最长子数组的长度」
转换为 「找出一个最长的子数组，该子数组内最多允许有 K 个 0 」

思路：
	1. 使用 left 和 right 两个指针，分别指向滑动窗口的左右边界。
	2. right 主动右移：right 指针每走一次，如果遇到 A[right] 为 0，则表示滑动窗口中的 0 增加了 1
	3. left 被动右移：如果窗口中的 zeros 大于 k，则 left 右移，遇到 A[left] 为 0，窗口中的 zeros 减 1
	4. 滑动窗口长度的最大值就是所求

《挑战程序设计竞赛》这本书中把滑动窗口叫做「虫取法」，
因为滑动窗口的两个指针移动的过程和虫子爬动的过程非常像：前脚不动，把后脚移动过来；后脚不动，把前脚向前移动

滑动窗口中用到了左右两个指针，它们移动的思路是：
	以右指针作为驱动，拖着左指针向前走。右指针每次只移动一步，而左指针在内部 for 循环中每次可能移动多步。
	右指针是主动前移，探索未知的新区域；左指针是被迫移动，负责寻找满足题意的区间。
*/

func longestOnes(A []int, K int) int {
	var res, left, right, zeros int
	N := len(A)
	// 右指针主动右移
	for ; right < N; right++ {
		if A[right] == 0 {
			// 窗口+1
			zeros++
		}
		for zeros > K {
			if A[left] == 0 {
				// 窗口-1
				zeros--
			}
			// 当窗口大于 k 时左指针被动右移
			left++
		}
		if right-left+1 > res {
			res = right - left + 1
		}
	}
	return res
}
