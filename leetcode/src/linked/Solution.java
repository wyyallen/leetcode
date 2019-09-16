package linked;

/**
 * @author yiwang <>
 * Created on 2019-08-22
 */
public class Solution {

    /**
     * 合并k个有序链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null ) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode res;
        if (l1.val < l2.val) {
            res = l1;
            l1 = l1.next;
        } else {
            res = l2;
            l2 = l2.next;
        }
        ListNode head = res;
        for (;l1 != null && l2 != null;) {
            if (l1.val < l2.val) {
                //l1小，直到找到大于l2的值后退出
                while (l1 != null) {
                    if (l1.val > l2.val) {
                        break;
                    }
                    res.next = l1;
                    l1 = l1.next;
                    res = res.next;
                }
            } else {
                //l2小，直到找到大于l1的值后退出
                while (l2 != null) {
                    if (l2.val > l1.val) {
                        break;
                    }
                    res.next = l2;
                    l2 = l2.next;
                    res = res.next;
                }
            }
        }
        if (l1 == null) {
            res.next = l2;
        }
        if (l2 == null) {
            res.next = l1;
        }
        return head;
    }

    /**
     * 删除排序链表中的重复元素,只保留之前没有重复的元素。
     * 思路： 链表是已经排序的，。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1000);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;
        while (fast != null) {
            while (fast.next != null && fast.val == fast.next.val) {
                fast = fast.next;
            }
            if (slow.next == fast) {
                slow = slow.next;
            } else {
                slow.next = fast.next;
            }
            fast = fast.next;
        }
        return dummy.next;
    }

    /**
     * 反转从m到n的链表，只使用一次遍历。
     * 思路：翻转指定链表，再和原链表拼接。
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-100);
        dummy.next = head;
        ListNode pre = dummy;
        int i = 1;
        while (i++ < m) {
            pre = pre.next;
        }
        //双指针进行链表翻转
        ListNode cur = pre.next;
        ListNode temptail = null;
        while (i++ <= n + 1) {
            ListNode next = cur.next;
            cur.next = temptail;
            temptail = cur;
            cur = next;
        }
        //将翻转部分和原链表拼接,此时的cur就是m的后一个节点
        pre.next.next = cur;
        pre.next = temptail;
        return dummy.next;

    }
}
