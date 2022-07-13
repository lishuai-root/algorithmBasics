package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: Alice and Bob have a different total number of candies. You are given two integer arrays aliceSizes and bobSizes where aliceSizes[i] is the number of candies of the ith box of candy that Alice has and bobSizes[j] is the number of candies of the jth box of candy that Bob has.
 * <p>
 * Since they are friends, they would like to exchange one candy box each so that after the exchange, they both have the same total amount of candy. The total amount of candy a person has is the sum of the number of candies in each box they have.
 * <p>
 * Return an integer array answer where answer[0] is the number of candies in the box that Alice must exchange, and answer[1] is the number of candies in the box that Bob must exchange. If there are multiple answers, you may return any one of them. It is guaranteed that at least one answer exists.
 * @author: LISHUAI
 * @createDate: 2022/6/11 18:48
 * @version: 1.0
 */

public class LeetCode_888 {

    public static void main(String[] args) {
//        int[] aliceSizes = {1, 1}, bobSizes = {2, 2};
        int[] aliceSizes = {1, 2, 5}, bobSizes = {2, 4};
        int[] ints = fairCandySwap_02(aliceSizes, bobSizes);
        if (ints != null) {
            for (int i : ints) {
                System.out.print(i + " ");
            }
        }

        System.out.println();
    }

    public static int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        Arrays.sort(aliceSizes);
        Arrays.sort(bobSizes);
        int aliceSize = 0, bobSize = 0, ch;
        for (int i : aliceSizes) {
            aliceSize += i;
        }
        for (int i : bobSizes) {
            bobSize += i;
        }
        ch = (bobSize - aliceSize) >> 1;


        for (int size : aliceSizes) {
            int index = search(bobSizes, 0, bobSizes.length - 1, (size + ch));
            if (index != -1) {
                return new int[]{size, size + ch};
            }
        }
        return null;
    }

    private static int search(int[] nums, int left, int right, int target) {
        int mid = 0;
        while (right >= left) {
            mid = left + ((right - left) >>> 1);
            if (nums[mid] == target) {
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[mid] == target ? mid : -1;
    }

    public static int[] fairCandySwap_02(int[] aliceSizes, int[] bobSizes) {
        int aliceSize = 0, bobSize = 0, ch, index = 0;
        Set<Integer> bobSet = new HashSet<>();

//        for (int i : aliceSizes) {
//            aliceSize += i;
//        }
//        for (int i : bobSizes) {
//            bobSize += i;
//            bobSet.add(i);
//        }
        while (index < aliceSizes.length && index < bobSizes.length) {
            aliceSize += aliceSizes[index];
            bobSize += bobSizes[index];
            bobSet.add(bobSizes[index]);
            index++;
        }

        while (index < aliceSizes.length) {
            aliceSize += aliceSizes[index];
            index++;
        }
        while (index < bobSizes.length) {
            bobSize += bobSizes[index];
            bobSet.add(bobSizes[index]);
            index++;
        }
        ch = (bobSize - aliceSize) >> 1;
        for (int size : aliceSizes) {
            if (bobSet.contains(size + ch)) {
                return new int[]{size, size + ch};
            }
        }
        return null;
    }
}
