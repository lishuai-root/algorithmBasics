package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description: There is a 2D grid of size n x n where each cell of this grid has a lamp that is initially turned off.
 * <p>
 * You are given a 2D array of lamp positions lamps, where lamps[i] = [rowi, coli] indicates that the lamp at grid[rowi][coli] is turned on.
 * Even if the same lamp is listed more than once, it is turned on.
 * <p>
 * When a lamp is turned on, it illuminates its cell and all other cells in the same row, column, or diagonal.
 * <p>
 * You are also given another 2D array queries, where queries[j] = [rowj, colj]. For the jth query, determine whether grid[rowj][colj] is illuminated or not.
 * After answering the jth query, turn off the lamp at grid[rowj][colj] and its 8 adjacent lamps if they exist.
 * A lamp is adjacent if its cell shares either a side or corner with grid[rowj][colj].
 * <p>
 * Return an array of integers ans, where ans[j] should be 1 if the cell in the jth query was illuminated, or 0 if the lamp was not.
 * <p>
 * <p>
 * We have the initial grid with all lamps turned off. In the above picture we see the grid after turning on the lamp at grid[0][0] then turning on the lamp at grid[4][4].
 * The 0th query asks if the lamp at grid[1][1] is illuminated or not (the blue square). It is illuminated, so set ans[0] = 1. Then, we turn off all lamps in the red square.
 * The 1st query asks if the lamp at grid[1][0] is illuminated or not (the blue square). It is not illuminated, so set ans[1] = 0. Then, we turn off all lamps in the red rectangle.
 * @author: LISHUAI
 * @createDate: 2022/6/4 20:06
 * @version: 1.0
 */

public class LeetCode_1001 {

    private static final int[][] TMP = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    private static int N;
    private static Map<Integer, Set<Integer>> map;
    private static Map<Integer, Integer> AAMap, BBMap, rowMap, colMap;

    public static void main(String[] args) {
//        int n = 5;
//        int[][] lamps = {{0, 0}, {4, 4}}, queries = {{1, 1}, {1, 0}};
//        int n = 5;
//        int[][] lamps = {{0, 0}, {4, 4}}, queries = {{1, 1}, {1, 1}};
//        int n = 5;
//        int[][] lamps = {{0, 0}, {0, 4}}, queries = {{0, 4}, {0, 1}, {1, 4}};
        int n = 5;
        int[][] lamps = {{0, 0}, {0, 1}, {0, 4}}, queries = {{0, 0}, {0, 1}, {0, 2}};
        int[] ints = gridIllumination(n, lamps, queries);
        for (int i : ints) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        AAMap = new HashMap<>();
        BBMap = new HashMap<>();
        rowMap = new HashMap<>();
        colMap = new HashMap<>();
        N = n;
        int[] ans = new int[queries.length];
        map = lamp(lamps);
        gridIlluminationProcess(queries, ans, 0);
        return ans;
    }

    private static void gridIlluminationProcess(int[][] queries, int[] ans, int index) {
        if (index >= queries.length) {
            return;
        }
        int[] cur = queries[index];
        int row = cur[0];
        int col = cur[1];
        if (isLamps(row, col)) {
            ans[index] = 1;
        }
        close(row, col);
        for (int[] ints : TMP) {
            int r = row + ints[0];
            int c = col + ints[1];
            close(r, c);
        }
        gridIlluminationProcess(queries, ans, index + 1);
    }

    private static Map<Integer, Set<Integer>> lamp(int[][] lamps) {
        Map<Integer, Set<Integer>> map = new HashMap<>(lamps.length);
        for (int[] ints : lamps) {
            int row = ints[0];
            int col = ints[1];
            Set<Integer> set = map.computeIfAbsent(row, k -> new HashSet<>());
            if (!set.contains(col)) {
                set.add(col);
                int a = checkAA(row, col);
                int b = checkBB(row, col);
                AAMap.put(a, AAMap.getOrDefault(a, 0) + 1);
                BBMap.put(b, BBMap.getOrDefault(b, 0) + 1);
                rowMap.put(row, rowMap.getOrDefault(row, 0) + 1);
                colMap.put(col, colMap.getOrDefault(col, 0) + 1);
            }

        }
        return map;
    }

    private static boolean isLamps(int row, int col) {
        if (rowMap.containsKey(row) && rowMap.get(row) > 0) {
            return true;
        }
        if (colMap.containsKey(col) && colMap.get(col) > 0) {
            return true;
        }
        int a = checkAA(row, col);
        if (AAMap.containsKey(a) && AAMap.get(a) > 0) {
            return true;
        }
        int b = checkBB(row, col);
        return BBMap.containsKey(b) && BBMap.get(b) > 0;
    }

    private static void close(int row, int col) {
        Set<Integer> rows = map.get(row);
        if (rows != null && rows.contains(col)) {
            int a = checkAA(row, col);
            int b = checkBB(row, col);
            AAMap.put(a, AAMap.get(a) - 1);
            BBMap.put(b, BBMap.get(b) - 1);
            rowMap.put(row, rowMap.get(row) - 1);
            colMap.put(col, colMap.get(col) - 1);
            rows.remove(col);
        }
    }

    private static int checkAA(int row, int col) {
        int min = Math.min(row, col);
        row -= min;
        col -= min;
        if (col == 0) {
            return row;
        }
        return N + col - 1;
    }

    private static int checkBB(int row, int col) {
        int min = Math.min(N - col - 1, row);
        row -= min;
        col += min;
        if (col == N - 1) {
            return row;
        }
        return N + col - 1;
    }
}
