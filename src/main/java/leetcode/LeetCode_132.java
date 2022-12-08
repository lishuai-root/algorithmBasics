package leetcode;

/**
 * @description: Given a string s, partition s such that every
 * substring
 * of the partition is a
 * palindrome
 * .
 * <p>
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * @author: LISHUAI
 * @createDate: 2022/12/9 4:10
 * @version: 1.0
 */

public class LeetCode_132 {

    public static void main(String[] args) {
//        String s = "aab";
        String s = "ababababababababababababcbabababababababababababa";
        System.out.println(minCut_dp(s));
        System.out.println(minCut_dp_02(s));
//        int i = minCut(s);
//        System.out.println(i);
    }

    public static int minCut(String s) {
        return minCutProcess(s, 0, 0);
    }

    private static int minCutProcess(String s, int left, int right) {
        if (left == s.length() - 1) {
            return 0;
        }
        if (right == s.length() - 1) {
            return isPalindromes(s, left, s.length() - 1) ? 0 : s.length() - left;
        }
        int p1 = Integer.MAX_VALUE;
        if (isPalindromes(s, left, right)) {
            p1 = minCutProcess(s, right + 1, right + 1) + 1;
        }
        int p2 = minCutProcess(s, left, right + 1);
        return Math.min(p1, p2);
    }

    public static int minCut_dp(String s) {
        int len = s.length();
        int[][] dp = new int[len + 1][len + 1];

        for (int i = len - 2; i >= 0; i--) {
            dp[i][len - 1] = isPalindromes(s, i, len - 1) ? 0 : len - i;
            for (int j = len - 2; j >= i; j--) {
                int p1 = Integer.MAX_VALUE;
                if (isPalindromes(s, i, j)) {
                    p1 = dp[j + 1][j + 1] + 1;
                }
                dp[i][j] = Math.min(dp[i][j + 1], p1);
            }
        }
        return dp[0][0];
    }

    public static int minCut_dp_02(String s) {
        int len = s.length();
        int[][] dp = new int[len + 1][len + 1];
        boolean[][] bls = new boolean[len + 1][len + 1];
        for (int i = 0; i < s.length(); i++) {
            maxPalindromes(s, i, i, bls);
            maxPalindromes(s, i, i + 1, bls);
        }

        for (int i = len - 2; i >= 0; i--) {
            dp[i][len - 1] = bls[i][len - 1] ? 0 : len - i;
            for (int j = len - 2; j >= i; j--) {
                int p1 = Integer.MAX_VALUE;
                if (bls[i][j]) {
                    p1 = dp[j + 1][j + 1] + 1;
                }
                dp[i][j] = Math.min(dp[i][j + 1], p1);
            }
        }
        return dp[0][0];
    }


    private static int maxPalindromes(String s, int left, int right, boolean[][] bls) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            bls[left][right] = true;
            left--;
            right++;
        }
        return right - left - 1;
    }

    private static boolean isPalindromes(String s, int left, int right) {
        while (left < right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        return left >= right;
    }
}
