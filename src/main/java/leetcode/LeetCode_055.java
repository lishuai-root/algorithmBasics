package leetcode;

/**
 * @description: You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 * <p>
 * Return true if you can reach the last index, or false otherwise.
 * @author: LISHUAI
 * @createDate: 2021/11/18 21:48
 * @version: 1.0
 */

public class LeetCode_055 {

    private static boolean bs = false;

    public static void main(String[] args) {

        int[] arr = new int[]{2, 5, 0, 0};

        boolean b = canJump(arr);

        System.out.println(b);
    }

    public static boolean canJump(int[] nums) {

        int max = 0;

        for (int i = 0; i < nums.length && i <= max; i++) {

            max = Math.max(max, i + nums[i]);

            if (max >= nums.length - 1) {

                return true;
            }
        }

        return false;
    }

    public static void process(int[] nums, int index) {

        if (index >= nums.length - 1) {

            bs = true;
        }

        for (int i = 1; i <= nums[index] && !bs; i++) {


            if (nums[index + i] != 0 || index + i >= nums.length - 1) {

                process(nums, index + i);

            }
        }
    }

    public static boolean jumpAgain(int[] nums) {

        int nowMax = 0, cur = 0, next = 0, way = 0;

        int len = nums.length;

        while (cur < len - 1) {

            if (cur + nums[cur] >= len - 1) {

                return true;
            }

            for (int i = cur; i <= nums[cur] + cur; i++) {

                if (nowMax < i + nums[i]) {

                    nowMax = i + nums[i];

                    next = i;
                }
            }

            if (next == cur) {

                return false;
            }

            cur = next;

            way++;
        }

        return true;
    }

    public boolean canJump_02(int[] nums) {

        process(nums, 0);

        return bs;
    }
}
