package sort

/*
有点像打牌，从左到右依次排好序，右边挑出一个依次跟左边的数进行比较，插到合适的位置，左边都是已经排好的
*/
func InsertSort(nums []int) {
	for i := 0; i < len(nums); i++ {
		for j := i; j > 0; j-- {
			if nums[j] < nums[j-1] {
				nums[j], nums[j-1] = nums[j-1], nums[j]
			}
		}
	}
}
