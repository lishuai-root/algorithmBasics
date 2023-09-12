package leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * @description: You are given an integer array arr. From some starting index, you can make a series of jumps. The (1st, 3rd, 5th, ...) jumps in the series are called odd-numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even-numbered jumps. Note that the jumps are numbered, not the indices.
 * <p>
 * You may jump forward from index i to index j (with i < j) in the following way:
 * <p>
 * During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such that arr[i] <= arr[j] and arr[j] is the smallest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
 * During even-numbered jumps (i.e., jumps 2, 4, 6, ...), you jump to the index j such that arr[i] >= arr[j] and arr[j] is the largest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
 * It may be the case that for some index i, there are no legal jumps.
 * A starting index is good if, starting from that index, you can reach the end of the array (index arr.length - 1) by jumping some number of times (possibly 0 or more than once).
 * <p>
 * Return the number of good starting indices.
 * @author: LiShuai
 * @createDate: 2023/9/6 21:01
 * @version: 1.0
 */

public class LeetCode_975 {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 1, 4};
        int i = oddEvenJumps_02(arr);
        System.out.println(i);
    }

    public static int oddEvenJumps(int[] arr) {
        int len = arr.length, ans = 1;
        boolean[][] dp = new boolean[len][2];
        dp[len - 1][0] = true;
        dp[len - 1][1] = true;

        for (int i = len - 2; i >= 0; --i) {
            int min = -1, max = -1;
            for (int j = len - 1; j > i; --j) {
                if (arr[j] <= arr[i]) {
                    min = min == -1 || arr[j] >= arr[min] ? j : min;
                }
                if (arr[j] >= arr[i]) {
                    max = max == -1 || arr[j] <= arr[max] ? j : max;
                }
            }
            boolean bo = max != -1 && dp[max][0];
            boolean be = min != -1 && dp[min][1];
            dp[i][1] = bo;
            dp[i][0] = be;
            if (dp[i][1]) {
                ++ans;
            }
        }
        return ans;
    }

    public static int oddEvenJumps_02(int[] arr) {
        int len = arr.length;
        int ans = 1;
        boolean[][] dp = new boolean[len][2];
        dp[len - 1][0] = true;
        dp[len - 1][1] = true;
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        map.put(arr[len - 1], len - 1);
        for (int i = len - 2; i >= 0; --i) {
            Map.Entry<Integer, Integer> min = map.floorEntry(arr[i]);
            Map.Entry<Integer, Integer> max = map.ceilingEntry(arr[i]);
            dp[i][1] = max != null && dp[max.getValue()][0];
            dp[i][0] = min != null && dp[min.getValue()][1];
            if (dp[i][1]) {
                ++ans;
            }
            map.put(arr[i], i);
        }
        return ans;
    }
}
