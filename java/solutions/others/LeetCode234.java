package solutions.others;

import core.ListNode;
import util.SolutionsFacade;

public class LeetCode234 implements SolutionsFacade {

    /**
     * 一种时间复杂度O(2n)或O(3n)，空间复杂度O(n)的解法
     * O(3n)法如下文；O(2n)法使用队列替代数组即可；但空间复杂度都未降到O(1)
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        // O(3n)法
        ListNode p = head;
        int len = 0;
        while (p != null) {
            len += 1;
            p = p.next;
        }
        int[] arr = new int[len];
        p = head;
        for (int i = 0; i < len; i++) {
            arr[i] = p.val;
            p = p.next;
        }

        for (int i = 0, j = len - 1; i <= j; i++, j--) {
            if (arr[i] != arr[j])
                return false;
        }
        return true;
    }


    /**
     * 方法二：反转链表法（可将空间复杂度压缩到O(1)）
     * 思路： 先走一遍链表找出中间节点（根据数量或快慢指针），将后半部分链表反转；
     *      然后，从末尾和头部同时开始遍历链表（直到中间点），全部相同时回文串；有一个不同则说明不可能回文。
     *      注意，最后要将后半部分再次反转以保证原链表结构不变。
     */


    /**
     * 方法三：哈希法（逐渐流氓）
     * 回文串无论正序与逆序用同一哈希算法所得值相同。
     * 实际的哈希算法其实是n阶二项表达式：hash = a[1]*seed^(n-1)+ a[2]*seed^(n-2)+ ... + a[n]*seed^0 （正序）
     * 则反序为： hash = a[1]*seed^0 + a[2]*seed^1 + ... + a[n]*seed^(n-1) （逆序）
     *
     * 因此，只需一次遍历，就可算出二者的值，比较而这是否相等即可。
     *
     * 质疑：是否存在一组值，其hash值与正序的hash值相同，但（a1正 != a1负）... 这是个数学问题！！
     */
    public boolean isPalindrome3(ListNode head) {
        long hash1 = 0, hash2 = 0, h = 1;
        long seed = (long) (1e9 + 7);
        ListNode p = head;
        while (p != null) {
            hash1 = hash1 * seed + p.val;
            hash2 = hash2 + h * p.val;
            h *= seed;
            p = p.next;
        }
        return hash1 == hash2;
    }



    /**
     * 递归解法：
     * 递归的返回过程实际上时链表的逆序，若它和正序的值完全相同，则说明是回文串。
     * 自己写法：将结果状态设置成全局变量
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        recurse(head, head);
        return res;
    }

    private boolean res = true;

    public ListNode recurse(ListNode node, ListNode head){
        if(node==null)
            return head;
        ListNode refer = recurse(node.next, head);
        if(refer.val!=node.val)
            res= false;
        refer = refer.next;
        return refer;
    }


    /**
     * 递归官方写法：将前进指针设置为全局变量
     * @param head
     * @return
     */

    public ListNode frontPointer = null;

    public boolean isPalindrome2(ListNode head) {
        frontPointer = head;
        return recursiveCheck(head);
    }

    public boolean recursiveCheck(ListNode node){
        if(node!=null){
            if(!recursiveCheck(node.next))  // 一旦内部函数为false，则一直返回false
                return false;
            // 原始框架
            if(node.val != frontPointer.val)
                return false;
            frontPointer = frontPointer.next;
        }
        return true;
    }



    @Override
    public void calculate(Object...objects) {
        boolean state = isPalindrome1((ListNode) objects[0]);
        System.out.println(state);
    }
}
