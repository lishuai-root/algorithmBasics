package leetcode;

/**
 * @description: There is a function signFunc(x) that returns:
 * <p>
 * 1 if x is positive.
 * -1 if x is negative.
 * 0 if x is equal to 0.
 * You are given an integer array nums. Let product be the product of all values in the array nums.
 * <p>
 * Return signFunc(product).
 * @author: LISHUAI
 * @createDate: 2023/5/6 14:46
 * @version: 1.0
 */

public class LeetCode_1822 {

    public static int arraySign(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            if (i == 0) {
                return 0;
            }
            ans ^= i;
        }
        return ans < 0 ? -1 : 1;
    }
}
