package leetcode;

import java.util.Arrays;

/**
 * @description: There are two types of soup: type A and type B. Initially, we have n ml of each type of soup. There are four kinds of operations:
 * <p>
 * Serve 100 ml of soup A and 0 ml of soup B,
 * Serve 75 ml of soup A and 25 ml of soup B,
 * Serve 50 ml of soup A and 50 ml of soup B, and
 * Serve 25 ml of soup A and 75 ml of soup B.
 * When we serve some soup, we give it to someone, and we no longer have it. Each turn, we will choose from the four operations with an equal probability 0.25. If the remaining volume of soup is not enough to complete the operation, we will serve as much as possible. We stop once we no longer have some quantity of both types of soup.
 * <p>
 * Note that we do not have an operation where all 100 ml's of soup B are used first.
 * <p>
 * Return the probability that soup A will be empty first, plus half the probability that A and B become empty at the same time. Answers within 10-5 of the actual answer will be accepted.
 * @author: LiShuai
 * @createDate: 2023/7/30 19:02
 * @version: 1.0
 */

public class LeetCode_808 {
    public static void main(String[] args) {
//        int n = 850;
//        int n = 660295675;
        int n = 4500;
        double v = soupServings(n);
        System.out.println(v);
    }

    public static double soupServings(int n) {
        /**
         * 当n == 4500时结果为0.999990468596144，当n >= 4500时，结果无限接近1
         * 该题要求结果误差在10^-5范围内即为正确答案，0.99999和1.0的误差为0.00001也就是10^-5
         * 如果误差精度不是10^-5，那么此处的判断不一定正确
         */
        if (n >= 4500) {
            return 1;
        }
        int k = (n + 24) / 25;
        double[][] dp = new double[k + 1][k + 1];
        for (double[] ds : dp) {
            Arrays.fill(ds, -1);
        }
        return soupServingsProcess(k, k, dp);
    }

    private static double soupServingsProcess(int n1, int n2, double[][] dp) {
        if (n1 <= 0 && n2 <= 0) {
            return 0.5D;
        }
        if (n1 <= 0) {
            return 1.0D;
        }
        if (n2 <= 0) {
            return 0.0D;
        }
        if (dp[n1][n2] != -1) {
            return dp[n1][n2];
        }
        double ans = 0.0D;
        for (int i = 1; i <= 4; i++) {
            ans += soupServingsProcess(n1 - i, n2 - 4 + i, dp);
        }
        ans = ans / 4.0D;
        dp[n1][n2] = ans;
        return ans;
    }
}
