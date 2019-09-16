package linked;

/**
 * @author yiwang <>
 * Created on 2019-09-05
 */
public class listTest {
    public static void main(String[] args) {
        ListNode test = buildListNode();
        Solution solution = new Solution();
        ListNode res = solution.deleteDuplicates(test);
        printNode(res);
    }

    public static ListNode buildListNode() {
        ListNode test = new ListNode(1);
        test.next = new ListNode(1);
        test.next.next = new ListNode(1);
        test.next.next.next = new ListNode(2);
        test.next.next.next.next = new ListNode(3);
        return test;
    }

    public static void printNode(ListNode listNode) {
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
