package leetcode;

import java.util.PriorityQueue;

/**
 * @description: Given the head of a linked list, return the list after sorting it in ascending order.
 * @author: LISHUAI
 * @createDate: 2021/11/24 23:56
 * @version: 1.0
 */

public class LeetCode_148 {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 1, 3};

        ListNode listNode = makeList(arr);

        ListNode listNode1 = sortList_02(listNode);

        while (listNode1 != null) {
            System.out.println(listNode1.val);

            listNode1 = listNode1.next;
        }
    }

    public static ListNode makeList(int[] nums) {

        ListNode head = new ListNode();

        ListNode tail = head;

        for (int num : nums) {

            tail.next = new ListNode(num);

            tail = tail.next;
        }

        return head.next;
    }

    public static ListNode sortList_02(ListNode head) {

        if (head == null) {

            return null;
        }

        int size = 0;

        ListNode root = head;

        while (root != null) {

            size++;

            root = root.next;
        }

        ListNode[] maxStack = new ListNode[size];

        ListNode[] minStack = new ListNode[size];

        int maxIndex = -1, minIndex = -1;


        while (head != null) {

            if (maxIndex >= 0 && head.val > maxStack[maxIndex].val) {

                while (maxIndex >= 0 && head.val > maxStack[maxIndex].val) {

                    minStack[++minIndex] = maxStack[maxIndex--];
                }

                maxStack[++maxIndex] = head;
            } else {

                while (minIndex >= 0 && head.val < minStack[minIndex].val) {

                    maxStack[++maxIndex] = minStack[minIndex--];
                }

                minStack[++minIndex] = head;
            }

            head = head.next;
        }

        head = new ListNode();

        root = head;

        for (int i = 0; i <= minIndex; i++) {

            root.next = minStack[i];

            root = root.next;
        }

        while (maxIndex >= 0) {

            root.next = maxStack[maxIndex--];

            root = root.next;
        }

        root.next = null;

        return head.next;
    }

    public static ListNode sortList(ListNode head) {

        if (head == null) {

            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);

        while (head != null) {

            queue.add(head);

            head = head.next;
        }

        head = new ListNode();

        ListNode tail = head;

        while (!queue.isEmpty()) {

            tail.next = queue.poll();

            tail = tail.next;
        }

        tail.next = null;

        return head.next;
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
