package solutions.others;

import core.ListNode;
import util.SolutionsFacade;


public class LeetCode2 implements SolutionsFacade {

    /**
     * 逆序排列，非常适合做加法，因为数字顺序正好是个-十-千，因此维护一个进位符，当两个链表相加大于10时进位，注意点：
     * 1. 非个位相加时先看看进位符是否有值，若有则结果再加一，并更新进位符（大于10则置位，否则清空）
     * 2. 当两条链表全部遍历完依旧要检查进位符，若置位则为它添加值为1的节点于末尾。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // bit代表进位符
        boolean bit = false;
        ListNode iter = null;
        ListNode header = null;
        // L1 L2 同时为null退出
        while(l1!=null || l2!=null){
            int val = 0;
            if (l1!=null) {
                val += l1.val;
                l1 = l1.next;
            }
            if (l2!=null) {
                val += l2.val;
                l2 = l2.next;
            }
            if (bit){
                val += 1;
                bit = false;
            }
            if (val>=10){
                val -= 10;
                bit = true;
            }
            // 创建节点，连接节点
            ListNode node = new ListNode(val);
            if(iter!= null)
                iter.next = node;
            if(iter == null){
                header = node;
            }
            iter = node;
        }
        // 如果加完还有进位符，则为它新建节点。
        if(bit)
            iter.next = new ListNode(1);

        return header;
    }


    @Override
    public void calculate(Object[] objects){

        addTwoNumbers((ListNode)objects[0], (ListNode)objects[1]);

    }
}
