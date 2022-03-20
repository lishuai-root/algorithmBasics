package leetcode;

import java.util.LinkedList;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/6 15:49
 * @version: 1.0
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 */

public class LeetCode_297 {

    private static final String SPILT = "=";

    private static final String NOT_NUMBER = "N";

    public static void main(String[] args) {


        String str = "1=2=3=N=N=4=5";

        TreeNode deserialize = deserialize_002(str);

        String serialize = serialize(deserialize);

        System.out.println(serialize);

        fn_001(deserialize);
    }


    private static void fn_001(TreeNode root) {

        LinkedList<TreeNode> list = new LinkedList<>();

        TreeNode node = null;

        list.add(root);

        while (!list.isEmpty()) {

            node = list.pop();

            if (node != null) {

                list.add(node.left);

                list.add(node.right);

                System.out.print(node.val + "   ");
            } else {

                System.out.print("null   ");
            }

        }

        System.out.println();

        System.out.println("------------");
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {

        if (root == null) {

            return "";
        }

        TreeNode tn = null;

        StringBuffer buffer = new StringBuffer();

        LinkedList<TreeNode> list = new LinkedList<>();

        list.add(root);

        while (!list.isEmpty()) {

            tn = list.pop();

            if (tn != null) {

                list.add(tn.left);

                list.add(tn.right);

                buffer.append(tn.val)
                        .append(SPILT);
            } else {

                buffer.append(NOT_NUMBER)
                        .append(SPILT);
            }
        }

        return buffer.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {

        TreeNode root = null;

        if (data == null || data.equals(NOT_NUMBER) || data.equals("")) {

            return root;
        }

        String[] strs = data.split(SPILT);

        int index = 0;

        root = new TreeNode(Integer.parseInt(strs[index--]));

        TreeNode node = null;

        LinkedList<TreeNode> list = new LinkedList<>();

        list.add(root);

        while (!list.isEmpty()) {

            node = list.pop();

            if (node != null) {

                index++;

                if (index * 2 + 1 < strs.length && !strs[index * 2 + 1].equals(NOT_NUMBER)) {

                    node.left = new TreeNode(Integer.parseInt(strs[index * 2 + 1]));
                }

                if (index * 2 + 2 < strs.length && !strs[index * 2 + 2].equals(NOT_NUMBER)) {

                    node.right = new TreeNode(Integer.parseInt(strs[index * 2 + 2]));
                }

                list.add(node.left);

                list.add(node.right);

            }
        }

        return root;
    }


    public static TreeNode deserialize_002(String data) {

        TreeNode root = null;

        if (data == null || data.equals(NOT_NUMBER) || data.equals("")) {

            return root;
        }

        String[] strs = data.split(SPILT);

        int index = 0, tail = 0, head = -1, size = 0, len = strs.length;

        root = new TreeNode(Integer.parseInt(strs[index]));

        TreeNode node = null;

        TreeNode[] queue = new TreeNode[len];

        queue[++head] = root;

        size++;

        while (size > 0) {

            node = queue[tail];

            tail = ++tail % len;

            size--;

            if (index * 2 + 1 < strs.length && !strs[index * 2 + 1].equals(NOT_NUMBER)) {

                node.left = new TreeNode(Integer.parseInt(strs[index * 2 + 1]));

                queue[(head = ++head % len)] = node.left;

                size++;
            }

            if (index * 2 + 2 < strs.length && !strs[index * 2 + 2].equals(NOT_NUMBER)) {

                node.right = new TreeNode(Integer.parseInt(strs[index * 2 + 2]));

                queue[(head = ++head % len)] = node.right;

                size++;
            }

            index++;

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
