package leetcode;

/**
 * @description: Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
 * @author: LISHUAI
 * @createDate: 2022/6/9 21:42
 * @version: 1.0
 */

public class LeetCode_653 {
    private static TreeNode head;

    public static boolean findTarget(TreeNode root, int k) {
        head = root;
        return findTargetProcess(root, k);
    }

    private static boolean findTargetProcess(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (root.val << 1 != k && exists(head, k - root.val)) {
            return true;
        }
        return findTargetProcess(root.left, k) || findTargetProcess(root.right, k);
    }

    private static boolean exists(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (root.val == k) {
            return true;
        }
        if (root.val > k) {
            return exists(root.left, k);
        } else {
            return exists(root.right, k);
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
