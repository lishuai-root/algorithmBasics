package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: You are given an array of binary strings strs and two integers m and n.
 * <p>
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 * <p>
 * A set x is a subset of a set y if all elements of x are also elements of y.
 * @author: LISHUAI
 * @createDate: 2022/5/23 19:35
 * @version: 1.0
 */

public class LeetCode_474 {

    private static Integer[][][][] cache;
    private static Map<String, Integer> cacheMap;

    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
//        String[] strs = {"011", "1", "11", "0", "010", "1", "10", "1", "1", "0", "0", "0", "01111", "011", "11", "00", "11", "10", "1", "0", "0", "0", "0", "101", "001110", "1", "0", "1", "0", "0", "10", "00100", "0", "10", "1", "1", "1", "011", "11", "11", "10", "10", "0000", "01", "1", "10", "0"};
//        int m = 44, n = 39;
//        String[] strs = {"10", "0", "1"};
//        int m = 1, n = 1;
        int i = findMaxForm(strs, m, n);
        System.out.println(i);
        int i2 = findMaxForm_02(strs, m, n);
        System.out.println(i2);
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] counts = getCounts(strs);
//        cache = new Integer[strs.length][m + 1][n + 1][strs.length + 1];
        cacheMap = new HashMap<>();
//        return findMaxFormProcess(counts, m, n, 0, 0, 0, 0);
        return findMaxFormProcess(counts, m, n, 0);
    }

    private static int[][] getCounts(String[] strs) {
        int[][] counts = new int[strs.length][2];

        for (int j = 0; j < strs.length; j++) {
            String str = strs[j];
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    counts[j][0]++;
                } else {
                    counts[j][1]++;
                }
            }
        }
        return counts;
    }

    private static int findMaxFormProcess(int[][] counts, int m, int n, int curM, int curN, int size, int index) {
        if (curM > m || curN > n) {
            return 0;
        }

        if (index >= counts.length) {
            return size;
        }

        if (cacheMap.containsKey("" + index + curM + curN + size)) {
            return cacheMap.get("" + index + curM + curN + size);
        }

//        if (cache[index][curM][curN][size] != null) {
//            return cache[index][curM][curN][size];
//        }

        int ans = 0;
        int[] ints = counts[index];

        ans = Math.max(ans, findMaxFormProcess(counts, m, n, curM + ints[0], curN + ints[1], size + 1, index + 1));
        ans = Math.max(ans, findMaxFormProcess(counts, m, n, curM, curN, size, index + 1));
//        cache[index][curM][curN][size] = ans;

        cacheMap.put("" + index + curM + curN + size, ans);
        return ans;
    }

    private static int findMaxFormProcess(int[][] counts, int m, int n, int index) {
        if (m < 0 || n < 0) {
            return 0;
        }

        if (index >= counts.length) {
            return 0;
        }
        int[] ints = counts[index];

        return Math.max(findMaxFormProcess(counts, m - ints[0], n - ints[1], index + 1) + 1,
                findMaxFormProcess(counts, m, n, index + 1));
    }

    public static int findMaxForm_dp(String[] strs, int m, int n) {
        int len = strs.length, row = 0;
        int[][] counts = getCounts(strs);
        int[][][] dp = new int[2][m + 1][n + 1];

        for (int i = len - 1; i >= 0; i--) {
            row = i & 1;
            int curM = counts[i][0];
            int curN = counts[i][1];
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    int c = 0;
                    if (j + curM <= m && k + curN <= n) {
                        c = dp[row ^ 1][j + curM][k + curN] + 1;
                    }
                    dp[row][j][k] = Math.max(dp[row ^ 1][j][k], c);
                }
            }
        }

        return dp[row][0][0];
    }

    public static int findMaxForm_02(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] counts = getCounts(strs);
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= m - counts[i][0]; j++) {
                for (int k = 0; k <= n - counts[i][1]; k++) {
                    dp[j][k] = Math.max(dp[j][k], dp[j + counts[i][0]][k + counts[i][1]] + 1);
                }
            }
        }

        return dp[0][0];
    }
}
