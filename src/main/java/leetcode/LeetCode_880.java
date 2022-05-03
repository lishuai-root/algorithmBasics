package leetcode;

/**
 * @description: You are given an encoded string s. To decode the string to a tape,
 * the encoded string is read one character at a time and the following steps are taken:
 * <p>
 * If the character read is a letter, that letter is written onto the tape.
 * If the character read is a digit d, the entire current tape is repeatedly written d - 1 more times in total.
 * Given an integer k, return the kth letter (1-indexed) in the decoded string.
 * <p>
 * It is guaranteed that k is less than or equal to the length of the decoded string.
 * @author: LISHUAI
 * @createDate: 2022/4/14 21:47
 * @version: 1.0
 */

public class LeetCode_880 {

    public static void main(String[] args) {
//        String s = "leet2code3";
//        int k = 10;
//        String s = "y959q969u3hb22odq595";
//        int k = 222280369;
//        String s = "a2b3c4d5e6f7g8h9";
//        int k = 9;
//        String s = "ha22";
//        int k = 5;
        String s = "ajx37nyx97niysdrzice4petvcvmcgqn282zicpbx6okybw93vhk782unctdbgmcjmbqn25rorktmu5ig2qn2y4xagtru2nehmsp";
        int k = 976159153;
//        976159153
//        2147483647
        System.out.println(Integer.MAX_VALUE);
//        String s = "a2345678999999999999999";
//        int k = 1;
//        String s = "a23";
//        int k = 6;
        String s1 = decodeAtIndex_03(s, k);
        System.out.println(s1);

//        String s2 = decodeAtIndex_02(s, k);
//        System.out.println(s2);
        fn_001(s);
    }

    private static void fn_001(String s) {
        char[] chars = s.toCharArray();
        long len = 0;
        for (char c : chars) {

            if (c >= 'a' && c <= 'z') {
                ++len;

            } else {
                len *= (c - '0');
            }
            if (c == 'a') {
                System.out.println("a : " + len);
            }
        }
    }

    public static String decodeAtIndex_02(String s, int k) {

        int len = 0, index = -1, charIndex = 0;
        int[][] stack = new int[s.length()][2];
        char[] chars = new char[s.length()];
        StringBuilder sbr = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sbr.append(c);
                if (sbr.length() == k) {
                    return String.valueOf(sbr.charAt(sbr.length() - 1));
                }
            } else {
                if (sbr.length() * (c - '0') >= k) {
                    break;
                }
                String line = sbr.toString();
                sbr.append(line.repeat(Math.max(0, c - '0' - 1)));
            }

        }

        int i = (k % sbr.length()) - 1 >= 0 ? (k % sbr.length()) - 1 : sbr.length() - 1;
        return String.valueOf(sbr.charAt(i));
    }

    public static String decodeAtIndex(String s, int k) {

        char[] chars = s.toCharArray();
        char cm = ' ';
        while (k > 1) {
            int len = 0, pre = 0, curLen = 0;
            System.out.println(k);
            for (char c : chars) {
                cm = c;
                if (c >= 'a' && c <= 'z') {
                    ++len;
                    if (len == k) {
                        return String.valueOf(c);
                    }
                } else {
                    if (len * (c - '0') >= k) {
                        k = k % len == 0 ? len : k % len;
                        break;
                    }
                    len *= (c - '0');
                }

            }
//            System.out.println(cm);
        }
        System.out.println(k);
        return chars[0] + "";
    }

    public static String decodeAtIndex_03(String s, int k) {

        long size = 0;

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (Character.isDigit(a)) {
                size *= a - '0';
            } else {
                size++;
            }
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            char a = s.charAt(i);
            k %= size;
            if (k == 0 && !Character.isDigit(a)) {
                return "" + a;
            }

            if (Character.isDigit(a)) {
                size /= a - '0';
            } else {
                size--;
            }


        }

        return "";


    }
}
