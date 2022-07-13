package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: A sequence x1, x2, ..., xn is Fibonacci-like if:
 * <p>
 * n >= 3
 * xi + xi+1 == xi+2 for all i + 2 <= n
 * Given a strictly increasing array arr of positive integers forming a sequence, return the length of the longest Fibonacci-like subsequence of arr. If one does not exist, return 0.
 * <p>
 * A subsequence is derived from another sequence arr by deleting any number of elements (including none) from arr, without changing the order of the remaining elements. For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].
 * @author: LISHUAI
 * @createDate: 2022/7/6 22:54
 * @version: 1.0
 */

public class LeetCode_873 {
    private static Integer[][][] cache;

    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] arr = {2, 4, 7, 8, 9, 10, 14, 15, 18, 23, 32, 50};   //4 14 18 32 50
//        int[] arr = {1, 3, 7, 11, 12, 14, 18};
        int i = lenLongestFibSubseq(arr);
        System.out.println(i);
    }

    public static int lenLongestFibSubseq(int[] arr) {
        int[] dp = new int[arr.length];
        cache = new Integer[arr.length][arr.length][arr.length];
        int i = lenLongestFibSubseqProcess(arr, dp, 0, 0);
        return i < 3 ? 0 : i;
    }

    private static int lenLongestFibSubseqProcess(int[] arr, int[] dp, int cur, int index) {
        if (cur >= arr.length) {
            return 0;
        }
        if (index >= 2 && cache[dp[index - 2]][dp[index - 1]][cur] != null) {
            return cache[dp[index - 2]][dp[index - 1]][cur];
        }
        int p1 = lenLongestFibSubseqProcess(arr, dp, cur + 1, index);
        int p2 = Integer.MIN_VALUE;
        if (index < 2 || arr[dp[index - 1]] + arr[dp[index - 2]] == arr[cur]) {
            dp[index] = cur;
            p2 = lenLongestFibSubseqProcess(arr, dp, cur + 1, index + 1) + 1;
        }
        int ans = Math.max(p1, p2);
        if (index >= 2) {
            cache[dp[index - 2]][dp[index - 1]][cur] = ans;
        }
        return ans;
    }

    private static int lenLongestFibSubseqProcess(int[] arr, int pre, int cur, int index, int count) {
        if (index >= arr.length) {
            return 0;
        }

        int p1 = lenLongestFibSubseqProcess(arr, pre, cur, index + 1, count);
        int p2 = Integer.MIN_VALUE;
        if (count < 2 || arr[pre] + arr[cur] == arr[index]) {
            p2 = lenLongestFibSubseqProcess(arr, cur, index, index + 1, count + 1) + 1;
        }
        return Math.max(p1, p2);
    }

    public static int lenLongestFibSubseq_02(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][2];
        int max = 0;
        for (int i = 0; i < len; i++) {
            dp[i][0] = 1;
            dp[i][1] = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (j == 0 || arr[i] == dp[j][1]) {
                    if (dp[j][0] >= dp[i][0]) {
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = arr[i] + arr[j];
                    }
                }
            }
            max = Math.max(max, dp[i][0]);
        }
        return max < 3 ? 0 : max;
    }

    public int lenLongestFibSubseq_other(int[] arr) {
        int n = arr.length;
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = arr[i];
                int b = arr[j];
                int k = 2;
                while (map.containsKey(a + b)) {
                    int c = a + b;
                    a = b;
                    b = c;
                    k++;
                }
                max = Math.max(max, k);
            }
        }
        return max == 2 ? 0 : max;
    }
}
