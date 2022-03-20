package leetcode;

import java.util.LinkedList;

/**
 * @description: Given the head of a singly linked list, return true if it is a palindrome.
 * @author: LISHUAI
 * @createDate: 2021/11/27 23:11
 * @version: 1.0
 */

public class LeetCode_234 {

    public static boolean isPalindrome(ListNode head) {

        LinkedList<ListNode> h = new LinkedList<>();

        while (head != null) {

            h.addLast(head);


            head = head.next;
        }

        while (h.size() > 1) {

            if (h.pollFirst().val != h.pollLast().val) {

                return false;
            }
        }

        return true;
    }

    public static boolean isPalindrome_02(ListNode head) {

        int size = 0, index;

        ListNode node = head;

        while (node != null) {

            size++;

            node = node.next;
        }

        node = head;

        ListNode pre = null, cur = null;

        index = size / 2;

        while (index > 0) {

            cur = node;

            node = node.next;

            cur.next = pre;

            pre = cur;

            index--;
        }

        if ((size & 1) == 1) {

            node = node.next;
        }

        while (node != null) {

            if (node.val != cur.val) {

                return false;
            }

            node = node.next;

            cur = cur.next;
        }

        return true;
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
