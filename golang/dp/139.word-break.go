package dp

/*
动态规划
dc[i] = dc[j] && wordSet[s[j:i]]
假如wordDict=["apple", "pen", "code"],s = "applepencode";
        dp[8] = dp[5] + check("pen")
dp["onetwothreefour"] = dp["onetwothree"这一段] && 判断一下"four"
dp["onetwothreefour"] = dp["onetwothre"这一段] && 判断一下"efour"
*/
func wordBreak(s string, wordDict []string) bool {
	wordSet := make(map[string]bool, len(wordDict))
	for _, wd := range wordDict {
		wordSet[wd] = true
	}
	length := len(s)
	dc := make([]bool, length+1)
	dc[0] = true
	for i := 1; i <= length; i++ {
		for j := 0; j < i; j++ {
			if dc[j] && wordSet[s[j:i]] {
				dc[i] = true
				break
			}
		}
	}
	return dc[length]
}
