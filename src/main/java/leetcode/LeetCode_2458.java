package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.
 * <p>
 * You have to perform m independent queries on the tree where in the ith query you do the following:
 * <p>
 * Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
 * Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.
 * <p>
 * Note:
 * <p>
 * The queries are independent, so the tree returns to its initial state after each query.
 * The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
 * @author: LISHUAI
 * @createDate: 2023/3/11 16:06
 * @version: 1.0
 */

public class LeetCode_2458 {

    public static void main(String[] args) {
        int[] nodes = new int[]{1, 3, 4, 2, -1, 6, 5, -1, -1, -1, -1, -1, 7};
        int[] queries = new int[]{4};
        TreeNode root = makeTree(nodes);
        int[] ints = treeQueries(root, queries);
        for (int i : ints) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] treeQueries(TreeNode root, int[] queries) {
        List<int[]> levels = new ArrayList<>();
        List<List<Integer>> heights = new ArrayList<>();
        int height = treeQueriesProcess(levels, heights, root, 0);
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] ints = levels.get(queries[i]);
            if (ints[1] < height) {
                answer[i] = height;
                continue;
            }
            List<Integer> list = heights.get(ints[0]);
            if (list.size() == 1) {
                answer[i] = ints[0] - 1;
                continue;
            }
            answer[i] = list.get(0);
        }
        return answer;
    }

    private static int treeQueriesProcess(List<int[]> levels, List<List<Integer>> heights, TreeNode node, int level) {
        if (node == null) {
            return level - 1;
        }
        int left = treeQueriesProcess(levels, heights, node.left, level + 1);
        int right = treeQueriesProcess(levels, heights, node.right, level + 1);
        int val = node.val;
        while (levels.size() <= val) {
            levels.add(new int[2]);
        }
        while (heights.size() <= level) {
            heights.add(new ArrayList<>());
        }
        int[] ints = levels.get(val);
        ints[0] = level;
        ints[1] = Math.max(left, right);
        List<Integer> list = heights.get(level);
        if (list.size() == 0) {
            list.add(ints[1]);
        } else if (list.size() == 1) {
            int k = list.get(0);
            list.set(0, Math.min(k, ints[1]));
            list.add(Math.max(k, ints[1]));
        } else {
            int k = Math.min(list.get(1), ints[1]);
            list.set(1, Math.max(list.get(1), ints[1]));
            list.set(0, Math.max(k, list.get(0)));
        }

        return ints[1];
    }

    private static TreeNode makeTree(int[] nodes) {
        if (nodes.length == 0 || nodes[0] == -1) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(nodes[0]);
        queue.addLast(root);
        int index = 1;

        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            if (index < nodes.length) {
                int n = nodes[index++];
                if (n != -1) {
                    node.left = new TreeNode(n);
                    queue.addLast(node.left);
                }
            }
            if (index < nodes.length) {
                int n = nodes[index++];
                if (n != -1) {
                    node.right = new TreeNode(n);
                    queue.addLast(node.right);
                }
            }
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
