package leetcode;

/**
 * @description: There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
 * <p>
 * In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row), then Bob calculates the value of each row which is the sum of the values of all the stones in this row. Bob throws away the row which has the maximum value, and Alice's score increases by the value of the remaining row. If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.
 * <p>
 * The game ends when there is only one stone remaining. Alice's is initially zero.
 * <p>
 * Return the maximum score that Alice can obtain.
 * @author: LiShuai
 * @createDate: 2023/5/30 21:58
 * @version: 1.0
 */

public class LeetCode_1653 {
    public static void main(String[] args) {
        System.out.println(500 * 1000000);
    }

    public static int stoneGameV(int[] stoneValue) {
        int len = stoneValue.length;
        int[][] dp = new int[len][len];
        int[] sufSum = new int[len + 1];
        sufSum[len - 1] = stoneValue[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            sufSum[i] = sufSum[i + 1] + stoneValue[i];
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                int total = sufSum[i] - sufSum[j + 1];
                int sum = 0, ans = 0;
                for (int k = i; k < j; k++) {
                    sum += stoneValue[k];
                    if (sum < total - sum) {
                        ans = Math.max(ans, dp[i][k] + sum);
                    } else if (sum > total - sum) {
                        ans = Math.max(ans, dp[k + 1][j] + total - sum);
                    } else {
                        ans = Math.max(ans, Math.max(dp[i][k], dp[k + 1][j]) + sum);
                    }
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][len - 1];
    }
}
