package leetcode;

/**
 * @description: Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 * <p>
 * The algorithm for myAtoi(string s) is as follows:
 * <p>
 * Read in and ignore any leading whitespace.
 * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively.
 * Assume the result is positive if neither is present.
 * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
 * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
 * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231,
 * and integers greater than 231 - 1 should be clamped to 231 - 1.
 * Return the integer as the final result.
 * Note:
 * <p>
 * Only the space character ' ' is considered a whitespace character.
 * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
 * @author: LISHUAI
 * @createDate: 2021/11/30 20:54
 * @version: 1.0
 */

public class LeetCode_008 {

    public static void main(String[] args) {

        System.out.println((int) '0');
        System.out.println((int) '9');

        String s = "010";

        int i = myAtoi(s);

        System.out.println(i);
    }

    public static int myAtoi(String s) {

        if (s.length() == 0) {

            return 0;
        }

        String maxStr = "2147483648";

        String minStr = "2147483647";

        int index = 0;

        while (index < s.length() && (s.charAt(index) == ' ' || s.charAt(index) == '0')) {

            index++;
        }

        if (index >= s.length()) {

            return 0;
        }

        if (s.charAt(index) != '-' && s.charAt(index) != '+' && (s.charAt(index) > 57 || s.charAt(index) < 48)) {

            return 0;
        }


        if (index >= s.length()) {

            return 0;
        }

        while (index < s.length() && (s.charAt(index) < 48 || s.charAt(index) > 57) && s.charAt(index) != '-' && s.charAt(index) != '+') {

            index++;
        }

        char c = s.charAt(index++);


        if (c == '-' || c == '+') {
            while (index < s.length() && s.charAt(index) == '0') {

                index++;
            }
        }


        if (index > s.length()) {

            return 0;
        }


        StringBuilder sb = new StringBuilder();

        sb.append(c);

        while (index < s.length()) {

            c = s.charAt(index++);

            if (c == '.') {

                while (index < s.length() && ((s.charAt(index) <= 57 && s.charAt(index) >= 48) || s.charAt(index) == ' ')) {
                    index++;
                }

                break;
            }

            if (c > 57 || c < 48) {

                break;
            }


            sb.append(c);
        }

        for (int i = index; i < s.length(); i++) {

            if (s.charAt(index) <= 57 && s.charAt(index) >= 48) {

                return 0;
            }
        }

        String str = sb.toString();

        if (str.equals("+") || str.equals("-")) {

            return 0;
        }

        if (str.charAt(0) == '-') {

            if (str.length() - 1 == maxStr.length()) {
                for (int i = 1; i < str.length(); i++) {

                    if (str.charAt(i) > maxStr.charAt(i - 1)) {

                        return Integer.MIN_VALUE;
                    }
                }

                return Integer.parseInt(str);

            } else if (str.length() - 1 > maxStr.length()) {

                return Integer.MIN_VALUE;
            } else {

                return Integer.parseInt(str);
            }
        } else {

            if (str.length() == minStr.length()) {

                for (int i = 0; i < str.length(); i++) {

                    if (str.charAt(i) > minStr.charAt(i)) {

                        return Integer.MAX_VALUE;
                    }
                }

                return Integer.parseInt(str);
            } else if (str.length() > minStr.length()) {

                return Integer.MAX_VALUE;
            } else {

                return Integer.parseInt(str);
            }

        }
    }
}
