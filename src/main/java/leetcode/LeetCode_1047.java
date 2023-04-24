package leetcode;

/**
 * @description: You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.
 * <p>
 * We repeatedly make duplicate removals on s until we no longer can.
 * <p>
 * Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
 * @author: LISHUAI
 * @createDate: 2023/4/24 20:24
 * @version: 1.0
 */

public class LeetCode_1047 {

    public static void main(String[] args) {
        String s = "aaaaaaaa";
        String s1 = removeDuplicates(s);
        System.out.println(s1);
    }

    public static String removeDuplicates(String s) {
        char[] chars = s.toCharArray();
        int index = -1;

        for (int i = 0; i < chars.length; i++) {
            if (index != -1 && chars[index] == chars[i]) {
                --index;
            } else {
                chars[++index] = chars[i];
            }
        }
        return String.valueOf(chars, 0, index + 1);
    }
}
