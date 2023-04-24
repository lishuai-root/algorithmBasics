package leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @description: Given an array of integers nums, sort the array in ascending order and return it.
 * <p>
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.
 * @author: LISHUAI
 * @createDate: 2023/3/1 19:57
 * @version: 1.0
 */

public class LeetCode_912 {

    private static final Logger logger = LoggerFactory.getLogger(LeetCode_912.class);

    public static int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    public static void main(String[] args) {
        logger.error("error");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace", new RuntimeException());
    }
}
