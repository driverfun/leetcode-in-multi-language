package solutions.others;

import core.ListNode;
import util.SolutionsFacade;

public class LeetCode21 implements SolutionsFacade {

    /**
     * 迭代法：（版本一）
     * 这里用了last指针对链表进行了连接，主要思路是将l2插入到l1中。实际上有更简便的实现方式。
     * @param l1 1号链表头节点
     * @param l2 2号链表头节点
     * @return 新的头节点
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        ListNode head = l1.val<=l2.val? l1: l2;
        ListNode p = l1, q = l2, last = p, tmp;
        while(p!=null && q!=null){
            if(q.val<p.val){
                if(last!=p){
                    last.next =q;
                }
                tmp = q.next;
                q.next = p;
                last = q;
                q = tmp;
            }
            else{
                last =p;
                p= p.next;
            }
        }
        if(q!=null){
            last.next = q;
        }
        return head;
    }


    /**
     * 递归法！！！
     * 相当于从尾到头的逆序合并方式！
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        if(l1.val< l2.val){
            l1.next = mergeTwoLists1(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists1(l1, l2.next);
            return l2;
        }
    }


    /**
     * 迭代第二版（使用虚拟头节点）
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode dump = new ListNode(-1);
        ListNode pre = dump, p = l1, q = l2;
        while(p!= null & q!= null){
            // 每次选择值小的节点连接
            if(p.val<q.val){
                pre.next = p;
                pre = pre.next;
                p = p.next;
            }else{
                pre.next = q;
                pre = pre.next;
                q = q.next;
            }
        }
        // 最后将没有走完的链表连接进来
        pre.next = p!=null?p:q;
        return dump.next;
    }


    @Override
    public void calculate(Object... objects) {
        ListNode head = mergeTwoLists2((ListNode)objects[0], (ListNode)objects[1]);
        System.out.println("yes");
    }


}
