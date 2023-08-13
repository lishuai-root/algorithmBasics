package leetcode;

import java.util.Arrays;

/**
 * @description: A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
 * <p>
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.
 * <p>
 * If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.
 * @author: LiShuai
 * @createDate: 2023/8/4 22:57
 * @version: 1.0
 */

public class LeetCode_403 {

    private static Boolean[][] cache;


    public static boolean canCross(int[] stones) {
        int len = stones.length;
        cache = new Boolean[len][len + 1];
        return canCrossProcess(stones, 0, 0);
    }

    private static boolean canCrossProcess(int[] stones, int index, int preJump) {
        if (index == stones.length - 1) {
            return true;
        }

        if (cache[index][preJump] != null) {
            return cache[index][preJump];
        }
        boolean ans = false;
        for (int i = Math.max(1, preJump - 1); !ans && i <= preJump + 1; ++i) {
            int p = find(stones, index + 1, stones.length - 1, stones[index] + i);
            if (p != -1) {
                ans = canCrossProcess(stones, p, i);
            }
        }
        cache[index][preJump] = ans;
        return ans;
    }

    private static int find(int[] stones, int start, int end, int target) {
        int left = start, right = end;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (stones[mid] < target) {
                left = mid + 1;
            } else if (stones[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static boolean canCross_dp(int[] stones) {
        int len = stones.length;
        boolean[][] dp = new boolean[len][len + 1];
        Arrays.fill(dp[len - 1], true);

        for (int i = len - 2; i >= 0; --i) {
            int index = i + 1;
            for (int j = 1; j <= i + 1 && index < len; j++) {
                while (index < len && stones[i] + j > stones[index]) {
                    ++index;
                }
                if (index < len && stones[i] + j == stones[index]) {
                    dp[i][j] = dp[index][j - 1] | dp[index][j] | dp[index][j + 1];
                }
            }
        }
        return dp[0][1];
    }
}
