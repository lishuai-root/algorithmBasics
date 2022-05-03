package leetcode;

/**
 * @description: Given an encoded string, return its decoded string.
 * <p>
 * The encoding rule is: k[encoded_string],
 * where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * <p>
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * <p>
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * @author: LISHUAI
 * @createDate: 2021/11/28 17:11
 * @version: 1.0
 */

public class LeetCode_394 {

    public static void main(String[] args) {

//        String s = "abc3[cd]xyz";
//        String s = "3[a2[c]]";
//        String s = "2[abc]3[cd]ef2[abc]3[cd]ef2[abc]3[cd]ef2[abc]3[cd]ef2[abc]3[cd]ef";
        String s = "3[2[2[2[2[a]]]]]";
//        String s = "3[a]2[bc]";

        String s1 = decodeString(s);

        System.out.println(s1);
        System.out.println(s1.length());

        StringBuilder ss1 = new StringBuilder();
        StringBuilder ss2 = new StringBuilder();

        ss1.append("[".repeat(10000));
        ss2.append("[".repeat(10000));
        String str = ss1 + "a" + ss2;
        String s2 = decodeString(str);
        System.out.println(s2);

    }

    public static String decodeString(String s) {

        return decodeStringProcess(s);
    }

    private static String decodeStringProcess(String s) {
        char[] chars = s.toCharArray();
        Info info = decodeStringProcess(chars, 0);
        return info.value;
    }

    private static Info decodeStringProcess(char[] chars, int start) {
        int size = 0;
        StringBuilder sbr = new StringBuilder();

        while (start < chars.length) {
            if (chars[start] >= 'a' && chars[start] <= 'z') {
                sbr.append(chars[start]);
            } else if (chars[start] >= '0' && chars[start] <= '9') {
                size = size * 10 + (chars[start] - '0');
            } else if (chars[start] == '[') {
                Info info = decodeStringProcess(chars, start + 1);
                sbr.append(info.value.repeat(Math.max(size, 1)));
                start = info.index;
                size = 0;
            } else {
                break;
            }
            start++;
        }
        return new Info(sbr.toString(), start);
    }

    static class Info {
        String value;
        int index;

        public Info(String value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
