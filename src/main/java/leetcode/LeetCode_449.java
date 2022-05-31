package leetcode;

import java.util.LinkedList;

/**
 * @description: Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work.
 * You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.
 * <p>
 * The encoded string should be as compact as possible.
 * @author: LISHUAI
 * @createDate: 2022/2/21 20:44
 * @version: 1.0
 */

public class LeetCode_449 {

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        return serializeProcess(root);
    }

    private static String serializeProcess(TreeNode root) {
        TreeNode tn;

        StringBuffer buffer = new StringBuffer();

        LinkedList<TreeNode> list = new LinkedList<>();

        list.add(root);

        while (!list.isEmpty()) {

            tn = list.pop();

            if (tn != null) {

                list.add(tn.left);

                list.add(tn.right);

                buffer.append(tn.val)
                        .append("-");
            } else {

                buffer.append("N")
                        .append("-");
            }
        }

        return buffer.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        TreeNode root = null;

        if (data == null || "N".equals(data) || "".equals(data)) {

            return root;
        }

        String[] strs = data.split("-");

        int index = 0;

        root = new TreeNode(Integer.parseInt(strs[index--]));

        TreeNode node = null;

        LinkedList<TreeNode> list = new LinkedList<>();

        list.add(root);

        while (!list.isEmpty()) {

            node = list.pop();

            if (node != null) {

                index++;

                if (index * 2 + 1 < strs.length && !"N".equals(strs[index * 2 + 1])) {

                    node.left = new TreeNode(Integer.parseInt(strs[index * 2 + 1]));
                }

                if (index * 2 + 2 < strs.length && !"N".equals(strs[index * 2 + 2])) {

                    node.right = new TreeNode(Integer.parseInt(strs[index * 2 + 2]));
                }

                list.add(node.left);

                list.add(node.right);

            }
        }

        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
