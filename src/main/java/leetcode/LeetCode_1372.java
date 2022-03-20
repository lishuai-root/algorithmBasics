package leetcode;

/**
 * @description: You are given the root of a binary tree.
 * <p>
 * A ZigZag path for a binary tree is defined as follow:
 * <p>
 * Choose any node in the binary tree and a direction (right or left).
 * If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
 * Change the direction from right to left or from left to right.
 * Repeat the second and third steps until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 * <p>
 * Return the longest ZigZag path contained in that tree.
 * @author: LISHUAI
 * @createDate: 2022/1/2 23:20
 * @version: 1.0
 */

public class LeetCode_1372 {

    public static int longestZigZag(TreeNode root) {

//        Info info = process(root);
//
//        return info.max;

//        return Math.max(Math.max(processBefore(root.left, 1, 0), processBefore(root.right, 1, 1)), -1);

        return Math.max(processBefore(root.left, 1, 0), processBefore(root.right, 1, 1));
    }

    private static int processBefore(TreeNode root, int cur, int p) {

        if (root == null) {

            return 0;
        }

        int max;

        if (p == 0) {

            max = Math.max(processBefore(root.left, 1, 0), processBefore(root.right, cur + 1, 1));
        } else {

            max = Math.max(processBefore(root.left, cur + 1, 0), processBefore(root.right, 1, 1));
        }

        return Math.max(cur, max);
    }

    private static Info process(TreeNode root) {

        if (root == null) {

            return null;
        }

        Info left = process(root.left);

        Info right = process(root.right);

        Info info = new Info();

        int max = 0;

        if (left != null) {

            info.leftNum = left.rightNum + 1;

            max = Math.max(info.leftNum, left.max);
        }

        if (right != null) {

            info.rightNum = right.leftNum + 1;

            max = Math.max(max, right.max);
        }

        info.max = Math.max(info.rightNum, max);

        return info;
    }

    static class Info {

        int leftNum, rightNum, max;

        public Info() {
        }

        public Info(int leftNum, int rightNum) {

            this.leftNum = leftNum;

            this.rightNum = rightNum;
        }
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
