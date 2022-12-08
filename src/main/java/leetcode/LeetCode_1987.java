package leetcode;

/**
 * @description: You are given a binary string binary. A subsequence of binary is considered good if it is not empty and has no leading zeros (with the exception of "0").
 * <p>
 * Find the number of unique good subsequences of binary.
 * <p>
 * For example, if binary = "001", then all the good subsequences are ["0", "0", "1"], so the unique good subsequences are "0" and "1". Note that subsequences "00", "01", and "001" are not good because they have leading zeros.
 * Return the number of unique good subsequences of binary. Since the answer may be very large, return it modulo 109 + 7.
 * <p>
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 * @author: LISHUAI
 * @createDate: 2022/12/8 6:49
 * @version: 1.0
 */

public class LeetCode_1987 {

    public static void main(String[] args) {
        String binary = "001";
        int i = numberOfUniqueGoodSubsequences(binary);
        System.out.println(i);
    }

    public static int numberOfUniqueGoodSubsequences(String binary) {
        int mod = (int) 1e9 + 7, ends0 = 0, ends1 = 0, has0 = 0;
        for (int i = 0; i < binary.length(); ++i) {
            if (binary.charAt(i) == '1') {
                ends1 = (ends0 + ends1 + 1) % mod;
            } else {
                ends0 = (ends0 + ends1) % mod;
                has0 = 1;
            }
        }
        return (ends0 + ends1 + has0) % mod;
    }
}
