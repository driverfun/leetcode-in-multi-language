package dp

/*
暴力解法，穷举，从长到短，依次判断 cbbd、cbb|bbd、cb|bb|bd、c|b|b|d
遇到第一个回文字符串即可返回
*/
func longestPalindromeV1(s string) string {
	length := len(s)
	if length == 0 || length == 1 {
		return s
	}
	for j := length; j >= 1; j-- {
		for i := 0; i+j <= length; i++ {
			if IsPalindrome(s[i : i+j]) {
				return s[i : i+j]
			}
		}
	}
	return ""
}

func IsPalindrome(s string) bool {
	length := len(s)
	for i, j := 0, length-1; i < j; i, j = i+1, j-1 {
		if s[i] != s[j] {
			return false
		}
	}
	return true
}

/*
动态规划，d[i][j] = 1 表示从 i 到 j 的子串为回文子串，则 d[i+1][j-1]也应当是回文子串
可以画一个表来表示，通过填表的方式即可看出，循环的顺序对于解题至关重要，应当一列一列的循环
*/

func LongestPalindromeV2(s string) string {
	length := len(s)
	if length == 0 || length == 1 {
		return s
	}
	dp := make([][]bool, length)
	for i := 0; i < length; i++ {
		dp[i] = make([]bool, length)
	}
	maxLength, start := 0, 0
	for j := 0; j < length; j++ {
		for i := 0; i <= j; i++ {
			if j-i <= 2 {
				dp[i][j] = s[i] == s[j]
			} else {
				dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
			}
			if dp[i][j] && j-i+1 > maxLength {
				maxLength = j - i + 1
				start = i
			}
		}
	}
	return s[start : start+maxLength]
}
