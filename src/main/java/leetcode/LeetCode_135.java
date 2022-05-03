package leetcode;

/**
 * @description: There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 * <p>
 * You are giving candies to these children subjected to the following requirements:
 * <p>
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 * @author: LISHUAI
 * @createDate: 2022/4/5 22:48
 * @version: 1.0
 */

public class LeetCode_135 {

    public static void main(String[] args) {
        int[] ratings = {1, 0, 2};
//        int[] ratings = {1, 2, 2};
//        int[] ratings = {29, 51, 87, 87, 72, 12};
//        int[] ratings = {1, 6, 10, 8, 7, 3, 2};
        int candy = candy(ratings);
        System.out.println(candy);
    }

    public static int candy(int[] ratings) {
        if (ratings.length == 1) {
            return 1;
        }
        int ans = 0, left = 0, right = 0, tmp, cur = 0, c;

        while (right < ratings.length) {
            tmp = cur;
            cur = 1;
            while (right < ratings.length - 1 && ratings[right] > ratings[right + 1]) {
                right++;
            }

            c = right++ - left + 1;
            ans += ((c * c + c) >>> 1);
            while (right < ratings.length && ratings[right] > ratings[right - 1]) {
                ans += (++cur);
                right++;
            }

            if (left > 0 && ratings[left - 1] != ratings[left]) {
                if (ratings[left - 1] > ratings[left] && tmp <= c) {
                    ans += c - tmp + 1;
                } else if (ratings[left] > ratings[left - 1] && c <= tmp) {
                    ans += tmp - c + 1;
                }

            }

            left = right;
        }

        return ans;
    }
}
