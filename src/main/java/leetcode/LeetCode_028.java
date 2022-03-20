package leetcode;

/**
 * @description: Implement strStr().
 * <p>
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Clarification:
 * <p>
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * <p>
 * For the purpose of this problem, we will return 0 when needle is an empty string.
 * This is consistent to C's strstr() and Java's indexOf().
 * @author: LISHUAI
 * @createDate: 2021/12/2 21:32
 * @version: 1.0
 */

public class LeetCode_028 {

    public static void main(String[] args) {
        System.out.println("abc".indexOf(""));
    }

    public static int strStr(String haystack, String needle) {

        return haystack.indexOf(needle);
    }
}
