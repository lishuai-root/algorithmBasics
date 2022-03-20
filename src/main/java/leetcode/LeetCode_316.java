package leetcode;

import java.util.Arrays;

/**
 * @description: Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 * @author: LISHUAI
 * @createDate: 2022/3/18 17:10
 * @version: 1.0
 */

public class LeetCode_316 {

    public static void main(String[] args) {
        String s = "bcabc";
        String s1 = removeDuplicateLetters(s);
        System.out.println(s1);
    }

    public static String removeDuplicateLetters(String s) {
        int[] ans = new int[26];
        int[] max = new int[26];
        char[] chars = s.toCharArray();
        Arrays.fill(ans, -1);
        for (int i = 0; i < chars.length; i++) {
            max[chars[i] - 'a'] = i;
        }
        for (int i = 0; i < s.length(); i++) {
            if (ans[chars[i] - 'a'] == -1) {
                ans[chars[i] - 'a'] = i;
                continue;
            }

            int cc = chars[i] - 'a';
            int c = ans[chars[i] - 'a'];
            int p = c;
            boolean b = true;
//            while (++c < i && b) {
////                if (chars[c] > chars[i] && max[c] < i) {
////                    b = false;
////                }
//                if (chars[c] < chars[i]) {
//                    b = false;
//                }
//            }
//            if (chars[c] > chars[c + 1]) {
//                ans[chars[i] - 'a'] = i;
//            }
            while (++cc < ans.length) {
                if (ans[cc] == c + 1) {

                }
            }
//            if (b) {
//                ans[chars[i] - 'a'] = i;
//            }
        }
        Arrays.sort(ans);
        StringBuilder sbr = new StringBuilder();
        for (int i : ans) {
            if (i != -1) {
                sbr.append(chars[i]);
            }
        }
        return sbr.toString();
    }
}
