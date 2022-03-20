package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description: Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * @author: LISHUAI
 * @createDate: 2021/11/26 22:38
 * @version: 1.0
 */

public class LeetCode_199 {

    public static void main(String[] args) {


    }


    public static List<Integer> rightSideView(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        process(root, list);

        return list;
    }

    public static void process(TreeNode root, List<Integer> list) {

        if (root == null) {

            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        int index, next = 1;

        while (!queue.isEmpty()) {

            index = next;

            next = 0;

            for (int i = 0; i < index; i++) {

                root = queue.poll();

                if (root.left != null) {

                    next++;

                    queue.add(root.left);
                }

                if (root.right != null) {

                    next++;

                    queue.add(root.right);
                }

            }

            list.add(root.val);
        }
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
