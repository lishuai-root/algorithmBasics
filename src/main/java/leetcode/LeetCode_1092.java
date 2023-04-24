package leetcode;

/**
 * @description: Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.
 * <p>
 * A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.
 * @author: LISHUAI
 * @createDate: 2022/12/15 15:15
 * @version: 1.0
 */

public class LeetCode_1092 {

    public static void main(String[] args) {
//        String str1 = "bbbaaaba", str2 = "bbababbb";
        String str1 = "abac", str2 = "cab";
//        String str1 = "aaaaaaaa", str2 = "aaaaaaaa";
        String s = shortestCommonSupersequence(str1, str2);
        System.out.println(s);
        System.out.println(shortestCommonSupersequence_dp(str1, str2));
        System.out.println(shortestCommonSupersequence_dp_02(str1, str2));
    }

    public static String shortestCommonSupersequence(String str1, String str2) {
        int len = str1.length() + str2.length();
        char[] chars = new char[len];
//        int index = shortestCommonSupersequenceProcess(str1, str2, 0, 0, chars, 0);
        return shortestCommonSupersequenceProcess(str1, str2, 0, 0, chars, 0);
    }

    private static String shortestCommonSupersequenceProcess(String str1, String str2, int index1, int index2, char[] chars, int charsIndex) {
        if (index1 >= str1.length()) {
            for (int i = index2; i < str2.length(); i++) {
                chars[charsIndex++] = str2.charAt(i);
            }
            return String.valueOf(chars, 0, charsIndex);
        }
        if (index2 >= str2.length()) {
            for (int i = index1; i < str1.length(); i++) {
                chars[charsIndex++] = str1.charAt(i);
            }
            return String.valueOf(chars, 0, charsIndex);
        }

        String ans;
        if (str1.charAt(index1) == str2.charAt(index2)) {
            chars[charsIndex] = str1.charAt(index1);
            return shortestCommonSupersequenceProcess(str1, str2, index1 + 1, index2 + 1, chars, charsIndex + 1);
        } else {
            chars[charsIndex] = str1.charAt(index1);
            String p1 = shortestCommonSupersequenceProcess(str1, str2, index1 + 1, index2, chars, charsIndex + 1);
            chars[charsIndex] = str2.charAt(index2);
            String p2 = shortestCommonSupersequenceProcess(str1, str2, index1, index2 + 1, chars, charsIndex + 1);
            if (p1.length() < p2.length()) {
                ans = p1;
            } else {
                ans = p2;
            }
        }
        return ans;
    }

    public static String shortestCommonSupersequence_dp(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        String[][] dp = new String[len1 + 1][len2 + 1];
        for (int i = 0; i < len2; i++) {
            dp[len1][i] = str2.substring(i, len2);
        }
        for (int i = 0; i <= len1; i++) {
            dp[i][len2] = str1.substring(i, len1);
        }

        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = str1.charAt(i) + dp[i + 1][j + 1];
                } else {
                    if (dp[i][j + 1].length() < dp[i + 1][j].length()) {
                        dp[i][j] = str2.charAt(j) + dp[i][j + 1];
                    } else {
                        dp[i][j] = str1.charAt(i) + dp[i + 1][j];
                    }
                }
                dp[i + 1][j + 1] = null;
            }
        }
        return dp[0][0];
    }

    public static String shortestCommonSupersequence_dp_02(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        StringBuilder[][] dp = new StringBuilder[len1 + 1][len2 + 1];
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = new StringBuilder().append(str2.substring(0, i));
        }
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = new StringBuilder().append(str1.substring(0, i));
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]
                            .append(str1.charAt(i - 1));
                } else {
                    if (dp[i][j - 1].length() < dp[i - 1][j].length()) {
                        dp[i][j] = new StringBuilder().append(dp[i][j - 1].toString())
                                .append(str2.charAt(j - 1));
                    } else {
                        dp[i][j] = new StringBuilder().append(dp[i - 1][j].toString())
                                .append(str1.charAt(i - 1));
                    }
                }
//                dp[i - 1][j - 1] = null;
            }
        }
        return dp[len1][len2].toString();
    }

    public static String shortestCommonSupersequence_02(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        String l = (len1 >= len2 ? str1 : str2);
        String s = (len1 < len2 ? str1 : str2);
        int[] ints = new int[s.length()];
        int i = shortestCommonSupersequence_02_process(str1, str2, 0, 0, ints, 0);
        for (int j = 0; j < i; j++) {
            System.out.print(ints[j]);
        }
        System.out.println();
        return "";
    }

    private static int shortestCommonSupersequence_02_process(String str1, String str2, int index1, int index2, int[] ints, int index) {
        if (index1 >= str1.length() || index2 >= str2.length()) {
            for (int j = 0; j < index; j++) {
                System.out.print(ints[j]);
            }
            System.out.println();
            return index;
        }

        int ans = 0;
        if (str1.charAt(index1) == str2.charAt(index2)) {
            ints[index] = index2;
            ans = shortestCommonSupersequence_02_process(str1, str2, index1 + 1, index2 + 1, ints, index + 1);

        } else {
            int p1 = shortestCommonSupersequence_02_process(str1, str2, index1, index2 + 1, ints, index);
            int p2 = shortestCommonSupersequence_02_process(str1, str2, index1 + 1, index2, ints, index);
            ans = Math.max(p1, p2);
        }
        return ans;
    }
}
