package leetcode;

import java.util.List;

/**
 * @description: Given the root of a binary search tree, and an integer k,
 * return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 * @author: LISHUAI
 * @createDate: 2021/11/27 23:08
 * @version: 1.0
 */

public class LeetCode_230 {

    private static int size;

    public static int kthSmallest(TreeNode root, int k) {

//        List<Integer> list = new ArrayList<>();
//
//        process(root, list);
//
//        return list.get(k - 1);
        size = k;
        return process(root).val;
    }

    private static void process(TreeNode root, List<Integer> list) {

        if (root == null) {

            return;
        }

        process(root.left, list);

        list.add(root.val);

        process(root.right, list);
    }

    private static TreeNode process(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode node = process(root.left);
        if (size == 0) {
            return node;
        }

        if (--size == 0) {
            return root;
        }
        return process(root.right);
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
