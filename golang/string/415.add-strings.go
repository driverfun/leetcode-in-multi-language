package string

import "strconv"

// 字符串相加，用双指针从尾部一次相加，用 add 保留进位
func addStrings(num1 string, num2 string) string {
	ans := ""
	add := 0
	for i, j := len(num1)-1, len(num2)-1; i >= 0 || j >= 0 || add > 0; i, j = i-1, j-1 {
		var x, y int
		if i >= 0 {
			// ASSCI 相减，得到 x
			x = int(num1[i] - '0')
		}
		if j >= 0 {
			y = int(num2[j] - '0')
		}
		// 注意加上进位
		result := x + y + add
		// 获得进位
		add = result / 10
		// 每一次加的结果一次加上
		ans = strconv.Itoa(result%10) + ans
	}
	return ans
}
