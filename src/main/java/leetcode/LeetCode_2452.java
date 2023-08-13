package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given two string arrays, queries and dictionary. All words in each array comprise of lowercase English letters and have the same length.
 * <p>
 * In one edit you can take a word from queries, and change any letter in it to any other letter. Find all words from queries that, after a maximum of two edits, equal some word from dictionary.
 * <p>
 * Return a list of all words from queries, that match with some word from dictionary after a maximum of two edits. Return the words in the same order they appear in queries.
 * @author: LiShuai
 * @createDate: 2023/8/2 19:52
 * @version: 1.0
 */

public class LeetCode_2452 {

    public static List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> ans = new ArrayList<String>();
        for (String word : queries) {
            for (String dic : dictionary) {
                int len = word.length();
                int k = 0;
                for (int i = 0; i < len; i++) {
                    if (word.charAt(i) != dic.charAt(i)) {
                        ++k;
                    }
                    if (k > 2) {
                        break;
                    }
                }
                if (k <= 2) {
                    ans.add(word);
                    break;
                }
            }
        }
        return ans;
    }
}
