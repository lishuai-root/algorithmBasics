package leetcode;

import java.util.*;

/**
 * @description: You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.
 * <p>
 * Each minute, a node becomes infected if:
 * <p>
 * The node is currently uninfected.
 * The node is adjacent to an infected node.
 * Return the number of minutes needed for the entire tree to be infected.
 * @author: LiShuai
 * @createDate: 2023/7/13 0:21
 * @version: 1.0
 */

public class LeetCode_2385 {

    private static boolean b;
    private static int s, sl;


    public static void main(String[] args) {
//        int[] arr = {1, 5, 3, Integer.MAX_VALUE, 4, 10, 6, 9, 2};
        int[] arr = {1, 2, Integer.MAX_VALUE, 3, Integer.MAX_VALUE, 4, Integer.MAX_VALUE, 5};
        TreeNode node = makeTree(arr);
        int i = amountOfTime(node, 1);
        System.out.println(i);
    }

    private static TreeNode makeTree(int[] arr) {
        TreeNode root = new TreeNode(arr[0]);
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.addLast(root);
        int index = 0;
        while (++index < arr.length) {
            TreeNode node = queue.removeLast();
            if (arr[index] != Integer.MAX_VALUE) {
                TreeNode n = new TreeNode(arr[index]);
                queue.addLast(n);
                node.left = n;
            }
            if (++index < arr.length && arr[index] != Integer.MAX_VALUE) {
                TreeNode n = new TreeNode(arr[index]);
                queue.addLast(n);
                node.right = n;
            }
        }
        return root;
    }

    public static int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        createGraph(root, graph);
        return maxDistance(graph, new LinkedList<>(List.of(start)), new HashSet<>(Set.of(start)));
    }

    private static void createGraph(TreeNode root, Map<Integer, List<Integer>> graph) {
        List<Integer> adjacent = graph.computeIfAbsent(root.val, parameter -> new ArrayList<>());

        if (root.left != null) {
            graph.computeIfAbsent(root.left.val, param -> new ArrayList<>()).add(root.val);
            adjacent.add(root.left.val);
            createGraph(root.left, graph);
        }
        if (root.right != null) {
            graph.computeIfAbsent(root.right.val, param -> new ArrayList<>()).add(root.val);
            adjacent.add(root.right.val);
            createGraph(root.right, graph);
        }
    }

    private static int maxDistance(Map<Integer, List<Integer>> graph, Queue<Integer> queue, Set<Integer> visited) {
        int maxDistance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int currentNode = queue.poll();
                for (int neighbour : graph.get(currentNode)) {
                    if (!visited.contains(neighbour)) {
                        queue.offer(neighbour);
                        visited.add(neighbour);
                    }
                }
            }
            maxDistance++;
        }

        return maxDistance - 1;
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
