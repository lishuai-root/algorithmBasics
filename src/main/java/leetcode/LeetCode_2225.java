package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.
 * <p>
 * Return a list answer of size 2 where:
 * <p>
 * answer[0] is a list of all players that have not lost any matches.
 * answer[1] is a list of all players that have lost exactly one match.
 * The values in the two lists should be returned in increasing order.
 * <p>
 * Note:
 * <p>
 * You should only consider the players that have played at least one match.
 * The testcases will be generated such that no two matches will have the same outcome.
 * @author: LISHUAI
 * @createDate: 2022/7/26 22:09
 * @version: 1.0
 */

public class LeetCode_2225 {


    public static List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] ints : matches) {
            map.put(ints[0], map.getOrDefault(ints[0], 0));
            map.put(ints[1], map.getOrDefault(ints[1], 0) + 1);
        }
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int i : map.keySet()) {
            if (map.get(i) == 0) {
                l1.add(i);
            } else if (map.get(i) == 1) {
                l2.add(i);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        l1.sort(Integer::compare);
        l2.sort(Integer::compare);

        ans.add(l1);
        ans.add(l2);
        return ans;
    }

    public static List<List<Integer>> findWinners_02(int[][] matches) {
        int[] cache = new int[100001];
//        Arrays.fill(cache, -1);
        for (int[] ints : matches) {
            cache[ints[1]]++;
        }
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int[] ints : matches) {
            if (cache[ints[0]] == 0) {
                l1.add(ints[0]);
            } else if (cache[ints[0]] == 1) {
                l2.add(ints[0]);
            }
            cache[ints[0]] = -1;
            if (cache[ints[1]] == 0) {
                l1.add(ints[1]);
            } else if (cache[ints[1]] == 1) {
                l2.add(ints[1]);
            }
            cache[ints[1]] = -1;
        }
        List<List<Integer>> ans = new ArrayList<>();
        l1.sort(Integer::compare);
        l2.sort(Integer::compare);
        ans.add(l1);
        ans.add(l2);
        return ans;
    }
}
