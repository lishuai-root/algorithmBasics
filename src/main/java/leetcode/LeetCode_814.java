package leetcode;

/**
 * @description: Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 * <p>
 * A subtree of a node node is node plus every node that is a descendant of node.
 * @author: LISHUAI
 * @createDate: 2022/1/11 20:59
 * @version: 1.0
 */

public class LeetCode_814 {

    public static TreeNode pruneTree(TreeNode root) {

        return process(root);
    }

    private static TreeNode process(TreeNode root) {

        if (root == null) {

            return root;
        }

        root.left = process(root.left);

        root.right = process(root.right);

        if (root.val == 0 && root.left == null && root.right == null) {

            return null;
        } else {

            return root;
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
