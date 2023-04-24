package leetcode;

/**
 * @description: You are given an integer array cookies, where cookies[i] denotes the number of cookies in the ith bag. You are also given an integer k that denotes the number of children to distribute all the bags of cookies to. All the cookies in the same bag must go to the same child and cannot be split up.
 * <p>
 * The unfairness of a distribution is defined as the maximum total cookies obtained by a single child in the distribution.
 * <p>
 * Return the minimum unfairness of all distributions.
 * @author: LISHUAI
 * @createDate: 2023/4/2 21:15
 * @version: 1.0
 */

public class LeetCode_2305 {

    public static int distributeCookies(int[] cookies, int k) {
        int[] child = new int[k];
        return distributeCookiesProcess(cookies, child, 0, 0);
    }

    private static int distributeCookiesProcess(int[] cookies, int[] child, int index, int sum) {
        if (index >= cookies.length) {
            return sum;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < child.length; i++) {
            child[i] += cookies[index];
            ans = Math.min(ans, distributeCookiesProcess(cookies, child, index + 1, Math.max(sum, child[i])));
            child[i] -= cookies[index];
        }
        return ans;
    }
}
