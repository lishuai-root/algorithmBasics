package leetcode;

/**
 * @description: You are given a string s consisting of digits and an integer k.
 * <p>
 * A round can be completed if the length of s is greater than k. In one round, do the following:
 * <p>
 * Divide s into consecutive groups of size k such that the first k characters are in the first group,
 * the next k characters are in the second group, and so on. Note that the size of the last group can be smaller than k.
 * Replace each group of s with a string representing the sum of all its digits. For example, "346" is replaced with "13" because 3 + 4 + 6 = 13.
 * Merge consecutive groups together to form a new string. If the length of the string is greater than k, repeat from step 1.
 * Return s after all rounds have been completed.
 * @author: LISHUAI
 * @createDate: 2022/4/17 17:39
 * @version: 1.0
 */

public class LeetCode_2243 {

    public static void main(String[] args) {
        String s = "11111222223";
        int k = 3;
        String s1 = digitSum(s, k);
    }

    public static String digitSum(String s, int k) {
        String line = s;
        StringBuilder sbr = new StringBuilder();

        while (line.length() > k) {
            sbr.delete(0, sbr.length());
            int left = 0, right = 0;
            while (right < line.length()) {
                int sum = 0;
                right = Math.min(right + k, line.length());
                while (left < right) {
                    sum += (line.charAt(left++) - '0');
                }
                sbr.append(sum);
            }
            line = sbr.toString();
        }
        return line;
    }
}














