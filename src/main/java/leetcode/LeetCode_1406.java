package leetcode;

/**
 * @description: Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
 * <p>
 * Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2, or 3 stones from the first remaining stones in the row.
 * <p>
 * The score of each player is the sum of the values of the stones taken. The score of each player is 0 initially.
 * <p>
 * The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.
 * <p>
 * Assume Alice and Bob play optimally.
 * <p>
 * Return "Alice" if Alice will win, "Bob" if Bob will win, or "Tie" if they will end the game with the same score.
 * @author: LiShuai
 * @createDate: 2023/5/29 22:19
 * @version: 1.0
 */

public class LeetCode_1406 {

    public static void main(String[] args) {
//        int[] values = {1, 2, 3, 7};
//        int[] values = {1, 2, 3, -9};
//        int[] values = {1, 2, 3, 6};
        int[] values = {-1, -2, -3};
        String s = stoneGameIII(values);
        System.out.println(s);
    }

    public static String stoneGameIII(int[] stoneValue) {
        int len = stoneValue.length, sum = 0;
        int[] dp = new int[len];

        for (int i = len - 1; i >= 0; i--) {
            sum += stoneValue[i];
            dp[i] = Integer.MIN_VALUE;
            for (int j = 1; j < 4; j++) {
                int p = i + j < len ? dp[i + j] : 0;
                dp[i] = Math.max(dp[i], sum - p);
            }
        }

        if ((sum & 1) == 0) {
            if (dp[0] > (sum >> 1)) {
                return "Alice";
            } else if (dp[0] < (sum >> 1)) {
                return "Bob";
            } else {
                return "Tie";
            }
        } else {
            return dp[0] > (sum >> 1) ? "Alice" : "Bob";
        }
    }
}
