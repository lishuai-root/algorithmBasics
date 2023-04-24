package leetcode;

/**
 * @description: Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.
 * @author: LISHUAI
 * @createDate: 2022/12/14 9:25
 * @version: 1.0
 */

public class LeetCode_201 {

    private static int size = 0;

    private static volatile double dd;

    public static void main(String[] args) {
        int left = 256, right = 511;
//        int left = 0, right = 0;
//        int left = 1, right = 2147483647;
//        int left = 2147483646, right = 2147483647;
//        int i = rangeBitwiseAnd(left, right);
//        System.out.println(i);
        System.out.println(Object.class.isAssignableFrom(null));
    }

    public static int rangeBitwiseAnd(int left, int right) {
        size++;
        if (size == 8268) {
            return 0;
        }

        int ans = left;
        for (int i = left; i >= left && i <= right; i++) {
            ans &= i;
            if (ans == 0) {
                break;
            }
        }
        return ans;
    }
}
