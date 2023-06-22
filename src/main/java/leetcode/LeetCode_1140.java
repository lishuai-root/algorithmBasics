package leetcode;

/**
 * @description: Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.
 * <p>
 * Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
 * <p>
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
 * <p>
 * The game continues until all the stones have been taken.
 * <p>
 * Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
 * @author: LiShuai
 * @createDate: 2023/5/29 21:19
 * @version: 1.0
 */

public class LeetCode_1140 {

    private static Integer[][] cache;

    public static void main(String[] args) {
//        int[] piles = {2, 7, 9, 4, 4};
        int[] piles = {1, 2, 3, 4, 5, 100};
        int i = stoneGameII(piles);
        System.out.println(i);
        System.out.println(stoneGameII_dp(piles));
    }

    public static int stoneGameII(int[] piles) {
        int len = piles.length;
        int[] sum = new int[len];
        sum[len - 1] = piles[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            sum[i] = sum[i + 1] + piles[i];
        }
        cache = new Integer[len][len << 1];
        return stoneGameIIProcess(sum, 0, 1);
    }

    private static int stoneGameIIProcess(int[] sum, int index, int m) {
        if (index >= sum.length) {
            return 0;
        }
        if (cache[index][m] != null) {
            return cache[index][m];
        }
        int ans = 0;

        for (int i = 1; i <= (m << 1); i++) {
            int p = stoneGameIIProcess(sum, index + i, Math.max(i, m));
            ans = Math.max(ans, sum[index] - p);
        }
        cache[index][m] = ans;
        return ans;
    }

    public static int stoneGameII_dp(int[] piles) {
        int len = piles.length;
        int sum = 0;
        int[][] dp = new int[len][len + 1];

        for (int i = len - 1; i >= 0; i--) {
            sum += piles[i];
            for (int j = 1; j <= len; j++) {
                int ans = 0;
                for (int k = 1; k <= (j << 1); k++) {
                    int p = i + k < len ? dp[i + k][Math.max(k, j)] : 0;
                    ans = Math.max(ans, sum - p);
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][1];
    }
}
