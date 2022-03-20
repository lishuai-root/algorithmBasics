package leetcode;

/**
 * @description: Given two binary trees original and cloned and given a reference to a node target in the original tree.
 * <p>
 * The cloned tree is a copy of the original tree.
 * <p>
 * Return a reference to the same node in the cloned tree.
 * <p>
 * Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.
 * <p>
 * Follow up: Could you solve the problem if repeated values on the tree are allowed?
 * @author: LISHUAI
 * @createDate: 2021/12/14 23:06
 * @version: 1.0
 */

public class LeetCode_1379 {

    public static final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {

        return process(original, cloned, target);
    }

    private static TreeNode process(TreeNode root, TreeNode target) {

        if (root == null) {

            return null;
        }

        if (root.val == target.val) {

            return root;
        }

        TreeNode node = null;

        node = process(root.left, target);

        if (node == null) {

            node = process(root.right, target);
        }

        return node;
    }

    private static TreeNode process(TreeNode original, TreeNode cloned, TreeNode target) {

        if (original == null) {

            return null;
        }

        if (original == target) {

            return cloned;
        }

        TreeNode node = null;

        node = process(original.left, cloned.left, target);

        if (node == null) {

            node = process(original.right, cloned.right, target);
        }

        return node;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
