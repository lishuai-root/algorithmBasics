package leetcode;

import java.util.Arrays;

/**
 * @description: Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.
 * <p>
 * Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.
 * @author: LISHUAI
 * @createDate: 2022/12/6 0:57
 * @version: 1.0
 */

public class LeetCode_556 {

    public static void main(String[] args) {
//        int n = 12;
        int n = 2147483486;
//        int n = 21;
//        int n = 11;
        int i = nextGreaterElement(n);
        System.out.println(i);
    }

    public static int nextGreaterElement(int n) {
        char[] chars = (n + "").toCharArray();

        for (int i = chars.length - 1; i >= 0; i--) {
            int k = i;
            char c = Character.MAX_VALUE;
            for (int j = chars.length - 1; j > i; j--) {
                if (chars[j] > chars[i] && chars[j] < c) {
                    c = chars[j];
                    k = j;
                }
            }
            if (k != i) {
                c = chars[i];
                chars[i] = chars[k];
                chars[k] = c;
                Arrays.sort(chars, i + 1, chars.length);
                long l = Long.parseLong(String.valueOf(chars));
                System.out.println(l);
                return l <= Integer.MAX_VALUE ? (int) l : -1;
            }
        }
        return -1;
    }
}
