package leetcode;

/**
 * @description: Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
 * <p>
 * A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
 * @author: LISHUAI
 * @createDate: 2021/12/16 20:25
 * @version: 1.0
 */

public class LeetCode_1026 {

    public static int maxAncestorDiff(TreeNode root) {

        return process(root, root.val, root.val);
    }

    private static int process(TreeNode root, int max, int min) {

        if (root == null) {

            return 0;
        }

        int result = Math.max(Math.abs(max - root.val), Math.abs(min - root.val));

        max = Math.max(max, root.val);

        min = Math.min(min, root.val);

        result = Math.max(result, process(root.left, max, min));

        result = Math.max(result, process(root.right, max, min));

        return result;
    }

    private static int process_01(TreeNode root, int max, int min) {

        if (root == null) {

            return 0;
        }

        int result = Math.max(Math.abs(max - root.val), Math.abs(min - root.val));

        max = Math.max(max, root.val);

        min = Math.min(min, root.val);

        return Math.max(result,
                Math.max(process(root.left, max, min), process(root.right, max, min)));
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
