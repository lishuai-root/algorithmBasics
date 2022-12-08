package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Given an array of strings strs, return the length of the longest uncommon
 * subsequence between them. If the longest uncommon subsequence does not exist, return -1.
 * <p>An uncommon subsequence between an array of strings is a string that is a subsequence of
 * one string but not the others.
 * <p>A subsequence of a string s is a string that can be obtained after deleting any number of
 * characters from s.
 * <p>For example, "abc" is a subsequence of "aebdc" because you can delete the underlined
 * characters in "aebdc" to get "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and
 * "" (empty string).
 * @author: LISHUAI
 * @createDate: 2022/3/19 21:52
 * @version: 1.0
 */
public class LeetCode_522 {

    public static void main(String[] args) {
//        String[] strs = {"aba", "cdc", "eae"};
//        String[] strs = {"aaa", "aaa", "aa"};
        String[] strs = {"abc", "aba", "aa"};
        int luSlength = findLUSlength(strs);
        System.out.println(luSlength);
        System.out.println(findLUSlength_other(strs));
    }

    public static int findLUSlength_other(String[] strs) {
        int res = -1, n = strs.length;
        for (int i = 0; i < n; i++) {
            if (strs[i].length() < res) continue;
            int j = -1;
            while (++j < n) if (i != j && isSubsequence(strs[i], strs[j])) break;
            if (j == n) res = Math.max(res, strs[i].length());
        }
        return res;
    }

    public static boolean isSubsequence(String a, String b) {
        int i = 0, j = 0;
        while (i < a.length() && j < b.length()) if (a.charAt(i) == b.charAt(j++)) i++;
        return i == a.length();
    }

    public static int findLUSlength(String[] strs) {
        char[] chars = new char[11];
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String s : strs) {
            findLUSlengthProcess(s, map, chars, 0, 0);
        }
        int ans = -1;
        for (String s : map.keySet()) {
            if (map.get(s) == 1) {
                ans = Math.max(ans, s.length());
            }
        }
        return ans;
    }

    private static void findLUSlengthProcess(String s, Map<String, Integer> map, char[] chars, int k, int index) {
        if (index >= s.length()) {
            return;
        }
        findLUSlengthProcess(s, map, chars, k, index + 1);
        chars[k] = s.charAt(index);
        String t = String.valueOf(chars, 0, k + 1);
        map.put(t, map.getOrDefault(t, 0) + 1);
        findLUSlengthProcess(s, map, chars, k + 1, index + 1);
    }

}
