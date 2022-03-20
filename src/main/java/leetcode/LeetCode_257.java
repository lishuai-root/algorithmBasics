package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given the root of a binary tree, return all root-to-leaf paths in any order.
 * <p>
 * A leaf is a node with no children.
 * @author: LISHUAI
 * @createDate: 2022/1/11 20:43
 * @version: 1.0
 */

public class LeetCode_257 {

    public static void main(String[] args) {

        int i = -100;

        System.out.println("" + i);
    }

    public static List<String> binaryTreePaths(TreeNode root) {

        List<String> list = new ArrayList<>();

        StringBuffer sb = new StringBuffer();

        process(root, list, sb);

        return list;
    }

    private static void process(TreeNode root, List<String> list, StringBuffer sb) {

        if (root == null) {

            return;
        }

        String line = root.val + "->";

        sb.append(line);

        if (root.left == null && root.right == null) {

            list.add(sb.substring(0, sb.length() - 2));
        } else {

            process(root.left, list, sb);

            process(root.right, list, sb);
        }

        sb.delete(sb.length() - line.length(), sb.length());
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
