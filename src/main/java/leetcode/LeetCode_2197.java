package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given an array of integers nums. Perform the following steps:
 * <p>
 * Find any two adjacent numbers in nums that are non-coprime.
 * If no such numbers are found, stop the process.
 * Otherwise, delete the two numbers and replace them with their LCM (Least Common Multiple).
 * Repeat this process as long as you keep finding two adjacent non-coprime numbers.
 * Return the final modified array. It can be shown that replacing adjacent non-coprime numbers in any arbitrary order will lead to the same result.
 * <p>
 * The test cases are generated such that the values in the final array are less than or equal to 108.
 * <p>
 * Two values x and y are non-coprime if GCD(x, y) > 1 where GCD(x, y) is the Greatest Common Divisor of x and y.
 * @author: LISHUAI
 * @createDate: 2022/5/6 23:05
 * @version: 1.0
 */

public class LeetCode_2197 {

    public static void main(String[] args) {
        int[] nums = {31, 97561, 97561, 97561, 97561, 97561, 97561, 97561, 97561};
        List<Integer> list = replaceNonCoprimes(nums);
        System.out.println(gcd(97561, 97561));
        System.out.println(97561 * 97561 / 97561);
    }

    public static List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> stack = new ArrayList<>();
        int gcd, cur;

        for (int num : nums) {
            cur = stack.size() - 1;
            while (cur >= 0 && (gcd = gcd(stack.get(cur), num)) > 1) {
                cur = stack.remove(cur);
                long s = (long) cur * num;
                num = (int) (s / gcd);
                cur = stack.size() - 1;
            }
            stack.add(num);
        }
        return stack;
    }

    public static List<Integer> replaceNonCoprimes_02(int[] nums) {
        int[] stack = new int[nums.length];
        int index = -1, gcd;
        for (int i : nums) {
            while (index != -1 && (gcd = gcd(stack[index], i)) > 1) {
                long s = (long) stack[index--] * i;
                i = (int) (s / gcd);
            }
            stack[++index] = i;
        }
        List<Integer> ans = new ArrayList<>(index + 1);
        for (int i = 0; i <= index; i++) {
            ans.add(stack[i]);
        }
        return ans;
    }

    public static List<Integer> replaceNonCoprimes_03(int[] nums) {
        int index = 0, cur, gcd;

        for (int i = 1; i < nums.length; i++) {
            cur = nums[i];
            while (i - index - 1 >= 0 && (gcd = gcd(nums[i - index - 1], cur)) > 1) {
                long s = (long) nums[i - index - 1] * cur;
                cur = (int) (s / gcd);
//                cur = nums[i - index - 1] / gcd * cur;
                index++;
            }
            nums[i - index] = cur;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= nums.length - index; i++) {
            ans.add(nums[i]);
        }
        return ans;
    }

    private static int gcd(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return gcd(y, x % y);
        }
    }
}
