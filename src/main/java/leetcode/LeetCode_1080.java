package leetcode;

/**
 * @description: Given the root of a binary tree and an integer limit, delete all insufficient nodes in the tree simultaneously, and return the root of the resulting binary tree.
 * <p>
 * A node is insufficient if every root to leaf path intersecting this node has a sum strictly less than limit.
 * <p>
 * A leaf is a node with no children.
 * @author: LISHUAI
 * @createDate: 2022/8/11 20:18
 * @version: 1.0
 */

public class LeetCode_1080 {

    public static TreeNode sufficientSubset(TreeNode root, int limit) {
        return sufficientSubsetProcess(root, limit, 0).node;
    }

    public static Info sufficientSubsetProcess(TreeNode root, int limit, int sum) {
        if (root == null) {
            return new Info(null, Integer.MIN_VALUE);
        }

        Info left = sufficientSubsetProcess(root.left, limit, sum + root.val);
        Info right = sufficientSubsetProcess(root.right, limit, sum + root.val);
        root.left = left.node;
        root.right = right.node;
        int max;

        if (left.val == Integer.MIN_VALUE && right.val == Integer.MIN_VALUE) {
            max = sum + root.val;
        } else if (left.val == Integer.MIN_VALUE) {
            max = right.val;
        } else if (right.val == Integer.MIN_VALUE) {
            max = left.val;
        } else {
            max = Math.max(left.val, right.val);
        }

        if (max < limit) {
            return new Info(null, max);
        }
        return new Info(root, max);
    }


    static class Info {
        TreeNode node;
        int val;

        public Info(TreeNode node, int val) {
            this.node = node;
            this.val = val;
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
