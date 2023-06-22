package leetcode;

/**
 * @description: The appeal of a string is the number of distinct characters found in the string.
 * <p>
 * For example, the appeal of "abbca" is 3 because it has 3 distinct characters: 'a', 'b', and 'c'.
 * Given a string s, return the total appeal of all of its substrings.
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * @author: LISHUAI
 * @createDate: 2023/4/29 22:21
 * @version: 1.0
 */

public class LeetCode_2262 {

    public static void main(String[] args) {
        String s = "abbca";
        long l = appealSum(s);
        System.out.println(l);
    }

    public static long appealSum(String s) {
        int len = s.length();
        int[] ri = new int[26];
        long ans = 0, pre = 0;

        for (int i = len - 1; i >= 0; i--) {
            int c = s.charAt(i) - 'a';
            if (ri[c] != 0) {
                pre += ri[c] - i;
            } else {
                pre += len - i;
            }
            ri[c] = i;
            ans += pre;
        }
        return ans;
    }
}
