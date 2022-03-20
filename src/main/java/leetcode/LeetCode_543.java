package leetcode;

/**
 * @description: Given the root of a binary tree, return the length of the diameter of the tree.
 * <p>
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * <p>
 * The length of a path between two nodes is represented by the number of edges between them.
 * @author: LISHUAI
 * @createDate: 2021/11/29 18:20
 * @version: 1.0
 */

public class LeetCode_543 {

    private static int max = 0;

    public static int diameterOfBinaryTree(TreeNode root) {

        process(root);

        return max;
    }

    private static int process(TreeNode root) {

        if (root == null) {

            return 0;
        }

        int left = process(root.left);

        int right = process(root.right);

        max = Math.max(max, left + right);

        return Math.max(left, right) + 1;
    }

    public class TreeNode {
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
