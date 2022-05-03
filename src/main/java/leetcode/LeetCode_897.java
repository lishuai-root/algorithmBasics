package leetcode;

import java.util.Queue;

/**
 * @description: Given the root of a binary search tree,
 * rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree,
 * and every node has no left child and only one right child.
 * @author: LISHUAI
 * @createDate: 2022/4/17 15:45
 * @version: 1.0
 */

public class LeetCode_897 {

    public static TreeNode increasingBST(TreeNode root) {
//        Queue<TreeNode> queue = new PriorityQueue<>((a, b) -> {
//            return a.val - b.val;
//        });
//        increasingBSTProcess(root, queue);
//        TreeNode head = new TreeNode();
//        root = head;
//        while (!queue.isEmpty()) {
//            root.right = queue.poll();
//            root = root.right;
//            root.left = null;
//        }
//        return head.right;
        root.right = increasingBSTProcess(root.right, (TreeNode) null);
        TreeNode node = root.left;
        root.left = null;
        return increasingBSTProcess(node, root);
    }

    private static void increasingBSTProcess(TreeNode root, Queue<TreeNode> queue) {
        if (root == null) {
            return;
        }
        increasingBSTProcess(root.left, queue);
        queue.add(root);
        increasingBSTProcess(root.right, queue);
    }

    private static TreeNode increasingBSTProcess(TreeNode root, TreeNode node) {
        if (root == null) {
            return node;
        }

        root.right = increasingBSTProcess(root.right, node);
        node = root.left;
        root.left = null;
        return increasingBSTProcess(node, root);
    }

    static class TreeNode {
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
