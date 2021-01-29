package util;

import core.ListNode;

public class LinkedListFactory {

    public static ListNode getLinkedListFromIntegerArray(int[] nums){
        ListNode iter = null;
        ListNode header = null;
        for( int i = 0;i< nums.length;i++){
            ListNode node = new ListNode(nums[i]);
            if(iter!=null)
                iter.next = node;
            if(iter== null)
                header = node;
            iter = node;
        }
        return header;
    }


}
