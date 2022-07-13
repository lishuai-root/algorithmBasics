package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears once or twice, return an array of all the integers that appears twice.
 * <p>
 * You must write an algorithm that runs in O(n) time and uses only constant extra space.
 * @author: LISHUAI
 * @createDate: 2022/6/12 13:17
 * @version: 1.0
 */

public class LeetCode_442 {

    public static void main(String[] args) {
//        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        int[] nums = {1};
        List<Integer> duplicates = findDuplicates(nums);
        for (int i : duplicates) {
            System.out.println(i);
        }
    }

    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int index = 0;

        while (index < nums.length) {
            if (nums[index] != index + 1 && nums[index] != -1) {
                if (nums[nums[index] - 1] == nums[index]) {
                    ans.add(nums[index]);
                    nums[index] = -1;
                } else {
                    int c = nums[nums[index] - 1];
                    nums[nums[index] - 1] = nums[index];
                    nums[index] = c;
                    index--;
                }
            }
            index++;
        }
        return ans;
    }

}
