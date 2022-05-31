package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given the root of a binary tree and an integer targetSum,
 * return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 * <p>
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 * @author: LISHUAI
 * @createDate: 2022/5/9 4:00
 * @version: 1.0
 */

public class LeetCode_113 {

    private static List<List<Integer>> ans;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        pathSumProcess(list, root, targetSum, 0);
        return ans;
    }

    public void pathSumProcess(List<Integer> list, TreeNode root, int targetSum, int sum) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.left == null && root.right == null && sum + root.val == targetSum) {
            ArrayList<Integer> l = new ArrayList<>(list);
            ans.add(l);
        }
        pathSumProcess(list, root.left, targetSum, sum + root.val);
        pathSumProcess(list, root.right, targetSum, sum + root.val);
        list.remove(list.size() - 1);
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
