package leetcode;

/**
 * @description: Given a binary tree root,
 * return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * @author: LISHUAI
 * @createDate: 2022/1/4 22:28
 * @version: 1.0
 */

public class LeetCode_1373 {

    private static int ans;

    public static void main(String[] args) {

        TreeNode root = new TreeNode(5);

        root.left = new TreeNode(4);

        root.right = new TreeNode(6);

        int i = maxSumBST(root);

        System.out.println(i);
    }

    public static int maxSumBST(TreeNode root) {

        ans = 0;

        process_02(root);

        return ans;
    }

    private static Info process(TreeNode root) {

        if (root == null) {

            return null;
        }

        Info left = process(root.left);

        Info right = process(root.right);

        Info info = new Info(root.val, root.val, true, root.val);

        if (left != null) {

            info.bst &= left.bst && root.val > left.max;

            info.max = Math.max(info.max, left.max);

            info.min = Math.min(info.min, left.min);

            info.sum += left.sum;
        }

        if (right != null) {

            info.bst &= right.bst && root.val < right.min;

            info.max = Math.max(info.max, right.max);

            info.min = Math.min(info.min, right.min);

            info.sum += right.sum;
        }

        if (info.bst) {

            ans = Math.max(ans, info.sum);
        }

        return info;
    }

    private static Info process_02(TreeNode root) {

        if (root == null) {

            return new Info(Integer.MIN_VALUE, Integer.MAX_VALUE, true, 0);
        }

        Info left = process_02(root.left);

        Info right = process_02(root.right);

        left.bst = left.bst & right.bst & root.val < right.min & root.val > left.max;

        left.sum += root.val + right.sum;

        left.max = Math.max(root.val, Math.max(left.max, right.max));

        left.min = Math.min(root.val, Math.min(left.min, right.min));

        if (left.bst) {

            ans = Math.max(ans, left.sum);
        }

        return left;
    }

    public static class Info {

        int max, min, sum;

        boolean bst;

        public Info() {
        }

        public Info(boolean bst, int sum) {
            this.bst = bst;
            this.sum = sum;
        }

        public Info(int max, int min, boolean bst, int sum) {
            this.max = max;
            this.min = min;
            this.bst = bst;
            this.sum = sum;
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
