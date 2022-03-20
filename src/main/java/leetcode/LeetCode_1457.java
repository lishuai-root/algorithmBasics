package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: Given a binary tree where node values are digits from 1 to 9.
 * A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 * <p>
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 * @author: LISHUAI
 * @createDate: 2022/1/4 2:17
 * @version: 1.0
 */

public class LeetCode_1457 {

    private static Set<String> set;

    private static int[] arr = {2, 3, 1, 3, 1, Integer.MAX_VALUE, 1};

    public static void main(String[] args) {
        TreeNode treeNode = makeTree(arr);

        int i = pseudoPalindromicPaths(treeNode);

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

    public static int pseudoPalindromicPaths(TreeNode root) {

        set = new HashSet<>();
        int[] dp = new int[10];

        int[] result = new int[10];

        return Math.max(treeProcess(root, dp, result, root.val, root.val + ""), 1);
//        return Math.max(process(root.left, 0, root.val, 0) + process(root.right, 0, root.val, 0), 1);
    }

    private static int treeProcess(TreeNode root, int[] dp, int[] result, int s, String str) {

        if (root == null) {

            return 0;
        }

        dp[root.val] += 1;

        s ^= root.val;

        int l = treeProcess(root.left, dp, result, s, str + root.val) + treeProcess(root.right, dp, result, s, str + root.val);

        int size = 0;

        for (int i : dp) {

            if ((i & 1) != 0) {

                size++;
            }
        }

        if (size <= 1 && !set.contains(str)) {

            set.add(str + root.val);

            l++;
        }

        dp[root.val] -= 1;

        s ^= root.val;

        return l;
    }

    private static int process(TreeNode root, int sum, int r, int s) {

        if (root == null) {

            return 0;
        }

        s ^= root.val;

        r ^= root.val;

        int l = process(root.left, sum, r, s) + process(root.right, sum, r, s);

        if (s == 0 || r == root.val) {

            l++;
        }

        return l;
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
