package leetcode;

/**
 * @description: There is a test that has n types of questions. You are given an integer target and a 0-indexed 2D integer array types where types[i] = [counti, marksi] indicates that there are counti questions of the ith type, and each one of them is worth marksi points.
 * <p>
 * Return the number of ways you can earn exactly target points in the exam. Since the answer may be too large, return it modulo 109 + 7.
 * <p>
 * Note that questions of the same type are indistinguishable.
 * <p>
 * For example, if there are 3 questions of the same type, then solving the 1st and 2nd questions is the same as solving the 1st and 3rd questions, or the 2nd and 3rd questions.
 * @author: LiShuai
 * @createDate: 2023/8/3 22:18
 * @version: 1.0
 */

public class LeetCode_2585 {

    private static Integer[][] cache;

    public static void main(String[] args) {
        int[][] types = {{6, 1}, {3, 2}, {2, 3}};
        int target = 6;
        int i = waysToReachTarget(target, types);
        System.out.println(i);
        System.out.println(waysToReachTarget_dp(target, types));
    }

    public static int waysToReachTarget(int target, int[][] types) {
        cache = new Integer[types.length][target + 1];
        return waysToReachTargetProcess(target, types, 0, 0);
    }

    private static int waysToReachTargetProcess(int target, int[][] types, int index, int sum) {
        if (sum == target) {
            return 1;
        }
        if (index >= types.length || sum > target) {
            return 0;
        }
        if (cache[index][sum] != null) {
            return cache[index][sum];
        }
        int ans = 0;
        int[] curs = types[index];
        for (int i = 0; i <= curs[0]; i++) {
            ans += waysToReachTargetProcess(target, types, index + 1, sum + i * curs[1]);
            ans %= 1000000007;
        }
        cache[index][sum] = ans;
        return ans;
    }

    public static int waysToReachTarget_dp(int target, int[][] types) {
        int len = types.length;
        int[] dp = new int[target + 1];
        dp[target] = 1;

        for (int i = len - 1; i >= 0; --i) {
            int[] curs = types[i];
            for (int j = 0; j < target; ++j) {
                int p = dp[j];
                for (int k = 1; k <= curs[0] && j + k * curs[1] <= target; ++k) {
                    p += dp[j + k * curs[1]];
                    p %= 1000000007;
                }
                dp[j] = p;
            }
        }
        return dp[0];
    }
}
