package leetcode;

import java.util.List;

/**
 * @description: There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.
 * <p>
 * When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
 * <p>
 * Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.
 * @author: LISHUAI
 * @createDate: 2022/12/22 16:21
 * @version: 1.0
 */

public class LeetCode_841 {

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] bls = new boolean[rooms.size()];
        return canVisitAllRoomsProcess(rooms, 0, bls) == rooms.size();
    }

    private static int canVisitAllRoomsProcess(List<List<Integer>> rooms, int cur, boolean[] bls) {
        if (bls[cur]) {
            return 0;
        }
        bls[cur] = true;
        int ans = 1;
        List<Integer> list = rooms.get(cur);
        if (!list.isEmpty()) {
            for (int next : list) {
                ans += canVisitAllRoomsProcess(rooms, next, bls);
            }
        }
        return ans;
    }
}
