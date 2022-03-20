package leetcode;

/**
 * @description: Given the root of a binary search tree and the lowest and highest boundaries as low and high,
 * trim the tree so that all its elements lies in [low, high].
 * Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant).
 * It can be proven that there is a unique answer.
 * <p>
 * Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
 * @author: LISHUAI
 * @createDate: 2021/12/25 22:38
 * @version: 1.0
 */

public class LeetCode_669 {

    private static TreeNode head;


    public static TreeNode trimBST(TreeNode root, int low, int high) {

        head = null;

        process(root, low, high);

        return head;
    }

    private static void process(TreeNode root, int low, int high) {

        if (root == null) {

            return;
        }

        TreeNode left = root.left, right = root.right;

        if (root.val >= low && root.val <= high) {

            addTreeNode(root);
        }

        process(left, low, high);

        process(right, low, high);
    }

    private static void addTreeNode(TreeNode node) {

        node.left = null;

        node.right = null;

        if (head == null) {

            head = node;

            return;
        }

        TreeNode pre = null, n = head;

        int i = 0;

        while (n != null) {

            pre = n;

            i = n.val - node.val;

            if (i > 0) {

                n = n.left;
            } else if (i < 0) {

                n = n.right;
            }
        }

        if (i > 0) {

            pre.left = node;
        } else if (i < 0) {

            pre.right = node;
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
