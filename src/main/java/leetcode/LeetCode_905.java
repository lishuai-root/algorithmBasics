package leetcode;

import java.util.Random;

/**
 * @description: Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
 * <p>
 * Return any array that satisfies this condition.
 * @author: LISHUAI
 * @createDate: 2022/5/2 15:12
 * @version: 1.0
 */

public class LeetCode_905 {

    public static void main(String[] args) {
        int[] nums = makeArr(10000, 100000);
        int o = 0;
        for (int i : nums) {
            if ((i & 1) == 0) {
                ++o;
            }
        }
        nums = sortArrayByParity(nums);
        for (int i = 0; i < o; i++) {
            if ((nums[i] & 1) != 0) {
                System.out.println(i + " : " + nums[i]);
                break;
            }
        }
        System.out.println("------------");
        for (int i = o; i < nums.length; i++) {
            if ((nums[i] & 1) != 1) {
                System.out.println(i + " : " + nums[i]);
                break;
            }
        }
    }

    private static int[] makeArr(int size, int rang) {
        int[] ans = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            ans[i] = random.nextInt(rang);
        }
        return ans;
    }

    public static int[] sortArrayByParity(int[] nums) {
        int i = -1, j = -1;
        while (i < nums.length) {
            while (++i < nums.length && (nums[i] & 1) != 0) ;
            while (++j < i && (nums[j] & 1) != 1) ;
            if (i < nums.length && j < i) {
                nums[i] = nums[j] ^ nums[i];
                nums[j] = nums[j] ^ nums[i];
                nums[i] = nums[j] ^ nums[i];
            }
        }
        return nums;
    }

}
