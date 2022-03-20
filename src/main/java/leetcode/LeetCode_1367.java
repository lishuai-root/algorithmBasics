package leetcode;

/**
 * @description: Given a binary tree root and a linked list with head as the first node.
 * <p>
 * Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.
 * <p>
 * In this context downward path means a path that starts at some node and goes downwards.
 * @author: LISHUAI
 * @createDate: 2021/12/7 22:49
 * @version: 1.0
 */

public class LeetCode_1367 {

    public static boolean isSubPath(ListNode head, TreeNode root) {

        return process(head, root);
    }

    private static boolean process(ListNode cur, TreeNode root) {

        if (root == null) {

            return cur == null;
        }

        if (cur == null) {

            return true;
        }

        boolean b = false;

        if (cur.val == root.val) {

            b = b | isExists(cur.next, root.left);

            if (!b) {

                b = b | isExists(cur.next, root.right);
            }
        }

        if (!b) {

            b = b | process(cur, root.left);

            if (!b) {

                b = b | process(cur, root.right);
            }
        }

        return b;
    }

    private static boolean isExists(ListNode next, TreeNode right) {

        if (right == null) {

            return next == null;
        }

        if (next == null) {

            return true;
        }

        if (next.val != right.val) {

            return false;
        }

        boolean b = false;

        b = b | isExists(next.next, right.left);

        if (!b) {

            b = b | isExists(next.next, right.right);
        }

        return b;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
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
