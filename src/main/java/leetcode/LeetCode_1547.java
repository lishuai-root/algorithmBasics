package leetcode;

import java.util.Arrays;

/**
 * @description: Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick of length 6 is labelled as follows:
 * <p>
 * <p>
 * Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
 * <p>
 * You should perform the cuts in order, you can change the order of the cuts as you wish.
 * <p>
 * The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts. When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut). Please refer to the first example for a better explanation.
 * <p>
 * Return the minimum total cost of the cuts.
 * @author: LiShuai
 * @createDate: 2023/5/28 20:37
 * @version: 1.0
 */

public class LeetCode_1547 {

    private static int[] result;
    private static int index;

    private static Integer[][] cache;

    public static void main(String[] args) {
//        int n = 9;
//        int[] cuts = {5, 6, 1, 4, 2};
        int n = 30;
        int[] cuts = {18, 15, 13, 7, 5, 26, 25, 29};
//        int n = 7;
//        int[] cuts = {1, 3, 4, 5};
        int i = minCost(n, cuts);
        System.out.println(i);
        System.out.println(minCost_dp(n, cuts));
        System.out.println(minCost_dp_02(n, cuts));
    }

    public static int minCost(int n, int[] cuts) {
        int[] dp = new int[n];
        int[] count = new int[n];
        for (int i : cuts) {
            dp[i] = 1;
        }
        for (int i = 1; i < n; i++) {
            count[i] = count[i - 1] + dp[i];
        }
        cache = new Integer[n + 1][n + 1];
        return minCostProcess(dp, 0, n);
    }

    private static int minCostProcess(int[] dp, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int k = -1;
        if (cache[left][right] != null) {
            return cache[left][right];
        }
        for (int i = left + 1; i < right; i++) {
            if (dp[i] == 1) {
                int p = minCostProcess(dp, left, i) + minCostProcess(dp, i, right);
                if (p < ans) {
                    ans = p;
                    k = i;
                }
            }
        }
        ans = ans == Integer.MAX_VALUE ? 0 : ans + right - left;
        cache[left][right] = ans;
        return ans;
    }

    public static int minCost_dp(int n, int[] cuts) {
        int[][] dp = new int[n + 1][n + 1];
        int[] cache = new int[n];
        for (int i : cuts) {
            cache[i] = 1;
        }
        Arrays.fill(dp[n], Integer.MAX_VALUE);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n; j++) {
                int p = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    if (cache[k] == 1) {
                        p = Math.min(p, dp[i][k] + dp[k][j]);
                    }
                }
                dp[i][j] = p == Integer.MAX_VALUE ? 0 : p + j - i;
            }
        }
        return dp[0][n];
    }

    public static int minCost_dp_02(int n, int[] cuts) {
        int len = cuts.length;
        int[][] dp = new int[len + 1][len + 1];
        Arrays.sort(cuts);

        for (int i = len - 1; i >= 0; i--) {
            int l = i - 1 >= 0 ? cuts[i - 1] : 0;
            for (int j = i; j < len; j++) {
                int r = j + 1 < len ? cuts[j + 1] : n;
                int p = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    p = Math.min(p, (k - 1 >= i ? dp[i][k - 1] : 0) + (k + 1 <= j ? dp[k + 1][j] : 0));
                }
                dp[i][j] = p == Integer.MAX_VALUE ? 0 : p + r - l;
            }
        }
        return dp[0][len - 1];
    }
}
