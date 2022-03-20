package leetcode;

/**
 * @description: You are given the head of a linked list with n nodes.
 * <p>
 * For each node in the list, find the value of the next greater node. That is, for each node,
 * find the value of the first node that is next to it and has a strictly larger value than it.
 * <p>
 * Return an integer array answer where answer[i] is the value of the next greater node of the ith node (1-indexed).
 * If the ith node does not have a next greater node, set answer[i] = 0.
 * @author: LISHUAI
 * @createDate: 2021/12/8 21:17
 * @version: 1.0
 */

public class LeetCode_1019 {

    public static void main(String[] args) {

        int[] arr = new int[]{2, 7, 4, 3, 5};

        ListNode listNode = makeList(arr);

        int[] ints = nextLargerNodes(listNode);

        for (int i : ints) {

            System.out.print(i + "  ");
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

    public static int[] nextLargerNodes(ListNode head) {

        int size = 0, index = -1;

        int[] sum, stack, result;

        ListNode node = head;

        while (node != null) {

            size++;

            node = node.next;
        }

        sum = new int[size];

        stack = new int[size];

        result = new int[size];

        node = head;

        while (node != null) {

            sum[++index] = node.val;

            node = node.next;
        }

        index = -1;

        for (int i = 0; i < sum.length; i++) {

            while (index != -1 && sum[i] > sum[stack[index]]) {

                result[stack[index]] = sum[i];

                index--;
            }

            stack[++index] = i;
        }


        return result;
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
