package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an array of events where events[i] = [startDayi, endDayi, valuei]. The ith event starts at startDayi and ends at endDayi, and if you attend this event, you will receive a value of valuei. You are also given an integer k which represents the maximum number of events you can attend.
 * <p>
 * You can only attend one event at a time. If you choose to attend an event, you must attend the entire event. Note that the end day is inclusive: that is, you cannot attend two events where one of them starts and the other ends on the same day.
 * <p>
 * Return the maximum sum of values that you can receive by attending events.
 * @author: LiShuai
 * @createDate: 2023/7/15 21:18
 * @version: 1.0
 */

public class LeetCode_1751 {

    public static int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        return maxValueProcess(events, k, 0, 0);
    }

    private static int maxValueProcess(int[][] events, int k, int left, int right) {
        if (k == 0 || right >= events.length) {
            return 0;
        }
        int p1 = maxValueProcess(events, k, left, right + 1);
        int p2 = 0;
        if (left < events[right][0]) {
            p2 = maxValueProcess(events, k - 1, events[right][1], right + 1) + events[right][2];
        }
        return Math.max(p1, p2);
    }


    public static int maxValue_dp(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int len = events.length;
        int[][] dp = new int[k + 1][len + 1];
        int[] reindex = new int[len];
        for (int i = 0; i < len; i++) {
            reindex[i] = findNext(events, i, len - 1, events[i][1]);
        }
        for (int i = k - 1; i >= 0; --i) {
            for (int j = len - 1; j >= 0; --j) {
                int v = reindex[j] == -1 ? 0 : dp[i + 1][reindex[j]];
                dp[i][j] = Math.max(events[j][2] + v, dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }

    private static int findNext(int[][] events, int l, int r, int target) {
        int ans = -1, left = l, right = r;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (events[mid][0] <= target) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }
        return ans;
    }
}
