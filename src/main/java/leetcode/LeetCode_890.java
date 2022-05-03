package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a list of strings words and a string pattern, return a list of words[i] that match pattern.
 * You may return the answer in any order.
 * <p>
 * A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x),
 * we get the desired word.
 * <p>
 * Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter,
 * and no two letters map to the same letter.
 * @author: LISHUAI
 * @createDate: 2022/4/25 19:36
 * @version: 1.0
 */

public class LeetCode_890 {

    public static void main(String[] args) {
        String[] words = {"abc", "deq", "mee", "aqq", "dkd", "ccc"};
        String pattern = "abb";
        List<String> list = findAndReplacePattern(words, pattern);
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        char[] woChars, paChars, wordChar;
        char[] patternChar = pattern.toCharArray();
        for (String word : words) {
            if (word.length() != pattern.length()) {
                continue;
            }
            wordChar = word.toCharArray();
            woChars = new char[26];
            paChars = new char[26];
            int i = 0;

            while (i < patternChar.length) {
                if (paChars[patternChar[i] - 'a'] == 0 && woChars[wordChar[i] - 'a'] == 0) {
                    paChars[patternChar[i] - 'a'] = wordChar[i];
                    woChars[wordChar[i] - 'a'] = patternChar[i];
                } else {
                    if (paChars[patternChar[i] - 'a'] != wordChar[i] || woChars[wordChar[i] - 'a'] != patternChar[i]) {
                        break;
                    }
                }
                ++i;
            }
            if (i == patternChar.length) {
                ans.add(word);
            }
        }

        return ans;
    }
}








