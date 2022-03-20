package leetcode;

/**
 * @description: Given the head of a singly linked list, reverse the list, and return the reversed list.
 * @author: LISHUAI
 * @createDate: 2021/11/27 15:10
 * @version: 1.0
 */

public class LeetCode_206 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5};

        ListNode listNode = makeList(arr);

        ListNode node = reverseList(listNode);

        while (node != null) {

            System.out.println(node.val);

            node = node.next;
        }

    }

    public static ListNode makeList(int[] arr) {

        ListNode head = new ListNode(), tail = head;

        for (int i = 0; i < arr.length; i++) {

            tail.next = new ListNode(arr[i]);

            tail = tail.next;
        }

        return head.next;
    }

    public static ListNode reverseList(ListNode head) {

        if (head == null) {

            return head;
        }

        ListNode pre = null, cur = null;

        while (head.next != null) {

            cur = head;

            head = head.next;

            cur.next = pre;

            pre = cur;
        }

        head.next = pre;

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
