package leetcode;

/**
 * @description: A binary tree is uni-valued if every node in the tree has the same value.
 * <p>
 * Given the root of a binary tree, return true if the given tree is uni-valued, or false otherwise.
 * @author: LISHUAI
 * @createDate: 2022/1/11 21:04
 * @version: 1.0
 */

public class LeetCode_965 {

    public static boolean isUnivalTree(TreeNode root) {

        return process(root, root.val);
    }

    private static boolean process(TreeNode root, int value) {

        if (root == null) {

            return true;
        }

        boolean bl = root.val == value;

        if (bl) {

            bl = process(root.left, root.val) && process(root.right, root.val);
        }

        return bl;
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
