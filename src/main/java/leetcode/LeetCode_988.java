package leetcode;

/**
 * @description: You are given the root of a binary tree where each node has a value in the range [0, 25] representing the letters 'a' to 'z'.
 * <p>
 * Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
 * <p>
 * As a reminder, any shorter prefix of a string is lexicographically smaller.
 * <p>
 * For example, "ab" is lexicographically smaller than "aba".
 * A leaf of a node is a node that has no children.
 * @author: LISHUAI
 * @createDate: 2022/5/9 4:14
 * @version: 1.0
 */

public class LeetCode_988 {

    private static int size;
    private static String ansStr;


    private static void reMin(String str) {
        if (ansStr == null || "".equals(ansStr)) {
            ansStr = str;
            return;
        }
        int len = Math.min(str.length(), ansStr.length());
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) < ansStr.charAt(i)) {
                ansStr = str;
                return;
            } else if (str.charAt(i) > ansStr.charAt(i)) {
                return;
            }
        }
        if (str.length() < ansStr.length()) {
            ansStr = str;
        }
    }

    public static void main(String[] args) {

    }

    public String smallestFromLeaf(TreeNode root) {
        size = treeHeight(root);
        ansStr = null;
        System.out.println(size);
        char[] chars = new char[size];
        smallestFromLeafProcess(root, chars, size - 1);
        return ansStr;
    }

    private void smallestFromLeafProcess(TreeNode root, char[] chars, int level) {
        if (root == null) {
            return;
        }
        chars[level] = (char) (root.val + 'a');
        if (root.left == null && root.right == null) {
            reMin(String.valueOf(chars, level, size - level));
            return;
        }
        smallestFromLeafProcess(root.left, chars, level - 1);
        smallestFromLeafProcess(root.right, chars, level - 1);

    }

    private int treeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(treeHeight(root.left), treeHeight(root.right)) + 1;
    }

    public class TreeNode {
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
