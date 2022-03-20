package leetcode;

/**
 * @description: Given the head of a linked list, rotate the list to the right by k places.
 * @author: LISHUAI
 * @createDate: 2021/12/12 15:56
 * @version: 1.0
 */

public class LeetCode_61 {

    public static void main(String[] args) {

        int[] arr = {1};

        ListNode listNode = makeList(arr);

        ListNode l = rotateRight(listNode, 1);

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

    public static ListNode rotateRight(ListNode head, int k) {

        if (head == null) {

            return head;
        }

        ListNode node = head, tail;

        int size = 1;

        while (node.next != null) {

            size++;

            node = node.next;
        }

        tail = node;

        k = k % size;

        if (k == 0) {

            return head;
        }

        node = head;

        while (--size > k) {

            node = node.next;
        }

        tail.next = head;

        head = node.next;

        node.next = null;

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
