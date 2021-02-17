package solutions.others;

import core.ListNode;
import util.SolutionsFacade;

public class LeetCode234 implements SolutionsFacade {

    /**
     * 一种时间复杂度O(2n)或O(3n)，空间复杂度O(n)的解法
     * O(3n)法如下文；O(2n)法使用队列替代数组即可；但空间复杂度都未降到O(1)
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        // O(3n)法
        ListNode p = head;
        int len = 0;
        while(p!=null){
            len +=1 ;
            p = p.next;
        }
        int[] arr = new int[len];
        p = head;
        for(int i = 0;i<len;i++){
            arr[i] = p.val;
            p = p.next;
        }

        for(int i=0,j=len-1;i<=j;i++,j--){
            if(arr[i]!=arr[j])
                return false;
        }
        return true;
    }


    @Override
    public void calculate(Object... objects) {
        boolean state = isPalindrome((ListNode)objects[0]);
        System.out.println(state) ;
    }
}
