package leetcode;

/**
 * @description: You are given the customer visit log of a shop represented by a 0-indexed string customers consisting only of characters 'N' and 'Y':
 * <p>
 * if the ith character is 'Y', it means that customers come at the ith hour
 * whereas 'N' indicates that no customers come at the ith hour.
 * If the shop closes at the jth hour (0 <= j <= n), the penalty is calculated as follows:
 * <p>
 * For every hour when the shop is open and no customers come, the penalty increases by 1.
 * For every hour when the shop is closed and customers come, the penalty increases by 1.
 * Return the earliest hour at which the shop must be closed to incur a minimum penalty.
 * <p>
 * Note that if a shop closes at the jth hour, it means the shop is closed at the hour j.
 * @author: LiShuai
 * @createDate: 2023/8/30 23:11
 * @version: 1.0
 */

public class LeetCode_2483 {

    public static int bestClosingTime(String customers) {
        char[] chars = customers.toCharArray();
        int len = chars.length;
        int cy = 0, cn = 0;
        for (char aChar : chars) {
            if (aChar == 'N') {
                ++cn;
            }
        }
        int ai = len, ans = cn;
        for (int i = len - 1; i >= 0; --i) {
            if (chars[i] == 'N') {
                --cn;
            } else {
                ++cy;
            }
            if (ans >= cn + cy) {
                ans = cn + cy;
                ai = i;
            }
        }
        return ai;
    }
}
