package leetcode;

/**
 * @description: A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * <p>
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 * <p>
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 * <p>
 * In addition to the mapping above, an encoded message may contain the '*' character,
 * which can represent any digit from '1' to '9' ('0' is excluded). For example, the encoded message "1*" may represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19".
 * Decoding "1*" is equivalent to decoding any of the encoded messages it can represent.
 * <p>
 * Given a string s consisting of digits and '*' characters, return the number of ways to decode it.
 * <p>
 * Since the answer may be very large, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2022/4/18 18:40
 * @version: 1.0
 */

public class LeetCode_639 {

    public static void main(String[] args) {
//        String s = "**";
//        String s = "2*";
//        String s = "1*72*";
//        String s = "7*9*3*6*3*0*5*4*9*7*3*7*1*8*3*2*0*0*6*";
//        String s = "*10*1";
        String s = "3*";
        int i = numDecodings(s);
        System.out.println(i);
        System.out.println((int) '*');
        System.out.println((int) '0');
        System.out.println((int) '1');
    }

    public static int numDecodings(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        long[] dp = new long[s.length() + 1];
        int m = 1000000007;

        dp[s.length()] = 1;
        dp[s.length() - 1] = s.charAt(s.length() - 1) == '0' ? 0 : 1;
        if (s.charAt(s.length() - 1) == '*') {
            dp[s.length() - 1] *= 9;
        }
        for (int i = s.length() - 2; i >= 0; --i) {
            if (s.charAt(i) != '0') {

//                if (s.charAt(i + 1) == '*') {
//                    if (s.charAt(i) == '1') {
//                        dp[i] += dp[i + 1] + (dp[i + 2] * 9);
//                    } else if (s.charAt(i) == '2') {
//                        dp[i] += dp[i + 1] + (dp[i + 2] * 6);
//                    } else if (s.charAt(i) == '*') {
//                        dp[i] += (dp[i + 1] * 9) + (dp[i + 2] * 15);
//                    } else {
//                        dp[i] = dp[i + 1];
//                    }
//                } else {
//                    if (s.charAt(i) == '*') {
//                        if (s.charAt(i + 1) <= '6') {
//                            dp[i] += (dp[i + 1] * 9) + (dp[i + 2] * 2);
//                        } else {
//                            dp[i] += (dp[i + 1] * 9) + dp[i + 2];
//                        }
//                    } else {
//                        dp[i] += dp[i + 1] + (Integer.parseInt(s.substring(i, i + 2)) <= 26 ? dp[i + 2] : 0);
//                    }
//                }
//                dp[i] %= m;

                int x = 1, y = 0;

                if (s.charAt(i) == '*') {
                    x = 9;
                    if (s.charAt(i + 1) == '*') {
                        y = 15;
                    } else if (s.charAt(i + 1) <= '6') {
                        y = 2;
                    } else {
                        y = 1;
                    }
                }

                if (s.charAt(i + 1) == '*') {
                    if (s.charAt(i) == '1') {
                        y = 9;
                    } else if (s.charAt(i) == '2') {
                        y = 6;
                    }
                } else if (s.charAt(i) != '*') {
                    y = Integer.parseInt(s.substring(i, i + 2)) <= 26 ? 1 : 0;
                }
                dp[i] = (dp[i + 1] * x + dp[i + 2] * y) % m;
            }

        }
        return (int) (dp[0] % m);
    }
}
