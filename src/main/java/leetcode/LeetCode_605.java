package leetcode;

/**
 * @description: You have a long flowerbed in which some of the plots are planted, and some are not. However,
 * flowers cannot be planted in adjacent plots.
 * <p>
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n,
 * return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
 * @author: LISHUAI
 * @createDate: 2022/1/18 20:43
 * @version: 1.0
 */

public class LeetCode_605 {

    public static void main(String[] args) {

        int[] arr = {0, 0, 1, 0, 0};

        int n = 1;

        boolean b = canPlaceFlowers(arr, n);

        System.out.println(b);
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {

        int pre, next;

        for (int i = 0; i < flowerbed.length && n > 0; i++) {

            if (flowerbed[i] == 1) {

                continue;
            }

            pre = i - 1 >= 0 ? flowerbed[i - 1] : 0;

            next = i + 1 < flowerbed.length ? flowerbed[i + 1] : 0;
            if (pre + next == 0) {

                flowerbed[i] = 1;

                n--;
                i++;
            }
        }

        return n == 0;
    }
}
