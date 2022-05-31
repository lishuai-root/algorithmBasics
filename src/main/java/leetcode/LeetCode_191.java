package leetcode;

/**
 * @description: Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 * <p>
 * Note:
 * <p>
 * Note that in some languages, such as Java, there is no unsigned integer type. In this case, the input will be given as a signed integer type.
 * It should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3, the input represents the signed integer. -3.
 * @author: LISHUAI
 * @createDate: 2022/5/26 21:12
 * @version: 1.0
 */

public class LeetCode_191 {
    

    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {

        int ans = 0;

        while (n != 0) {
            ans++;
            n ^= (-n & n);
        }
        return ans;
    }

}
