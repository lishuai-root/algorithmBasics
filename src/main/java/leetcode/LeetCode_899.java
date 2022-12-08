package leetcode;

import java.util.Arrays;

/**
 * @description: You are given a string s and an integer k. You can choose one of the first k letters of s and append it at the end of the string..
 * <p>
 * Return the lexicographically smallest string you could have after applying the mentioned step any number of moves.
 * @author: LISHUAI
 * @createDate: 2022/12/1 4:50
 * @version: 1.0
 */

public class LeetCode_899 {
    // Time Complexity: O(N ^ 2)
    // Space Complexity: O(N)
    class Solution {
        public String orderlyQueue(String s, int k) {
            String ans = s;
            if (k == 1) {
                // "cba" -> "bac" -> "acb" -> "cba" -> ...
                // we only have N swaps here
                // as it will become the original string after N swaps
                // hence, we can try all N possible swaps and find the lexicographically smallest one
                for (int i = 0; i < s.length(); i++) {
                    String t = s.substring(i) + s.substring(0, i);
                    if (ans.compareTo(t) > 0) {
                        ans = t;
                    }
                }
            } else {
                // if k > 1, we can move any character to any position by swapping two adjacent characters
                // By swapping a number of times,
                // e.g. "cab"
                // eventually we could have "abc", "acb", "bca", "bac", "cba", "cab" (3 * 2 * 1 = 6 possible arrangements)
                // so the lexicographically smallest string would be the sorted string
                char[] ca = ans.toCharArray();
                Arrays.sort(ca);
                ans = new String(ca);
            }
            return ans;
        }
    }
}
