package leetcode;

/**
 * @description: You are given an integer array nums and an integer k.
 * <p>
 * Find the longest subsequence of nums that meets the following requirements:
 * <p>
 * The subsequence is strictly increasing and
 * The difference between adjacent elements in the subsequence is at most k.
 * Return the length of the longest subsequence that meets the requirements.
 * <p>
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 * @author: LISHUAI
 * @createDate: 2022/9/13 22:25
 * @version: 1.0
 */

public class LeetCode_2407 {

    private static Integer[][] cache;

    public static void main(String[] args) {
//        int[] nums = {7, 4, 5, 1, 8, 12, 4, 7};
//        int k = 5;
//        int[] nums = {4, 2, 1, 4, 3, 4, 5, 8, 15};
//        int k = 3;
//        int[] nums = {1, 5};
//        int k = 1;
        int[] nums = makeArray();
        int k = 1;
//        int i = lengthOfLIS(nums, k);
//        System.out.println(i);
        int i1 = lengthOfLIS_dp(nums, k);
        System.out.println(i1);
        System.out.println(lengthOfLIS_02(nums, k));
    }

    private static int[] makeArray() {
        int[] ints = new int[100000];
        for (int i = 0; i < 100000; i++) {
            ints[i] = i + 1;
        }
        return ints;
    }

    public static int lengthOfLIS(int[] nums, int k) {
        cache = new Integer[nums.length][nums.length];
        return lengthOfLISProcess(nums, k, 0, -1);
    }

    private static int lengthOfLISProcess(int[] nums, int k, int index, int pre) {
        if (index >= nums.length) {
            return 0;
        }
        if (pre != -1 && cache[index][pre] != null) {
            return cache[index][pre];
        }
        int p1 = lengthOfLISProcess(nums, k, index + 1, pre);
        int p2 = 0;
        if (pre == -1 || (nums[index] > nums[pre] && nums[index] - nums[pre] <= k)) {
            p2 = lengthOfLISProcess(nums, k, index + 1, index) + 1;
        }
        if (pre != -1) {
            cache[index][pre] = Math.max(p1, p2);
        }
        return Math.max(p1, p2);
    }


    public static int lengthOfLIS_dp(int[] nums, int k) {
        int len = nums.length;
        int[] dp = new int[len + 1];

        for (int i = len; i > 0; i--) {
            for (int j = 0; j <= i; j++) {
                int p1 = dp[j];
                int p2 = 0;
                if (j == 0 || (nums[i - 1] > nums[j - 1] && nums[i - 1] - nums[j - 1] <= k)) {
                    p2 = dp[i] + 1;
                }
                dp[j] = Math.max(p1, p2);
            }
        }

//        for (int i : dp) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
        return dp[0];
    }

    public static int lengthOfLIS_02(int[] nums, int k) {
        int len = nums.length, max = 0;
        int[] dp = new int[len];
        dp[0] = 1;
        int allMax = 1;

        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[max] && nums[i] - nums[max] <= k) {
                dp[i] = dp[max] + 1;
                max = i;
                continue;
            }
            int m = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && nums[i] - nums[j] <= k) {
                    m = Math.max(dp[j], m);
                }
            }
            dp[i] = m + 1;
            allMax = Math.max(allMax, dp[i]);
            if (dp[i] > dp[max]) {
                max = i;
            }
        }
//        System.out.println(max);
        return dp[max];
    }

    public int lengthOfLIS_other(int[] nums, int k) {
        // find the boundaries of the segment treee
        var minVal = Integer.MAX_VALUE;
        var maxVal = Integer.MIN_VALUE;
        for (int n : nums) {
            minVal = Math.min(minVal, n);
            maxVal = Math.max(maxVal, n);
        }

        // build the segment tree
        var dp = new SegmentTree(minVal, maxVal);
        for (int num : nums) {
            // find longest chain in range
            var preMax = 1 + dp.rangeMaxQuery(num - k, num - 1);
            // store the results
            dp.update(num, preMax);
        }
        return dp.val;
    }

    static final class SegmentTree {
        final int lo;
        final int hi;
        SegmentTree left;
        SegmentTree right;
        int val;

        SegmentTree(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
            if (lo != hi) {
                var mid = lo + (hi - lo) / 2;
                this.left = new SegmentTree(lo, mid);
                this.right = new SegmentTree(mid + 1, hi);
            }
        }

        void update(int index, int val) {
            if (index < this.lo || this.hi < index) // out of range
                return;
            if (lo == hi) { // found node
                this.val = val;
                return;
            }
            this.left.update(index, val);
            this.right.update(index, val);
            this.val = Math.max(this.left.val, this.right.val);
        }

        int rangeMaxQuery(int lo, int hi) {
            if (hi < this.lo || this.hi < lo) // not overlap
                return 0;
            if (lo <= this.lo && this.hi <= hi) // in range
                return this.val;
            return Math.max(this.left.rangeMaxQuery(lo, hi), this.right.rangeMaxQuery(lo, hi));
        }

        @Override
        public String toString() {
            return "[" + lo + "," + hi + "]->" + val;
        }
    }
}
