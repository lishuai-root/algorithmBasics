package leetcode;

/**
 * @description: Given the head of a linked list and a value x,
 * partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * @author: LISHUAI
 * @createDate: 2021/12/12 16:05
 * @version: 1.0
 */

public class LeetCode_86 {

    public static void main(String[] args) {

        int[] arr = {4, 1};

        ListNode listNode = makeList(arr);

        ListNode l = partition_02(listNode, 3);

        while (l != null) {

            System.out.print(l.val + "  ");

            l = l.next;
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

    public static ListNode partition(ListNode head, int x) {

        ListNode left = head, right, cur = head, lPre = head, rPre;

        while (cur != null) {

            if (cur.val == x) {

                break;
            }

            cur = cur.next;
        }

        right = cur.next;

        rPre = cur;

        while (left != null || right != null) {

            while (left != cur && left.val < x) {

                lPre = left;

                left = left.next;
            }

            while (right != null && right.val >= x) {

                rPre = right;

                right = right.next;
            }

            if (right != null && left != cur) {

                right.val = right.val ^ left.val;
                left.val = right.val ^ left.val;
                right.val = right.val ^ left.val;

                lPre = left;

                rPre = right;

                left = left.next;

                right = right.next;
            } else if (left == cur && right == null) {

                break;
            } else if (right == null) {

                rPre.next = left;

                lPre = left.next;

                left.next = null;

                left = lPre.next;

                right = rPre.next;
            } else if (left == cur) {

                lPre.next = right;

                rPre.next = right.next;

                right.next = left;

                lPre = lPre.next;

                right = rPre;
            }
        }

        return head;
    }

    public static ListNode partition_02(ListNode head, int x) {

        ListNode node = head, left = null, right, pre;

        ListNode root = new ListNode();

        root.next = head;

        pre = root;

        while (node != null) {

            while (node != null && node.val < x) {

                pre = node;

                node = node.next;
            }

            if (node == null) {

                break;
            }

            if (left == null) {

                left = node;
            }

            while (node.next != null && node.next.val >= x) {

                node = node.next;
            }

            right = node;

            node = node.next;

            if (node == null) {

                break;
            }

            right.next = node.next;

            pre.next = node;

            pre = pre.next;

            node.next = left;

            node = right;
        }

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
