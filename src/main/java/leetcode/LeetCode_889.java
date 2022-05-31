package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: Given two integer arrays,
 * preorder and postorder where preorder is the preorder traversal of a binary tree of distinct values and postorder is the postorder traversal of the same tree,
 * reconstruct and return the binary tree.
 * <p>
 * If there exist multiple answers, you can return any of them.
 * @author: LISHUAI
 * @createDate: 2022/5/29 19:36
 * @version: 1.0
 */

public class LeetCode_889 {

    private static int index;

    public static void main(String[] args) {
//        int[] preorder = {1, 2, 4, 5, 3, 6, 7}, postorder = {4, 5, 2, 6, 7, 3, 1};
        int[] preorder = {1, 2, 4, 5, 3, 6, 7}, postorder = {4, 5, 2, 6, 7, 3, 1};
        TreeNode treeNode = constructFromPrePost(preorder, postorder);
        showB(treeNode);
    }

    private static void show(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        show(root.left);
        show(root.right);
    }

    private static void showB(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            root = queue.poll();
            System.out.print(root.val + " ");
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
        }
    }

    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        index = 0;
        return constructFromPrePostProcess(preorder, postorder, 0, postorder.length - 1);
    }

    private static TreeNode constructFromPrePostProcess(int[] preorder, int[] postorder, int start, int end) {
        if (start > end) {
            return null;
        }
        index++;
        int left = -1;
        TreeNode root = new TreeNode(postorder[end]);
        for (int i = start; i < end; i++) {
            if (preorder[index] == postorder[i]) {
                left = i;
                break;
            }
        }

        if (left != -1) {
            root.left = constructFromPrePostProcess(preorder, postorder, start, left);
            root.right = constructFromPrePostProcess(preorder, postorder, left + 1, end - 1);
        }

        return root;
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
