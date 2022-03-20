package leetcode;

/**
 * @description: Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * @author: LISHUAI
 * @createDate: 2021/11/15 21:00
 * @version: 1.0
 */

public class LeetCode_020 {

    public static void main(String[] args) {
        String s = "(]";

        boolean valid = isValid(s);

        System.out.println(valid);
    }

    public static boolean isValid(String s) {

        int len = s.length();

        char[] chars = new char[len];

        int index = -1;

        char c = ' ';

        for (int i = 0; i < len; i++) {

            c = s.charAt(i);

            if (c == '(' || c == '{' || c == '[') {

                chars[++index] = c;
            } else {

                if (index < 0) {

                    return false;
                }

                if (c == ')' && chars[index--] != '(') {

                    return false;
                } else if (c == '}' && chars[index--] != '{') {

                    return false;
                } else if (c == ']' && chars[index--] != '[') {

                    return false;
                }

            }
        }

        return index == -1;
    }
}
