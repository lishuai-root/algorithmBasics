package leetcode;

/**
 * @description: Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list. Return the linked list sorted as well.
 * @author: LISHUAI
 * @createDate: 2021/12/8 22:28
 * @version: 1.0
 */

public class LeetCode_82 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 4, 5};

        ListNode listNode = makeList(arr);

        ListNode node = deleteDuplicates_02(listNode);

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

    public static ListNode deleteDuplicates(ListNode head) {

        if (head == null) {

            return head;
        }

        int size = 0, index = -1;

        ListNode node = head;

        while (node != null) {

            size++;

            node = node.next;
        }

        int[] arr = new int[size];

        node = head;

        while (node != null) {

            int m = 0;

            while (node != null && index != -1 && node.val == arr[index]) {

                node = node.next;

                m++;
            }

            if (m != 0) {

                index--;
            }

            if (node != null) {

                arr[++index] = node.val;

                node = node.next;
            }

        }

        if (index == -1) {

            return null;
        }

        node = new ListNode();

        node.next = head;

        ListNode n = node;


        for (int i = 0; i <= index; i++) {

            node = node.next;

            node.val = arr[i];
        }

        n.next = null;

        node.next = null;

        return head;
    }

    public static ListNode deleteDuplicates_02(ListNode head) {

        if (head == null) {

            return head;
        }

        ListNode root = new ListNode(), tail = root, pre = root, node = head;

        if (head.next != null) {

            if (head.next.val == head.val) {

                root.val = head.val;
            } else {

                root.val = Integer.MAX_VALUE;
            }
        }

        root.next = head;

        int size = 0;


        while (node != null) {

            if (pre.val == node.val) {

                while (node.next != null && pre.val == node.val) {

                    size++;

                    node = node.next;
                }

                if (node.next != null) {

                    pre = node;

                    node = node.next;

                    continue;
                }

                if (pre.val != node.val) {


                    tail = tail.next;

                    tail.val = node.val;
                }

                break;
            }

            tail = tail.next;

            if (size > 0) {

                tail.val = pre.val;
            }

            pre = node;

            node = node.next;
        }

        tail.next = null;

        return root.next;
    }

    public static ListNode deleteDuplicates_03(ListNode head) {
        ListNode root = new ListNode(), pre = root, cur = head, tail;

        while (cur != null) {
            tail = cur;
            while (tail != null && cur.val == tail.val) {
                tail = tail.next;
            }
            if (cur.next == tail) {
                pre.next = cur;
                pre = pre.next;
            }
            cur = tail;
        }
        pre.next = null;
        return root.next;
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
