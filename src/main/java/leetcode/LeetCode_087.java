package leetcode;

/**
 * @description: We can scramble a string s to get a string t using the following algorithm:
 * <p>
 * If the length of the string is 1, stop.
 * If the length of the string is > 1, do the following:
 * Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
 * Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
 * Apply step 1 recursively on each of the two substrings x and y.
 * Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.
 * @author: LISHUAI
 * @createDate: 2022/4/9 15:14
 * @version: 1.0
 */

public class LeetCode_087 {

    private static char[] wordCount1, wordCount2;
    private static int size = 0;
    private static Boolean[][][][] dp;

    public static void main(String[] args) {
//        String s1 = "great", s2 = "rgeat";
//        String s1 = "abcde", s2 = "caebd";
//        String s1 = "a", s2 = "b";
//        String s1 = "abc", s2 = "bca";
//        String s1 = "abcdbdacbdac", s2 = "bdacabcdbdac";
//        String s1 = "eebaacbcbcadaaedceaaacadccd", s2 = "eadcaacabaddaceacbceaabeccd";
        String s1 = "ccababcaabcb", s2 = "bccbccaaabab";
        long start = System.currentTimeMillis();
        boolean b = isScramble(s1, s2);
        long end = System.currentTimeMillis();
        System.out.println(b);
        System.out.println("times : " + (end - start));
        System.out.println("size : " + size);
    }

    public static boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        char[] chars1 = s1.toCharArray(), chars2 = s2.toCharArray();
        if (!isEqual(chars1, 0, chars1.length - 1, chars2, 0, chars2.length - 1)) {
            return false;
        }
        dp = new Boolean[s1.length()][s1.length()][s1.length()][s1.length()];
        return isScrambleProcess(chars1, 0, chars1.length - 1, chars2, 0, chars2.length - 1);
    }

    private static boolean isScrambleProcess(char[] chars1, int start1, int end1, char[] chars2, int start2, int end2) {
        size++;
//        System.out.println("start1 : " + start1 + ", end1 : " + end1 + ", start2 : " + start2 + ", end2 : " + end2);
        if (start1 >= end1 || start2 >= end2) {
            return chars1[start1] == chars2[start2];
        }

        if (dp[start1][end1][start2][end2] != null) {
            return dp[start1][end1][start2][end2];
        }
        boolean ans = false;
        int sp = end1 - start1;
        int[] wordStart1 = new int[26], wordStart2 = new int[26], endEnd2 = new int[26];

        int k = 0;
        for (; k <= sp; k++) {
            if (chars1[start1 + k] != chars2[start2 + k]) {
                break;
            }
        }

        if (k > sp) {
            dp[start1][end1][start2][end2] = true;
            return true;
        }
        for (int i = 0; i < sp && !ans; i++) {
            wordStart1[chars1[start1 + i] - 'a']++;
            wordStart2[chars2[start2 + i] - 'a']++;
            endEnd2[chars2[end2 - i] - 'a']++;


            if (isEqual(wordStart1, wordStart2)) {
                ans = (isScrambleProcess(chars1, start1, start1 + i, chars2, start2, start2 + i)
                        && isScrambleProcess(chars1, start1 + i + 1, end1, chars2, start2 + i + 1, end2));
            }
            if (!ans) {
                if (isEqual(wordStart1, endEnd2)) {
                    ans = (isScrambleProcess(chars1, start1, start1 + i, chars2, end2 - i, end2)
                            && isScrambleProcess(chars1, start1 + i + 1, end1, chars2, start2, end2 - i - 1));
                }
            }

        }

//        for (int i = 0; i < sp && !ans; i++) {
//            if (isEqual(chars1, start1, start1 + i, chars2, start2, start2 + i) &&
//                    isEqual(chars1, start1 + i + 1, end1, chars2, start2 + i + 1, end2)) {
//                ans |= (isScrambleProcess(chars1, start1, start1 + i, chars2, start2, start2 + i)
//                        && isScrambleProcess(chars1, start1 + i + 1, end1, chars2, start2 + i + 1, end2));
//            }
//            if (!ans) {
//                if (isEqual(chars1, start1, start1 + i, chars2, end2 - i, end2) &&
//                        isEqual(chars1, start1 + i + 1, end1, chars2, start2, end2 - i - 1)) {
//                    ans |= (isScrambleProcess(chars1, start1, start1 + i, chars2, end2 - i, end2)
//                            && isScrambleProcess(chars1, start1 + i + 1, end1, chars2, start2, end2 - i - 1));
//                }
//            }
//        }
        dp[start1][end1][start2][end2] = ans;
        return ans;
    }

    private static boolean isEqual(int[] word1, int[] word2) {
        for (int i = 0; i < word1.length; i++) {
            if (word1[i] != word2[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isEqual(char[] chars1, int start1, int end1, char[] chars2, int start2, int end2) {
        if (end1 - start1 != end2 - start2) {
            return false;
        }
        int[] result1 = new int[26], result2 = new int[26];
        int sp = end1 - start1;

        for (int i = 0; i <= sp; i++) {
            result1[chars1[start1 + i] - 'a']++;
            result2[chars2[start2 + i] - 'a']++;
        }
        for (int i = 0; i < result1.length; i++) {
            if (result1[i] != result2[i]) {
                return false;
            }
        }
        return true;
    }


}
