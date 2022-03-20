package leetcode;

/**
 * @description: You are given the root of a binary tree where each node has a value 0 or 1.
 * Each root-to-leaf path represents a binary number starting with the most significant bit.
 * <p>
 * For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
 * Return the sum of these numbers.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bits integer.
 * @author: LISHUAI
 * @createDate: 2022/1/11 20:22
 * @version: 1.0
 */

public class LeetCode_1022 {

    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 0, 1, 0, 1};

    }

    public static int sumRootToLeaf(TreeNode root) {

        return process(root, 0);
    }

    private static int process(TreeNode root, int sum) {

        if (root == null) {

            return 0;
        }

        sum = (sum << 1) + root.val;

        return Math.max(sum, process(root.left, sum) + process(root.right, sum));
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
