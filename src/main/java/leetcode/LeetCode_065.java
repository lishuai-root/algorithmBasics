package leetcode;

import java.util.Set;

/**
 * @description: A valid number can be split up into these components (in order):
 * <p>
 * A decimal number or an integer.
 * (Optional) An 'e' or 'E', followed by an integer.
 * A decimal number can be split up into these components (in order):
 * <p>
 * (Optional) A sign character (either '+' or '-').
 * One of the following formats:
 * One or more digits, followed by a dot '.'.
 * One or more digits, followed by a dot '.', followed by one or more digits.
 * A dot '.', followed by one or more digits.
 * An integer can be split up into these components (in order):
 * <p>
 * (Optional) A sign character (either '+' or '-').
 * One or more digits.
 * For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].
 * <p>
 * Given a string s, return true if s is a valid number.
 * @author: LISHUAI
 * @createDate: 2022/12/17 21:34
 * @version: 1.0
 */

public class LeetCode_065 {

    private static final Set<Character> TEMP;

    static {
        TEMP = new java.util.HashSet<>();
        for (int i = 0; i < 26; i++) {
            TEMP.add((char) (i + 97));
            TEMP.add((char) (i + 65));
        }
    }

    public static void main(String[] args) {
        System.out.println((int) 'a');
        System.out.println((int) 'A');
    }

    public static boolean isNumber(String s) {
        if (s.length() == 1) return Character.isDigit(s.charAt(0));
        s = s.toLowerCase();
        int countE = 0, countLetter = 0, countDot = 0, countSign = 0, countSymbol = 0, countDigit = 0;
        for (char c : s.toCharArray()) {
            if (c == 'e') countE++;
            else if (c == '.') countDot++;
            else if (Character.isLowerCase(c)) countLetter++;
            else if (c == '-' || c == '+') countSign++;
            else if (Character.isDigit(c)) countDigit++;
            else countSymbol++;
        }
        if (countE > 1 || countDot > 1 || countLetter > 0
                || countSign > 2 || countSymbol > 0 || countDigit < s.length() - 4) return false;

        String[] arr = s.split("e");
        boolean containsE = countE > 0;
        if (arr.length > 2) return false;
        if (arr.length == 2) {
            String two = arr[1];
            if (two.length() == 1 && !Character.isDigit(two.charAt(0))) return false;
            if (two.length() > 0) {
                if (!(two.charAt(0) == '+' || two.charAt(0) == '-' || Character.isDigit(two.charAt(0))))
                    return false;
                for (int i = 1; i < two.length(); i++) {
                    char c = two.charAt(i);
                    if (!Character.isDigit(c)) return false;
                }
            }
        }
        if ((arr.length == 1 || arr[0].length() == 0) && containsE) return false;
        String one = arr[0];
        if (one.length() > 0) {
            if (!(one.charAt(0) == '+' || one.charAt(0) == '-' || one.charAt(0) == '.' || Character.isDigit(one.charAt(0))))
                return false;
            String[] arrOne = one.split("\\.");
            if (arrOne.length > 2) return false;
            if (arrOne.length == 2) {
                String oneTwo = arrOne[1];
                for (int i = 0; i < oneTwo.length(); i++) {
                    char c = oneTwo.charAt(i);
                    if (!Character.isDigit(c)) return false;
                }
            }
            if (arrOne.length < 1) return false;
            String oneOne = arrOne[0];
            if (oneOne.length() == 1 && !Character.isDigit(oneOne.charAt(0)) && arrOne.length == 1) return false;
            for (int i = 1; i < oneOne.length(); i++) {
                char c = oneOne.charAt(i);
                if (!Character.isDigit(c)) return false;
            }
        }

        return true;
    }
}

