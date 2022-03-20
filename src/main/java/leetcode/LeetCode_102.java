package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description: Given the root of a binary tree,
 * return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 * @author: LISHUAI
 * @createDate: 2021/11/21 15:07
 * @version: 1.0
 */

public class LeetCode_102 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, Integer.MIN_VALUE, 3, Integer.MIN_VALUE, 4, Integer.MIN_VALUE, 5};

        TreeNode treeNode = makeTree(arr);

        printTree(treeNode);

        System.out.println();

        List<List<Integer>> lists = levelOrder(treeNode);

        for (List<Integer> list : lists) {

            for (Integer integer : list) {

                System.out.print(integer + "   ");
            }

            System.out.println();
        }

    }

    public static void printTree(TreeNode root) {

        if (root == null) {

            return;
        }

        System.out.print(root.val + "   ");

        printTree(root.left);

        printTree(root.right);
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

    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();

        if (root == null) {

            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        int index = 1, next = 1;

        while (!queue.isEmpty()) {

            List<Integer> l = new ArrayList<>();

            index = next;

            next = 0;

            for (int i = 0; i < index; i++) {

                root = queue.poll();

                l.add(root.val);

                if (root.left != null) {

                    next++;

                    queue.add(root.left);
                }

                if (root.right != null) {

                    next++;

                    queue.add(root.right);
                }
            }

            list.add(l);
        }

        return list;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();

        if (root == null) {

            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        int index = 1, next = 1, level = 1;

        while (!queue.isEmpty()) {

            List<Integer> l = new ArrayList<>();

            index = next;

            next = 0;

            for (int i = 0; i < index; i++) {

                root = queue.poll();

                l.add(root.val);

                if ((level & 1) == 1) {
                    if (root.left != null) {

                        next++;

                        queue.add(root.left);
                    }

                    if (root.right != null) {

                        next++;

                        queue.add(root.right);
                    }
                } else {

                    if (root.right != null) {

                        next++;

                        queue.add(root.right);
                    }

                    if (root.left != null) {

                        next++;

                        queue.add(root.left);
                    }
                }

            }

            list.add(l);
        }

        return list;
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
