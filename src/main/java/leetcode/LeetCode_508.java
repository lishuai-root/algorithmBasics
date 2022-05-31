package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: Given the root of a binary tree, return the most frequent subtree sum. If there is a tie, return all the values with the highest frequency in any order.
 * <p>
 * The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
 * @author: LISHUAI
 * @createDate: 2022/5/15 20:11
 * @version: 1.0
 */

public class LeetCode_508 {
    private static int max;

    public static int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        max = 0;
        findFrequentTreeSumProcess(root, map, list);

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private static int findFrequentTreeSumProcess(TreeNode root, Map<Integer, Integer> map, List<Integer> list) {
        if (root == null) {
            return 0;
        }

        int left = findFrequentTreeSumProcess(root.left, map, list);
        int right = findFrequentTreeSumProcess(root.right, map, list);
        int ans = left + right + root.val;
        map.put(ans, map.getOrDefault(ans, 0) + 1);
        if (map.get(ans) > max) {
            max = map.get(ans);
            list.clear();
            list.add(ans);
        } else if (map.get(ans) == max) {
            list.add(ans);
        }
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
