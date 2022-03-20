package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description: You have k lists of sorted integers in non-decreasing order.
 * Find the smallest range that includes at least one number from each of the k lists.
 * <p>
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 * @author: LISHUAI
 * @createDate: 2021/12/12 19:01
 * @version: 1.0
 */

public class LeetCode_632 {

    public static void main(String[] args) {

        List<List<Integer>> list = new ArrayList<>();


    }

    public static int[] smallestRange(List<List<Integer>> nums) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int min = Integer.MAX_VALUE, left, right = 0, size = Integer.MAX_VALUE;

        int[] result = new int[2];

        for (List<Integer> list : nums) {

            queue.addAll(list);
        }


        return result;
    }
}
