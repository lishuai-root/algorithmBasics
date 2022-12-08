package leetcode;

import java.util.LinkedList;

/**
 * @description: You are given the root of a binary tree with unique values.
 * <p>
 * In one operation, you can choose any two nodes at the same level and swap their values.
 * <p>
 * Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.
 * <p>
 * The level of a node is the number of edges along the path between it and the root node.
 * @author: LISHUAI
 * @createDate: 2022/12/9 2:10
 * @version: 1.0
 */

public class LeetCode_2471 {


    public static int minimumOperations(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.addLast(root);
        int ans = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] nums = new int[size];
            int index = 0;
            while (--size >= 0) {
                TreeNode node = queue.removeFirst();
                nums[index++] = node.val;
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
            ans += selectSort(nums, 0, index);
        }
        return ans;
    }

    private static int selectSort(int[] nums, int start, int end) {
        int ans = 0;
        for (int i = start; i < end - 1; i++) {
            int max = i;
            for (int j = i + 1; j < end; j++) {
                if (nums[max] > nums[j]) {
                    max = j;
                }
            }
            if (max != i) {
                ans++;
                nums[max] = nums[i];
            }
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
