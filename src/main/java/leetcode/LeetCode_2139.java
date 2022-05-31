package leetcode;

/**
 * @description: You are playing a game with integers. You start with the integer 1 and you want to reach the integer target.
 * <p>
 * In one move, you can either:
 * <p>
 * Increment the current integer by one (i.e., x = x + 1).
 * Double the current integer (i.e., x = 2 * x).
 * You can use the increment operation any number of times, however, you can only use the double operation at most maxDoubles times.
 * <p>
 * Given the two integers target and maxDoubles, return the minimum number of moves needed to reach target starting with 1.
 * @author: LISHUAI
 * @createDate: 2022/5/27 22:41
 * @version: 1.0
 */

public class LeetCode_2139 {

    public static void main(String[] args) {
//        int target = 5, maxDoubles = 0;
        int target = 19, maxDoubles = 2;
//        int target = 10, maxDoubles = 4;
//        int target = 1000000000, maxDoubles = 100;
        int i = minMoves_02(target, maxDoubles);
        System.out.println(i);
    }

    public static int minMoves(int target, int maxDoubles) {
        if (maxDoubles == 0) {
            return target - 1;
        }
        return minMovesProcess(target, 1, maxDoubles);
    }


    private static int minMovesProcess(int target, int cur, int maxDoubles) {
        if (cur > target) {
            return Integer.MAX_VALUE;
        }

        if (cur == target) {
            return 0;
        }
        int ans = 0;
        ans = minMovesProcess(target, cur + 1, maxDoubles) + 1;
        if (maxDoubles > 0) {
            int p = minMovesProcess(target, cur << 1, maxDoubles - 1);
            if (p != Integer.MAX_VALUE) {
                ans = Math.min(ans, p + 1);
            }
        }
        return ans;
    }

    public static int minMoves_dp(int target, int maxDoubles) {
        if (maxDoubles == 0) {
            return target - 1;
        }
        int[][] dp = new int[target + 1][maxDoubles + 1];
//        Arrays.fill(dp[target], Integer.MAX_VALUE);
        for (int i = 0; i < target; i++) {
            dp[i][maxDoubles] = i;
        }
        int ans = Integer.MAX_VALUE;

        for (int i = target - 1; i >= 0; i--) {
            for (int j = 1; j <= target; j++) {
                int p1 = dp[i + 1][j] + 1;
                if ((i << 1) <= target) {
                    p1 = Math.min(p1, dp[i << 1][j - 1] + 1);
                }
                dp[i][j] = p1;
                ans = Math.min(ans, p1);
            }
        }
        return ans;
    }

    public static int minMoves_02(int target, int maxDoubles) {
        if (maxDoubles == 0) {
            return target - 1;
        }
        int ans = 0;
        while (target != 1 && maxDoubles > 0) {
            ans++;
            if ((target & 1) == 0) {
                target >>>= 1;
                maxDoubles--;
                continue;
            }
            target--;
        }
        ans += target - 1;
        return ans;
    }
}
