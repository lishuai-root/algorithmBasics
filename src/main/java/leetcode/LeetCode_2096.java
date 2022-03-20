package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @description: You are given the root of a binary tree with n nodes.
 * Each node is uniquely assigned a value from 1 to n.
 * You are also given an integer startValue representing the value of the start node s,
 * and a different integer destValue representing the value of the destination node t.
 * <p>
 * Find the shortest path starting from node s and ending at node t.
 * Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'.
 * Each letter indicates a specific direction:
 * <p>
 * 'L' means to go from a node to its left child node.
 * 'R' means to go from a node to its right child node.
 * 'U' means to go from a node to its parent node.
 * Return the step-by-step directions of the shortest path from node s to node t.
 * @author: LISHUAI
 * @createDate: 2021/12/16 20:53
 * @version: 1.0
 */

public class LeetCode_2096 {


    private static boolean bl;

    private static String result;

    private static int size = 0;

    private static StringBuilder sb = new StringBuilder();

    public static String getDirections(TreeNode root, int startValue, int destValue) {

        process(lowestCommonAncestor(root, startValue, destValue), startValue, destValue, "", 0);

        bl = true;

        for (int i = 0; i < size; i++) {

            sb.append("U");
        }

        sb.append(result);

        return sb.toString();
    }

    private static void process(TreeNode root, int start, int dest, String str, int len) {

        if (root == null) {

            return;
        }

        if (root.val == start) {

            size = len;
        }

        if (root.val == dest) {

            result = str;

            return;
        }

        process(root.left, start, dest, str + "L", len + 1);

        process(root.right, start, dest, str + "R", len + 1);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, int start, int dest) {

        Set<TreeNode> set = new HashSet<>();

        bl = false;

        process_02(root, start, dest, set);

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.addLast(root);

        TreeNode node;

        while (!queue.isEmpty()) {

            node = queue.pollFirst();

            if (set.contains(node)) {

                System.out.println(node.val);
                return node;
            }

            if (node.left != null) {

                queue.addLast(node.left);
            }

            if (node.right != null) {

                queue.addLast(node.right);
            }
        }

        return null;
    }

    public static void process_02(TreeNode root, int start, int dest, Set<TreeNode> set) {

        if (root == null) {

            return;
        }

        process_02(root.left, start, dest, set);

        if (root.val == start || root.val == dest) {

            bl = !bl;
        }

        if (bl || root.val == start || root.val == dest) {

            set.add(root);
        }

        process_02(root.right, start, dest, set);
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
