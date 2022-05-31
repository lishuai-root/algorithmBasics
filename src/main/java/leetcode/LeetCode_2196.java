package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description: You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,
 * <p>
 * If isLefti == 1, then childi is the left child of parenti.
 * If isLefti == 0, then childi is the right child of parenti.
 * Construct the binary tree described by descriptions and return its root.
 * <p>
 * The test cases will be generated such that the binary tree is valid.
 * @author: LISHUAI
 * @createDate: 2022/5/29 21:04
 * @version: 1.0
 */

public class LeetCode_2196 {

    private static int root;

    public static void main(String[] args) {
        int[][] nums = {{20, 15, 1}, {20, 17, 0}, {50, 20, 1}, {50, 80, 0}, {80, 19, 1}};
        TreeNode binaryTree = createBinaryTree(nums);
        System.out.println(binaryTree.val);
    }

    public static TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, int[]> map = getMap(descriptions);
//        Queue<TreeNode> queue = new LinkedList<>();
//        TreeNode head = new TreeNode(root);
//        queue.offer(head);
//        System.out.println(root);
//
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            if (map.containsKey(node.val)) {
//                int[] curs = map.get(node.val);
//                if (curs[1] != 0) {
//                    node.left = new TreeNode(curs[1]);
//                    queue.offer(node.left);
//                }
//                if (curs[0] != 0) {
//                    node.right = new TreeNode(curs[0]);
//                    queue.offer(node.right);
//                }
//            }
//        }
//        return head;
        return createBinaryTreeProcess(map, root);
    }

    private static TreeNode createBinaryTreeProcess(Map<Integer, int[]> map, int cur) {
        TreeNode root = new TreeNode(cur);

        if (map.containsKey(cur)) {
            int[] ints = map.get(cur);
            if (ints[1] != 0) {
                root.left = createBinaryTreeProcess(map, ints[1]);
            }
            if (ints[0] != 0) {
                root.right = createBinaryTreeProcess(map, ints[0]);
            }
        }
        return root;
    }

    private static Map<Integer, int[]> getMap(int[][] descriptions) {
        Map<Integer, int[]> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int[] ints : descriptions) {
            set.add(ints[0]);
            int[] curs = map.computeIfAbsent(ints[0], k -> new int[2]);
            curs[ints[2]] = ints[1];
        }
        for (int[] ints : descriptions) {
            set.remove(ints[1]);
        }
        root = set.iterator().next();
        return map;
    }

    public static TreeNode createBinaryTree_02(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<TreeNode> set = new HashSet<>();
        for (int[] ints : descriptions) {
            TreeNode p = map.computeIfAbsent(ints[0], k -> new TreeNode(ints[0]));
            TreeNode c = map.computeIfAbsent(ints[1], k -> new TreeNode(ints[1]));
            if (ints[2] == 1) {
                p.left = c;
            } else {
                p.right = c;
            }
            set.add(c);
        }
        for (int i : map.keySet()) {
            if (!set.contains(map.get(i))) {
                return map.get(i);
            }
        }
        return null;
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
