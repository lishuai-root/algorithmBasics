package leetcode;

/**
 * @description: You are given the head of a linked list.
 * <p>
 * The nodes in the linked list are sequentially assigned to non-empty groups whose lengths form the sequence of the natural numbers (1, 2, 3, 4, ...). The length of a group is the number of nodes assigned to it. In other words,
 * <p>
 * The 1st node is assigned to the first group.
 * The 2nd and the 3rd nodes are assigned to the second group.
 * The 4th, 5th, and 6th nodes are assigned to the third group, and so on.
 * Note that the length of the last group may be less than or equal to 1 + the length of the second to last group.
 * <p>
 * Reverse the nodes in each group with an even length, and return the head of the modified linked list.
 * @author: LISHUAI
 * @createDate: 2021/12/15 21:23
 * @version: 1.0
 */

public class LeetCode_2074 {

    public static void main(String[] args) {

        int[] arr = {1, 1, 0, 6, 5};

        ListNode listNode = makeList(arr);

        ListNode node = reverseEvenLengthGroups(listNode);

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

    public static ListNode reverseEvenLengthGroups(ListNode head) {

        if (head == null) {

            return head;
        }

        int len = 1;

        ListNode pre = head, node = head.next, tail, n, d;

        while (node != null) {

            int i = 0;

            for (; i < len && node.next != null; i++) {

                node = node.next;
            }

            if ((i & 1) == 1) {

                tail = pre.next;

                d = tail;

                pre.next = node;

                node = node.next;

                pre = node;

                for (int j = 0; j <= len && tail != null; j++) {

                    n = tail.next;

                    tail.next = pre;

                    pre = tail;

                    tail = n;
                }

                pre = d;
            } else {

                pre = node;

                node = node.next;
            }

            len++;
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
