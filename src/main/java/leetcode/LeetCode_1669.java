package leetcode;

/**
 * @description: You are given two linked lists: list1 and list2 of sizes n and m respectively.
 * <p>
 * Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
 * <p>
 * The blue edges and nodes in the following figure indicate the result:
 * @author: LISHUAI
 * @createDate: 2021/12/10 23:04
 * @version: 1.0
 */

public class LeetCode_1669 {

    public static void main(String[] args) {

        int[] l1 = {0, 1, 2, 3, 4, 5, 6};

        int[] l2 = {1000000, 1000001, 1000002, 1000003, 1000004};

        ListNode list1 = makeList(l1);

        ListNode list2 = makeList(l2);

        ListNode listNode = mergeInBetween(list1, 2, 5, list2);

        while (listNode != null) {

            System.out.print(listNode.val + "  ");

            listNode = (ListNode) listNode.next;
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

    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {

        ListNode node1 = list1, node2 = list2, pre, tail, cur;

        int size = 1;

        while (size++ < a) {

            node1 = node1.next;
        }

        pre = node1;

        node1 = node1.next;

        while (size++ <= b) {

            node1 = node1.next;
        }

        cur = node1;

        while (node2.next != null) {

            node2 = node2.next;
        }

        tail = node2;

        pre.next = list2;

        tail.next = cur.next;

        cur.next = null;

        return list1;
    }

    public static class ListNode extends util.ListNode {
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
