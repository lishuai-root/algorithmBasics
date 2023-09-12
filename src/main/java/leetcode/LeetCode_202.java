package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: Write an algorithm to determine if a number n is happy.
 * <p>
 * A happy number is a number defined by the following process:
 * <p>
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 * @author: LiShuai
 * @createDate: 2023/9/4 21:39
 * @version: 1.0
 */

public class LeetCode_202 {
    private static final Set<Integer> set;

    static {
        set = new HashSet<Integer>();
        set.add(1);
        set.add(7);
    }

    public static boolean isHappy(int n) {
        while (n > 9) {
            int p = 0;
            int t = n;
            while (t > 0) {
                int q = t % 10;
                if (q != 0) {
                    p += (q * q);
                }
                t /= 10;
            }
            n = p;
        }
        return n == 1 || n == 7;
    }
}
