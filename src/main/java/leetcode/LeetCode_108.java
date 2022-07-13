package leetcode;

/**
 * @description: Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
 * <p>
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
 * @author: LISHUAI
 * @createDate: 2022/6/9 22:18
 * @version: 1.0
 */

public class LeetCode_108 {

    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTProcess(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBSTProcess(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + ((right - left) >>> 1);
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBSTProcess(nums, left, mid - 1);
        node.right = sortedArrayToBSTProcess(nums, mid + 1, right);
        return node;
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
