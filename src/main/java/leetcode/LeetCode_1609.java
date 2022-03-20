package leetcode;

import java.util.LinkedList;

/**
 * @description: A binary tree is named Even-Odd if it meets the following conditions:
 * <p>
 * The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
 * For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
 * For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
 * Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.
 * @author: LISHUAI
 * @createDate: 2022/1/10 21:10
 * @version: 1.0
 */

public class LeetCode_1609 {

    public static boolean isEvenOddTree(TreeNode root) {

        LinkedList<TreeNode> queue = new LinkedList<>();

        TreeNode node;

        int size, level = 0, pre;

        queue.offer(root);

        while (!queue.isEmpty()) {

            size = queue.size();

            pre = (level & 1) == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            for (int i = 0; i < size; i++) {

                node = queue.pop();

                if (((node.val ^ level) & 1) == 0) {

                    return false;
                }

                if ((level & 1) == 0 && node.val <= pre) {

                    return false;
                } else if ((level & 1) == 1 && node.val >= pre) {

                    return false;
                }


                pre = node.val;

                if (node.left != null) {

                    queue.offer(node.left);
                }

                if (node.right != null) {

                    queue.offer(node.right);
                }
            }

            level = ++level & 1;
        }

        return true;
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
