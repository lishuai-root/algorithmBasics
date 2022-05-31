package leetcode;

/**
 * @description: 最长回文子序列
 * @author: LISHUAI
 * @createDate: 2021/6/21 23:30
 * @version: 1.0
 */

public class LeetCode_516 {

    private static Integer[][] cache;

    public static void main(String[] args) {

        String str = "cbbd";

        int i = fn_001(str);
        int i1 = process_002(str);

        System.out.println(i);
        System.out.println(i1);
    }

    private static int fn_001(String s) {

        if (s == null || s.length() < 1)
            return 0;
        cache = new Integer[s.length()][s.length()];
        return process(s, 0, s.length() - 1);
    }

    private static int process(String str, int left, int right) {
        if (left == right)
            return 1;

        if (left > right || right < left)
            return 0;

        if (cache[left][right] != null) {
            return cache[left][right];
        }
        int ans;
        if (str.charAt(left) == str.charAt(right)) {
            ans = 2 + process(str, left + 1, right - 1);
        } else {
            ans = Math.max(process(str, left + 1, right), process(str, left, right - 1));
        }

        cache[left][right] = ans;
        return ans;
    }

    private static int process_002(String s) {

        if (s == null || s.length() < 1)
            return 0;
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0, m = i; m < len; j++, m++) {
                if (s.charAt(m) == s.charAt(j)) {
                    dp[j][m] = 2 + dp[j + 1][m - 1];
                } else {
                    dp[j][m] = Math.max(dp[j + 1][m], dp[j][m - 1]);
                }
            }
        }

//        for (int i = 0; i < len; i++) {
//
//            for (int j = 0; j < len; j++) {
//
//                System.out.print(dp[i][j] + "  ");
//
//            }
//            System.out.println();
//        }

        return dp[0][len - 1];
    }

    private static int process_003(String s) {

        if (s == null || s.length() < 1)
            return 0;
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }

        for (int i = 1; i < len; i++) {

            for (int j = i; j < len; j++) {
                if (s.charAt(j) == s.charAt(j - i)) {
                    dp[j - i][j] = 2 + dp[j - i + 1][j - 1];
                } else {
                    dp[j - i][j] = Math.max(dp[j - i + 1][j], dp[j - i][j - 1]);
                }
            }
        }

//        for (int i = 0; i < len; i++) {
//
//            for (int j = 0; j < len; j++) {
//
//                System.out.print(dp[i][j] + "  ");
//
//            }
//            System.out.println();
//        }

        return dp[0][len - 1];
    }
}
