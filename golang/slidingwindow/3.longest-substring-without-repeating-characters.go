package slidingwindow

/*
	滑动窗口求最长连续子串
*/
func lengthOfLongestSubstring(s string) int {
	if s == "" {
		return 0
	}
	left, right := 0, 0
	length := len(s)
	m := make(map[byte]bool)
	result := 0
	for ; right < length; right++ {
		for m[s[right]] {
			delete(m, s[left])
			left++
		}
		m[s[right]] = true
		if right-left+1 > result {
			result = right - left + 1
		}
	}
	return result
}
