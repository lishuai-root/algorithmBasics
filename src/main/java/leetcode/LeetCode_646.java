package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
 * <p>
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
 * <p>
 * Return the length longest chain which can be formed.
 * <p>
 * You do not need to use up all the given intervals. You can select pairs in any order.
 * @author: LISHUAI
 * @createDate: 2022/3/22 21:34
 * @version: 1.0
 */

public class LeetCode_646 {

    public static void main(String[] args) {
        int[][] pairs = {{1, 2}, {7, 8}, {4, 5}};
        int i = findLongestChain(pairs);
        System.out.println(i);
    }

    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> {
            int c = a[1] - b[1];
            if (c == 0) {
                c = a[0] - b[0];
            }
            return c;
        });

        int ans = 0, cur = Integer.MIN_VALUE;

        for (int[] pair : pairs) {
            if (pair[0] > cur) {
                cur = pair[1];
                ans++;
            }
        }

        return ans;
    }
}
