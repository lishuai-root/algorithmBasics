package leetcode;

import java.util.Arrays;

/**
 * @description: Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 * <p>
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * @author: LISHUAI
 * @createDate: 2022/4/5 14:54
 * @version: 1.0
 */

public class LeetCode_43 {

    public static void main(String[] args) {
//        String num1 = "2", num2 = "3";
//        String num1 = "123", num2 = "456";
//        String multiply = multiply(num1, num2);
//        System.out.println(multiply);
        fn_001(2, 3);
    }


    public static void fn_001(int n, int m) {
        int i1 = Integer.bitCount(5);
        System.out.println(i1);
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if ((1 << i & n) != 0) {
                result += m << i;
            }
        }
        System.out.println(result);
    }

    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        char[] ans = new char[(num1.length() + num2.length())];
        char[] chars1 = num1.toCharArray(), chars2 = num2.toCharArray();
        int index = ans.length, index2;
        int up = 0, n1, n2, tmp;
        Arrays.fill(ans, '0');

        for (int i = 0; i < num1.length(); i++) {
            index = ans.length - i;
            index2 = chars2.length;
            n1 = chars1[num1.length() - i - 1] - '0';

            while (--index2 >= 0) {
                tmp = up;
                n2 = chars2[index2] - '0';
                up = n1 * n2 + (ans[--index] - '0') + tmp;
                ans[index] = (char) ((up % 10) + '0');
                up = up / 10;
            }

            while (up != 0) {
                up += (ans[--index] - '0');
                ans[index] = (char) ((up % 10) + '0');
                up /= 10;
            }
        }

        return String.valueOf(ans, index, ans.length - index);
    }
}
