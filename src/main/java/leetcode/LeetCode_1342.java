package leetcode;

/**
 * @description: Given an integer num, return the number of steps to reduce it to zero.
 * <p>
 * In one step, if the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.
 * @author: LISHUAI
 * @createDate: 2022/5/27 22:16
 * @version: 1.0
 */

public class LeetCode_1342 {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int i1 = numberOfSteps(i);
            int i2 = numberOfSteps_02(i);
            if (i1 != i2) {
                System.out.println(i1 + " " + i2 + " " + i);
                break;
            }
        }
    }

    public static int numberOfSteps(int num) {
        int ans = 0;
        while (num != 0) {
            ans++;
            if ((num & 1) == 0) {
                num >>>= 1;
            } else {
                num -= 1;
            }
        }
        return ans;
    }

    public static int numberOfSteps_02(int num) {
        int ans = 0;
        int tmp = num >>> 1;
        while (tmp != 0) {
            ans++;
            tmp = (-tmp & tmp) ^ tmp;
        }
        ans += 32 - Integer.numberOfLeadingZeros(num >>> 1);
        return (num & 1) == 0 ? ans : ans + 1;
    }
}
