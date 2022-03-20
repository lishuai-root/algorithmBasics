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

        String s = "abc3[cd]xyz";

        String s1 = decodeString(s);

        System.out.println(s1);
    }

    public static String decodeString(String s) {

        int index = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) >= 0 && s.charAt(i) <= 9) {

                index++;
            }
        }

        if (index > 0) {

            index = Integer.parseInt(s.substring(0, index));
        }

        return process(s, index);
    }

    public static String process(String s, int size) {
        int right, i = 1, childRight = 0, c = 0, f = 0;

        int left = s.indexOf('[');

        right = s.lastIndexOf(']');

        int childLeft = s.indexOf(left + 1, '[');

        String p = "";

        if (childLeft != -1) {

            childRight = s.indexOf(']', right);

            i = childLeft - 1;

            while (i >= 0 && s.charAt(i) >= 0 && s.charAt(i) <= 9) {

                c++;

                i--;
            }

            if (i + 1 < childLeft) {

                i = Integer.parseInt(s.substring(i + 1, childLeft));
            }

            p = process(s.substring(childLeft + 1, childRight), i);
        }

        i = left - 1;

        while (i >= 0 && s.charAt(i) >= 0 && s.charAt(i) <= 9) {

            f++;

            i--;
        }

        i = i + 1 < left ? Integer.parseInt(s.substring(i + 1, left)) : 1;

        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < i; j++) {

            sb.append(s.substring(0, left - f))
                    .append(s.substring(left + 1, childLeft - c))
                    .append(p)
                    .append(s.substring(right + 1, s.length()));
        }

        return sb.toString();
    }
}
