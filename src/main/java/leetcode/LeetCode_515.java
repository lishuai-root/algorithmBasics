package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
 * @author: LISHUAI
 * @createDate: 2022/1/7 21:43
 * @version: 1.0
 */

public class LeetCode_515 {

    public static List<Integer> largestValues(TreeNode root) {

//        int pre = 1, size;
//
//        TreeNode node, max;
//
//        LinkedList<TreeNode> queue = new LinkedList<>();
//
//        List<Integer> list = new ArrayList<>();
//
//        if (root == null) {
//
//            return list;
//        }
//
//        queue.offer(root);
//
//        max = root;
//
//        while (!queue.isEmpty()) {
//
//            size = pre;
//
//            pre = 0;
//
//            for (int i = 0; i < size; i++) {
//
//                node = queue.pop();
//
//                max = node.val > max.val ? node : max;
//
//                if (node.left != null) {
//
//                    queue.offer(node.left);
//
//                    pre++;
//                }
//
//                if (node.right != null) {
//
//                    queue.offer(node.right);
//
//                    pre++;
//                }
//            }
//
//            list.add(max.val);
//
//            max = queue.peek();
//        }

        List<Integer> list = new ArrayList<>();

        process(root, list, 1);

        return list;
    }

    private static void process(TreeNode root, List<Integer> list, int level) {

        if (root == null) {

            return;
        }

        if (list.size() < level) {

            for (int i = list.size(); i < level; i++) {

                list.add(Integer.MIN_VALUE);
            }
        }

        list.add(level - 1, Math.max(root.val, list.remove(level - 1)));

        process(root.left, list, level + 1);

        process(root.right, list, level + 1);
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
