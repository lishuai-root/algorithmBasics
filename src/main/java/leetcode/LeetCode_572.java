package leetcode;

/**
 * @description: Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
 * <p>
 * A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.
 * @author: LISHUAI
 * @createDate: 2022/5/15 20:57
 * @version: 1.0
 */

public class LeetCode_572 {

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return isSubtreeProcess(root, subRoot);
    }

    private static boolean isSubtreeProcess(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }

        if (isEqualTree(root, subRoot)) {
            return true;
        }
        return isSubtreeProcess(root.left, subRoot) || isSubtreeProcess(root.right, subRoot);
    }

    private static boolean isEqualTree(TreeNode root1, TreeNode root2) {
        if (root1 == root2) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return root1.val == root2.val && isEqualTree(root1.left, root2.left) && isEqualTree(root1.right, root2.right);
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
