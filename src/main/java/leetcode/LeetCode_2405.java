package leetcode;

/**
 * @description: Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.
 * <p>
 * Return the minimum number of substrings in such a partition.
 * <p>
 * Note that each character should belong to exactly one substring in a partition.
 * @author: LISHUAI
 * @createDate: 2023/4/5 0:33
 * @version: 1.0
 */

public class LeetCode_2405 {

    public static int partitionString(String s) {
        int ans = 1, bit = 0, index = 0;

        while (index < s.length()) {
            int c = s.charAt(index++) - 'a';
            if ((bit & (1 << c)) != 0) {
                bit = 0;
                ans++;
            }
            bit |= (1 << c);
        }
        return ans;
    }
}
