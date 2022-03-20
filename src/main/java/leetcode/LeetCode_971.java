package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: You are given the root of a binary tree with n nodes, where each node is uniquely assigned a value from 1 to n.
 * You are also given a sequence of n values voyage,
 * which is the desired pre-order traversal of the binary tree.
 * <p>
 * Any node in the binary tree can be flipped by swapping its left and right subtrees. For example, flipping node 1 will have the following effect:
 * <p>
 * <p>
 * Flip the smallest number of nodes so that the pre-order traversal of the tree matches voyage.
 * <p>
 * Return a list of the values of all flipped nodes. You may return the answer in any order.
 * If it is impossible to flip the nodes in the tree to make the pre-order traversal match voyage, return the list [-1].
 * @author: LISHUAI
 * @createDate: 2022/1/2 19:23
 * @version: 1.0
 */

public class LeetCode_971 {

    private static List<Integer> list;

    public static List<Integer> flipMatchVoyage_03(TreeNode root, int[] voyage) {

        list = new ArrayList<>();

        boolean b = process(root, voyage, 0, voyage.length - 1);

        if (!b) {

            list.clear();

            list.add(-1);
        }

        return list;
    }

    public static List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {

        int len = voyage.length;

        int[] trees = new int[len];

        list = new ArrayList<>();

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        int pre = 1, size, index = 0;

        TreeNode node;

        while (!queue.isEmpty() && index < len) {

            size = pre;

            pre = 0;

            for (int i = 0; i < size; i++) {

                node = queue.pop();

                trees[index++] = node.val;

                if (node.left != null) {

                    queue.offer(node.left);

                    pre++;
                }

                if (node.right != null) {

                    queue.offer(node.right);

                    pre++;
                }
            }
        }

        if (!queue.isEmpty()) {

            return list;
        }

        return list;
    }

    public static List<Integer> flipMatchVoyage_02(TreeNode root, int[] voyage) {

        int len = voyage.length;

        list = new ArrayList<>();

        if (root.val != voyage[0]) {

            list.add(-1);

            return list;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        int pre = 1, size, index = 1;

        TreeNode node;

        while (!queue.isEmpty() && index < len) {

            size = pre;

            pre = 0;

            for (int i = 0; i < size; i++) {

                node = queue.pop();

                if (node.left != null && node.right != null) {

                    if (index + 1 >= len || (node.left.val ^ node.right.val ^ voyage[index] ^ voyage[index + 1]) != 0) {

                        list.clear();

                        list.add(-1);

                        return list;
                    }

                    if (node.left.val == voyage[index + 1] && node.right.val == voyage[index]) {

                        list.add(node.val);

                        queue.offer(node.right);

                        queue.offer(node.left);
                    } else {

                        queue.offer(node.left);

                        queue.offer(node.right);
                    }

                    index += 2;

                    pre += 2;
                } else if (node.left != null) {

                    if (node.left.val == voyage[index]) {

                        queue.offer(node.left);

                        pre++;
                    } else {

                        list.clear();

                        list.add(-1);

                        return list;
                    }

                    index++;

                } else if (node.right != null) {

                    if (node.right.val == voyage[index]) {

                        queue.offer(node.right);

                        pre++;
                    } else {

                        list.clear();

                        list.add(-1);

                        return list;
                    }

                    index++;
                }
            }
        }

        if (index != len || !queue.isEmpty()) {

            list.clear();

            list.add(-1);
        }

        return list;
    }


    private static boolean process(TreeNode root, int[] voyage, int start, int end) {

        if (root == null || (root.left == null && root.right == null)) {

            return true;
        }


        if (start >= voyage.length - 1) {

            return false;
        }


        int l = start + 1, r = -1;

        boolean bl = false;

        if (root.left != null && root.right != null) {

            for (int i = start + 2; i <= end; i++) {

                if (voyage[i] == root.right.val || voyage[i] == root.left.val) {

                    r = i;

                    break;
                }
            }

            if (r == -1 || (root.left.val ^ root.right.val ^ voyage[l] ^ voyage[r]) != 0) {

                return false;
            }

            if (root.left.val == voyage[l]) {

                bl = process(root.left, voyage, l, r - 1) && process(root.right, voyage, r, end);
            } else {

                list.add(root.val);

                bl = process(root.right, voyage, l, r - 1) && process(root.left, voyage, r, end);
            }
        } else if (root.left != null) {

            if (root.left.val != voyage[l]) {

                return false;
            }

            bl = process(root.left, voyage, l, end);
        } else {

            if (root.right.val != voyage[l]) {

                return false;
            }

            bl = process(root.right, voyage, l, end);
        }

        return bl;
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
