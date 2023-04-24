package leetcode;

/**
 * @description: You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.
 * <p>
 * Return the merged string.
 * @author: LISHUAI
 * @createDate: 2023/4/18 19:55
 * @version: 1.0
 */

public class LeetCode_1768 {

    public static String mergeAlternately(String word1, String word2) {
        char[] chars = new char[word1.length() + word2.length()];
        int end = Math.min(word1.length(), word2.length());
        int index = 0, k = 0;
        while (index < end) {
            chars[k++] = word1.charAt(index);
            chars[k++] = word2.charAt(index);
            index++;
        }
        while (index < word1.length()) {
            chars[k++] = word1.charAt(index++);
        }
        while (index < word2.length()) {
            chars[k++] = word2.charAt(index++);
        }
        return String.valueOf(chars);
    }
}
