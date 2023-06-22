package leetcode;

/**
 * @description: Alice plays the following game, loosely based on the card game "21".
 * <p>
 * Alice starts with 0 points and draws numbers while she has less than k points. During each draw, she gains an integer number of points randomly from the range [1, maxPts], where maxPts is an integer. Each draw is independent and the outcomes have equal probabilities.
 * <p>
 * Alice stops drawing numbers when she gets k or more points.
 * <p>
 * Return the probability that Alice has n or fewer points.
 * <p>
 * Answers within 10-5 of the actual answer are considered accepted.
 * @author: LiShuai
 * @createDate: 2023/5/25 22:01
 * @version: 1.0
 */

public class LeetCode_837 {

    public static double new21Game(int n, int k, int maxPts) {
        if (k == 0 || n >= k + maxPts)
            return 1.0;

        double[] dp = new double[n + 1];
        double windowSum = 1.0;
        double probability = 0.0;

        dp[0] = 1.0;

        for (int i = 1; i <= n; i++) {
            dp[i] = windowSum / maxPts;

            if (i < k)
                windowSum += dp[i];
            else
                probability += dp[i];

            if (i - maxPts >= 0)
                windowSum -= dp[i - maxPts];
        }

        return probability;
    }
}
