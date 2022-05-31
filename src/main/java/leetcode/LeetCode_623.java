package leetcode;

/**
 * @description: Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.
 * <p>
 * Note that the root node is at depth 1.
 * <p>
 * The adding rule is:
 * <p>
 * Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with value val as cur's left subtree root and right subtree root.
 * cur's original left subtree should be the left subtree of the new left subtree root.
 * cur's original right subtree should be the right subtree of the new right subtree root.
 * If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new root of the whole original tree, and the original tree is the new root's left subtree.
 * @author: LISHUAI
 * @createDate: 2022/5/15 21:10
 * @version: 1.0
 */

public class LeetCode_623 {

    public static TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode head = new TreeNode(val);
            head.left = root;
            return head;
        }
        addOneRowProcess(root, val, depth, 1);
        return root;
    }

    private static void addOneRowProcess(TreeNode root, int val, int depth, int level) {
        if (root == null) {
            return;
        }
        if (level == depth - 1) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = new TreeNode(val);
            root.right = new TreeNode(val);
            root.left.left = left;
            root.right.right = right;
            return;
        }
        addOneRowProcess(root.left, val, depth, level + 1);
        addOneRowProcess(root.right, val, depth, level + 1);
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
