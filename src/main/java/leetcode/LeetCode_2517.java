package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an array of positive integers price where price[i] denotes the price of the ith candy and a positive integer k.
 * <p>
 * The store sells baskets of k distinct candies. The tastiness of a candy basket is the smallest absolute difference of the prices of any two candies in the basket.
 * <p>
 * Return the maximum tastiness of a candy basket.
 * @author: LISHUAI
 * @createDate: 2023/4/8 23:23
 * @version: 1.0
 */

public class LeetCode_2517 {


    public static void main(String[] args) {
        int[] price = {34, 116, 83, 15, 150, 56, 69, 42, 26};
        int k = 6;
        int i = maximumTastiness(price, k);
        System.out.println(i);
    }

    public static int maximumTastiness(int[] price, int k) {
        if (price.length == 1) {
            return 0;
        }
        Arrays.sort(price);

        int len = price.length, left = 1, right = price[len - 1] - price[0];
        if (k == 2) {
            return right;
        }

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int l = 1, c = 1, p = 0;
            while (c < k && l < len) {
                if (price[l] - price[p] >= mid) {
                    c++;
                    p = l;
                }
                l++;
            }
            if (c >= k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}
