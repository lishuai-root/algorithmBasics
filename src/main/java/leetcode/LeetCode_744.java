package leetcode;

/**
 * @description: You are given an array of characters letters that is sorted in non-decreasing order, and a character target. There are at least two different characters in letters.
 * <p>
 * Return the smallest character in letters that is lexicographically greater than target. If such a character does not exist, return the first character in letters.
 * @author: LiShuai
 * @createDate: 2023/6/9 22:42
 * @version: 1.0
 */

public class LeetCode_744 {

    public static char nextGreatestLetter(char[] letters, char target) {
        int ans = -1, left = 0, right = letters.length - 1;

        while (left <= right) {
            int mod = (left + right) >> 1;
            if (letters[mod] <= target) {
                left = mod + 1;
            } else {
                ans = mod;
                right = mod - 1;
            }
        }
        return ans == -1 ? letters[0] : letters[ans];
    }
}
