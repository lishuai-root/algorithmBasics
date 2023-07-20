package leetcode;

/**
 * @description: You are given the root of a binary tree that consists of exactly 3 nodes: the root, its left child, and its right child.
 * <p>
 * Return true if the value of the root is equal to the sum of the values of its two children, or false otherwise.
 * @author: LiShuai
 * @createDate: 2023/7/10 23:09
 * @version: 1.0
 */

public class LeetCode_2236 {

    public boolean checkTree(TreeNode root) {
        return root.val == (root.left.val + root.right.val);
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
