package leetcode;

/**
 * @description: You are given a string s. You can convert s to a
 * palindrome
 * by adding characters in front of it.
 * <p>
 * Return the shortest palindrome you can find by performing this transformation.
 * @author: LISHUAI
 * @createDate: 2023/3/3 21:17
 * @version: 1.0
 */

public class LeetCode_214 {

    public static void main(String[] args) {
//        String s = "aacecaaa";
//        String s = "abcd";
        String s = "aaaaaaaaaaaaaaaa";
        String s1 = shortestPalindrome(s);
        System.out.println(s1);
        System.out.println(shortestPalindrome_02(s));
    }

    public static String shortestPalindrome_02(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int len = s.length(), index = 0, k = len - 1;
        char[] chars = new char[len];

        while (k > 0) {
            int left = 0, right = k;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                left++;
                right--;
            }
            if (left < right) {
                chars[index++] = s.charAt(k);
            } else {
                break;
            }
            k--;
        }
        return String.valueOf(chars, 0, index) + s;
    }

    public static String shortestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int len = s.length(), mid = (len - 1) >>> 1;
        int index = -1;

        while (mid >= 0) {
            if (process(s, mid, mid + 1)) {
                index = (mid + 1) << 1;
            } else if (process(s, mid, mid)) {
                index = (mid << 1) + 1;
            }
            if (index != -1) {
                break;
            }
            mid--;
        }
        char[] chars = new char[s.length() - index];
        int k = chars.length;
        while (index < s.length()) {
            chars[--k] = s.charAt(index++);
        }
        return String.valueOf(chars) + s;
    }

    private static boolean process(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left--;
            right++;
        }
        return left < 0;
    }
}
