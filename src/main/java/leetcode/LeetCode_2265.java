package leetcode;

/**
 * @description: Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.
 * <p>
 * Note:
 * <p>
 * The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
 * A subtree of root is a tree consisting of root and all of its descendants.
 * @author: LISHUAI
 * @createDate: 2022/5/29 20:50
 * @version: 1.0
 */

public class LeetCode_2265 {

    public static int averageOfSubtree(TreeNode root) {
        Info info = averageOfSubtreeProcess(root);
        return info.ans;
    }

    private static Info averageOfSubtreeProcess(TreeNode root) {
        if (root == null) {
            return new Info();
        }
        Info left = averageOfSubtreeProcess(root.left);
        Info right = averageOfSubtreeProcess(root.right);
        int count = left.count + right.count + 1;
        int sum = left.sum + right.sum + root.val;
        int ans = left.ans + right.ans;
        if (sum / count == root.val) {
            ans++;
        }
        return new Info(count, sum, ans);
    }

    static class Info {
        int count, sum, ans;

        public Info() {
        }

        public Info(int count, int sum, int ans) {
            this.count = count;
            this.sum = sum;
            this.ans = ans;
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
