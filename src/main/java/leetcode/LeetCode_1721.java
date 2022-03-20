package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given the head of a linked list, and an integer k.
 * <p>
 * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
 * @author: LISHUAI
 * @createDate: 2021/12/7 21:21
 * @version: 1.0
 */

public class LeetCode_1721 {

    public static void main(String[] args) {

        int[] arr = new int[]{7, 9, 6, 6, 7, 8, 3, 0, 9, 5};

        ListNode node;

        ListNode listNode = makeList(arr);

        node = listNode;

        while (node != null) {

            System.out.print(node.val + "  ");

            node = node.next;
        }

        System.out.println();

        node = swapNodes_05(listNode, 5);

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

    public static ListNode swapNodes(ListNode head, int k) {

        ListNode node = head, tail = head;

        int size = 0;

        while (node != null) {

            size++;

            if (size == k) {

                tail = node;
            }

            node = node.next;
        }

        if (size == 1 || ((size & 1) == 1 && k == (size + 1) / 2)) {

            return head;
        }

        size = size - k + 1;

        node = head;

        while (--size > 0) {

            node = node.next;
        }


        node.val = node.val ^ tail.val;
        tail.val = node.val ^ tail.val;
        node.val = node.val ^ tail.val;

        return head;
    }

    public static ListNode swapNodes_02(ListNode head, int k) {


        List<ListNode> list = new ArrayList<>();

        ListNode node = head, tail;

        while (node != null) {

            list.add(node);

            node = node.next;
        }

        int size = list.size();

        if (size == 1 || ((size & 1) == 1 && k == (size + 1) / 2)) {

            return head;
        }

        node = list.get(k - 1);

        tail = list.get(size - k);

        node.val = node.val ^ tail.val;
        tail.val = node.val ^ tail.val;
        node.val = node.val ^ tail.val;

        return head;
    }


    public static ListNode swapNodes_03(ListNode head, int k) {

        ListNode node = head, tail = head;

        ListNode[] lists = new ListNode[k];

        int size = 0, index = -1;

        while (node != null) {

            size++;

            if (size == k) {

                tail = node;
            }

            index = ++index % k;

            lists[index] = node;

            node = node.next;
        }

        if (size == 1 || ((size & 1) == 1 && k == (size + 1) / 2)) {

            return head;
        }

        node = lists[++index % k];

        node.val = node.val ^ tail.val;
        tail.val = node.val ^ tail.val;
        node.val = node.val ^ tail.val;

        return head;
    }

    public static ListNode swapNodes_04(ListNode head, int k) {

        ListNode node = head, tail = head;

        ListNode[] lists = new ListNode[k];

        int size = 0, index = -1;

        while (node != null) {

            size++;

            index = ++index % k;

            lists[index] = node;

            node = node.next;
        }

        if (size == 1 || ((size & 1) == 1 && k == (size + 1) / 2)) {

            return head;
        }

        size = 0;

        node = head;

        while (++size != k) {

            node = node.next;
        }

        tail = lists[++index % k];

        node.val = node.val ^ tail.val;
        tail.val = node.val ^ tail.val;
        node.val = node.val ^ tail.val;

        return head;
    }

    public static ListNode swapNodes_05(ListNode head, int k) {

        ListNode node = head, tail = head, n;

        int size = 0;

        while (++size != k) {

            node = node.next;
        }

        n = node;

        node = node.next;

        while (node != null) {

            tail = tail.next;

            node = node.next;

            size++;
        }

        if (size == 1 || ((size & 1) == 1 && k == (size + 1) / 2)) {

            return head;
        }

        n.val = n.val ^ tail.val;
        tail.val = n.val ^ tail.val;
        n.val = n.val ^ tail.val;

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
