package leetcode;

/**
 * @description: Given an integer n, return true if it is a power of two. Otherwise, return false.
 * <p>
 * An integer n is a power of two, if there exists an integer x such that n == 2x.
 * @author: LiShuai
 * @createDate: 2023/6/12 22:19
 * @version: 1.0
 */

public class LeetCode_231 {

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n ^ (n & -n)) == 0;
    }
}
