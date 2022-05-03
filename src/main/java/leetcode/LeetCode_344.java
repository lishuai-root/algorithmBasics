package leetcode;

/**
 * @description: Write a function that reverses a string. The input string is given as an array of characters s.
 * <p>
 * You must do this by modifying the input array in-place with O(1) extra memory.
 * @author: LISHUAI
 * @createDate: 2022/4/1 20:16
 * @version: 1.0
 */

public class LeetCode_344 {

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        for (char c : s) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public static void reverseString(char[] s) {
        int len = s.length;
        char c;
        for (int i = 0; i < len >>> 1; i++) {
            c = s[i];
            s[i] = s[len - i - 1];
            s[len - i - 1] = c;
        }
    }
}
