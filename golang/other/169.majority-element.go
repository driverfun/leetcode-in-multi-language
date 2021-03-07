package other

/*
很简单能想到用 map，但并不是太好的做法，时间复杂度和空间复杂度都比较高
*/
func majorityElement(nums []int) int {
	m := make(map[int]int)
	for _, num := range nums {
		m[num] += 1
	}
	ret, maxCount := 0, 0
	for num, count := range m {
		if count > maxCount {
			maxCount = count
			ret = num
		}
	}
	return ret
}

/*
摩尔投票法：如何在任意多的候选人中（选票无序），选出获得票数最多的那个
算法可以分为两个阶段：
	1. 对抗阶段，分属两个候选人的选票两两抵消
	2. 技术阶段，计算对抗结果中最后留下的候选人票数是否有效
由于本题的最多数已经过半，而且一定存在最多数，所以最后剩下的 major 就是最多数
*/
func majorityElementV2(nums []int) int {
	major := 0
	count := 0
	for _, num := range nums {
		// count 为0，取当前数为最多数
		if count == 0 {
			major = num
		}
		if major == num {
			count++
		} else {
			// 遇到不同则抵消
			count--
		}
	}
	return major
}
