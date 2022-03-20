package leetcode;

import java.util.LinkedList;

/**
 * @description: Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * <p>
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 * @author: LISHUAI
 * @createDate: 2021/12/30 21:10
 * @version: 1.0
 */

public class LeetCode_1161 {

    public static int maxLevelSum(TreeNode root) {

        if (root == null) {

            return 0;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        int size, pre = 1, maxSum = root.val, minLevel = 1, sum, level = 0;

        TreeNode node;

        while (!queue.isEmpty()) {

            level++;

            size = pre;

            pre = 0;

            sum = 0;

            for (int i = 0; i < size; i++) {

                node = queue.pop();

                sum += node.val;

                if (node.left != null) {

                    queue.offer(node.left);

                    pre++;
                }

                if (node.right != null) {

                    queue.offer(node.right);

                    pre++;
                }

            }

            if (sum > maxSum) {

                maxSum = sum;

                minLevel = level;
            }
        }

        return minLevel;
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
