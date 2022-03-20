package leetcode;

/**
 * @description: Given the head of a linked list and an integer val,
 * remove all the nodes of the linked list that has Node.val == val, and return the new head.
 * @author: LISHUAI
 * @createDate: 2021/12/21 21:11
 * @version: 1.0
 */

public class LeetCode_203 {

    public static ListNode removeElements(ListNode head, int val) {

        if (head == null) {

            return head;
        }

        ListNode root = new ListNode(), pre = root, tail;

        root.next = head;

        while (head != null) {

            if (head.val == val) {

                tail = head.next;

                pre.next = tail;

                head.next = null;

                head = tail;

                continue;
            }

            pre = head;

            head = head.next;
        }

        head = root.next;

        root.next = null;

        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
