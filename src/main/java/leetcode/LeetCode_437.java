package leetcode;

import java.util.HashMap;

/**
 * @description: Given the root of a binary tree and an integer targetSum,
 * return the number of paths where the sum of the values along the path equals targetSum.
 * <p>
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 * @author: LISHUAI
 * @createDate: 2021/11/28 18:10
 * @version: 1.0
 */

public class LeetCode_437 {

    public static int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        return process(root, sum, 0, preSumMap);
    }

    // 返回方法数
    public static int process(TreeNode x, int sum, int preAll, HashMap<Integer, Integer> preSumMap) {
        if (x == null) {
            return 0;
        }
        int all = preAll + x.val;
        int ans = 0;
        if (preSumMap.containsKey(all - sum)) {
            ans = preSumMap.get(all - sum);
        }
        if (!preSumMap.containsKey(all)) {
            preSumMap.put(all, 1);
        } else {
            preSumMap.put(all, preSumMap.get(all) + 1);
        }
        ans += process(x.left, sum, all, preSumMap);
        ans += process(x.right, sum, all, preSumMap);
        if (preSumMap.get(all) == 1) {
            preSumMap.remove(all);
        } else {
            preSumMap.put(all, preSumMap.get(all) - 1);
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
