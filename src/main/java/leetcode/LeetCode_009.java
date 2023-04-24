package leetcode;

/**
 * @description: Given an integer x, return true if x is a
 * palindrome
 * , and false otherwise.
 * @author: LISHUAI
 * @createDate: 2022/12/11 8:37
 * @version: 1.0
 */

public class LeetCode_009 {

    public static boolean isPalindrome(int x) {
        if (x <= 0) {
            return x == 0;
        }
        String s = x + "";
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
