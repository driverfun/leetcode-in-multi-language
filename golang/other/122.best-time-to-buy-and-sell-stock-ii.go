package other

import "math"

func maxProfit(prices []int) int {
	curr := math.MaxInt32
	total := 0
	for i := 0; i < len(prices)-1; i++ {
		// 买
		if prices[i] < prices[i+1] && curr == math.MaxInt32 {
			curr = prices[i]
			continue
		}
		// 卖
		if prices[i] > prices[i+1] && prices[i] > curr {
			total = total + prices[i] - curr
			curr = math.MaxInt32
		}
	}
	// 到最后一天都没卖
	if curr != math.MaxInt32 {
		total = total + prices[len(prices)-1] - curr
	}
	return total
}

// 贪心
func maxProfitV2(prices []int) int {
	ans := 0
	for i := 1; i < len(prices); i++ {
		ans += max(0, prices[i]-prices[i-1])
	}
	return ans
}
