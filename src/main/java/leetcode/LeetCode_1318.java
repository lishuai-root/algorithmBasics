package leetcode;

/**
 * @description: Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
 * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
 * @author: LiShuai
 * @createDate: 2023/6/7 21:32
 * @version: 1.0
 */

public class LeetCode_1318 {

    public static int minFlips(int a, int b, int c) {
        int ans = 0, bo = 1;

        while (a != 0 || b != 0 || c != 0) {
            int ba = a & bo;
            int bb = b & bo;
            int bc = c & bo;
            if ((ba | bb) != bc) {
                if (bc != 0) {
                    ++ans;
                } else {
                    if (ba != 0) {
                        ++ans;
                    }
                    if (bb != 0) {
                        ++ans;
                    }
                }
            }
            a ^= ba;
            b ^= bb;
            c ^= bc;
            bo <<= 1;
        }
        return ans;
    }
}
