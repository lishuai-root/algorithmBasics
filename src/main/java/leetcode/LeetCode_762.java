package leetcode;

/**
 * @description: Given two integers left and right, return the count of numbers in the inclusive range [left, right] having a prime number of set bits in their binary representation.
 * <p>
 * Recall that the number of set bits an integer has is the number of 1's present when written in binary.
 * <p>
 * For example, 21 written in binary is 10101, which has 3 set bits.
 * @author: LISHUAI
 * @createDate: 2022/5/26 23:46
 * @version: 1.0
 */

public class LeetCode_762 {

    public static int countPrimeSetBits(int left, int right) {

        int[] tmp = new int[33];
        int ans = 0;
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = 1;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    tmp[i] = 0;
                    break;
                }
            }
        }

        while (left <= right) {
            int i = hammingWeight(left);
            ans += tmp[i];
            left++;
        }
        return ans;
    }

    private static int hammingWeight(int n) {

        int ans = 0;

        while (n != 0) {
            ans++;
            n ^= (-n & n);
        }
        return ans;
    }
}
