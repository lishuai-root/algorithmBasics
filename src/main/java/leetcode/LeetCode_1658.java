package leetcode;

/**
 * @description: You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.
 * <p>
 * Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.
 * @author: LISHUAI
 * @createDate: 2022/6/11 16:05
 * @version: 1.0
 */

public class LeetCode_1658 {

    public static void main(String[] args) {
        int[] nums = {1241, 8769, 9151, 3211, 2314, 8007, 3713, 5835, 2176, 8227, 5251, 9229, 904, 1899, 5513, 7878, 8663, 3804, 2685, 3501, 1204, 9742, 2578, 8849, 1120, 4687, 5902, 9929, 6769, 8171, 5150, 1343, 9619, 3973, 3273, 6427, 47, 8701, 2741, 7402, 1412, 2223, 8152, 805, 6726, 9128, 2794, 7137, 6725, 4279, 7200, 5582, 9583, 7443, 6573, 7221, 1423, 4859, 2608, 3772, 7437, 2581, 975, 3893, 9172, 3, 3113, 2978, 9300, 6029, 4958, 229, 4630, 653, 1421, 5512, 5392, 7287, 8643, 4495, 2640, 8047, 7268, 3878, 6010, 8070, 7560, 8931, 76, 6502, 5952, 4871, 5986, 4935, 3015, 8263, 7497, 8153, 384, 1136};
        int x = 894887480;
//        int[] nums = {1, 1, 4, 2, 3};
//        int x = 5;
//        int[] nums = {5, 6, 7, 8, 9};
//        int x = 4;
//        int[] nums = {3, 2, 20, 1, 1, 3};
//        int x = 10;
        int i = minOperations_02(nums, x);
        System.out.println(i);
    }

    public static int minOperations(int[] nums, int x) {
        return minOperationsProcess(nums, x, 0, nums.length - 1);
    }

    private static int minOperationsProcess(int[] nums, int x, int left, int right) {
        if (x == 0) {
            return 0;
        }
        if (left > right) {
            return -1;
        }
        int ans = -1;
        int p1 = minOperationsProcess(nums, x - nums[left], left + 1, right);
        if (p1 != -1) {
            ans = p1 + 1;
        }

        int p2 = minOperationsProcess(nums, x - nums[right], left, right - 1);
        if (p2 != -1) {
            if (ans == -1) {
                ans = p2 + 1;
            } else {
                ans = Math.min(ans, p2 + 1);
            }
        }
        return ans;
    }

    public static int minOperations_02(int[] nums, int x) {
        int left = 0, right = nums.length, sum = 0, ans = Integer.MAX_VALUE;
        while (left < nums.length && sum < x) {
            sum += nums[left++];
        }
        if (sum == x) {
            ans = left;
        }
        while (--right >= left) {
            sum += nums[right];
            while (left > 0 && sum - nums[left - 1] >= x) {
                sum -= nums[--left];
            }
            if (sum == x) {
                ans = Math.min(ans, nums.length - right + left);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * other people method
     *
     * @param nums
     * @param x
     * @return
     */
    public static int minOperations_03(int[] nums, int x) {
        int n = nums.length, total = 0;
        for (int i : nums)
            total += i;

        total -= x;
        if (total == 0)
            return n;

        int size = -1;
        int s = 0, e = 0, curr = 0;

        while (e < n) {
            curr += nums[e++];
            while (s < n && curr > total)
                curr -= nums[s++];

            if (curr == total)
                size = Math.max(size, (e - s));

        }
        return size == -1 ? -1 : n - size;
    }
}
