package leetcode;

import java.util.Arrays;

/**
 * @description: Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * @author: LiShuai
 * @createDate: 2023/7/19 20:52
 * @version: 1.0
 */

public class LeetCode_435 {

    public static void main(String[] args) {
        int[][] intervals = {{-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49}, {95, 99}, {58, 95}, {-31, 49}, {66, 98}, {-63, 2}, {30, 47}, {-40, -26}};
        int i = eraseOverlapIntervals_02(intervals);
        System.out.println(i);
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int len = intervals.length;
        int[] dp = new int[len + 1];
        for (int i = len - 1; i >= 0; --i) {
            int v = findNext(intervals, i, len - 1, intervals[i][1]);
            v = v == -1 ? 0 : dp[v];
            dp[i] = Math.max(v + 1, dp[i + 1]);
        }
        return len - dp[0];
    }

    private static int findNext(int[][] events, int l, int r, int target) {
        int ans = -1, left = l, right = r;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (events[mid][0] < target) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }
        return ans;
    }

    public static int eraseOverlapIntervals_02(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int prev = 0;
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= intervals[prev][1]) {
                prev = i;
                count++;
            }
        }
        return n - count;
    }
}
