package leetcode;

import java.io.File;
import java.util.Scanner;

/**
 * @description: Given an array of integers arr, return the number of subarrays with an odd sum.
 * <p>
 * Since the answer can be very large, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2022/7/7 13:35
 * @version: 1.0
 */

public class LeetCode_1524 {


    public static void main(String[] args) throws Exception {
//        int[] arr = {1, 3, 5};
//        int[] arr = {2, 4, 6};
//        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int[] arr = makeArray();
        int i = numOfSubarrays(arr);
        System.out.println(i);
    }

    private static int[] makeArray() throws Exception {
        Scanner scanner = new Scanner(new File("src/main/resources/leetCode_1524.txt"));
        String line = scanner.nextLine();
        String[] split = line.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        return nums;
    }

    public static int numOfSubarrays(int[] arr) {
        if (arr.length == 1) {
            return (arr[0] & 1) == 0 ? 0 : 1;
        }
        int tmp = 1000000007;
        int o = (arr[0] & 1) ^ 1;
        int j = o ^ 1;
        int ans = j;

        for (int i = 1; i < arr.length; i++) {
            if ((arr[i] & 1) == 0) {
                ++o;
            } else {
                int t = o;
                o = j;
                j = t + 1;
            }
            ans = (ans + j) % tmp;
        }
        return ans;
    }
}
