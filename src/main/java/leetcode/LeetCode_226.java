package leetcode;

/**
 * @description: Given the root of a binary tree, invert the tree, and return its root.
 * @author: LISHUAI
 * @createDate: 2021/11/27 22:52
 * @version: 1.0
 */

public class LeetCode_226 {

    public static TreeNode invertTree(TreeNode root) {

        process(root);

        return root;
    }

    public static void process(TreeNode root) {

        if (root == null) {

            return;
        }

        TreeNode node = root.left;

        root.left = root.right;

        root.right = node;

        process(root.left);

        process(root.right);
    }

    static public class TreeNode {
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
