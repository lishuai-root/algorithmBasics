package leetcode;

/**
 * @description: You are given the root node of a binary search tree (BST) and a value to insert into the tree.
 * Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 * <p>
 * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion.
 * You can return any of them.
 * @author: LISHUAI
 * @createDate: 2022/1/4 22:06
 * @version: 1.0
 */

public class LeetCode_701 {

    public static TreeNode insertIntoBST(TreeNode root, int val) {

        return inset(root, val);
    }

    private static TreeNode inset(TreeNode root, int val) {

        return insertProcess(root, val);
    }

    private static TreeNode insertProcess(TreeNode root, int val) {

        if (root == null) {

            return new TreeNode(val);
        }

        int c = root.val - val;

        if (c < 0) {

            root.right = insertProcess(root.right, val);
        } else {

            root.left = insertProcess(root.left, val);
        }

        return root;
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
