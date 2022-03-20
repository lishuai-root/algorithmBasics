package leetcode;

/**
 * @description: Given the head of a singly linked list and an integer k,
 * split the linked list into k consecutive linked list parts.
 * <p>
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than one.
 * This may lead to some parts being null.
 * <p>
 * The parts should be in the order of occurrence in the input list,
 * and parts occurring earlier should always have a size greater than or equal to parts occurring later.
 * <p>
 * Return an array of the k parts.
 * @author: LISHUAI
 * @createDate: 2021/12/7 22:17
 * @version: 1.0
 */

public class LeetCode_725 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        ListNode listNode = makeList(arr);

        ListNode[] listNodes = splitListToParts(listNode, 3);

        for (ListNode l : listNodes) {

            if (l == null) {

                System.out.println("null");

                continue;
            }

            while (l != null) {

                System.out.print(l.val + "  ");

                l = l.next;
            }

            System.out.println();
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

    public static ListNode[] splitListToParts(ListNode head, int k) {

        ListNode[] listNodes = new ListNode[k];

        if (head == null) {

            return listNodes;
        }

        ListNode node = head, pre;

        int size = 0;

        while (node != null) {

            size++;

            node = node.next;
        }

        int n = size / k, m = size % k, index = -1, cur;

        while (index < k - 1) {

            if (m-- > 0) {

                cur = n + 1;
            } else {

                cur = n;
            }

            listNodes[++index] = head;

            while (cur-- > 1) {

                head = head.next;
            }

            pre = head;

            head = head.next;

            pre.next = null;
        }

        return listNodes;
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
