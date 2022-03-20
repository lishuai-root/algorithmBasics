package leetcode;

/**
 * @description: Given two binary strings a and b, return their sum as a binary string.
 * @author: LISHUAI
 * @createDate: 2022/1/10 20:34
 * @version: 1.0
 */

public class LeetCode_067 {

    public static void main(String[] args) {

        String a = "1010", b = "1011";

        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {

        StringBuffer sb = new StringBuffer();

        int len = Math.min(a.length(), b.length());

        int aLen = a.length(), bLen = b.length();

        int ac, bc, ans = 0;

        int i = 1;

        for (; i <= len; i++) {

            ac = a.charAt(aLen - i) == '1' ? 1 : 0;

            bc = b.charAt(bLen - i) == '1' ? 1 : 0;

            ans += (ac + bc);

            sb.append(ans & 1);

            ans >>>= 1;
        }

        while (a.length() >= i) {

            ac = a.charAt(aLen - i) == '1' ? 1 : 0;

            ans += ac;

            sb.append(ans & 1);

            ans >>>= 1;

            i++;
        }

        while (b.length() >= i) {

            bc = b.charAt(bLen - i) == '1' ? 1 : 0;

            ans += bc;

            sb.append(ans & 1);

            ans >>>= 1;

            i++;
        }

        if (ans != 0) {

            sb.append(ans);
        }
        return sb.reverse().toString();
    }
}
