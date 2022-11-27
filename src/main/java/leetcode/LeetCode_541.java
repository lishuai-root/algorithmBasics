package leetcode;

/**
 * @description: Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.
 * <p>
 * If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and leave the other as original.
 * @author: LISHUAI
 * @createDate: 2022/9/22 17:40
 * @version: 1.0
 */

public class LeetCode_541 {

    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        String s1 = reverseStr(s, k);
        System.out.println(s1);
        String s2 = reverseStr_02(s, k);
        System.out.println(s2);
    }

    public static String reverseStr(String s, int k) {
        int index = k - 1, left = 0, right = 0, len = s.length();
        char[] chars = s.toCharArray();
        while (left < len) {
            right = Math.min(index, len - 1);
            while (left < right) {
                char c = chars[left];
                chars[left++] = chars[right];
                chars[right--] = c;
            }
            left = index + k + 1;
            index += (k << 1);
        }
        return String.valueOf(chars);
    }


    public static String reverseStr_02(String s, int k) {
        int len = s.length(), index = Math.min(len - 1, k - 1);
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (((i / k) & 1) == 0) {
                if (i < index) {
                    char c = chars[i];
                    chars[i] = chars[index];
                    chars[index--] = c;
                }
            } else {
                index = Math.min(len - 1, i + k);
            }
        }
        return String.valueOf(chars);
    }
}
