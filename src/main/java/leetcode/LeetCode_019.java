package leetcode;

import java.util.Stack;

/**
 * @description: Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * @author: LISHUAI
 * @createDate: 2021/11/15 20:19
 * @version: 1.0
 */

public class LeetCode_019 {

    public static ListNode removeNthFromEnd_02(ListNode head, int n) {

        ListNode preNode = head, nextNode = head;

        int i = 0;

        while (i++ != n) {

            nextNode = nextNode.next;
        }

        if (nextNode == null) {

            return head.next;
        }

        while (nextNode.next != null) {

            nextNode = nextNode.next;

            preNode = preNode.next;
        }

        preNode.next = preNode.next.next;

        return head;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode[] listNodes = new ListNode[31];

        int i = 0;

        while (head != null) {

            listNodes[i++] = head;

            head = head.next;
        }

        if (n == i) {

            return listNodes[1];
        }

        listNodes[i - n - 1].next = listNodes[i - n + 1];

        listNodes[i - n].next = null;

        return listNodes[0];
    }


    public static ListNode removeNthFromEnd_01(ListNode head, int n) {

        Stack<ListNode> stack = new Stack<>();

        ListNode node = null, newHead = head, preNode = null;

        while (head != null) {

            node = head;

            stack.push(node);

            head = head.next;
        }

        for (int i = 0; i < n; i++) {

            node = stack.pop();
        }

        if (node == newHead) {

            newHead = node.next;
        } else {

            preNode = stack.pop();

            preNode.next = node.next;

        }

        node = null;

        return newHead;
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
