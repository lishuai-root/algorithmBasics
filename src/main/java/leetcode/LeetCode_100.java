package leetcode;

/**
 * @description: Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * <p>
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * @author: LISHUAI
 * @createDate: 2022/1/11 20:38
 * @version: 1.0
 */

public class LeetCode_100 {

    public static boolean isSameTree(TreeNode p, TreeNode q) {

        return process(p, q);
    }

    private static boolean process(TreeNode p, TreeNode q) {

        if (p == null && q == null) {

            return true;
        }

        boolean b = treeEquals(p, q);

        if (b) {

            b = process(p.left, q.left) && process(p.right, q.right);
        }

        return b;
    }

    private static boolean treeEquals(TreeNode p, TreeNode q) {

        if (p == null || q == null) {

            return false;
        }

        return p.val == q.val;
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
}
