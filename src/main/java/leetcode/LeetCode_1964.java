package leetcode;

/**
 * @description: You want to build some obstacle courses. You are given a 0-indexed integer array obstacles of length n, where obstacles[i] describes the height of the ith obstacle.
 * <p>
 * For every index i between 0 and n - 1 (inclusive), find the length of the longest obstacle course in obstacles such that:
 * <p>
 * You choose any number of obstacles between 0 and i inclusive.
 * You must include the ith obstacle in the course.
 * You must put the chosen obstacles in the same order as they appear in obstacles.
 * Every obstacle (except the first) is taller than or the same height as the obstacle immediately before it.
 * Return an array ans of length n, where ans[i] is the length of the longest obstacle course for index i as described above.
 * @author: LISHUAI
 * @createDate: 2023/5/7 20:36
 * @version: 1.0
 */

public class LeetCode_1964 {

    public static void main(String[] args) {
        int[] obstacles = {1, 2, 1, 3};
        int[] ints = longestObstacleCourseAtEachPosition_dp(obstacles);
        for (int i : ints) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int len = obstacles.length;
        int[] ans = new int[len];
        int[] dp = new int[len];
        ans[0] = 1;
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            ans[i] = longestObstacleCourseAtEachPositionProcess(obstacles, i, i - 1, dp) + 1;
        }
        return ans;
    }

    private static int longestObstacleCourseAtEachPositionProcess(int[] obstacles, int pre, int cur, int[] dp) {
        if (cur == 0) {
            return obstacles[cur] <= obstacles[pre] ? 1 : 0;
        }
        int p1 = longestObstacleCourseAtEachPositionProcess(obstacles, pre, cur - 1, dp);
        int p2 = 0;
        if (obstacles[pre] >= obstacles[cur - 1]) {
            p2 = longestObstacleCourseAtEachPositionProcess(obstacles, cur, cur - 1, dp) + 1;
        }
        return Math.max(p1, p2);
    }


    public static int[] longestObstacleCourseAtEachPosition_dp(int[] obstacles) {
        int len = obstacles.length;
        int[] ans = new int[len];
        int[] temp = new int[len];
        int index = 0;

        for (int i = 0; i < len; i++) {
            int left = 0, right = index;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (temp[mid] <= obstacles[i]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            ans[i] = left + 1;
            temp[left] = obstacles[i];
            if (left == index) {
                index++;
            }
        }
        return ans;
    }

}
