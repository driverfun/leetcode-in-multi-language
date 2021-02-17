package solutions.others;

import core.ListNode;
import util.SolutionsFacade;

public class LeetCode206 implements SolutionsFacade {

    /**
     * 迭代版本：3个指针变来变去
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head!= null){
            ListNode cur = head;
            ListNode last = cur, tmp = cur.next;
            while(tmp!=null){
                cur = tmp;
                tmp = cur.next;
                cur.next = last;
                if(last==head)              // 修改原头号节点的next为空
                    head.next = null;
                last = cur;

            }
            head = cur;
        }

        return head;
    }


    /**
     * 递归版本：递归结束后返回当前节点，并在回退时将结果节点next指向当前节点。
     * 注意：新的头节点的保存，以及原始头结点的next为空操作！
     * @param head
     * @return
     */
    private ListNode header= null;
    public ListNode reverseList1(ListNode head) {
        if(head!=null)
            recurse(head, head);
        return header;
    }

    public ListNode recurse(ListNode node, ListNode head){
        if(node.next!= null) {
            recurse(node.next, head).next = node;
            if (node == head)
                node.next = null;
        }
        else
            header = node;
        return node;
    }


    /**
     * 官方标准版的递归写法
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        // 链表中只有一个节点的情况，不需要反转。
        if(head ==null || head.next == null)
            return head;
        ListNode header = reverseList2(head.next);
        head.next.next = head;
        // 断开原来关联，避免回路。
        head.next = null;
        return header;
    }


    @Override
    public void calculate(Object... objects) {
        ListNode head= reverseList2((ListNode)objects[0]);
        System.out.println("321");
    }
}
