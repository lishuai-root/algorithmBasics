package leetcode;

/**
 * @description: Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 * @author: LISHUAI
 * @createDate: 2021/12/12 14:34
 * @version: 1.0
 */

public class LeetCode_92 {

    public static void main(String[] args) {

        int[] arr = {3, 5};

        ListNode listNode = makeList(arr);

        ListNode l = reverseBetween(listNode, 1, 2);

        while (l != null) {

            System.out.print(l.val + "  ");

            l = l.next;
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

    public static ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || left == right) {

            return head;
        }

        ListNode root = new ListNode(), node, tail = root, pre = null, cur = null;

        root.next = head;

        int size = 0;

        while (++size < left) {

            tail = tail.next;
        }

        node = tail.next.next;

        pre = tail.next;

        while (size++ < right) {

            cur = node;

            node = node.next;

            cur.next = pre;

            pre = cur;
        }

        cur = tail.next;

        tail.next = pre;

        cur.next = node;

        return root.next;
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
