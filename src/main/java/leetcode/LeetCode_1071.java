package leetcode;

/**
 * @description: For two strings s and t, we say "t divides s" if and only if s = t + ... + t (i.e., t is concatenated with itself one or more times).
 * <p>
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 * @author: LISHUAI
 * @createDate: 2023/2/1 21:19
 * @version: 1.0
 */

public class LeetCode_1071 {

    public static void main(String[] args) {
        System.out.println(gcdOfStrings("ABCDEF", "ABC"));
    }

    public static String gcdOfStrings(String str1, String str2) {
        String l = str1.length() >= str2.length() ? str1 : str2;
        String s = str1.length() >= str2.length() ? str2 : str1;
        int sLen = s.length(), lLen = l.length();
        if (l.equals(s) || isTrue(l, s, sLen - 1)) {
            return s;
        }
        if (!l.startsWith(s)) {
            return "";
        }
        for (int i = sLen >>> 1; i >= 0; i--) {
            if (l.charAt(i) == s.charAt(i) && isTrue(l, s, i)) {
                return s.substring(0, i + 1);
            }
        }
        return "";
    }


    private static boolean isTrue(String l, String s, int index) {
        if (index > l.length() >>> 1) {
            return false;
        }
        int lLen = l.length(), sLen = s.length();
        if (lLen % (index + 1) != 0 || sLen % (index + 1) != 0) {
            return false;
        }
        int p = 0;
        while (p < lLen) {
            for (int i = 0; i <= index; i++) {
                if (s.charAt(i) != l.charAt(p + i)) {
                    return false;
                }
                if (p + i < sLen && s.charAt(i) != s.charAt(p + i)) {
                    return false;
                }
            }
            p += index + 1;
        }
        return true;
    }
}
