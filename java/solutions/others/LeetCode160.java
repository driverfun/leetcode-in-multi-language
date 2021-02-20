package solutions.others;

import core.ListNode;

public class LeetCode160 {


    /**
     * 说明：相交链表意为尾部相同的意思，若A链相交后长度与B链不同，则很多解法不适用。
     * 如果两个链表必定相交，那么让长的那个先走掉二者长度差，在命两个指针同时行走，找到交点的一天。
     * 这个思路在某种意义讲就是快慢指针，因为相交后部分完全相同，则长度差必是交点前造成的，因此快指针先走掉多出的部分，再同步前进总会找到交点。
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int x = getLength(headA);
        int y = getLength(headB);
        ListNode pl = y>x?headB:headA;
        ListNode ps = y>x?headA:headB;
        int a = y>x?y:x;
        int b = y>x?x:y;
        for(int i=(a-b);i>0;i--){
            pl = pl.next;
        }
        for(int i=b;i>0;i--){
            if(pl== ps)
                return pl;
            else{
                pl = pl.next;
                ps = ps.next;
            }
        }
        return  null;
    }
    public int getLength(ListNode head){
        int n = 0;
        while(head!=null){
            n++;
            head = head.next;
        }
        return n;
    }


    /**
     * 官方解法有：
     * 一、暴力解法：对A链的每个元素遍历一次B链看能否找到相同节点，最先满足条件的节点即交点。 时间复杂度：O(mn)，空间：O(1)
     * 二、哈希表：对前者的优化，将B链存入哈希表，再依次检查A链中元素是否出现在哈希表，最先满足条件的节点即交点，时间复杂度：O(m+n),空间：O(n)
     * 三、双指针法（八字法）：即A链到达末尾后连接到B链的头，B链到达末尾后连接到A链的头，若二链相交，必会在某一刻节点相同。
     *      这种移动方式形同八字，所以我命名为八字法。
     *      至于为何相同，假设A链比B链长2，则B会先到终点，让他再去A链进行，咋二者在第二趟前进时会追上差距，同时到达交点。
     *
     *    何时停止？记录A链和B链的末尾节点，如果二者不同，说明不可能相交，停止前进。
     */
}
