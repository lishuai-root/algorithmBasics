package leetcode;

/**
 * @description: Given the root of a complete binary tree, return the number of the nodes in the tree.
 * <p>
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree,
 * and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * <p>
 * Design an algorithm that runs in less than O(n) time complexity.
 * @author: LISHUAI
 * @createDate: 2021/12/14 22:17
 * @version: 1.0
 */

public class LeetCode_222 {

    public static int countNodes(TreeNode root) {

        return process(root);
    }

    private static int process(TreeNode root) {

        if (root == null) {

            return 0;
        }

        int num = 1;

        num += process(root.left);

        num += process(root.right);

        return num;
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
