package leetcode;

/**
 * @description: You are given two binary trees root1 and root2.
 * <p>
 * Imagine that when you put one of them to cover the other,
 * some nodes of the two trees are overlapped while the others are not.
 * You need to merge the two trees into a new binary tree.
 * The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node.
 * Otherwise, the NOT null node will be used as the node of the new tree.
 * <p>
 * Return the merged tree.
 * <p>
 * Note: The merging process must start from the root nodes of both trees.
 * @author: LISHUAI
 * @createDate: 2021/9/2 20:39
 * @version: 1.0
 */

public class LeetCode_617 {

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        return process(root1, root2);
    }

    private static TreeNode process(TreeNode left, TreeNode right) {

        if (left == null && right == null) {

            return null;
        }

        int l = left == null ? 0 : left.val;

        int r = right == null ? 0 : right.val;

        TreeNode node = new TreeNode(l + r);

        node.left = process((left == null ? null : left.left), (right == null ? null : right.left));

        node.right = process((left == null ? null : left.right), (right == null ? null : right.right));

        return node;
    }


    private static TreeNode process_02(TreeNode left, TreeNode right) {

        if (left == null && right == null) {

            return null;
        }

        TreeNode node;

        int l = 0, r = 0;

        if (left != null) {

            node = left;
        } else {

            node = right;
        }

        l = left == null ? 0 : left.val;

        r = right == null ? 0 : right.val;

        node.val = l + r;

        node.left = process((left == null ? null : left.left), (right == null ? null : right.left));

        node.right = process((left == null ? null : left.right), (right == null ? null : right.right));

        return node;
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
