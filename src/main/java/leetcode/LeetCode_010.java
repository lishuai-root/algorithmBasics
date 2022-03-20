package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: https://leetcode.com/problems/regular-expression-matching/
 * <p>
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 * @author: LISHUAI
 * @createDate: 2021/11/9 22:08
 * @version: 1.0
 */

public class LeetCode_010 {

    public static void main(String[] args) {

        String s = "aa", p = "a";

        boolean match = isMatch(s, p);
        boolean match2 = isMatch_02(s, p);

        System.out.println(match);
        System.out.println(match2);

    }

    public static boolean isMatch_02(String s, String p) {
        int len = p.length(), left = 0, right = 0, index = 0;
        boolean ans = true, isc = false;

        while (index < s.length() && right < len && ans) {
            left = right;
            while (right < len) {
                if (p.charAt(right) == '.' || p.charAt(right) == '*') {
                    break;
                }
                right++;
            }
            isc = false;
            if (p.charAt(left) == '.') {
                right++;
                index++;
                continue;
            }

            if (p.charAt(left) == '*') {
                right++;
                isc = true;
                continue;
            }
            if (isc) {
                int i = s.substring(index).indexOf(p.substring(left, right));
                if (i != -1) {
                    ans = true;
                } else {
                    ans = false;
                }
                index += i + right - left;
            } else {
                ans = s.substring(index).startsWith(p.substring(left, right));
                index += right - left;
            }
        }

        return (ans && index >= s.length()) || isc;
    }

    public static boolean isMatch(String s, String p) {

        boolean result = false;

        char sc = ' ', pc = ' ', beforePC = ' ';

        int pLen = p.length(), sLen = s.length();

        int i = 0, j = 0;

        System.out.println("s : " + s);
        System.out.println("p : " + p);

        for (; i < sLen && j < pLen; i++, j++) {

            sc = s.charAt(i);

            pc = p.charAt(j);

            if (pc == '.' || pc == sc) {

                continue;
            } else if (pc == '*') {

                if (p.charAt(j - 1) == '.') {

                    if (j + 1 >= pLen) {

                        return true;
                    } else {

                        beforePC = p.charAt(j + 1);

                        while (sc != beforePC && ++i < sLen) {

                            sc = s.charAt(i);
                        }

                        i++;
                    }

                } else {

//                    beforePC = p.charAt(j - 1);
//
//                    while (sc == beforePC && ++i < sLen) {
//
//                        sc = s.charAt(i);
//                    }
//
//                    while (++j < pLen && beforePC == p.charAt(j)) ;
//
//                    j--;
//
//                    i--;

                    beforePC = p.charAt(j - 1);

                    while (sc == beforePC && (i + 1) < sLen) {

                        System.out.println("sc : " + sc);
                        System.out.println("beforePC : " + beforePC);


                        result = isMatch(s.substring(i - 1, sLen), p.substring(j + 1, pLen));


                        if (result) {

                            return result;
                        }

                        sc = s.charAt(++i);
                    }

//                    j--;

                    if (i + 1 != sLen)
                        i--;
                }

            } else {

                if ((j + 1 >= pLen) || (p.charAt(j + 1) != '*')) {


                    return false;
                }


                i--;
            }

        }

        System.out.println("i : " + i);
        System.out.println("j : " + j);
        System.out.println("s : " + s);
        System.out.println("p : " + p);
        return i >= sLen && j >= pLen;
    }

    private static List process(String s, int sLeft, int sRight, String p, int pLeft, int pRight) {

        ArrayList<Integer> list = null;

        if (sLeft > sRight || pLeft > pRight) {

            return list;
        }

        list = new ArrayList<>();

        String ps = p.substring(pLeft, pRight);

        return list;
    }
}




