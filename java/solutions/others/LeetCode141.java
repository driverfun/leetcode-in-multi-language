package solutions.others;

import core.ListNode;

public class LeetCode141 {

    /**
     * 环形链表，经典题目，常见解法：
     * 一、哈希表，将出现过的节点存入表，当遍历到某个节点发现已在表中，说明存在环。
     * 二、快慢指针，例如在操场跑圈，快指针因为比慢指针走得快，所以总有追上它的一天，由此判断是否存在环。
     *    总共使用俩指针，故复杂度为O(1)。
     * @param head
     * @return
     */
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
