package leetcode;

/**
 * @description: Given the root of a binary tree,
 * split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.
 * <p>
 * Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.
 * <p>
 * Note that you need to maximize the answer before taking the mod and not after taking it.
 * @author: LISHUAI
 * @createDate: 2022/1/4 20:32
 * @version: 1.0
 */

public class LeetCode_1339 {

    private static final int MODULO = 1000000000 + 7;

    private static long sum;

    private static long max;

    private static long min;

    public static int maxProduct(TreeNode root) {

//        max = 0;

        sum = 0;

        min = Integer.MAX_VALUE;

        treeSum(root);

        process_02(root);

        sum -= min;

        long l = sum / 2;

        long r = sum / 2;

        if (sum % 2 == 1) {

            l++;
        }

        l %= MODULO;

        r %= MODULO;

        return (int) ((l * r) % MODULO);
    }

    private static long process_02(TreeNode root) {

        if (root == null) {

            return 0;
        }

        long l = process_02(root.left);

        long r = process_02(root.right);

        min = Math.min(min, Math.min(Math.abs(sum - 2 * l), Math.abs(sum - 2 * r)));

        return (l + r + root.val) % MODULO;
    }

    private static long process(TreeNode root) {

        if (root == null) {

            return 0;
        }

        long l = process(root.left);

        long r = process(root.right);

        max = Math.max(max, Math.max(l * (sum - l), r * (sum - r)));

        return (l + r + root.val) % MODULO;
    }

    private static void treeSum(TreeNode root) {

        if (root == null) {

            return;
        }

        sum += root.val % MODULO;

        treeSum(root.left);

        treeSum(root.right);
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
