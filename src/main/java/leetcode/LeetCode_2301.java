package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description: You are given two strings s and sub. You are also given a 2D character array mappings where mappings[i] = [oldi, newi] indicates that you may perform the following operation any number of times:
 * <p>
 * Replace a character oldi of sub with newi.
 * Each character in sub cannot be replaced more than once.
 * <p>
 * Return true if it is possible to make sub a substring of s by replacing zero or more characters according to mappings. Otherwise, return false.
 * <p>
 * A substring is a contiguous non-empty sequence of characters within a string.
 * @author: LISHUAI
 * @createDate: 2023/3/19 21:14
 * @version: 1.0
 */

public class LeetCode_2301 {

    public static boolean matchReplacement(String s, String sub, char[][] mappings) {
        if (sub.length() > s.length()) {
            return false;
        }
        Map<Character, Set<Character>> map = makeMapping(mappings);
        int sLen = s.length(), subLen = sub.length();

        for (int i = 0; i < sLen - subLen + 1; i++) {
            int index = 0;
            while (index < subLen) {
                char sc = s.charAt(i + index);
                char subc = sub.charAt(index);
                Set<Character> set = map.get(subc);
                if (sc != subc && (set == null || !set.contains(sc))) {
                    break;
                }
                index++;
            }
            if (index >= subLen) {
                return true;
            }
        }
        return false;
    }


    private static Map<Character, Set<Character>> makeMapping(char[][] mappings) {
        HashMap<Character, Set<Character>> map = new HashMap<>(mappings.length);
        for (char[] chars : mappings) {
            Set<Character> set = map.computeIfAbsent(chars[0], o -> new HashSet<>());
            set.add(chars[1]);
        }
        return map;
    }
}
