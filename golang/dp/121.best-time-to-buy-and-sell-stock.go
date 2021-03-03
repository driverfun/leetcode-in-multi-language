package dp

/*
贪心，遍历的过程中记录下最小值，取接下来遍历的节点中 prices[i] - minPrice 的最大者
*/
func maxProfit(prices []int) int {
	length := len(prices)
	if length < 2 {
		return 0
	}
	minPrice := prices[0]
	ret := 0
	for i := 0; i < length; i++ {
		if prices[i]-minPrice > ret {
			ret = prices[i] - minPrice
		}
		if prices[i] < minPrice {
			minPrice = prices[i]
		}
	}
	return ret
}

/*
动态规划, f(i) = max(f(i-1), 0) + prices[i] - prices[i-1]
*/

func maxProfitV2(prices []int) int {
	if len(prices) < 2 {
		return 0
	}
	maxP, prevP := 0, 0
	for i := 1; i < len(prices); i++ {
		prevP = max(prevP, 0) + prices[i] - prices[i-1]
		maxP = max(maxP, prevP)
	}
	return maxP
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
