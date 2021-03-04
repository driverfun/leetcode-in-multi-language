package solutions.others;

import core.ListNode;
import util.SolutionsFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class LeetCode148 implements SolutionsFacade {

    /**
     * 思路：
     * 猥琐思路：将链表的值存入数组，然后数组排序（要nlogN的话可选的有归并、堆排、快排），再将结果放回链表
     * 这里的数组排序采用了归并（实际上看了官方题解可以直接对链表分治的），但用了O(2n)的辅助空间。
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if(head==null)
            return null;

        //1. 将链表值拷入数组
        List<Integer> vals = new ArrayList<>();
        ListNode p = head;
        while(p!=null){
            vals.add(p.val);
            p = p.next;
        }
        int len = vals.size();
        List<Integer> sortedVals = new ArrayList<>(Collections.nCopies(len, 1));
        //2. 对数组归并排序
        divideAndConquerer(vals, 0,len-1, sortedVals);

        //3. 再将新的值拷贝到原数组
        p = head;
        int k = 0;
        while(p !=null){
            p.val = sortedVals.get(k++);
            p = p.next;
        }
        return head;
    }


    // O(nlogN)
    void divideAndConquerer(List<Integer> before, int start, int end, List<Integer>after){
        if(start == end)
            return ;
        int mid = (start+end)>>1;
        // 先分
        divideAndConquerer(before, start,mid, after);
        divideAndConquerer(before, mid+1, end, after);
        // 后治
        int p = start, q = mid+1, k = start;
        while(p<=mid && q<=end){
            if(before.get(p)<before.get(q)){
                after.set(k++, before.get(p++));
            }
            else{
                after.set(k++, before.get(q++));
            }
        }
        // 把屁股站上去
        while(p<=mid)
            after.set(k++, before.get(p++));
        while(q<=end)
            after.set(k++, before.get(q++));
        // 用新数组改变旧数组
        for(k=start;k<=end;k++)
            before.set(k, after.get(k));

    }


    /**
     * 归并法：自顶向下式！
     *
     * 传入一段链表，其中对它进行分割改造（中间点屁股断开）
     * 重新整理后返回新的头节点
     * @return
     */
    ListNode divideAndMerge(ListNode head){
        if(head.next ==null)
            return head;
        ListNode slow = head, fast = head;
        while(fast.next!=null&& fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode h2 = slow.next;
        slow.next = null;
        ListNode h1 =divideAndMerge(head);
        h2 = divideAndMerge(h2);
        return merge(h1, h2);
    }


    /**
     * 将第二支链表合并到第一支来。
     * 共用到5个指针
     * @param h1
     * @param h2
     * @return
     */
    ListNode merge(ListNode h1, ListNode h2){
        ListNode head = h1.val<=h2.val? h1: h2;
        ListNode p = h1, q = h2, last = p, tmp;
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


    @Override
    public void calculate(Object... objects) {
//        sortList((ListNode)objects[0]);
        ListNode head = divideAndMerge((ListNode)objects[0]);
        System.out.println("stop");
    }
}
