package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description: You are given two string arrays words1 and words2.
 * <p>
 * A string b is a subset of string a if every letter in b occurs in a including multiplicity.
 * <p>
 * For example, "wrr" is a subset of "warrior" but is not a subset of "world".
 * A string a from words1 is universal if for every string b in words2, b is a subset of a.
 * <p>
 * Return an array of all the universal strings in words1. You may return the answer in any order.
 * @author: LISHUAI
 * @createDate: 2022/7/30 23:05
 * @version: 1.0
 */

public class LeetCode_916 {

    public static void main(String[] args) {
        String[] word1 = makeStringArray(10000, 10);
        String[] word2 = makeStringArray(10000, 10);
        long start = System.currentTimeMillis();
        wordSubsets(word1, word2);
        long end = System.currentTimeMillis();
        System.out.println("wordSubsets : " + (end - start));
        start = System.currentTimeMillis();
        wordSubsets_02(word1, word2);
        end = System.currentTimeMillis();
        System.out.println("wordSubsets_02 : " + (end - start));
    }

    private static String[] makeStringArray(int size, int len) {
        String[] str = new String[size];
        char[] chars = new char[len];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < len; j++) {
                chars[j] = (char) (random.nextInt(26) + 'a');
            }
            str[i] = String.valueOf(chars);
        }
        return str;
    }

    public static List<String> wordSubsets(String[] words1, String[] words2) {

        int[] sum2 = new int[26];
        for (String line : words2) {
            int[] s = new int[26];
            for (int i = 0; i < line.length(); i++) {
                s[line.charAt(i) - 'a']++;
            }
            for (int i = 0; i < s.length; i++) {
                if (s[i] > sum2[i]) {
                    sum2[i] = s[i];
                }
            }
        }

        List<String> ans = new ArrayList<>();
        for (String line : words1) {
            int[] s = new int[26];
            for (int i = 0; i < line.length(); i++) {
                s[line.charAt(i) - 'a']++;
            }
            if (max(s, sum2)) {
                ans.add(line);
            }
        }
        return ans;
    }

    private static boolean max(int[] sum1, int[] sum2) {
        for (int i = 0; i < sum1.length; i++) {
            if (sum1[i] < sum2[i]) {
                return false;
            }
        }
        return true;
    }


    public static List<String> wordSubsets_02(String[] words1, String[] words2) {

        int[] sum2 = new int[26];
        int flag = 0;
        for (String line : words2) {
            int[] s = new int[26];
            for (int i = 0; i < line.length(); i++) {
                int c = line.charAt(i) - 'a';
                s[c]++;
                sum2[c] = Math.max(sum2[c], s[c]);
                flag |= (1 << c);
            }
        }

        List<String> ans = new ArrayList<>();
        for (String line : words1) {
            int[] s = new int[26];
            int f = flag;
            for (int i = 0; i < line.length(); i++) {
                int c = line.charAt(i) - 'a';
                s[c]++;
                if (s[c] >= sum2[c] && ((1 << c) & f) != 0) {
                    f ^= (1 << c);
                }
            }
            if (f == 0) {
                ans.add(line);
            }
        }
        return ans;
    }
}
