package leetcode;

import java.util.LinkedList;

/**
 * @description: Given the root of a binary tree, return the leftmost value in the last row of the tree.
 * @author: LISHUAI
 * @createDate: 2021/12/23 23:25
 * @version: 1.0
 */

public class LeetCode_513 {

    public static int findBottomLeftValue(TreeNode root) {

        TreeNode node = root, pre = null;

        LinkedList<TreeNode> queue = new LinkedList<>();

        int size = 0, index = 1;

        queue.offer(root);

        while (!queue.isEmpty()) {

            size = index;

            index = 0;

            pre = queue.peek();

            for (int i = 0; i < size; i++) {

                node = queue.pop();

                if (node.left != null) {

                    queue.add(node.left);

                    index++;
                }

                if (node.right != null) {

                    queue.add(node.right);

                    index++;
                }
            }
        }

        return pre.val;
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
