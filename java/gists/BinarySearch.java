package gists;


import java.util.Arrays;

public class BinarySearch {


    /**
     * 二分查找，主要考虑上、下边界临界值的处理
     * @param sortedArray
     * @param target
     * @return
     */
    public static boolean findIt(int[] sortedArray, int target){
        int start = 0 ;     // 第一个元素下标
        int end = sortedArray.length-1;     // 最后一个元素下标
        int mid;
        while (start<= end){
            mid= (start+end)>>1;
            if (sortedArray[mid]<target){
                // 下届移动到右侧
                start = mid+1;
            }
            else if (sortedArray[mid]> target){
                end = mid -1;
                // 上届移动到左侧
            }
            else{
                // 相等为找到。
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args){
        int[] array = {1, 5, 4, 3, 2,6};
        Arrays.sort(array);

        System.out.println(findIt(array, 8));
    }

}
