package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: A good meal is a meal that contains exactly two different food items with a sum of deliciousness equal to a power of two.
 * <p>
 * You can pick any two different foods to make a good meal.
 * <p>
 * Given an array of integers deliciousness where deliciousness[i] is the deliciousness of the i​​​​​​th​​​​​​​​ item of food, return the number of different good meals you can make from this list modulo 109 + 7.
 * <p>
 * Note that items with different indices are considered different even if they have the same deliciousness value.
 * @author: LiShuai
 * @createDate: 2023/5/31 21:58
 * @version: 1.0
 */

public class LeetCode_1711 {

    public static int countPairs(int[] deliciousness) {
        int ans = 0, len = deliciousness.length;
        Map<Integer, Integer> map = new HashMap<>(len);
        map.put(deliciousness[0], 1);
        int max = deliciousness[0];
        int min = deliciousness[0];

        for (int i = 1; i < len; i++) {
            int k = Math.max(-1 >>> Integer.numberOfLeadingZeros(deliciousness[i] + min - 1), 0) + 1;
            int p = 0;
            while (k > 0 && k - deliciousness[i] <= max) {
                p += map.getOrDefault(k - deliciousness[i], 0);
                k <<= 1;
            }
            ans += p;
            ans %= 1000000007;
            map.put(deliciousness[i], map.getOrDefault(deliciousness[i], 0) + 1);
            max = Math.max(max, deliciousness[i]);
            min = Math.min(min, deliciousness[i]);
        }
        return ans;
    }
}
