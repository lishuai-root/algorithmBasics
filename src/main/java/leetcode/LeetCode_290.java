package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: Given a pattern and a string s, find if s follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 * @author: LISHUAI
 * @createDate: 2022/1/17 22:38
 * @version: 1.0
 */

public class LeetCode_290 {

    public static void main(String[] args) {

        String pattern = "ab", s = "happy hacking";

        boolean b = wordPattern(pattern, s);

        System.out.println(b);
    }

    public static boolean wordPattern(String pattern, String s) {

        String[] strs = new String[26];

        Set<String> set = new HashSet<>();

        String[] split = s.split(" ");

        int j = 0;

        for (int i = 0; i < pattern.length(); i++, j++) {

            if (i >= split.length) {

                return false;
            }

            if (strs[pattern.charAt(i) - 'a'] == null && !set.contains(split[i])) {

                strs[pattern.charAt(i) - 'a'] = split[i];

                set.add(split[i]);
            } else {

                if (!split[i].equals(strs[pattern.charAt(i) - 'a'])) {

                    return false;
                }
            }
        }

        return j == split.length;
    }
}
