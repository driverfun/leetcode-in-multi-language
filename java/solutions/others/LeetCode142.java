package solutions.others;

import core.ListNode;

public class LeetCode142 {

    /**
     * 类似141题目，方法一：哈希表，很快就能找到环之入口，但空间复杂度高；
     *
     * 方法二：快慢指针，先同上一题，确认环是否存在，因为此时慢指针已在环内——
     * 笨办法：找出环的长度，命新指针每次前进一步，慢指针就在环内走一遍，如果相遇即为入口节点；（这里不再实现，因为时间复杂度爆炸）
     *
     * 标准答案：根据数学推导，新指针和慢指针同时前进x步后必相遇，此为入口节点。
     * 推导过程：环之前路径为m， 环周长l, 快指针2, 慢指针1。 则有： m+1x = m+ l + 2x, x=l，即慢指针走l下才能相遇
     *         所以当新指针出发时，慢指针已经在圈内l-m的位置，新指针出发m步后到达入口，慢指针也是l-(l-m) =m回到入口（结合画图）。
     * 由此得出结论，注意实际编写代码时让慢指针先走一步来弥补和新指针步数上的差值（如果是其他语言可以用do...while...）
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        // 1. 确认环是否存在
        if(head ==null)
            return null;

        ListNode pre=head, slow = head, fast = head.next;
        while(slow!= null&&fast !=null && fast.next!=null){
            if(slow== fast){
                // 环已存在，找出入口
                slow = slow.next;
                while(pre!=slow){
                    pre = pre.next;
                    slow = slow.next;
                }
                return pre;
            }
            else{
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        return null;
    }

}
