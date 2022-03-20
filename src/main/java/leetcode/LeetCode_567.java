package leetcode;

/**
 * @description: Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * <p>
 * In other words, return true if one of s1's permutations is the substring of s2.
 * @author: LISHUAI
 * @createDate: 2022/2/11 19:10
 * @version: 1.0
 */

public class LeetCode_567 {

    public static void main(String[] args) {

        String s1 = "dd", s2 = "rroogzkdktk";

        boolean b = checkInclusion(s1, s2);

        System.out.println(b);
    }

    public static boolean checkInclusion(String s1, String s2) {

        if (s1.length() > s2.length()) {

            return false;
        }

        int[] dp = new int[26];

        int ans = s1.length(), left = 0, right = 0;

        for (int i = 0; i < s1.length(); i++) {

            dp[s1.charAt(i) - 'a']++;
        }

        for (; right < s1.length(); right++) {

            System.out.println((char) (s2.charAt(right)) + "  " + dp[s2.charAt(right) - 'a']);

            if (dp[s2.charAt(right) - 'a'] > 0) {

                ans--;
            }
            dp[s2.charAt(right) - 'a']--;
        }


        for (; right < s2.length(); right++, left++) {

            if (ans == 0) {
                System.out.println(left + "  " + right);
                System.out.println(ans);
                return true;
            }


            if (dp[s2.charAt(right) - 'a'] > 0) {
                ans--;
            }
            dp[s2.charAt(right) - 'a']--;


            dp[s2.charAt(left) - 'a']++;
            if (dp[s2.charAt(left) - 'a'] > 0) {
                ans++;
            }

        }

        System.out.println(ans);
        return ans == 0;
    }
}
