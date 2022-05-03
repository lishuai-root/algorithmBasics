package leetcode;

/**
 * @description: Given the root of a binary tree, return the length of the longest path,
 * where each node in the path has the same value. This path may or may not pass through the root.
 * <p>
 * The length of the path between two nodes is represented by the number of edges between them.
 * @author: LISHUAI
 * @createDate: 2022/3/29 22:38
 * @version: 1.0
 */

public class LeetCode_687 {

    private static int max;

    public static int longestUnivaluePath(TreeNode root) {
        max = 0;
        longestUnivaluePathProcess(root);
        return max;
    }

    private static int longestUnivaluePathProcess(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = longestUnivaluePathProcess(root.left) + 1;
        int right = longestUnivaluePathProcess(root.right) + 1;
        if (root.left == null || root.left.val != root.val) {
            left = 0;
        }

        if (root.right == null || root.right.val != root.val) {
            right = 0;
        }
        max = Math.max(max, left + right);

        return Math.max(left, right);
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
