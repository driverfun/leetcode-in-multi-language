package solutions.others;

public class LeetCode34 {

    /**
     * 递归解法：（二分法的改造）
     *
     * 1. 每次找到的中间值等于目标时，因为无法确定上下边界，所以左、右区间的搜索必须继续；
     * 2. 中间值小于目标值时，说明左区间不可能有结果了，只搜索右区间，反之亦然；
     * 3. 搜索会一直进行到左下标大于右下标时结束，所以上下界最后都会出现即（中间值等于上、下界），
     *    因此当中间值等于目标值时，刷新结果中的上下界，随着递归的进行就会得到正确答案。
     *
     * 复杂度分析：
     * Time-    O(logN)
     * Space-   O(logN) 递归占用的空间
     *
     * 这道题的官方解法：
     * 1. 分别二分查找区间的上下界，然后修改二分时对应的判断条件。
     * 2. 上述解法因为是迭代的，空间复杂度O(1)，时间复杂度O(2logN)，可以有两种写法。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};     // res[0] < res[1]
        BinarySearch(nums, target, 0, nums.length-1, res);
        return res;
    }

    public void BinarySearch(int[] nums, int target, int start, int end, int[] res){
        if(start> end)
            return ;
        int mid = (start+end)>>1;
        if(nums[mid] == target){
            if(mid<res[0]|| res[0]==-1)
                res[0] = mid;
            if(mid>res[1]|| res[1]==-1)
                res[1] = mid;
            BinarySearch(nums, target, start, mid-1, res);
            BinarySearch(nums, target, mid+1, end, res);
        }else if(nums[mid]<target){
            BinarySearch(nums, target, mid+1, end, res);
        }
        else{
            BinarySearch(nums, target, start, mid-1, res);
        }
    }

}
