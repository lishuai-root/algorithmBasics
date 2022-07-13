package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: You are given the root of a binary tree. We install cameras on the tree nodes where
 * each camera at a node can monitor its parent, itself, and its immediate children.
 * <p>Return the minimum number of cameras needed to monitor all nodes of the tree.
 * @author: LISHUAI
 * @createDate: 2021/12/30 21:38
 * @version: 1.0
 */
public class LeetCode_968 {

    private static int[] arr = {
            0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 0
    };

    private static Map<TreeNode, Integer> map;

    private static Map<TreeNode, Integer> mapTwo;

    public static void main(String[] args) {

        TreeNode node = makeTree(arr);

        System.out.println(viewTree(node));

        int i = minCameraCover(node);

        System.out.println(i);
    }

    private static TreeNode makeTree(int[] arr) {

        return makeTreeProcess(arr, 0);
    }

    private static TreeNode makeTreeProcess(int[] arr, int index) {

        if (index >= arr.length || arr[index] == Integer.MAX_VALUE) {

            return null;
        }

        TreeNode node = new TreeNode(arr[index]);

        node.left = makeTreeProcess(arr, index * 2 + 1);

        node.right = makeTreeProcess(arr, index * 2 + 2);

        return node;
    }

    private static int viewTree(TreeNode root) {

        if (root == null) {

            return 0;
        }

        return Math.max(viewTree(root.left), viewTree(root.right)) + 1;
    }

    public static int minCameraCover_002(TreeNode root) {

        return 0;
    }

    public static int minCameraCover(TreeNode root) {

        map = new HashMap<>();

        mapTwo = new HashMap<>();

        return Math.max(process(root, 2), 1);
    }

    private static int process(TreeNode root, int level) {

        if (root == null && level == 3) {

            return 1;
        }

        if (root == null) {

            return 0;
        }

        int min, n, l2, r2;

        //        min = process(root.left, 1) + process(root.right, 1) + 1;

        min =
                map.containsKey(root) ? map.get(root) : process(root.left, 1) + process(root.right, 1) + 1;

        if (!map.containsKey(root)) {

            map.put(root, min);
        }

        //        if (level == 3) {
        //
        //            return min;
        //        }

        l2 = mapTwo.containsKey(root.left) ? mapTwo.get(root.left) : process(root.left, 2);

        if (!mapTwo.containsKey(root.left)) {

            mapTwo.put(root.left, l2);
        }

        r2 = mapTwo.containsKey(root.right) ? mapTwo.get(root.right) : process(root.right, 2);

        if (!mapTwo.containsKey(root.right)) {

            mapTwo.put(root.right, r2);
        }

        if (level == 1) {

            min = Math.min(min, l2 + r2);
        } else if (level == 2) {

            int l3 = map.containsKey(root.left) ? map.get(root.left) : process(root.left, 3);

            if (!map.containsKey(root.left)) {

                map.put(root.left, l3);
            }

            int r3 = map.containsKey(root.right) ? map.get(root.right) : process(root.right, 3);

            if (!map.containsKey(root.right)) {

                map.put(root.right, r3);
            }

            n = Math.min(l3 + r2, l2 + r3);

            n = Math.min(n, l3 + r3);

            min = Math.min(min, n);

            min = Math.max(min, 1);
        }

        return min;
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
