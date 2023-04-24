package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company is the one with headID.
 * <p>
 * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1. Also, it is guaranteed that the subordination relationships have a tree structure.
 * <p>
 * The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his direct subordinates, and they will inform their subordinates, and so on until all employees know about the urgent news.
 * <p>
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).
 * <p>
 * Return the number of minutes needed to inform all the employees about the urgent news.
 * @author: LISHUAI
 * @createDate: 2022/12/11 17:44
 * @version: 1.0
 */

public class LeetCode_1376 {

    public static void main(String[] args) {
        int n = 6, headID = 2;
        int[] manager = {2, 2, -1, 2, 2, 2}, informTime = {0, 0, 1, 0, 0, 0};
        int i = numOfMinutes_02(n, headID, manager, informTime);
        System.out.println(i);
    }


    public static int numOfMinutes_02(int n, int headID, int[] manager, int[] informTime) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        int ans = 0;
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] != -1) {
                ans = Math.max(ans, numOfMinutesProcess(manager, informTime, dp, i) - informTime[i]);
            }
        }
        return ans;
    }

    private static int numOfMinutesProcess(int[] manger, int[] informTime, int[] dp, int cur) {
        if (manger[cur] == -1) {
            return informTime[cur];
        }
        if (dp[cur] != -1) {
            return dp[cur];
        }
        int ans = numOfMinutesProcess(manger, informTime, dp, manger[cur]) + informTime[cur];
        dp[cur] = ans;
        return ans;
    }

    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        TreeNode[] tree = new TreeNode[n];
        TreeNode root = null;
        for (int i = 0; i < manager.length; i++) {
            tree[i] = new TreeNode(informTime[i]);
            if (manager[i] == -1) {
                root = tree[i];
            }
        }
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] != -1) {
                tree[manager[i]].child.add(tree[i]);
            }
        }
        return numOfMinutesProcess(root);
    }

    private static int numOfMinutesProcess(TreeNode node) {
        if (node == null || node.child.isEmpty()) {
            return 0;
        }

        int ans = 0;
        for (TreeNode n : node.child) {
            ans = Math.max(ans, numOfMinutesProcess(n));
        }
        return ans + node.value;
    }


    static class TreeNode {
        List<TreeNode> child;
        int value;

        public TreeNode(int value) {
            this.child = new ArrayList<>();
            this.value = value;
        }
    }
}
