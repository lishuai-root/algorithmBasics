package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.
 * <p>
 * Return the maximum score you can get by erasing exactly one subarray.
 * <p>
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
 * @author: LISHUAI
 * @createDate: 2022/6/12 12:58
 * @version: 1.0
 */

public class LeetCode_1695 {

    public static void main(String[] args) {
//        int[] nums = {4, 2, 4, 5, 6};
        int[] nums = {5, 2, 1, 2, 5, 2, 1, 2, 5};
        int i = maximumUniqueSubarray(nums);
        System.out.println(i);
    }

    public static int maximumUniqueSubarray(int[] nums) {
        int left = 0, right = 0, ans = 0, curSum = 0;
        int[] sum = new int[10001];

        while (right < nums.length) {
            sum[nums[right]]++;
            curSum += nums[right];
            while (left < right && sum[nums[right]] > 1) {
                curSum -= nums[left];
                sum[nums[left++]]--;
            }
            ans = Math.max(ans, curSum);
            right++;
        }
        return ans;
    }

    public static int maximumUniqueSubarray_map(int[] nums) {
        int left = 0, right = 0, ans = 0, curSum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (right < nums.length) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            curSum += nums[right];
            while (left < right && map.get(nums[right]) > 1) {
                curSum -= nums[left];
                map.put(nums[left], map.get(nums[left++]) - 1);
            }
            ans = Math.max(ans, curSum);
            right++;
        }
        return ans;
    }
}
