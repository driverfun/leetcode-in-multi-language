package gists;

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


    public static void main(String[] args){
        Sorts  ins = new Sorts();
        int[] array = new int[]{4, 6, 3, 5, 9};
        ins.HeapSort(array);

        System.out.println("debug");

    }

}
