package leetcode;

/**
 * @description: Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
 * <p>
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * @author: LISHUAI
 * @createDate: 2022/4/16 15:25
 * @version: 1.0
 */

public class LeetCode_538 {

    public static TreeNode convertBST(TreeNode root) {
        convertBSTProcess(root, 0);
        return root;
    }

    private static int convertBSTProcess(TreeNode root, int value) {
        if (root == null) {
            return value;
        }

        value = convertBSTProcess(root.right, value);
        root.val += value;
        value = convertBSTProcess(root.left, root.val);
        return value;
    }

    static class TreeNode {
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
