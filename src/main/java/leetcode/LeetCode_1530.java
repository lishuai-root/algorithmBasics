package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: You are given the root of a binary tree and an integer distance.
 * A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.
 * <p>
 * Return the number of good leaf node pairs in the tree.
 * <p>
 * The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.
 * @author: LISHUAI
 * @createDate: 2022/4/16 15:31
 * @version: 1.0
 */

public class LeetCode_1530 {

    private static int size;

    public static int countPairs(TreeNode root, int distance) {
        size = 0;
        countPairsProcess(root, distance);
        return size;
    }

    private static Map<Integer, Integer> countPairsProcess(TreeNode root, int distance) {
        if (root == null) {
            return new HashMap<>();
        }
        Map<Integer, Integer> cur = new HashMap<>();
        if (root.left == null && root.right == null) {
            cur.put(1, 1);
            return cur;
        }
        Map<Integer, Integer> left = countPairsProcess(root.left, distance);
        Map<Integer, Integer> right = countPairsProcess(root.right, distance);


        for (int i : right.keySet()) {
            for (int j : left.keySet()) {
                if (i + j <= distance) {
                    size += (left.get(j) * right.get(i));
                }

            }
            if (i + 1 <= distance) {
                cur.put(i + 1, right.get(i) + left.getOrDefault(i, 0));
            }
        }

        for (int i : left.keySet()) {
            if (i + 1 <= distance && !cur.containsKey(i + 1)) {
                cur.put(i + 1, left.get(i) + right.getOrDefault(i, 0));
            }
        }
        return cur;
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
