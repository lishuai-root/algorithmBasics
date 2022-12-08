package leetcode;

/**
 * @description: Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].
 * @author: LISHUAI
 * @createDate: 2022/12/8 2:52
 * @version: 1.0
 */

public class LeetCode_938 {

    public static int rangeSumBST(TreeNode root, int low, int high) {
        return rangeSumBSTProcess(root, low, high);
    }

    private static int rangeSumBSTProcess(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }

        int ans = rangeSumBSTProcess(node.left, low, high);
        ans += rangeSumBSTProcess(node.right, low, high);
        if (node.val >= low && node.val <= high) {
            ans += node.val;
        }
        return ans;
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
