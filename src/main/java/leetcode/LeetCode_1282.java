package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: There are n people that are split into some unknown number of groups. Each person is labeled with a unique ID from 0 to n - 1.
 * <p>
 * You are given an integer array groupSizes, where groupSizes[i] is the size of the group that person i is in. For example, if groupSizes[1] = 3, then person 1 must be in a group of size 3.
 * <p>
 * Return a list of groups such that each person i is in a group of size groupSizes[i].
 * <p>
 * Each person should appear in exactly one group, and every person must be in a group. If there are multiple answers, return any of them. It is guaranteed that there will be at least one valid solution for the given input.
 * @author: LiShuai
 * @createDate: 2023/9/12 21:58
 * @version: 1.0
 */

public class LeetCode_1282 {

    public static List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < groupSizes.length; i++) {
            List<Integer> list = map.computeIfAbsent(groupSizes[i], o -> new ArrayList<Integer>());
            list.add(i);
            if (list.size() == groupSizes[i]) {
                ans.add(list);
                map.remove(groupSizes[i]);
            }
        }
        return ans;
    }
}
