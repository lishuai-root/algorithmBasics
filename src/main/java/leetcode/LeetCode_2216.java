package leetcode;

/**
 * @description: You are given a 0-indexed integer array nums. The array nums is beautiful if:
 * <p>
 * nums.length is even.
 * nums[i] != nums[i + 1] for all i % 2 == 0.
 * Note that an empty array is considered beautiful.
 * <p>
 * You can delete any number of elements from nums.
 * When you delete an element, all the elements to the right of the deleted element will be shifted one unit to the left to fill the gap created and all the elements to the left of the deleted element will remain unchanged.
 * <p>
 * Return the minimum number of elements to delete from nums to make it beautiful.
 * @author: LISHUAI
 * @createDate: 2022/6/28 21:33
 * @version: 1.0
 */

public class LeetCode_2216 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3, 3};
//        int[] nums = {1, 1, 2, 3, 5};
        int i = minDeletion(nums);
        System.out.println(i);
    }

    public static int minDeletion(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (((i - ans) & 1) == 0 && nums[i] == nums[i + 1]) {
                ans++;
            }
        }
        if (((nums.length - ans) & 1) != 0) {
            ans++;
        }
        return ans;
    }
}
