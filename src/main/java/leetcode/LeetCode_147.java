package leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @description: Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.
 * <p>
 * The steps of the insertion sort algorithm:
 * <p>
 * Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.
 * It repeats until no input elements remain.
 * The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially contains only the first element in the list.
 * One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.
 * @author: LISHUAI
 * @createDate: 2021/12/12 13:01
 * @version: 1.0
 */

public class LeetCode_147 {

    public static ListNode insertionSortList(ListNode head) {

        if (head == null) {

            return null;
        }

        ListNode root = head, cur = head, node;

        while (root.next != null) {

            node = root;

            cur = root;

            while (cur != null) {

                if (node.val > cur.val) {

                    node = cur;
                }

                cur = cur.next;
            }

            if (root != node) {

                root.val = root.val ^ node.val;
                node.val = root.val ^ node.val;
                root.val = root.val ^ node.val;
            }

            root = root.next;
        }

        return head;
    }

    public static ListNode insertionSortList_02(ListNode head) {

        if (head == null) {

            return head;
        }

        Stack<ListNode> stack = new Stack<>();

        ListNode root, node = head;

        stack.push(node);

        while (node.next != null) {

            stack.push(node);

            node = node.next;
        }

        while (!stack.isEmpty()) {

            node = stack.pop();

            while (node.next != null && node.val > node.next.val) {

                node.val = node.val ^ node.next.val;
                node.next.val = node.val ^ node.next.val;
                node.val = node.val ^ node.next.val;

                node = node.next;
            }
        }

        return head;
    }

    public static ListNode insertionSortList_03(ListNode head) {

        if (head == null) {

            return head;
        }

        ListNode node = head;

        int size = 0;

        while (node != null) {

            size++;

            node = node.next;
        }

        int[] arr = new int[size];

        node = head;

        size = 0;

        while (node != null) {

            arr[size++] = node.val;

            node = node.next;
        }

        Arrays.sort(arr);

        node = head;

        size = 0;

        while (node != null) {

            node.val = arr[size++];

            node = node.next;
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
