package leetcode;

/**
 * @description: Given an integer n, return true if it is a power of three. Otherwise, return false.
 * <p>
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 * @author: LISHUAI
 * @createDate: 2022/8/24 22:48
 * @version: 1.0
 */

public class LeetCode_326 {

    public static void main(String[] args) {
//        int n = 9;
        int n = 2147483647;
        boolean powerOfThree = isPowerOfThree(n);
        System.out.println(powerOfThree);
    }

    public static boolean isPowerOfThree(int n) {
        return n > 0 && (1162261467 % n) == 0;
    }
}
