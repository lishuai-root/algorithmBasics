package leetcode;

/**
 * @description: A decimal number is called deci-binary if each of its digits is either 0 or 1 without any leading zeros. For example, 101 and 1100 are deci-binary, while 112 and 3001 are not.
 * <p>
 * Given a string n that represents a positive decimal integer, return the minimum number of positive deci-binary numbers needed so that they sum up to n.
 * @author: LISHUAI
 * @createDate: 2022/6/27 10:30
 * @version: 1.0
 */

public class LeetCode_1689 {

    public static int minPartitions(String n) {
        int ans = 1;
        for (int i = 0; i < n.length() && ans < 9; i++) {
            ans = Math.max(ans, n.charAt(i) - '0');
        }
        return ans;
    }
}
