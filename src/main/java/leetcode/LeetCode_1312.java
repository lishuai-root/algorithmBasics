package leetcode;

/**
 * @description: Given a string s. In one step you can insert any character at any index of the string.
 * <p>
 * Return the minimum number of steps to make s palindrome.
 * <p>
 * A Palindrome String is one that reads the same backward as well as forward.
 * @author: LISHUAI
 * @createDate: 2022/12/9 18:16
 * @version: 1.0
 */

public class LeetCode_1312 {

    private static Integer[][] dp;

    public static void main(String[] args) {
        String s = "tldjbqjdogipebqsohdypcxjqkrqltpgviqtqz";
        int i = minInsertions(s);
        System.out.println(i);
    }

    public static int minInsertions(String s) {
        dp = new Integer[s.length()][s.length()];
        return minInsertionsProcess(s, 0, s.length() - 1);
    }

    private static int minInsertionsProcess(String s, int left, int right) {
        if (left >= right) {
            return 0;
        }
        if (dp[left][right] != null) {
            return dp[left][right];
        }
        int ans;
        if (s.charAt(left) == s.charAt(right)) {
            ans = minInsertionsProcess(s, left + 1, right - 1);
        } else {
            int p1 = minInsertionsProcess(s, left, right - 1);
            int p2 = minInsertionsProcess(s, left + 1, right);
            ans = Math.min(p1, p2) + 1;
        }
        dp[left][right] = ans;
        return ans;
    }
}
