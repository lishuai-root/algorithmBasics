package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/6 12:59
 * @version: 1.0
 * <p>
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 */

public class LeetCode_239 {

    public static void main(String[] args) {
        int[] nums = {1};

        int[] ints = maxSlidingWindow_002(nums, 1);

        for (int i = 0; i < ints.length; i++) {

            System.out.print(ints[i] + "   ");
        }
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

        int len = nums.length, max = Integer.MIN_VALUE;

        int[] result = new int[len - k + 1];

        for (int i = 0; i < k; i++) {

            max = Math.max(max, nums[i]);
        }

        result[0] = max;

        for (int i = k; i < len; i++) {

            max = nums[i];

            for (int j = i - 1; j > i - k; j--) {

                max = Math.max(max, nums[j]);
            }

            result[i - k + 1] = max;
        }

        return result;
    }

    public static int[] maxSlidingWindow_002(int[] nums, int k) {

        int len = nums.length, max = Integer.MIN_VALUE, tail = 0, index = -1, size = 0;

        int[] result = new int[len - k + 1];

        int[] stack = new int[k];

        for (int i = 0; i < k; i++) {

            while (size != 0 && nums[i] >= nums[stack[index]]) {

                index--;

                index = index == -1 ? k - 1 : index;

                size--;
            }

            stack[(index = ++index % k)] = i;

            size++;
        }

        result[0] = nums[stack[tail]];

        for (int i = k; i < len; i++) {

            if (stack[tail] == (i - k)) {

                tail = ++tail % k;

                size--;
            }

            while (size != 0 && nums[i] >= nums[stack[index]]) {

                index--;

                size--;

                index = index == -1 ? k - 1 : index;
            }

            stack[(index = ++index % k)] = i;

            size++;

            result[i - k + 1] = nums[stack[tail]];
        }

        return result;
    }
}
