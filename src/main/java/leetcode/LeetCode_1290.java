package leetcode;

/**
 * @description: Given head which is a reference node to a singly-linked list.
 * The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.
 * <p>
 * Return the decimal value of the number in the linked list.
 * @author: LISHUAI
 * @createDate: 2021/12/22 22:20
 * @version: 1.0
 */

public class LeetCode_1290 {

    public static int getDecimalValue(ListNode head) {

        if (head == null) {

            return 0;
        }

        int size = 0, ans = 0;

        ListNode node = head;

        while (node != null) {

            if (node.val == 1) {

                ans |= 1 << (31 - size);
            }

            size++;

            node = node.next;
        }

        ans = ans >>> (32 - size);

        return ans;
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
