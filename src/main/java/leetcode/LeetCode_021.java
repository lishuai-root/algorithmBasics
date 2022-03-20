package leetcode;

/**
 * @description: Merge two sorted linked lists and return it as a sorted list.
 * The list should be made by splicing together the nodes of the first two lists.
 * @author: LISHUAI
 * @createDate: 2021/11/15 21:12
 * @version: 1.0
 */

public class LeetCode_021 {

    public static void main(String[] args) {

        int[] arr1 = new int[]{1, 2, 4};
        int[] arr2 = new int[]{1, 3, 4};

        ListNode listNode = makeListNode(arr1);

        ListNode head = listNode;

        while (head != null) {

            System.out.println(head.val);

            head = head.next;
        }

        System.out.println("`````````````````````````````````````");
        ListNode listNode1 = makeListNode(arr2);

        head = listNode1;

        while (head != null) {

            System.out.println(head.val);

            head = head.next;
        }

        ListNode listNode2 = mergeTwoLists(listNode, listNode1);


    }

    private static ListNode makeListNode(int[] arr) {

        ListNode head = new ListNode(), tail = head;

        ListNode node = null;

        for (int j : arr) {

            node = new ListNode(j);

            tail.next = node;

            tail = node;
        }

        return head.next;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode leftNode = l1, rightNode = l2;

        ListNode head = new ListNode(), allHead = head;

        while (leftNode != null && rightNode != null) {

            if (leftNode.val < rightNode.val) {

                head.next = leftNode;

                leftNode = leftNode.next;
            } else {

                head.next = rightNode;

                rightNode = rightNode.next;
            }

            head = head.next;
        }

        while (leftNode != null) {

            head.next = leftNode;

            leftNode = leftNode.next;

            head = head.next;
        }

        while (rightNode != null) {

            head.next = rightNode;

            rightNode = rightNode.next;

            head = head.next;
        }

        return allHead.next;
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
