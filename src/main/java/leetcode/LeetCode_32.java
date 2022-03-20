package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/8/8 12:48
 * @version: 1.0
 */

public class LeetCode_32 {

    public static void main(String[] args) {

        String s = "()(())";

        int i = longestValidParentheses(s);

        System.out.println(i);
    }

    public static int longestValidParentheses(String s) {

        int result = 0, len = s.length(), index;

        char c;

        int[] dp = new int[s.length()];

        for (int i = 0; i < len; i++) {

            c = s.charAt(i);

            index = i - 1;

            if (c == ')' && index >= 0) {

                if (s.charAt(index) == '(') {
                    dp[i] = 2;

                    if (--index >= 0) {
                        dp[i] = dp[i] + dp[index];
                    }
                } else {

                    index = index - dp[index];

                    if (index >= 0) {

                        if (s.charAt(index) == '(') {

                            dp[i] = i - index + 1;

                            if (index - 1 >= 0) {

                                dp[i] = dp[i] + dp[index - 1];
                            }
                        }
                    }
                }
            }

            result = Math.max(result, dp[i]);
        }

        return result;
    }
}