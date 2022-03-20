package leetcode;

/**
 * @description: Given a binary tree root,
 * a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 * <p>
 * Return the number of good nodes in the binary tree.
 * @author: LISHUAI
 * @createDate: 2022/1/6 21:10
 * @version: 1.0
 */

public class LeetCode_1448 {

    public static int goodNodes(TreeNode root) {

        return process(root, root.val);
    }

    private static int process(TreeNode root, int max) {

        if (root == null) {

            return 0;
        }

        int i = 0;

        if (root.val >= max) {

            max = root.val;

            i++;
        }

//        if (root.val < max) {
//
//            return process(root.left, max) + process(root.right, max);
//        } else {
//
//            return process(root.left, root.val) + process(root.right, root.val) + 1;
//        }

        return process(root.left, max) + process(root.right, max) + i;
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
