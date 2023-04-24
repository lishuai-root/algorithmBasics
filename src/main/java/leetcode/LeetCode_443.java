package leetcode;

/**
 * @description: Given an array of characters chars, compress it using the following algorithm:
 * <p>
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 * <p>
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 * <p>
 * After you are done modifying the input array, return the new length of the array.
 * <p>
 * You must write an algorithm that uses only constant extra space.
 * @author: LISHUAI
 * @createDate: 2023/3/2 21:00
 * @version: 1.0
 */

public class LeetCode_443 {

    public static void main(String[] args) {
//        char[] chars = {'a'};
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int compress = compress(chars);
        System.out.println(compress);
    }

    public static int compress(char[] chars) {
        int ans = 0;
        int left = 0, right = 0, index = 0;

        while (right <= chars.length) {
            if (right == chars.length || chars[right] != chars[left]) {
                int size = right - left;
                ans++;
                chars[index++] = chars[left];
                if (size > 1) {
                    String s = "" + size;
                    ans += s.length();
                    for (int i = 0; i < s.length(); i++) {
                        chars[index++] = s.charAt(i);
                    }
                }
                left = right;
            }
            right++;
        }
        return ans;
    }
}
