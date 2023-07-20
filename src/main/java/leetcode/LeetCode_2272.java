package leetcode;

import java.util.Arrays;

/**
 * @description: The variance of a string is defined as the largest difference between the number of occurrences of any 2 characters present in the string. Note the two characters may or may not be the same.
 * <p>
 * Given a string s consisting of lowercase English letters only, return the largest variance possible among all substrings of s.
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * @author: LiShuai
 * @createDate: 2023/7/9 13:09
 * @version: 1.0
 */

public class LeetCode_2272 {

    public static int largestVariance(String s) {
        //First we record the last index of all letters in the string, if a letter never appears, its value would be -1
        int[] lastInd = new int[26];
        Arrays.fill(lastInd, -1);
        for (int i = s.length() - 1; i >= 0; i--)
            if (lastInd[s.charAt(i) - 'a'] == -1)
                lastInd[s.charAt(i) - 'a'] = i;
        int res = 0;
        //Organize all letters in pairs
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                //Only select two distinct letters that appear in the string
                if (i == j || lastInd[i] == -1 || lastInd[j] == -1) continue;
                int majorC = 0, minorC = 0;
                char major = (char) ('a' + i), minor = (char) ('a' + j);
                for (int k = 0; k < s.length(); k++) {
                    char cur = s.charAt(k);
                    //If this element is neither major nor minor, we skip it, it does not need to be considered
                    if (cur != major && cur != minor) continue;
                    if (cur == major) majorC++;
                    if (cur == minor) minorC++;
                    //if minorCount > majorCount and this minor element is not the last minor, we do not want this substring, we only want the major elements to dominate, so discard
                    if (majorC < minorC && k != lastInd[minor - 'a']) {
                        majorC = 0;
                        minorC = 0;
                    }
                    //We only consider a variance to be valid if at least one minor exists in this substring, so we update var when minor>1
                    if (minorC > 0) res = Math.max(res, majorC - minorC);
                }
            }
        }
        return res;
    }
}
