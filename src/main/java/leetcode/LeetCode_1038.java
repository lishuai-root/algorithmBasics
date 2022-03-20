package leetcode;

/**
 * @description: Given the root of a Binary Search Tree (BST),
 * convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
 * <p>
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * @author: LISHUAI
 * @createDate: 2022/1/6 22:25
 * @version: 1.0
 */

public class LeetCode_1038 {

    private static int sum;

    public static TreeNode bstToGst(TreeNode root) {

//        sum = 0;

        process(root, 0);

        return root;
    }

    private static void process(TreeNode root) {

        if (root == null) {

            return;
        }

        process(root.right);

        sum += root.val;

        root.val = sum;

        process(root.left);
    }

    private static int process(TreeNode root, int v) {

        if (root == null) {

            return 0;
        }

        int left = process(root.right, v);

        root.val += left;

        process(root.left, root.val);

        return left;
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
