package leetcode;

import java.util.Random;

/**
 * @description: You are given a 0-indexed integer array nums. In one step, remove all elements nums[i] where nums[i - 1] > nums[i] for all 0 < i < nums.length.
 * <p>
 * Return the number of steps performed until nums becomes a non-decreasing array.
 * @author: LISHUAI
 * @createDate: 2022/12/6 5:28
 * @version: 1.0
 */

public class LeetCode_2289 {

    public static void main(String[] args) {
//        int[] nums = {5, 3, 4, 4, 7, 3, 6, 11, 8, 5, 11};
//        int[] nums = {4, 5, 7, 7, 13};
        int[] nums = {7, 14, 4, 14, 13, 1, 2, 6, 13};
//        int[] nums = {5, 14, 15, 2, 11, 5, 13, 15};
//        int[] nums = makeArr(30, 100000);
//        int[] ints = Arrays.copyOf(nums, nums.length);
        int i = totalSteps(nums);
        System.out.println(i);
//        System.out.println(totalSteps_02(nums));
        System.out.println(totalSteps_03(nums));
//        for (int k : nums) {
//            System.out.print(k + " ");
//        }
//        System.out.println();

    }

    private static int[] makeArr(int size, int range) {
        int[] nums = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            nums[i] = rand.nextInt(range);
        }
        return nums;
    }

    public static int totalSteps_03(int[] nums) {
        int[] stack = new int[nums.length];
        int[] cache = new int[nums.length];
        int index = -1, ans = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            int size = 0;
            while (index != -1 && nums[i] > nums[stack[index]]) {
                size++;
                size = Math.max(size, cache[stack[index--]]);
            }
            cache[i] = size;
            ans = Math.max(ans, cache[i]);
            stack[++index] = i;
        }
        return ans;
    }

    public static int totalSteps(int[] nums) {
        int n = nums.length, res = 0, j = -1;
        int dp[] = new int[n], stack[] = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            while (j >= 0 && nums[i] > nums[stack[j]]) {
                dp[i] = Math.max(++dp[i], dp[stack[j--]]);
                res = Math.max(res, dp[i]);
            }
            stack[++j] = i;
        }
        return res;
    }

    public static int totalSteps_02(int[] nums) {
        int ans = 0, left = 0;

        while (true) {
            int size = 0, right = nums.length - 1;
            while (right > left) {
                if (nums[right] < nums[right - 1]) {
                    size++;
                } else {
                    nums[right + size] = nums[right];
                }
                right--;
            }
            if (size == 0) {
                break;
            }
            nums[left + size] = nums[left];
            left += size;
            ans++;
        }
        return ans;
    }
}
