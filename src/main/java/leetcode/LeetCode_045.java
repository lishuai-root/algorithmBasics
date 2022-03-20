package leetcode;

/**
 * @description: Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * You can assume that you can always reach the last index.
 * @author: LISHUAI
 * @createDate: 2021/11/16 22:54
 * @version: 1.0
 */


public class LeetCode_045 {

    public static void main(String[] args) {

        int[] arr = new int[]{0, 1};

        int i = jumpAgain(arr);

        System.out.println(i);
    }


    public static int jump(int[] nums) {

        return process(nums, 0, 0);
    }

    public static int process(int[] nums, int way, int index) {

        if (index == nums.length - 1) {

            return way;
        }

        if (index >= nums.length) {

            return nums.length;
        }

        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= nums[index]; i++) {

            int process = process(nums, way + 1, index + i);

            min = Math.min(min, process);

        }

        return min;
    }

    public static int jumpAgain(int[] nums) {

        int nowMax = 0, cur = 0, next = 0, way = 0;

        int len = nums.length;

        while (cur < len - 1) {

            if (cur + nums[cur] >= len - 1) {

                return ++way;
            }

            for (int i = cur; i <= nums[cur] + cur; i++) {

                if (nowMax < i + nums[i]) {

                    nowMax = i + nums[i];

                    next = i;
                }
            }

            if (next == cur) {

                return -1;
            }

            cur = next;

            way++;
        }

        return way;
    }

}
