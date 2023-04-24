package leetcode;

/**
 * @description: Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
 * @author: LISHUAI
 * @createDate: 2023/1/1 17:42
 * @version: 1.0
 */

public class LeetCode_205 {

    public static boolean isIsomorphic(String s, String t) {
        char[] chars = new char[256];
        char[] exists = new char[256];
        int len = s.length();

        for (int i = 0; i < len; i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if (chars[cs] == 0 && exists[ct] == 0) {
                chars[cs] = ct;
                exists[ct] = cs;
            } else if (chars[cs] != ct || exists[ct] != cs) {
                return false;
            }
        }
        return true;
    }
}
