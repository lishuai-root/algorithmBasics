package leetcode;

/**
 * @description: Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).
 * @author: LISHUAI
 * @createDate: 2023/2/13 20:59
 * @version: 1.0
 */

public class LeetCode_1523 {

    public static void main(String[] args) {
        System.out.println(countOdds(14, 17));
    }

    public static int countOdds(int low, int high) {
        return ((high + 1) - (((low & 1) == 1 ? low ^ 1 : low))) >>> 1;
    }
}
