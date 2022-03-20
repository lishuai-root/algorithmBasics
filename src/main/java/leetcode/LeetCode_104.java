package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: Given the root of a binary tree, return its maximum depth.
 * <p>
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * @author: LISHUAI
 * @createDate: 2021/11/21 17:07
 * @version: 1.0
 */

public class LeetCode_104 {

    public static void main(String[] args) {

        int max = 2;

        for (int i = 2; i <= 17; i++) {

            max = max * 2;
        }

        System.out.println(max);

        int[] ints = makeArray(100000);

        TreeNode treeNode = makeTree(ints);

        int i = maxDepth(treeNode);

        System.out.println(i);
    }

    public static TreeNode makeTree(int[] arr) {

        TreeNode head = new TreeNode(arr[0]);

        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode node = null;

        queue.add(head);

        int index = -1;

        while (!queue.isEmpty()) {

            node = queue.poll();

            if (node != null) {

                index++;

                if (2 * index + 1 < arr.length && arr[2 * index + 1] != Integer.MIN_VALUE) {

                    node.left = new TreeNode(arr[2 * index + 1]);
                }

                if (2 * index + 2 < arr.length && arr[2 * index + 2] != Integer.MIN_VALUE) {

                    node.right = new TreeNode(arr[2 * index + 2]);
                }

                queue.add(node.left);

                queue.add(node.right);
            }
        }

        return head;
    }

    public static int[] makeArray(int size) {

        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {

            arr[i] = i + 1;
        }

        return arr;
    }

    public static int maxDepth(TreeNode root) {

        int maxPath = 0;

        if (root == null) {

            return maxPath;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        int index = 1, next = 1;

        while (!queue.isEmpty()) {

            maxPath++;

            index = next;

            next = 0;

            for (int i = 0; i < index; i++) {

                root = queue.poll();

                if (root.left != null) {

                    next++;

                    queue.add(root.left);
                }

                if (root.right != null) {

                    next++;

                    queue.add(root.right);
                }
            }

        }

        return maxPath;
    }

    public static int maxDepth_02(TreeNode root) {

        if (root == null) {

            return 0;
        }

        return Math.max(maxDepth_02(root.left), maxDepth_02(root.right)) + 1;
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
