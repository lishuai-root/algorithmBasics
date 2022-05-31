package leetcode;

/**
 * @description: You are given the root of a binary tree containing digits from 0 to 9 only.
 * <p>
 * Each root-to-leaf path in the tree represents a number.
 * <p>
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
 * <p>
 * A leaf node is a node with no children.
 * @author: LISHUAI
 * @createDate: 2022/5/9 4:07
 * @version: 1.0
 */

public class LeetCode_129 {

    public int sumNumbers(TreeNode root) {
        return sumNumbersProcess(root, 0);
    }

    public int sumNumbersProcess(TreeNode root, int num) {
        if (root == null) {
            return 0;
        }
        int cur = num * 10 + root.val;
        if (root.left == null && root.right == null) {
            return cur;
        }
        return sumNumbersProcess(root.left, cur) + sumNumbersProcess(root.right, cur);
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
