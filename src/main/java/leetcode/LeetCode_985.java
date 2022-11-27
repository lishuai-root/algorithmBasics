package leetcode;

/**
 * @description: You are given an integer array nums and an array queries where queries[i] = [vali, indexi].
 * <p>
 * For each query i, first, apply nums[indexi] = nums[indexi] + vali, then print the sum of the even values of nums.
 * <p>
 * Return an integer array answer where answer[i] is the answer to the ith query.
 * @author: LISHUAI
 * @createDate: 2022/9/21 22:40
 * @version: 1.0
 */

public class LeetCode_985 {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4};
//        int[][] queries = {{1, 0}, {-3, 1}, {-4, 0}, {2, 3}};
        int[] nums = {1};
        int[][] queries = {{4, 0}};
        int[] ints = sumEvenAfterQueries(nums, queries);
        for (int i : ints) {
            System.out.println(i);
        }
    }

    public static int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int ans = 0, len = queries.length;
        for (int i : nums) {
            if ((i & 1) == 0) {
                ans += i;
            }
        }
        int[] ints = new int[len];
        for (int i = 0; i < queries.length; i++) {
            int[] cur = queries[i];
            if ((nums[cur[1]] & 1) == 0) {
                ans -= nums[cur[1]];
            }
            nums[cur[1]] += cur[0];
            if ((nums[cur[1]] & 1) == 0) {
                ans += nums[cur[1]];
            }
            ints[i] = ans;
        }
        return ints;
    }
}
