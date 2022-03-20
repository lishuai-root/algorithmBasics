package leetcode;

import lombok.Data;

import java.util.LinkedList;

/**
 * @description: You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * It is guaranteed that the list represents a number that does not have leading zeros.
 * @author: LISHUAI
 * @createDate: 2021/8/1 19:17
 * @version: 1.0
 */

public class LeetCode_02 {

    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers_02(ListNode l1, ListNode l2) {

        int num = 0;

        ListNode head = new ListNode(), tail = head, node = null;

        ListNode n1 = null, n2 = null;

        while (l1 != null && l2 != null) {

            num += l1.val + l2.val;

            node = new ListNode(num % 10);

            tail.next = node;

            tail = node;

            num /= 10;

            l1 = l1.next;

            l2 = l2.next;
        }

        while (l1 != null) {


            num += l1.val;

            node = new ListNode(num % 10);

            tail.next = node;

            tail = node;

            num /= 10;

            l1 = l1.next;
        }

        while (l2 != null) {


            num += l2.val;

            node = new ListNode(num % 10);

            tail.next = node;

            tail = node;

            num /= 10;

            l2 = l2.next;
        }

        if (num > 0) {
            node = new ListNode(num);

            tail.next = node;
        }


        return head.next;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        LinkedList<ListNode> node1 = new LinkedList<>();

        LinkedList<ListNode> node2 = new LinkedList<>();

        int num = 0, l = 0;

        ListNode head = new ListNode(), tail = head, node = null;

        ListNode n1 = null, n2 = null;

        while (l1 != null) {

            node1.add(l1);

            l1 = l1.next;
        }

        while (l2 != null) {

            node2.add(l2);

            l2 = l2.next;
        }


        while (!node1.isEmpty() && !node2.isEmpty()) {

            n1 = node1.pop();

            n2 = node2.pop();

            num += n1.val + n2.val;

            node = new ListNode(num % 10);

            tail.next = node;

            tail = node;

            num /= 10;
        }

        while (!node1.isEmpty()) {

            n1 = node1.pop();

            num += n1.val;

            node = new ListNode(num % 10);

            tail.next = node;

            tail = node;

            num /= 10;
        }

        while (!node2.isEmpty()) {

            n2 = node2.pop();

            num += n2.val;

            node = new ListNode(num % 10);

            tail.next = node;

            tail = node;

            num /= 10;
        }

        if (num > 0) {
            node = new ListNode(num);

            tail.next = node;

            tail = node;
        }

        return head.next;
    }

    @Data
    public class ListNode {
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
