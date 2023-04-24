package leetcode;

/**
 * @description: A binary string is monotone increasing if it consists of some number of 0's (possibly none), followed by some number of 1's (also possibly none).
 * <p>
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
 * <p>
 * Return the minimum number of flips to make s monotone increasing.
 * @author: LISHUAI
 * @createDate: 2023/1/17 21:57
 * @version: 1.0
 */

public class LeetCode_926 {

    public static int minFlipsMonoIncr(String s) {
        int q = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                q++;
            }
        }
        int p = 0, ans = Math.max(q, s.length() - q);
        for (int i = 0; i < s.length(); i++) {
            ans = Math.min(ans, p + q);
            if (s.charAt(i) == '1') {
                p++;
            } else {
                q--;
            }
        }
        return ans;
    }
}
