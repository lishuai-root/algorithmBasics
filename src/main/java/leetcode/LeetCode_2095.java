package leetcode;

/**
 * @description: You are given the head of a linked list. Delete the middle node,
 * and return the head of the modified linked list.
 * <p>
 * The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing,
 * where ⌊x⌋ denotes the largest integer less than or equal to x.
 * <p>
 * For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
 * @author: LISHUAI
 * @createDate: 2021/12/12 11:40
 * @version: 1.0
 */

public class LeetCode_2095 {

    public static void main(String[] args) {

        int[] arr = {1};

        ListNode listNode = makeList(arr);

        ListNode node = deleteMiddle(listNode);

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

    public static ListNode deleteMiddle(ListNode head) {

        if (head == null || head.next == null) {

            return null;
        }

        ListNode cur = head, modified = head;

        while (cur.next != null && cur.next.next != null) {

            cur = cur.next.next;

            modified = modified.next;
        }

        if (cur.next != null) {

            modified.next = modified.next.next;
        } else {

            modified.val = modified.next.val;

            modified.next = modified.next.next;
        }

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
