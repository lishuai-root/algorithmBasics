package leetcode;

import java.util.Arrays;

/**
 * @description: There are n different online courses numbered from 1 to n.
 * You are given an array courses where courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for durationi days and must be finished before or on lastDayi.
 * <p>
 * You will start on the 1st day and you cannot take two or more courses simultaneously.
 * <p>
 * Return the maximum number of courses that you can take.
 * @author: LISHUAI
 * @createDate: 2022/6/23 19:29
 * @version: 1.0
 */

public class LeetCode_630 {

    public static void main(String[] args) {
//        int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
        int[][] courses = {{5, 15}, {3, 19}, {6, 7}, {2, 10}, {5, 16}, {8, 14}, {10, 11}, {2, 19}};
//        int[][] courses = {{3, 2}, {4, 3}};
//        int[][] courses = {{1, 2}, {4, 3}};
        System.out.println(scheduleCourse_dp(courses));
    }

    public static int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> {
            int c = a[1] - b[1];
            if (c == 0) {
                c = a[0] - b[0];
            }
            return c;
        });

        return scheduleCourseProcess(courses, 0, 0);
    }

    private static int scheduleCourseProcess(int[][] courses, int index, int left) {
        if (index >= courses.length) {
            return 0;
        }

        int ans = 0;
        int[] curs = courses[index];
        if (curs[0] + left <= curs[1]) {
            ans = scheduleCourseProcess(courses, index + 1, left + curs[0]) + 1;
        }
        ans = Math.max(ans, scheduleCourseProcess(courses, index + 1, left));
        return ans;
    }

    public static int scheduleCourse_dp(int[][] courses) {
        Arrays.sort(courses, (a, b) -> {
            int c = a[1] - b[1];
            if (c == 0) {
                c = a[0] - b[0];
            }
            return c;
        });
        int len = courses.length;
        int max = courses[len - 1][1];
        int[] dp = new int[max + 1];
        for (int i = len - 1; i >= 0; i--) {
            int c = courses[i][1] - courses[i][0];
            for (int j = 0; j <= c; j++) {
                dp[j] = Math.max(dp[j + courses[i][0]] + 1, dp[j]);
            }
        }

        return dp[0];
    }

}
