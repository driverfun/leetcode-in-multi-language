package string

import "strconv"

// 按照平时的思路，先乘后加，时间复杂度 O(mn+n^2)
func multiplyV1(num1 string, num2 string) string {
	if num1 == "0" || num2 == "0" {
		return "0"
	}
	nums := make([]string, 0, len(num2))
	for i := len(num2) - 1; i >= 0; i-- {
		x := int(num2[i] - '0')
		ans := ""
		add := 0
		for j := len(num1) - 1; j >= 0 || add > 0; j-- {
			var y int
			if j >= 0 {
				y = int(num1[j] - '0')
			}
			result := x*y + add
			add = result / 10
			ans = strconv.Itoa(result%10) + ans
		}
		for a := len(num2) - 1; a > i; a-- {
			ans += "0"
		}
		nums = append(nums, ans)
	}
	ans := nums[0]
	for i := 1; i < len(nums); i++ {
		ans = stringAdd(ans, nums[i])
	}
	return ans
}

func stringAdd(num1, num2 string) string {
	ans := ""
	add := 0
	for i, j := len(num1)-1, len(num2)-1; i >= 0 || j >= 0 || add > 0; i, j = i-1, j-1 {
		var x, y int
		if i >= 0 {
			x = int(num1[i] - '0')
		}
		if j >= 0 {
			y = int(num2[j] - '0')
		}
		result := x + y + add
		add = result / 10
		ans = strconv.Itoa(result%10) + ans
	}
	return ans
}
