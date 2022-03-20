package leetcode;

/**
 * @description: Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 * @author: LISHUAI
 * @createDate: 2021/11/21 2:11
 * @version: 1.0
 */

public class LeetCode_101 {

    public static boolean isSymmetric(TreeNode root) {

        return process(root.left, root.right);
    }

    public static boolean process(TreeNode left, TreeNode right) {

        if (left == null && right == null) {

            return true;
        }

        boolean bs = left != null && right != null;

        return bs && left.val == right.val && process(left.right, right.left) && process(left.left, right.right);

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
