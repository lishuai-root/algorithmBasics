package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given the root of a binary tree, return the maximum width of the given tree.
 * <p>
 * The maximum width of a tree is the maximum width among all levels.
 * <p>
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
 * where the null nodes between the end-nodes are also counted into the length calculation.
 * <p>
 * It is guaranteed that the answer will in the range of 32-bit signed integer.
 * @author: LISHUAI
 * @createDate: 2022/1/7 21:58
 * @version: 1.0
 */

public class LeetCode_662 {

    public static int widthOfBinaryTree(TreeNode root) {

//        int maximum = 0, size = 1, pre = 1, left, right;
//
//        LinkedList<TreeNode> queue = new LinkedList<>();
//
//        TreeNode node;
//
//        queue.offer(root);
//
//        while (!queue.isEmpty() && pre > 0) {
//
//            pre = 0;
//
//            left = size;
//
//            right = 0;
//
//            for (int i = 0; i < size; i++) {
//
//                node = queue.pop();
//
//                if (node == null) {
//
//                    queue.offer(null);
//
//                    queue.offer(null);
//
//                    continue;
//                }
//
//                left = Math.min(left, i);
//
//                right = Math.max(right, i);
//
//                queue.offer(node.left);
//
//                queue.offer(node.right);
//
//                if (node.left != null || node.right != null) {
//
//                    pre++;
//                }
//
//            }
//
//            size <<= 1;
//            System.out.println(right + "   " + left);
//            maximum = Math.max(maximum, Math.abs(right - left) + 1);
//        }

        List<int[]> list = new ArrayList<>();

        process(root, list, 0, 0);

        int maximum = 1;

        for (int i = 1; i < list.size(); i++) {

            if (list.get(i)[0] != 0) {

                int[] arr = list.get(i);

                maximum = Math.max(maximum, arr[2] - arr[1]);
            }
        }

        return maximum;
    }

    private static void process(TreeNode root, List<int[]> list, int col, int level) {

        if (root == null) {

            return;
        }

        if (list.size() < level + 1) {

            for (int i = list.size(); i < level + 1; i++) {

                list.add(new int[3]);
            }
        }


        process(root.left, list, col * 2 + 1, level + 1);

        int[] arr = list.get(level);

        if (arr[0] == 0) {

            arr[1] = col;

            arr[0] = 1;
        } else {

            arr[2] = Math.max(arr[2], col);
        }

        process(root.right, list, col * 2 + 2, level + 1);
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
