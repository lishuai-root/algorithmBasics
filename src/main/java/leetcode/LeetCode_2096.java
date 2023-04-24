package leetcode;

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

    private static TreeNode parent;

    private static int startIndex, destIndex;

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(5);
//        root.left = new TreeNode(1);
//        root.left.left = new TreeNode(3);
//        root.right = new TreeNode(2);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(4);
//        String s = getDirections(root, 3, 6);
//        System.out.println(s);
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        String directions = getDirections(root, 2, 1);
        System.out.println(directions);
    }

    public static String getDirections(TreeNode root, int startValue, int destValue) {
        int len = 100000;
        char[] chars = new char[len];
        startIndex = 0;
        destIndex = chars.length;
        parent = null;
        int[] process = getDirectionsProcess(root, startValue, destValue, chars);
        if (process[0] == 0 || process[1] == 0) {
            return "";
        }
        return String.valueOf(chars, 0, startIndex) + String.valueOf(chars, destIndex, len - destIndex);
    }

    private static int[] getDirectionsProcess(TreeNode root, int startValue, int desValue, char[] chars) {
        if (root == null || parent != null) {
            return new int[]{0, 0};
        }

        int[] left = getDirectionsProcess(root.left, startValue, desValue, chars);
        int[] right = getDirectionsProcess(root.right, startValue, desValue, chars);
        int[] ans = new int[]{left[0] + right[0], left[1] + right[1]};
        if (parent == null) {
            if (left[0] != 0 || right[0] != 0) {
                chars[startIndex++] = 'U';
            } else if (root.val == startValue) {
                ans[0] = root.val;
            }
            if (left[1] != 0) {
                chars[--destIndex] = 'L';
            } else if (right[1] != 0) {
                chars[--destIndex] = 'R';
            } else if (root.val == desValue) {
                ans[1] = root.val;
            }

            if (ans[0] != 0 && ans[1] != 0) {
                parent = root;
            }
        }
        return ans;
    }

    private static int findMinPublicParentNode(TreeNode root, int startValue, int destValue) {
        if (root == null || parent != null) {
            return 0;
        }

        int ans = 0;
        if (root.val == startValue || root.val == destValue) {
            ans = 1;
        }
        int left = findMinPublicParentNode(root.left, startValue, destValue);
        int right = findMinPublicParentNode(root.right, startValue, destValue);
        ans += left + right;
        if (ans == 2 && parent == null) {
            parent = root;
        }
        return ans;
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
