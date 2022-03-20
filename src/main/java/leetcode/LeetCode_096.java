package leetcode;

/**
 * @description: Given an integer n,
 * return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 * @author: LISHUAI
 * @createDate: 2021/11/20 20:55
 * @version: 1.0
 */

public class LeetCode_096 {

    public static void main(String[] args) {

        int nums = 1;

        for (int i = 1; i <= 19; i++) {

            nums *= i;
        }

        System.out.println(nums);

        int i = numTrees(19);

        System.out.println(i);

        int i1 = numTrees_02(19);

        System.out.println(i1);
    }

    public static int numTrees(int n) {

        int[] ints = new int[n + 1];

        ints[0] = 1;

        ints[1] = 1;

        for (int i = 2; i <= n; i++) {

            for (int j = 0; j < i / 2; j++) {

                ints[i] += ints[j] * ints[i - j - 1];
            }

            ints[i] *= 2;

            if ((i & 1) == 1) {

                ints[i] += ints[i / 2] * ints[i / 2];
            }
        }

        return ints[n];
    }

    public static int numTrees_02(int n) {
        // Checking for Invalid Input
        if (n < 0) {
            throw new IllegalArgumentException("Invalid Input");
        }
        // For n == 0, empty tree is a valid BST.
        // For n == 1, valid BST can have only one node.
        if (n <= 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            // We only need to do half as dp[i] is symmetrical.
            // For example, when i = 5:
            // dp[i] = dp[0]*dp[4] + dp[1]*dp[3] + dp[2]*dp[2] + dp[3]*dp[1] + dp[4]*dp[0]
            // Here except dp[2]*dp[2] all others are appearing twice.
            for (int j = 0; j < i / 2; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
            dp[i] *= 2;

            // Only add i/2 * i/2 for odd numbers.
            if ((i & 1) == 1) {
                dp[i] += dp[i / 2] * dp[i / 2];
            }
        }

        return dp[n];
    }
}
