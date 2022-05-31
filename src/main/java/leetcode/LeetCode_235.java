package leetcode;

/**
 * @description: Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * @author: LISHUAI
 * @createDate: 2022/5/17 22:40
 * @version: 1.0
 */

public class LeetCode_235 {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorProcess(root, p.val, q.val);
    }

    private static TreeNode lowestCommonAncestorProcess(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        }

        TreeNode left = lowestCommonAncestorProcess(root.left, p, q);
        TreeNode right = lowestCommonAncestorProcess(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left == right) {
            if (root.val == p || root.val == q) {
                return root;
            }
            return null;
        }
        if (left != null) {
            if ((left.val == p && root.val == q) || (left.val == q && root.val == p)) {
                return root;
            } else {
                return left;
            }
        } else {
            if ((right.val == p && root.val == q) || (right.val == q && root.val == p)) {
                return root;
            } else {
                return right;
            }
        }
    }

    public static TreeNode lowestCommonAncestor_02(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorProcess_02(root, Math.max(p.val, q.val), Math.min(p.val, q.val));
    }

    private static TreeNode lowestCommonAncestorProcess_02(TreeNode root, int max, int min) {
        if (root == null) {
            return null;
        }
        TreeNode node;
        if (root.val < min) {
            node = lowestCommonAncestorProcess_02(root.right, max, min);
        } else if (root.val > max) {
            node = lowestCommonAncestorProcess_02(root.left, max, min);
        } else {
            node = root;
        }
        return node;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
