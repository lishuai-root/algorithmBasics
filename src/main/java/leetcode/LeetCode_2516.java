package leetcode;

/**
 * @description: You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.
 * <p>
 * Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.
 * @author: LISHUAI
 * @createDate: 2023/4/8 15:35
 * @version: 1.0
 */

public class LeetCode_2516 {

    public static void main(String[] args) {
        String s = "aabaaaacaabc";
        int i = takeCharacters(s, 2);
        System.out.println(i);
    }

    public static int takeCharacters(String s, int k) {
        if (k == 0) {
            return 0;
        }
        if (s.length() < 3 * k) {
            return -1;
        }
        int index = 0, sign = 0, len = s.length();
        int[] cache = new int[3];
        while (index < len) {
            int c = s.charAt(index++) - 'a';
            cache[c]++;
            if (cache[c] == k && ++sign == 3) {
                break;
            }
        }
        if (sign != 3) {
            return -1;
        }
        int ans = index, right = len;

        while (--index >= 0) {
            int c = s.charAt(index) - 'a';
            cache[c]--;
            if (cache[c] < k) {
                while (--right >= index) {
                    int n = s.charAt(right) - 'a';
                    cache[n]++;
                    if (c == n) {
                        break;
                    }
                }
            }
            ans = Math.min(ans, index + len - right);
        }
        return ans;
    }
}
