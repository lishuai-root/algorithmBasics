package leetcode;

/**
 * @description: You are given an integer array nums and two integers limit and goal.
 * The array nums has an interesting property that abs(nums[i]) <= limit.
 * <p>
 * Return the minimum number of elements you need to add to make the sum of the array equal to goal.
 * The array must maintain its property that abs(nums[i]) <= limit.
 * <p>
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 * @author: LISHUAI
 * @createDate: 2022/3/26 18:40
 * @version: 1.0
 */

public class LeetCode_1785 {
    public static void main(String[] args) {
//        int[] nums = {1, -1, 1};
//        int limit = 3, goal = -4;
        int[] nums = {1, -10, 9, 1};
        int limit = 100, goal = 0;
        int i = minElements_02(nums, limit, goal);
        System.out.println(i);
    }

    public static int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        int ans = 0;
        for (int i : nums) {
            sum += i;
        }
        sum = Math.abs(sum - goal);
        ans = (int) ((sum + limit - 1) / limit);
        return ans;
    }

    public static int minElements_02(int[] nums, int limit, int goal) {
        long g = goal;
        for (int i : nums) {
            g -= i;
        }
        g = Math.abs(g);
        return (int) ((g + limit - 1) / limit);
    }
}
