package solutions.others;

import core.ListNode;

public class LeetCode141 {

    public boolean hasCycle(ListNode head) {
        if (head ==null)
            return false;
        ListNode current= head;
        if (head ==null)
            return false;
        ListNode next = head.next;
        while(current!=null && next!=null && next.next!=null){
            // 先比较再前进
            if(current!=next){
                current = current.next;
                next = next.next.next;
            }else
                return true;
        }
        return false;
    }

}
