package leetcode;

/**
 * @description: You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:
 * <p>
 * i + minJump <= j <= min(i + maxJump, s.length - 1), and
 * s[j] == '0'.
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 * @author: LISHUAI
 * @createDate: 2022/12/11 3:24
 * @version: 1.0
 */

public class LeetCode_1871 {

    public static void main(String[] args) {
        String s = "011010";
//        String s = "00";
        int minJump = 2;
        int maxJump = 3;
        System.out.println(canReach(s, minJump, maxJump));
        System.out.println(canReach_02(s, minJump, maxJump));
    }

    public static boolean canReach(String s, int minJump, int maxJump) {
        if (s.charAt(s.length() - 1) == '1') {
            return false;
        }
        int len = s.length();
        boolean[] dp = new boolean[len];
        dp[len - 1] = true;

        for (int i = len - 2; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                dp[i] = false;
                continue;
            }
            boolean b = false;
            for (int j = i + minJump; !b && j <= Math.min(i + maxJump, len - 1); j++) {
                b = dp[j];
            }
            dp[i] = b;
        }
        return dp[0];
    }


    public static boolean canReach_02(String s, int minJump, int maxJump) {
        if (s.charAt(s.length() - 1) == '1') {
            return false;
        }
        int len = s.length();
        boolean[] dp = new boolean[len];
        dp[len - 1] = true;
        int k = 0;

        for (int i = len - 2; i >= 0; i--) {
            if (i + maxJump + 1 < len && dp[i + maxJump + 1]) {
                k--;
            }
            if (i + minJump < len && dp[i + minJump]) {
                k++;
            }
            dp[i] = s.charAt(i) == '0' && k > 0;
        }
        return dp[0];
    }

}
