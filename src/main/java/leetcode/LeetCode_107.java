package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given the root of a binary tree,
 * return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).
 * @author: LISHUAI
 * @createDate: 2022/5/9 3:49
 * @version: 1.0
 */

public class LeetCode_107 {

    private static void levelOrderBottomProcess(List<List<Integer>> ans, TreeNode node, int level) {
        if (node == null) {
            return;
        }
        while (level >= ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(node.val);
        levelOrderBottomProcess(ans, node.left, level - 1);
        levelOrderBottomProcess(ans, node.right, level - 1);
    }

    private int treeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(treeHeight(root.left), treeHeight(root.right)) + 1;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        int level = treeHeight(root);
        levelOrderBottomProcess(ans, root, level - 1);
        return ans;
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
