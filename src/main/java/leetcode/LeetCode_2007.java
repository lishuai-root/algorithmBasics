package leetcode;

import java.util.Arrays;

/**
 * @description: An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.
 * <p>
 * Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.
 * @author: LISHUAI
 * @createDate: 2022/9/15 19:01
 * @version: 1.0
 */

public class LeetCode_2007 {

    public static void main(String[] args) {
//        int[] changed = {6, 3, 0, 1};
//        int[] changed = {1, 3, 4, 2, 6, 8};
//        int[] changed = {0, 0, 0, 0};
//        int[] changed = {2, 2, 1, 1};
        int[] changed = {4, 4, 16, 20, 8, 8, 2, 10};
        int[] originalArray = findOriginalArray(changed);
        for (int i : originalArray) {
            System.out.println(i);
        }


    }

    public static int[] findOriginalArray(int[] changed) {
        int len = changed.length;
        if ((len & 1) != 0) {
            return new int[0];
        }
        Arrays.sort(changed);
        int left = len - 1, right = len - 1, index = len >>> 1;
        int[] ans = new int[index];

        while (right > 0 && left >= 0) {
            if (changed[right] == -1) {
                right--;
                continue;
            }
            if (left == right || (changed[left] << 1) > changed[right]) {
                left--;
            } else if (changed[left] << 1 == changed[right]) {
                ans[--index] = changed[left];
                changed[left--] = -1;
                right--;
            } else if ((changed[left] << 1) < changed[right]) {
                return new int[0];
            }
        }
        return index == 0 ? ans : new int[0];
    }
}
