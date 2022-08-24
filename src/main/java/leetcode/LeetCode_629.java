package leetcode;

/**
 * @description: For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and nums[i] > nums[j].
 * <p>
 * Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs. Since the answer can be huge, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2022/7/17 16:01
 * @version: 1.0
 */

public class LeetCode_629 {

    public static void main(String[] args) {
        int n = 3, k = 0;
//        int n = 3, k = 1;
//        int n = 1000, k = 1000;
        int i = kInversePairs(n, k);
        System.out.println(i);
    }

    public static int kInversePairs(int n, int k) {
        int[] num = new int[n];
        return kInversePairsProcess(num, k, 0);
    }

    private static int kInversePairsProcess(int[] num, int k, int index) {
        if (k < 0) {
            return 0;
        }
        if (index >= num.length) {
            if (k == 0) {
                return 1;
            }
            return 0;
        }
        int ans = 0;
        int c = index;
        for (int i = 0; i < num.length; i++) {
            if (num[i] == 0) {
                num[i] = 1;
                ans += kInversePairsProcess(num, k - c, index + 1);
                num[i] = 0;
            } else {
                c--;
            }
        }
        return ans;
    }

    public int kInversePairs_dp(int n, int k) {
        if (k > n * (n - 1) / 2) // n numbers can generate at most n * (n - 1) / 2 inverse pairs
            return 0;

        if (k == n * (n - 1) / 2 || k == 0)
            return 1;

        int mod = 1000000007;
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = 1; // deal with j = 0
            for (int j = 1; j < Math.min(k, i * (i - 1) / 2) + 1; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j] - (j >= i ? dp[i - 1][j - i] : 0)) % mod;
                // all dp[i][j] modulo 10^9 + 7
                // so dp[i - 1][j - 1] might bigger than dp[i][j - 1] + dp[i - 1][j]
                if (dp[i][j] < 0)
                    dp[i][j] += mod;
            }
        }

        return dp[n][k];
    }
}
