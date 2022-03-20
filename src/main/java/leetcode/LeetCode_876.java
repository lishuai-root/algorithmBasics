package leetcode;

/**
 * @description: Given the head of a singly linked list, return the middle node of the linked list.
 * <p>
 * If there are two middle nodes, return the second middle node.
 * @author: LISHUAI
 * @createDate: 2021/12/22 19:38
 * @version: 1.0
 */

public class LeetCode_876 {

    public static ListNode middleNode(ListNode head) {

        if (head == null) {

            return head;
        }

        ListNode node = head, pre = head;

        while (node.next != null && node.next.next != null) {

            node = node.next.next;

            pre = pre.next;
        }

        if (node.next != null) {

            pre = pre.next;
        }

        return pre;
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
