package leetcode;

/**
 * @description: You are given a string s consisting only of lowercase English letters.
 * <p>
 * In one move, you can select any two adjacent characters of s and swap them.
 * <p>
 * Return the minimum number of moves needed to make s a palindrome.
 * <p>
 * Note that the input will be generated such that s can always be converted to a palindrome.
 * @author: LISHUAI
 * @createDate: 2022/12/9 18:36
 * @version: 1.0
 */

public class LeetCode_2193 {

    public static void main(String[] args) {
//        String s = "letelt";
//        String s = "aabb";
        String s = "eqvvhtcsaaqtqesvvqch";
        int i = minMovesToMakePalindrome(s);
        System.out.println(i);
    }

    public static int minMovesToMakePalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        int ans = 0;

        while (left < right) {
            if (chars[left] != chars[right]) {
                int l = nextChar(chars, left, right, -1, left);
                int r = nextChar(chars, right, left, 1, right);
                ans += Math.min(right - l, r - left);
                if (right - l <= r - left) {
                    char c = chars[l];
                    for (int i = l; i < right; i++) {
                        chars[i] = chars[i + 1];
                    }
                    chars[right] = c;
                } else {
                    char c = chars[r];
                    for (int i = r; i > left; i--) {
                        chars[i] = chars[i - 1];
                    }
                    chars[left] = c;
                }
            }
            left++;
            right--;
        }
        return ans;
    }

    private static int nextChar(char[] chars, int target, int cur, int n, int end) {
        while (cur != end && chars[cur] != chars[target]) {
            cur += n;
        }
        return cur;
    }
}
