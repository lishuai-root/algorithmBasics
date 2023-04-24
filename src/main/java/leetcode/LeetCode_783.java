package leetcode;

/**
 * @description: Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.
 * @author: LISHUAI
 * @createDate: 2023/2/18 17:46
 * @version: 1.0
 */

public class LeetCode_783 {

    private static int ans;

    public static void main(String[] args) {
        System.out.println(1 - Integer.MIN_VALUE);
    }

    public static int minDiffInBST(TreeNode root) {
        ans = Integer.MAX_VALUE;
        minDiffInBSTProcess(root);
        return ans;
    }

    private static int[] minDiffInBSTProcess(TreeNode node) {
        if (node == null) {
            return new int[]{-1, -1};
        }
        int[] left = minDiffInBSTProcess(node.left);
        int[] right = minDiffInBSTProcess(node.right);

        int ml = left[1] == -1 ? Integer.MAX_VALUE : node.val - left[1];
        int mr = right[0] == -1 ? Integer.MAX_VALUE : right[0] - node.val;
        ans = Math.min(ans, Math.min(ml, mr));

        int min = left[0] == -1 ? node.val : left[0];
        int max = right[1] == -1 ? node.val : right[1];
        return new int[]{min, max};
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
