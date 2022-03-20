package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: Given the root of a binary tree,
 * construct a 0-indexed m x n string matrix res that represents a formatted layout of the tree.
 * The formatted layout matrix should be constructed using the following rules:
 * <p>
 * The height of the tree is height and the number of rows m should be equal to height + 1.
 * The number of columns n should be equal to 2height+1 - 1.
 * Place the root node in the middle of the top row (more formally, at location res[0][(n-1)/2]).
 * For each node that has been placed in the matrix at position res[r][c], place its left child at res[r+1][c-2height-r-1] and its right child at res[r+1][c+2height-r-1].
 * Continue this process until all the nodes in the tree have been placed.
 * Any empty cells should contain the empty string "".
 * Return the constructed matrix res.
 * @author: LISHUAI
 * @createDate: 2022/2/22 22:41
 * @version: 1.0
 */

public class LeetCode_655 {

    public static List<List<String>> printTree(TreeNode root) {

        int treeHeight = treeHeight(root);
        String[][] ans = new String[treeHeight + 1][(1 << (treeHeight + 1)) - 1];
        for (String[] s : ans) {
            Arrays.fill(s, "");
        }
        int r = 0, c = ((1 << (treeHeight + 1)) - 1) >>> 1;
        printTreeProcess(root, r, c, treeHeight, ans);
        List<List<String>> list = new ArrayList<>();
        for (String[] s : ans) {
            List<String> l = Arrays.asList(s);
            list.add(l);
        }
        return list;
    }

    private static void printTreeProcess(TreeNode root, int r, int c, int height, String[][] ans) {
        if (root == null) {
            return;
        }
        ans[r][c] = root.val + "";
        printTreeProcess(root.left, r + 1, c - (1 << height - r - 1), height, ans);
        printTreeProcess(root.right, r + 1, c + (1 << height - r - 1), height, ans);
    }

    private static int treeHeight(TreeNode root) {

        return treeHeightProcess(root);
    }

    private static int treeHeightProcess(TreeNode root) {
        if (root == null) {
            return -1;
        }
        return Math.max(treeHeightProcess(root.left), treeHeightProcess(root.right)) + 1;
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
