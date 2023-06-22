package leetcode;

/**
 * @description: Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
 * @author: LiShuai
 * @createDate: 2023/6/14 21:07
 * @version: 1.0
 */

public class LeetCode_530 {

    private TreeNode node;

    public int getMinimumDifference(TreeNode root) {
        return getMinimumDifferenceProcess(root);
    }

    private int getMinimumDifferenceProcess(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int left = getMinimumDifferenceProcess(root.left);
        int cur = node == null ? Integer.MAX_VALUE : root.val - node.val;
        node = root;
        int right = getMinimumDifferenceProcess(root.right);
        return Math.min(cur, Math.min(left, right));
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
