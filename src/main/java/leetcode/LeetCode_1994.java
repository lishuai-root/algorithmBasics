package leetcode;

/**
 * @description: You are given an integer array nums. We call a subset of nums good if its product can be represented as a product of one or more distinct prime numbers.
 * <p>
 * For example, if nums = [1, 2, 3, 4]:
 * [2, 3], [1, 2, 3], and [1, 3] are good subsets with products 6 = 2*3, 6 = 2*3, and 3 = 3 respectively.
 * [1, 4] and [4] are not good subsets with products 4 = 2*2 and 4 = 2*2 respectively.
 * Return the number of different good subsets in nums modulo 109 + 7.
 * <p>
 * A subset of nums is any array that can be obtained by deleting some (possibly none or all) elements from nums.
 * Two subsets are different if and only if the chosen indices to delete are different.
 * @author: LISHUAI
 * @createDate: 2022/5/1 15:20
 * @version: 1.0
 */

public class LeetCode_1994 {

    public static void main(String[] args) {
        int sum = 1;
        for (int i = 0; i < 2; i++) {
            sum *= 30;
        }
        System.out.println(sum);
    }

    public static int numberOfGoodSubsets(int[] nums) {
        return 0;
    }
}
