package leetcode;

import java.util.LinkedList;

/**
 * @description: Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 * @author: LISHUAI
 * @createDate: 2022/1/4 5:30
 * @version: 1.0
 */

public class LeetCode_111 {

    public static int minDepth(TreeNode root) {

        return process(root);
    }

    private static int process(TreeNode root) {

        if (root == null) {

            return 0;
        }

        int min = 0;

        if (root.left == null) {

            return process(root.right) + 1;
        }

        if (root.right == null) {

            return process(root.left);
        }

        return Math.min(process(root.left), process(root.right));
    }

    private static int minDepthProcess(TreeNode root) {

        LinkedList<TreeNode> queue = new LinkedList<>();

        int size, pre = 1, result = 0;

        if (root != null) {

            queue.offer(root);
        }

        TreeNode node;

        while (!queue.isEmpty()) {

            size = pre;

            pre = 0;

            result++;

            for (int i = 0; i < size; i++) {

                node = queue.pop();

                if (node.left == null && node.right == null) {

                    return result;
                }

                if (node.left != null) {

                    queue.offer(node.left);

                    pre++;
                }

                if (node.right != null) {

                    queue.offer(node.right);

                    pre++;
                }
            }
        }

        return result;
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
