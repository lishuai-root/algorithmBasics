package leetcode;

/**
 * @description: Given the root of a binary tree, construct a string consisting of parenthesis and integers from a binary tree with the preorder traversal way, and return it.
 * <p>
 * Omit all the empty parenthesis pairs that do not affect the one-to-one mapping relationship between the string and the original binary tree.
 * @author: LISHUAI
 * @createDate: 2022/9/7 20:22
 * @version: 1.0
 */

public class LeetCode_606 {

    public static String tree2str(TreeNode root) {
        StringBuilder sbr = new StringBuilder();
        tree2strProcess(sbr, root);
        return sbr.substring(1, sbr.length() - 1).toString();
    }

    private static void tree2strProcess(StringBuilder sbr, TreeNode root) {
        sbr.append("(");
        if (root == null) {
            sbr.append(")");
            return;
        }
        sbr.append(root.val);
        if (root.right == null) {
            if (root.left != null) {
                tree2strProcess(sbr, root.left);
            }
        } else {
            tree2strProcess(sbr, root.left);
            tree2strProcess(sbr, root.right);
        }
        sbr.append(")");
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
