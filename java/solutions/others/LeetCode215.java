package solutions.others;

import util.SolutionsFacade;

public class LeetCode215 implements SolutionsFacade {


    void insertSort(int[] arr, int end, int v){
        // 大则插入，
        int j = 0,  t = end;
        boolean insert =false;
        for(;j<=end;j++){
            if(arr[j]<v){
                insert = true;
                break;
            }

        }
        if(insert){
            // 找到了小于v的节点，之前的节点都已有序
            for(; t>j;t--){
                arr[t] = arr[t-1];
            }
            arr[j] = v;
        }
    }

    /**
     * 辅助数组长度k有序，从大到小，
     * 遍历原数组时对辅助数组插入排序，这样1次遍历即可找出，但辅助数组插排最差情况O(nk)
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int INF = -999999;
        int[] help = new int[k];
        for(int i = 0;i<k;i++)
            help[i] = INF;
        for(int i = 0;i<nums.length;i++){
            if(i<k){
                // 此时辅助数组未满，则对前i个元素插入排序
                insertSort(help, i, nums[i]);
            }
            else{
                insertSort(help,k-1 ,nums[i] );
            }
        }
        return help[help.length-1];
    }


    /**
     * 堆排法，我们排一个升序，在排的过程中，会将大顶堆的根节点一次调整到数组末尾，这些节点正巧依次是第1大、第2大...第k大的数字。
     * 当k=n时，算法相当于对整个数组排序，测试时间复杂度O(nlogn)，也是要比上一个插排版本O(n^2)快的。
     * 根据优化效果，可大胆猜测其测试用例，大部分的k属于较小数。
     *
     *
     * 另，快排也可以改造一番使用。
     *
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
        return HeapSort(nums,k);
    }

    void swap(int[] arr, int a, int b){
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    void BuildHeap(int[] nums){
        for(int j = (nums.length-2)>>1;j>=0;j--){
            AdjustHeap(nums, j, nums.length-1);
        }
    }

    void AdjustHeap(int[] nums, int start, int end){
        for(int k = start; 2*k+1<=end; ){
            // 右子节点存在且大于左子节点
            if(2*k+2<=end && nums[2*k+1]< nums[2*k+2]){
                if(nums[k]<nums[2*k+2]){
                    // 交换，并移动
                    swap(nums, k , 2*k+2);
                    k = 2*k +2;
                }
                else{   // 不用调整
                    break;
                }
            }
            // 右子节点不存在或者没有左子节点大
            else if(nums[k]<nums[2*k+1]){
                // 交换，并移动
                swap(nums, k , 2*k+1);
                k = 2*k +1;
            }
            else{   // 不用调整
                break;
            }
        }
    }

    int HeapSort(int[] nums, int k){
        // length-1 == 1
        BuildHeap(nums);
        for(int j= nums.length-1;j>=nums.length-k;j--){
            // 交换头尾
            swap(nums, j, 0);
            // 头元素变化，重新调整，并缩短范围
            AdjustHeap(nums, 0, j-1);
        }
        return nums[nums.length-k];
    }



    @Override
    public void calculate(Object... objects) {
        int k = findKthLargest1((int[])objects[0], (int)objects[1]);
        System.out.println("yes");
    }

}
