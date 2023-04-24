package leetcode;

/**
 * @description: You are given an integer num. You will apply the following steps exactly two times:
 * <p>
 * Pick a digit x (0 <= x <= 9).
 * Pick another digit y (0 <= y <= 9). The digit y can be equal to x.
 * Replace all the occurrences of x in the decimal representation of num by y.
 * The new integer cannot have any leading zeros, also the new integer cannot be 0.
 * Let a and b be the results of applying the operations to num the first and second times, respectively.
 * <p>
 * Return the max difference between a and b.
 * @author: LISHUAI
 * @createDate: 2023/4/17 19:44
 * @version: 1.0
 */

public class LeetCode_1432 {

    public static int maxDiff(int num) {
        String n = num + "";
        char[] chars = new char[n.length()];
        char c = 0;
        for (int i = 0; i < chars.length; i++) {
            if (c == 0 && n.charAt(i) != '9') {
                c = n.charAt(i);
            }
            if (c == 0 || c == n.charAt(i)) {
                chars[i] = '9';
            } else {
                chars[i] = n.charAt(i);
            }
        }
        int a = Integer.parseInt(String.valueOf(chars));

        if (n.charAt(0) > '1') {
            c = n.charAt(0);
            for (int i = 0; i < chars.length; i++) {
                if (c == n.charAt(i)) {
                    chars[i] = '1';
                } else {
                    chars[i] = n.charAt(i);
                }
            }
        } else {
            c = 0;
            chars[0] = n.charAt(0);
            for (int i = 1; i < chars.length; i++) {
                if (c == 0 && n.charAt(i) != '0' && n.charAt(i) != n.charAt(0)) {
                    c = n.charAt(i);
                }
                if (c == n.charAt(i)) {
                    chars[i] = '0';
                } else {
                    chars[i] = n.charAt(i);
                }
            }
        }
        int b = Integer.parseInt(String.valueOf(chars));
        return a - b;
    }
}
