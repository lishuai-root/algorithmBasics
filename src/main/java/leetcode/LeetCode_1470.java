package leetcode;

/**
 * @description: Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
 * <p>
 * Return the array in the form [x1,y1,x2,y2,...,xn,yn].
 * @author: LISHUAI
 * @createDate: 2023/2/6 21:51
 * @version: 1.0
 */

public class LeetCode_1470 {

    public static int[] shuffle(int[] nums, int n) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < n; i++) {
            ans[i << 1] = nums[i];
            ans[(i << 1) + 1] = nums[n + i];
        }
        return ans;
    }
}
