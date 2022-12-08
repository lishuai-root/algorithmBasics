package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Two strings are considered close if you can attain one from the other using the following operations:
 * <p>
 * Operation 1: Swap any two existing characters.
 * For example, abcde -> aecdb
 * Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
 * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
 * You can use the operations on either string as many times as necessary.
 * <p>
 * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
 * @author: LISHUAI
 * @createDate: 2022/12/2 23:06
 * @version: 1.0
 */

public class LeetCode_1657 {

    public static void main(String[] args) {
//        String word1 = "abc", word2 = "bca";
//        String word1 = "a", word2 = "aa";
        String word1 = "cabbba", word2 = "abbccc";
        boolean b = closeStrings(word1, word2);
        System.out.println(b);
        System.out.println((~0) + 1);
    }

    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for (int i = 0; i < word2.length(); i++) {
            count1[word1.charAt(i) - 'a']++;
            count2[word2.charAt(i) - 'a']++;
        }
        Map<Integer, Integer> map = new HashMap<>(32);
        int size = 0;
        for (int i = 0; i < count1.length; i++) {
            if ((count2[i] != 0 && count1[i] == 0)
                    || (count1[i] != 0 && count2[i] == 0)) {
                return false;
            }
            if (count2[i] != 0) {
                map.put(count2[i], map.getOrDefault(count2[i], 0) + 1);
                size++;
            }
        }

        for (int i : count1) {
            if (i != 0 && map.getOrDefault(i, 0) > 0) {
                map.put(i, map.get(i) - 1);
                size--;
            }
        }
        return size == 0;
    }

}
