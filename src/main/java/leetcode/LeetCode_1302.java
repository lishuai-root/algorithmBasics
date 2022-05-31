package leetcode;

/**
 * @description: Given the root of a binary tree, return the sum of values of its deepest leaves.
 * @author: LISHUAI
 * @createDate: 2022/5/15 19:29
 * @version: 1.0
 */

public class LeetCode_1302 {

    private static int TreeLevel, ans;

    public static int deepestLeavesSum(TreeNode root) {
        TreeLevel = 0;
        ans = 0;
        deepestLeavesSumProcess(root, 0);
        return ans;
    }

    private static void deepestLeavesSumProcess(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (level > TreeLevel) {
            TreeLevel = level;
            ans = root.val;
        } else if (level == TreeLevel) {
            ans += root.val;
        }
        deepestLeavesSumProcess(root.left, level + 1);
        deepestLeavesSumProcess(root.right, level + 1);
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
