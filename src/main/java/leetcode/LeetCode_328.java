package leetcode;

/**
 * @description: Given the head of a singly linked list,
 * group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 * <p>
 * The first node is considered odd, and the second node is even, and so on.
 * <p>
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * <p>
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 * @author: LISHUAI
 * @createDate: 2021/12/7 19:52
 * @version: 1.0
 */

public class LeetCode_328 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5};

        ListNode listNode = makeList(arr);

        ListNode node = oddEvenList_02(listNode);

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

    public static ListNode oddEvenList(ListNode head) {

        if (head == null) {

            return head;
        }

        int size = 1;

        ListNode tail, node = head, pre, next;

        while (node.next != null) {

            size++;

            node = node.next;
        }

        tail = node;

        if (size <= 2) {

            return head;
        }

        node = head;

        int cur = 1;

        while (++cur <= size) {

            pre = node;

            node = node.next;

            if ((cur & 1) == 1) {

                continue;
            }

            next = node.next;

            tail.next = node;

            node.next = null;

            tail = tail.next;

            pre.next = next;

            node = pre;
        }

        return head;
    }

    public static ListNode oddEvenList_02(ListNode head) {

        int size = 0;

        ListNode tail = head, node = head, nodeTail;

        while (node != null) {

            size++;

            node = node.next;
        }

        if (size <= 2) {

            return head;
        }

        ListNode[] lists = new ListNode[size];

        size = 0;

        node = head;

        while (node != null) {

            lists[size++] = node;

            node = node.next;
        }

        node = new ListNode();

        nodeTail = node;

        for (int i = 1; i < lists.length; i++) {

            if ((i & 1) != 1) {

                tail.next = lists[i];

                tail = tail.next;
            } else {

                nodeTail.next = lists[i];

                nodeTail = nodeTail.next;
            }
        }

        tail.next = node.next;

        nodeTail.next = null;

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
