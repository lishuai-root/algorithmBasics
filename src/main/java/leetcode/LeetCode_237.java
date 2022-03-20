package leetcode;

/**
 * @description: Write a function to delete a node in a singly-linked list.
 * You will not be given access to the head of the list, instead you will be given access to the node to be deleted directly.
 * <p>
 * It is guaranteed that the node to be deleted is not a tail node in the list.
 * @author: LISHUAI
 * @createDate: 2021/12/7 20:35
 * @version: 1.0
 */

public class LeetCode_237 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5};

        ListNode listNode = makeList(arr);

        ListNode node = listNode;

        for (int i = 0; i < 3; i++) {

            node = node.next;
        }

        System.out.println(node.val);

        deleteNode(node);

        while (listNode != null) {

            System.out.print(listNode.val + "  ");

            listNode = listNode.next;
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

    public static void deleteNode(ListNode node) {

        ListNode pre = node;

        while (node.next != null) {

            pre = node;

            node = node.next;

            pre.val = node.val;
        }

        pre.next = null;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode() {
        }
    }
}
