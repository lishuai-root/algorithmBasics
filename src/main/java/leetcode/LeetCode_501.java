package leetcode;

import java.util.*;

/**
 * @description: Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
 * <p>
 * If the tree has more than one mode, return them in any order.
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * @author: LISHUAI
 * @createDate: 2022/4/30 18:35
 * @version: 1.0
 */

public class LeetCode_501 {

    private static Map<Integer, Integer> map;
    private static int max, size;
    private static List<Integer> list;
    private static int[] result;
    private static int index;

    public static int[] findMode(TreeNode root) {
        map = new HashMap<>();
        max = Integer.MIN_VALUE;
        size = 0;
        list = new ArrayList<>();
        findModeProcess(root);
        int[] ans = new int[size];
        int index = 0;
//        for (int i : map.keySet()) {
//            if (map.get(i) == max) {
//                ans[index++] = i;
//            }
//        }
        for (Integer integer : list) {
            ans[index++] = integer;
        }
        return ans;
    }

    public static int[] findMode_02(TreeNode root) {
        result = new int[10001];
        index = 0;
        max = Integer.MIN_VALUE;
        findModeProcess_02(root);
        return Arrays.copyOfRange(result, 0, index);
    }

    private static int findModeProcess_02(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = findModeProcess_02(root.left);
        int right = findModeProcess_02(root.right);
        int leftVal = root.left == null ? Integer.MIN_VALUE : root.left.val;
        int rightVal = root.right == null ? Integer.MIN_VALUE : root.right.val;
        int ans = 1;

        if (root.val == leftVal) {
            ans += left;
        }
        if (root.val == rightVal) {
            ans += right;
        }
        if (ans > max) {
            max = ans;
            index = 0;
            result[index++] = root.val;
        }
        if (ans == max) {
            result[index++] = root.val;
        }
        return ans;
    }

    private static void findModeProcess(TreeNode root) {
        if (root == null) {
            return;
        }
        findModeProcess(root.left);
        findModeProcess(root.right);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        if (map.get(root.val) > max) {
            list.clear();
            list.add(root.val);
            max = map.get(root.val);
            size = 1;
        } else if (map.get(root.val) == max) {
            list.add(root.val);
            size++;
        }
    }
}


class TreeNode {
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