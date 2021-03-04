package solutions.others;

import core.ListNode;
import util.SolutionsFacade;



public class LeetCode19 implements SolutionsFacade {

    /**
     * 注意：本题存在被删节点是头节点的情况，一种优雅的处理方式是使用哑节点。
     *      所谓哑节点就是在头节点前再串入一个假的头节点，其后的遍历从这个假头节点开始，就可以免去一些边界条件的判断。
     */
    private ListNode delete = null;

    /**
     * 思路：空间复杂度 O(n)
     * 要求一趟遍历解决问题，想到了递归/栈的方式，在回退过程中，删除节点。
     * 注意：返回值最好设定为被删除节点的next节点。
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int[] step = new int[1];
        ListNode[] last = new ListNode[]{null};
        return DFS1(head, n, step, head);
    }


    ListNode DFS1(ListNode head, int n, int[] arr, ListNode prehead) {
        if(head == null) // 空指针则返回
            return null;
        ListNode last = DFS1(head.next, n, arr, prehead);
        arr[0]+=1;
        if(arr[0]==n){
            // 此时的head就是被删节点
            delete = head;
            if(delete == prehead)
                return head.next;
            // 早一步返回
            return head;
        }
        if(delete!=null){

            head.next = last.next;
            delete = null;
        }
        return head;
    }


    /**
     * 思路：快慢指针法，空间复杂度O(1)
     * 求倒数第n个节点，就让快指针先走n步，然后慢指针和它一起前进，当快指针走到最后一个节点时，慢指针的next就是要删除节点。
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dump= new ListNode(-1);
        dump.next = head;
        ListNode fast= dump, slow =dump;
        int k = 0;
        while(k++<n&& fast.next!=null)
            fast = fast.next;
        while(fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        // 找到了删除节点
        slow.next = slow.next.next;
        return dump.next;
    }


    @Override
    public void calculate(Object... objects) {
        ListNode head = removeNthFromEnd1((ListNode)objects[0], (int)objects[1]);
        System.out.println("yes");
    }

}
