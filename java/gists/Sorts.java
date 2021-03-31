package gists;

import java.util.Random;

public class Sorts {

    /**
     *   交换函数
     */
    void swap(int[] arr, int a, int b){
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    /**
     *   建堆函数
     */
    void BuildHeap(int[] nums){
        for(int j = (nums.length-2)>>1;j>=0;j--){
            AdjustHeap(nums, j, nums.length-1);
        }
    }

    /**
     *   调整函数
     */
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

    void HeapSort(int[] nums){
        BuildHeap(nums);
        for(int j= nums.length-1;j>=1;j--){
            // 交换头尾
            swap(nums, j, 0);
            // 头元素变化，重新调整，并缩短范围
            AdjustHeap(nums, 0, j-1);
        }
    }


    /**
     * 以升序为例，一轮调整后基数左侧元素全部小等于它，右侧元素大等于它。
     * 调整方式一： 填坑法
     *      即将基准保存起来，然后j填补i，i再填补j，如此反复。排完了再将基准放到正确的位置。
     * @param nums
     */
    void QuickSort1(int nums[], int start, int end){
        if(start>=end)
            return ;
        int base = nums[start];
        int i =start, j = end;
        while(i<j){
            // 先右移
            while(nums[j]> base && i<j)
                j--;
            if(i<j)
                nums[i++] = nums[j];
            // 再左移
            while(nums[i]<= base && i<j) {
                i++;
            }
            if(i<j)
                nums[j--] = nums[i];
        }
        nums[j] = base;

        QuickSort1(nums, start, j-1);
        QuickSort1(nums, j+1, end);
    }


    /**
     * 调整方式二：指针交换法
     *      结论：当一个数未归位时，大于它和小于它的数总是结伴出现。（未证明！）
     *
     * @param nums
     * @param start
     * @param end
     */
    void QuickSort2(int[] nums, int start, int end){
        if(start>=end)
            return ;
        int pivot = new Random().nextInt(end-start+1)+start;
        int base = nums[pivot];
        // 将基准换到首位来吧
        swap(nums, start, pivot);
        int i =start, j = end;
        while(i<j){
            // 找到了右侧交换者
            while(nums[j]> base && i<j)
                j--;
            // 找到了左侧交换者
            while(nums[i]<= base && i<j) {
                i++;
            }
            if(i<j)
                swap(nums, i, j);
        }
        swap(nums, j, start);
        QuickSort2(nums, start, j-1);
        QuickSort2(nums, j+1, end);
    }


    /**
     * 移位法：
     * 先将基准交换到末尾，再对末尾前的各个元素做调整（即小于基准的元素就和左侧元素交换）。
     * 结束后再将末尾元素与当前的左侧元素交换，让基准值归位。
     * @param nums
     * @param start
     * @param end
     */
    void QuickSort3(int[] nums, int start, int end){
        if(start>=end)
            return ;
        int base = nums[start];
        swap(nums, start, end );
        int k = start;
        // 从左到右将小于
        for(int i = start;i<end;i++) {
            if (nums[i] <= base) {
                swap(nums, i, k);
                k++;
            }
        }
        swap(nums, end, k);

        QuickSort3(nums, start, k-1);
        QuickSort3(nums, k+1, end);
    }


    public static void main(String[] args){
        Sorts  ins = new Sorts();

        int[] test = {6,4, 2, 3, 2, 1, 5, 3, 1};
//        int[] array = new int[]{72,6,57,88,60,42,42,83,83,73,48,85};
        ins.QuickSort2(test, 0, test.length-1);
//        ins.QuickSort3(array, 0, array.length-1);
//        ins.partition(new int[]{57, 42,60, 48}, 0,3);
        System.out.println("debug");

    }

}
