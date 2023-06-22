package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: There is a 1 million by 1 million grid on an XY-plane, and the coordinates of each grid square are (x, y).
 * <p>
 * We start at the source = [sx, sy] square and want to reach the target = [tx, ty] square. There is also an array of blocked squares, where each blocked[i] = [xi, yi] represents a blocked square with coordinates (xi, yi).
 * <p>
 * Each move, we can walk one square north, east, south, or west if the square is not in the array of blocked squares. We are also not allowed to walk outside of the grid.
 * <p>
 * Return true if and only if it is possible to reach the target square from the source square through a sequence of valid moves.
 * @author: LISHUAI
 * @createDate: 2023/5/11 21:59
 * @version: 1.0
 */

public class LeetCode_1036 {

    final int MAX_VISIT = 20000;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<String> blockedSet = new HashSet<>();
        for (int[] point : blocked) {
            blockedSet.add(getKey(point));
        }
        return canVisit(blockedSet, source, getKey(target)) && canVisit(blockedSet, target, getKey(source));
    }

    String getKey(int[] point) {
        return point[0] + "," + point[1];
    }

    boolean canVisit(Set<String> blocked, int[] source, String targetKey) {
        Set<String> visited = new HashSet<>();
        dfs(blocked, source, targetKey, visited);
        return visited.size() >= MAX_VISIT || visited.contains(targetKey);
    }

    void dfs(Set<String> blocked, int[] curr, String targetKey, Set<String> visited) {
        int i = curr[0], j = curr[1];
        if (i < 0 || j < 0 || i >= 1e6 || j >= 1e6) {
            return;
        }

        String key = getKey(curr);
        if (blocked.contains(key)) {
            return;
        }
        if (visited.size() >= MAX_VISIT || visited.contains(targetKey)) {
            return;
        }

        if (visited.contains(key)) {
            return;
        }
        visited.add(key);

        for (int[] next : new int[][]{{i - 1, j}, {i + 1, j}, {i, j - 1}, {i, j + 1}}) {
            dfs(blocked, next, targetKey, visited);
        }
    }
}
