package leetcode;

/**
 * @description: Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
 * <p>
 * The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.
 * <p>
 * Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.
 * <p>
 * Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.
 * @author: LiShuai
 * @createDate: 2023/5/30 23:11
 * @version: 1.0
 */

public class LeetCode_877 {

    public static boolean stoneGame(int[] piles) {
        int len = piles.length;
        int[] dp = new int[len];
        int[] fuxSum = new int[len + 1];
        fuxSum[len - 1] = piles[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            fuxSum[i] = fuxSum[i + 1] + piles[i];
        }

        for (int i = len - 1; i >= 0; i--) {
            dp[i] = piles[i];
            for (int j = i + 1; j < len; j++) {
                int total = fuxSum[i] - fuxSum[j + 1];
                dp[j] = total - Math.min(piles[i] - dp[j], piles[j] - dp[j - 1]);
            }
        }
        return dp[len - 1] > fuxSum[0] - dp[len - 1];
    }
}
