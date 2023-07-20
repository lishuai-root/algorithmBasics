package leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: You are installing a billboard and want it to have the largest height. The billboard will have two steel supports, one on each side. Each steel support must be an equal height.
 * <p>
 * You are given a collection of rods that can be welded together. For example, if you have rods of lengths 1, 2, and 3, you can weld them together to make a support of length 6.
 * <p>
 * Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.
 * @author: LiShuai
 * @createDate: 2023/6/24 21:03
 * @version: 1.0
 */

public class LeetCode_956 {

    public static void main(String[] args) {
//        int[] rods = {1, 2};
//        int[] rods = {815, 117, 854, 610, 382, 432, 805, 713, 905, 252, 790, 127, 835, 886, 675, 869, 463, 459, 45, 797};
        int[] rods = {96, 112, 101, 100, 104, 93, 106, 99, 114, 81, 94};
//        int[] rods = {3, 4, 3, 3, 2};
//        int[] rods = {56, 71, 53, 51, 56, 53, 61, 56, 56, 54, 64, 54, 52, 63, 700, 700, 700, 700, 700, 700};
//        int[] rods = {61, 45, 43, 54, 40, 53, 55, 47, 51, 59, 42};


        long start = System.currentTimeMillis();
        int i = tallestBillboard(rods);
        long end = System.currentTimeMillis();
        System.out.println("time : " + (end - start));
        System.out.println(i);
        start = System.currentTimeMillis();
        int i1 = tallestBillboard_02(rods);
        end = System.currentTimeMillis();
        System.out.println("time2 : " + (end - start));
        System.out.println(i1);
    }

    private static void test() {
        while (true) {
            int len = 20;
            int[] rods = new int[len];
            Random random = new Random();
            for (int i = 0; i < len; i++) {
                rods[i] = random.nextInt(1000);
            }
            long start = System.currentTimeMillis();
            int i = tallestBillboard(rods);
            long end = System.currentTimeMillis();
//            System.out.println("time : " + (end - start));
//            System.out.println(i);
            start = System.currentTimeMillis();
            int i1 = tallestBillboard_02(rods);
            end = System.currentTimeMillis();
//            System.out.println("time2 : " + (end - start));
//            System.out.println(i1);
            if (i != i1) {
                System.out.println(i + " - " + i1);
                System.out.println(Arrays.toString(rods));
                System.out.println("----------------------------------------------------------------");
                break;
            }
        }
    }

    public static int tallestBillboard(int[] rods) {
        int len = rods.length, sum = 0;
        for (int i : rods) {
            sum += i;
        }
        int dpLen = sum >> 1;
        int[][] dp = new int[dpLen + 1][dpLen + 1];
        for (int i = len - 1; i >= 0; i--) {
            sum -= rods[i];
            for (int j = 0; j <= Math.min(dpLen, sum); j++) {
                dp[j][j] = Math.max(dp[j][j], j);
                for (int k = 0; k <= Math.min(dpLen, sum - j); k++) {
                    int p = j + rods[i] > dpLen ? 0 : dp[j + rods[i]][k];
                    int q = k + rods[i] > dpLen ? 0 : dp[j][k + rods[i]];
                    dp[j][k] = Math.max(dp[j][k], Math.max(p, q));
                }
            }
        }
        return dp[0][0];
    }

    public static int tallestBillboard_02(int[] rods) {
        int len = rods.length, sum = 0;
        for (int i : rods) {
            sum += i;
        }
        int dpLen = sum >> 1;

        for (int i = dpLen; i > 0; i--) {
            int[] nums = Arrays.copyOf(rods, len);
            if (f(nums, i, false)) {
                return i;
            }
        }
        return 0;
    }

    private static boolean f(int[] rods, int target, boolean flag) {
        int len = rods.length;
        int[][] dp = new int[len][target + 1];
        return tallestBillboardProcess(rods, 0, target, 0, dp, flag);
    }

    public static boolean tallestBillboardProcess(int[] rods, int cur, int target, int index, int[][] dp, boolean flag) {
        if (target == cur) {
            return flag || f(rods, target, true);
        }

        if (index >= rods.length || target < cur) {
            return false;
        }

        if (dp[index][cur] != 0) {
            return dp[index][cur] == 1;
        }
        int c = rods[index];
        boolean ans = tallestBillboardProcess(rods, cur, target, index + 1, dp, flag);
        if (ans) {
            return true;
        }
        rods[index] = 0;
        ans = tallestBillboardProcess(rods, cur + c, target, index + 1, dp, flag);
        if (ans) {
            return true;
        }
        rods[index] = c;
        dp[index][cur] = 2;
        return false;
    }

    private static boolean tallestBillboard_dp(int[] rods, int target) {
        boolean[] dp = new boolean[target + 1];
        int len = rods.length;

        for (int i = len - 1; i >= 0; --i) {
            dp[target] = true;
            for (int j = 0; j <= target - rods[i]; j++) {
                dp[j] |= dp[j + rods[i]];
            }
        }
        return dp[0];
    }
}
