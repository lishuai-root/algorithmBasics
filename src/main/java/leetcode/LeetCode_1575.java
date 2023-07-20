package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an array of distinct positive integers locations where locations[i] represents the position of city i. You are also given integers start, finish and fuel representing the starting city, ending city, and the initial amount of fuel you have, respectively.
 * <p>
 * At each step, if you are at city i, you can pick any city j such that j != i and 0 <= j < locations.length and move to city j. Moving from city i to city j reduces the amount of fuel you have by |locations[i] - locations[j]|. Please notice that |x| denotes the absolute value of x.
 * <p>
 * Notice that fuel cannot become negative at any point in time, and that you are allowed to visit any city more than once (including start and finish).
 * <p>
 * Return the count of all possible routes from start to finish. Since the answer may be too large, return it modulo 109 + 7.
 * @author: LiShuai
 * @createDate: 2023/6/25 21:13
 * @version: 1.0
 */

public class LeetCode_1575 {

    private static final int T = 1000000007;

    public static void main(String[] args) {
        int[] locations = {2, 3, 6, 8, 4};
        int start = 1, finish = 3, fuel = 5;
        int i = countRoutes_dp(locations, start, finish, fuel);
        System.out.println(i);
    }

    public static int countRoutes(int[] locations, int start, int finish, int fuel) {
        return countRoutesProcess(locations, start, 0, finish, fuel);
    }

    private static int countRoutesProcess(int[] locations, int cur, int curFuel, int finish, int fuel) {
        if (curFuel > fuel) {
            return 0;
        }
        int ans = cur == finish ? 1 : 0;
        for (int i = 0; i < locations.length; i++) {
            if (i != cur) {
                ans += countRoutesProcess(locations, i, curFuel + Math.abs(locations[i] - locations[cur]), finish, fuel);
                ans %= T;
            }
        }
        return ans;
    }

    public static int countRoutes_dp(int[] locations, int start, int finish, int fuel) {
        int len = locations.length;
        int[][] dp = new int[fuel + 1][len + 1];
        start = locations[start];
        finish = locations[finish];
        Arrays.sort(locations);

        for (int i = fuel; i >= 0; --i) {
            int index = 0;
            for (int j = 0; j < len; j++) {
                int ans = (locations[j] == finish ? 1 : 0);
                while (index < len && i + Math.abs(locations[index] - locations[j]) > fuel) {
                    ++index;
                }
                for (int k = index; k < len; k++) {
                    int p = i + Math.abs(locations[k] - locations[j]);
                    if (p > fuel) {
                        break;
                    }
                    if (j != k) {
                        ans += dp[p][k];
                        ans %= T;
                    }
                }
                dp[i][j] = ans;
            }
        }
        for (int i = 0; i < len; i++) {
            if (start == locations[i]) {
                start = i;
                break;
            }
        }
        return dp[0][start];
    }
}
