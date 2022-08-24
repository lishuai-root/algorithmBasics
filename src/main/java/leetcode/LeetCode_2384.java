package leetcode;

/**
 * @description: You are given a string num consisting of digits only.
 * <p>
 * Return the largest palindromic integer (in the form of a string) that can be formed using digits taken from num. It should not contain leading zeroes.
 * <p>
 * Notes:
 * <p>
 * You do not need to use all the digits of num, but you must use at least one digit.
 * The digits can be reordered.
 * @author: LISHUAI
 * @createDate: 2022/8/24 21:19
 * @version: 1.0
 */

public class LeetCode_2384 {

    public static void main(String[] args) {
//        String num = "444947137";
        String num = "00009";
        String s = largestPalindromic_02(num);
        System.out.println(s);
    }

    public static String largestPalindromic(String num) {
        int[] count = new int[10];
        char[] chars = new char[num.length()];
        int left = 0, right = num.length(), c = -1;

        for (int i = 0; i < num.length(); i++) {
            count[num.charAt(i) - '0']++;
        }

        for (int i = 9; i >= 0; i--) {
            int k = count[i] >>> 1;
            for (int j = 0; j < k; j++) {
                chars[left++] = chars[--right] = (char) (i + '0');
            }
            count[i] ^= (k << 1);
            if (c == -1 && count[i] > 0) {
                c = i;
            }
        }

        if (chars[0] == '0') {
            if (c == -1) {
                c = 0;
            }
            left = 0;
            right = chars.length;
        }
        if (c != -1) {
            chars[left++] = (char) (c + '0');
        }
        return String.valueOf(chars, 0, left) + String.valueOf(chars, right, chars.length - right);
    }


    public static String largestPalindromic_02(String num) {
        int[] count = new int[10];
        char[] chars = new char[num.length()];
        int left = 0, right = num.length(), c = -1;

        for (int i = 0; i < num.length(); i++) {
            count[num.charAt(i) - '0']++;
        }

        for (int i = 9; i > 0; i--) {
            int k = count[i] >>> 1;
            for (int j = 0; j < k; j++) {
                chars[left++] = chars[--right] = (char) (i + '0');
            }
            count[i] ^= (k << 1);
            if (c == -1 && count[i] > 0) {
                c = i;
            }
        }

        if (left != 0) {
            int k = count[0] >>> 1;
            for (int i = 0; i < k; i++) {
                chars[left++] = chars[--right] = '0';
            }
            count[0] ^= (k << 1);
            if (c == -1 && count[0] > 0) {
                c = 0;
            }
        } else if (c == -1) {
            c = 0;
        }
        if (c != -1) {
            chars[left++] = (char) (c + '0');
        }
        return String.valueOf(chars, 0, left) + String.valueOf(chars, right, chars.length - right);
    }
}
