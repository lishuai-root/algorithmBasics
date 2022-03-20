package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/9/2 20:33
 * @version: 1.0
 */

public class LeetCode_448 {

    public static void main(String[] args) {

    }

    public List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> list = new ArrayList<>();

        int[] arr = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {

            arr[nums[i] - 1] = nums[i];
        }

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == 0) {

                list.add(i + 1);
            }

        }

        return list;
    }
}
