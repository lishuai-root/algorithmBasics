package leetcode;

/**
 * @description: Given the root of a binary tree, flatten the tree into a "linked list":
 * <p>
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 * @author: LISHUAI
 * @createDate: 2021/11/21 19:25
 * @version: 1.0
 */

public class LeetCode_114 {

    public static TreeNode tail;

    public static void flatten(TreeNode root) {

        tail = root;

        process(tail);
    }

    public static void process(TreeNode root) {

        if (root == null) {

            return;
        }

        TreeNode node = root.right;

        if (root.left != null) {

            tail.right = tail.left;

            tail.left = null;

            tail = tail.right;

            process(tail);
        }

        if (node != null) {

            tail.right = node;

            tail.left = null;

            tail = tail.right;

            process(tail);
        }

    }


    public class TreeNode {
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
