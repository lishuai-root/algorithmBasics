package leetcode;

import java.util.Random;

/**
 * @description: You are given a string text. You should split it to k substrings (subtext1, subtext2, ..., subtextk) such that:
 * <p>
 * subtexti is a non-empty string.
 * The concatenation of all the substrings is equal to text (i.e., subtext1 + subtext2 + ... + subtextk == text).
 * subtexti == subtextk - i + 1 for all valid values of i (i.e., 1 <= i <= k).
 * Return the largest possible value of k.
 * @author: LISHUAI
 * @createDate: 2022/12/1 23:36
 * @version: 1.0
 */

public class LeetCode_1147 {

    public static void main(String[] args) {
//        String text = "ghiabcdefhelloadamhelloabcdefghi";
//        String text = "aaa";
//        String text = "antaprezatepzapreanta";
//        String text = "elvtoelvto";
//        String text = "merchant";
        String text = makeString(1000);
//        int i = longestDecomposition(text);
//        System.out.println(i);
        System.out.println(longestDecomposition_dp(text));
    }

    private static String makeString(int size) {
        StringBuilder sbr = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            sbr.append(random.nextInt(26) + 'a');
        }
        return sbr.toString();
    }

    public static int longestDecomposition(String text) {
        return longestDecompositionProcess(text.toCharArray(), 0, 0);
    }

    private static int longestDecompositionProcess(char[] chars, int left, int right) {
        if (left << 1 >= chars.length) {
            return 0;
        }
        if ((left << 1) + (right - left + 1) >= chars.length) {
            return 1;
        }

        int ans = 0;
        if (equals(chars, left, right)) {
            ans = longestDecompositionProcess(chars, right + 1, right + 1) + 2;
        } else {
            ans = longestDecompositionProcess(chars, left, right + 1);
        }
        return ans;
    }

    public static int longestDecomposition_dp(String text) {
        int len = text.length();
        char[] chars = text.toCharArray();
        int[][] dp = new int[len + 1][len + 1];

        for (int i = (len >>> 1); i >= 0; i--) {
            for (int j = len - i - 1; j >= i; j--) {
                if ((i << 1) + (j - i + 1) >= len) {
                    dp[i][j] = 1;
                    continue;
                }
                if (equals(chars, i, j)) {
                    dp[i][j] = dp[j + 1][j + 1] + 2;
                } else {
                    dp[i][j] = dp[i][j + 1];
                }
            }
        }
        return dp[0][0];
    }


    private static boolean equals(char[] chars, int left, int right) {
        int size = right - left + 1;
        int start = chars.length - left - size;
        for (int i = 0; i < size; i++) {
            if (chars[left + i] != chars[start + i]) {
                return false;
            }
        }
        return true;
    }
}
