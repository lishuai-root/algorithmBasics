package leetcode;

/**
 * @description: Given two strings s and t, return true if they are equal when both are typed into empty text editors.
 * '#' means a backspace character.
 * <p>
 * Note that after backspacing an empty text, the text will continue empty.
 * @author: LISHUAI
 * @createDate: 2022/5/1 13:32
 * @version: 1.0
 */

public class LeetCode_844 {

    public static void main(String[] args) {
        String s = "a#c", t = "b";
//        String s = "ab##", t = "c#d#";
//        String s = "ab#c", t = "ad#c";
        boolean b = backspaceCompare(s, t);
        System.out.println(b);
    }

    public static boolean backspaceCompare(String s, String t) {
        char[] chars = new char[s.length()], chart = new char[t.length()];
        int indexS = 0, indexT = 0, start = 0;

        while (start < s.length() && start < t.length()) {
            if (s.charAt(start) == '#') {
                indexS = Math.max(0, --indexS);
            } else {
                chars[indexS++] = s.charAt(start);
            }

            if (t.charAt(start) == '#') {
                indexT = Math.max(0, --indexT);
            } else {
                chart[indexT++] = t.charAt(start);
            }
            ++start;
        }

        while (start < s.length()) {
            if (s.charAt(start) == '#') {
                indexS = Math.max(0, --indexS);
            } else {
                chars[indexS++] = s.charAt(start);
            }
            ++start;
        }

        while (start < t.length()) {
            if (t.charAt(start) == '#') {
                indexT = Math.max(0, --indexT);
            } else {
                chart[indexT++] = t.charAt(start);
            }
            ++start;
        }

        if (indexS != indexT) {
            return false;
        }

        for (int i = 0; i < indexS; i++) {
            if (chars[i] != chart[i]) {
                return false;
            }
        }
        return true;
    }


    public static boolean backspaceCompare_002(String s, String t) {
        char[] chars = s.toCharArray(), chart = t.toCharArray();
        int indexS = 0, indexT = 0, start = 0;

        while (start < chars.length && start < t.length()) {
            if (chars[start] == '#') {
                indexS = Math.max(0, --indexS);
            } else {
                chars[indexS++] = chars[start];
            }

            if (chart[start] == '#') {
                indexT = Math.max(0, --indexT);
            } else {
                chart[indexT++] = chart[start];
            }
            ++start;
        }

        while (start < chars.length) {
            if (chars[start] == '#') {
                indexS = Math.max(0, --indexS);
            } else {
                chars[indexS++] = chars[start];
            }
            ++start;
        }
        while (start < chart.length) {
            if (chart[start] == '#') {
                indexT = Math.max(0, --indexT);
            } else {
                chart[indexT++] = chart[start];
            }
            ++start;
        }

        if (indexS != indexT) {
            return false;
        }

        for (int i = 0; i < indexS; i++) {
            if (chars[i] != chart[i]) {
                return false;
            }
        }
        return true;
    }
}
