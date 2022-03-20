package leetcode;

/**
 * @description: Given the root of a binary tree, return the sum of values of nodes with an even-valued grandparent.
 * If there are no nodes with an even-valued grandparent, return 0.
 * <p>
 * A grandparent of a node is the parent of its parent if it exists.
 * @author: LISHUAI
 * @createDate: 2021/12/16 22:34
 * @version: 1.0
 */

public class LeetCode_1315 {

    public static int sumEvenGrandparent(TreeNode root) {

        return process(root, 1, 1);
    }

    private static int process(TreeNode root, int parent, int grandparent) {

        if (root == null) {

            return 0;
        }

        int sum = (grandparent & 1) == 0 ? root.val : 0;

        sum += process(root.left, root.val, parent);

        sum += process(root.right, root.val, parent);

        return sum;
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
