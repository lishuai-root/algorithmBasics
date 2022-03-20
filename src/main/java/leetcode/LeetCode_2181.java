package leetcode;

/**
 * @description: You are given the head of a linked list, which contains a series of integers separated by 0's.
 * The beginning and end of the linked list will have Node.val == 0.
 * <p>
 * For every two consecutive 0's, merge all the nodes lying in between them into a single node whose value is the sum of all the merged nodes.
 * The modified list should not contain any 0's.
 * <p>
 * Return the head of the modified linked list.
 * @author: LISHUAI
 * @createDate: 2022/2/23 22:54
 * @version: 1.0
 */

public class LeetCode_2181 {

    private static int count = 0;

    public static void main(String[] args) {
        int[] arr = {0, 3, 1, 0, 4, 5, 2, 0};
        ListNode listNode = makeList(arr);
        ListNode node = listNode;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }

        System.out.println();
        listNode = mergeNodes(listNode);
        node = listNode;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
        System.out.println(count);
    }

    private static ListNode makeList(int[] arr) {
        ListNode head = new ListNode(arr[0]), tail = head;
        for (int i = 1; i < arr.length; i++) {
            tail.next = new ListNode(arr[i]);
            tail = tail.next;
        }
        return head;
    }

    public static ListNode mergeNodes(ListNode head) {

        ListNode root = head.next, pre = head, cur = head;
        while (cur != null) {
            while (cur.val != 0) {
                cur = cur.next;
                pre.val += cur.val;
                count++;
            }
            pre.next = cur.next;
            pre = cur.next;
            cur = cur.next;
        }

        return root;
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
