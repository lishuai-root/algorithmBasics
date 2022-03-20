package leetcode;

/**
 * @description: Given string num representing a non-negative integer num, and an integer k,
 * return the smallest possible integer after removing k digits from num.
 * <p>
 * num consists of only digits.
 * num does not have any leading zeros except for the zero itself.
 * @author: LISHUAI
 * @createDate: 2022/2/18 18:28
 * @version: 1.0
 */

public class LeetCode_402 {

    public static void main(String[] args) {
        String num = "112";
        int k = 1;
        String s = removeKdigits(num, k);
        System.out.println(s);
    }

    public static String removeKdigits(String num, int k) {

        if (num.length() == k) {
            return "0";
        }
        char[] chars = num.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < chars.length - 1 && k > 0; i++) {
            if (chars[i] > chars[i + 1]) {
                k--;
                continue;
            }
            sb.append(chars[i]);
        }
        sb.append(num.substring(i, num.length()));
        i = chars.length - 1;
        if (k > 0) {
            char[] array = sb.toString().toCharArray();
            for (int j = array.length - 1; j > 0; j--) {
                if (array[j] >= array[i - 1] && k > 0) {
                    k--;
                    continue;
                }
                chars[i--] = array[j];
            }
            sb.delete(0, sb.length());
            for (; i < chars.length; i++) {
                sb.append(chars[i]);
            }
        }
        i = 0;
        while (i < sb.length() && sb.charAt(i) == '0') {
            i++;
        }

        String s = sb.substring(i, sb.length());
        return s.length() == 0 ? "0" : s;
    }
}
