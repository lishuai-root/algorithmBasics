package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 * <p>
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 * <p>
 * You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
 * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 * @author: LISHUAI
 * @createDate: 2023/2/7 20:22
 * @version: 1.0
 */

public class LeetCode_904 {

    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>(4);
        int ans = 0, left = 0, right = 0, len = fruits.length;

        while (right < len) {
            if (map.size() > 2) {
                int k = map.get(fruits[left]);
                if (k == 1) {
                    map.remove(fruits[left]);
                } else {
                    map.put(fruits[left], k - 1);
                }
                left++;
            } else {
                map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
                right++;
            }
            if (map.size() < 3) {
                ans = Math.max(ans, right - left);
            }
        }
        return ans;
    }

    public static int totalFruit_02(int[] fruits) {
        int ans = 0, left = 0, right = 0, len = fruits.length, size = 0;
        int[] cache = new int[len];

        while (right < len) {
            if (size > 2) {
                cache[fruits[left]]--;
                if (cache[fruits[left++]] == 0) {
                    size--;
                }
            } else {
                cache[fruits[right]]++;
                if (cache[fruits[right++]] == 1) {
                    size++;
                }
            }
            if (size <= 2) {
                ans = Math.max(ans, right - left);
            }
        }
        return ans;
    }
}
