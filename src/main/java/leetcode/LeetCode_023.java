package leetcode;

/**
 * @description: Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 * @author: LISHUAI
 * @createDate: 2021/12/14 22:37
 * @version: 1.0
 */

public class LeetCode_023 {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4};

        ListNode listNode = makeList(arr);

        ListNode node = swapPairs(listNode);

        while (node != null) {

            System.out.print(node.val + "  ");

            node = node.next;
        }
    }

    private static ListNode makeList(int[] arr) {

        ListNode head = new ListNode(), tail = head;

        for (int i : arr) {

            tail.next = new ListNode(i);

            tail = tail.next;
        }

        return head.next;
    }

    public static ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {

            return head;
        }

        ListNode node = head.next, pre = head, cur;

        head = node;

        pre.next = node.next;

        node.next = pre;

        cur = pre;

        while (pre.next != null && pre.next.next != null) {

            pre = pre.next;

            node = node.next.next.next;

            cur.next = node;

            pre.next = node.next;

            node.next = pre;

            cur = pre;
        }

        return head;
    }

    private static ListNode process(ListNode root) {

        if (root == null || root.next == null) {

            return root;
        }

        ListNode node = root.next, pre = root;

        node.val = node.val ^ pre.val;
        pre.val = node.val ^ pre.val;
        node.val = node.val ^ pre.val;

        while (node.next != null && node.next.next != null) {

            node = node.next.next;

            pre = pre.next.next;

            node.val = node.val ^ pre.val;
            pre.val = node.val ^ pre.val;
            node.val = node.val ^ pre.val;
        }

        return root;
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
