package leetcode;

/**
 * @description: Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.
 * @author: LISHUAI
 * @createDate: 2023/4/26 22:05
 * @version: 1.0
 */

public class LeetCode_258 {

    public static int addDigits(int num) {
        int ans = num;
        while (ans >= 10) {
            num = ans;
            ans = 0;
            while (num != 0) {
                ans += (num % 10);
                num = num / 10;
            }
        }
        return ans;
    }

    public static int addDigits_02(int num) {
        int ans = 0;
        while (num != 0) {
            ans += (num % 10);
            num = num / 10;
            ans %= 10;
        }
        return ans;
    }
}
