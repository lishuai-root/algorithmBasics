package leetcode;

/**
 * @description: Given an integer n,
 * return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.
 * <p>
 * An integer y is a power of three if there exists an integer x such that y == 3x.
 * @author: LISHUAI
 * @createDate: 2022/3/26 17:29
 * @version: 1.0
 */

public class LeetCode_1780 {

    private static final int[] nums = new int[20];

    static {
        nums[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            nums[i] = 3 * nums[i - 1];
        }
    }

    public static void main(String[] args) {
        int n = 12;
        boolean b = checkPowersOfThree(n);
        System.out.println(b);
    }

    public static boolean checkPowersOfThree(int n) {

        for (int i = nums.length - 1; i >= 0; i--) {
            if (n >= nums[i]) {
                n -= nums[i];
            }
            if (n == 0) {
                return true;
            }
        }
        return false;
    }
}
