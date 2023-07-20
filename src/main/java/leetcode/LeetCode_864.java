package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given an m x n grid grid where:
 * <p>
 * '.' is an empty cell.
 * '#' is a wall.
 * '@' is the starting point.
 * Lowercase letters represent keys.
 * Uppercase letters represent locks.
 * You start at the starting point and one move consists of walking one space in one of the four cardinal directions. You cannot walk outside the grid, or walk into a wall.
 * <p>
 * If you walk over a key, you can pick it up and you cannot walk over a lock unless you have its corresponding key.
 * <p>
 * For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter of the first k letters of the English alphabet in the grid. This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.
 * <p>
 * Return the lowest number of moves to acquire all keys. If it is impossible, return -1.
 * @author: LiShuai
 * @createDate: 2023/6/29 19:04
 * @version: 1.0
 */

public class LeetCode_864 {

    private static final int[][] T = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
//        String[] grid = {"..#....##.", "....d.#.D#", "#...#.c...", "..##.#..a.", "...#....##", "#....b....", ".#..#.....", "..........", ".#..##..A.", ".B..C.#..@"};
        String[] grid = {"..#....##.", "....d.#.D#", "#...#.c...", "..##.#..a.", "...#....##", "#....b....", ".#..#.....", "..........", ".#..##..A.", ".B..C.#..@"};
        int i = shortestPathAllKeys(grid);
        System.out.println(i);
    }

    public static int shortestPathAllKeys(String[] grid) {
        int rowLen = grid.length, colLen = grid[0].length();
        int keyNum = 0, startRow = 0, startCol = 0;
        List<int[]> targets = new ArrayList<int[]>();
        for (int i = 0; i < rowLen; i++) {
            String str = grid[i];
            for (int j = 0; j < colLen; j++) {
                char c = str.charAt(j);
                if (c >= 'a' && c <= 'z') {
                    targets.add(new int[]{i, j});
                    ++keyNum;
                } else if (c == '@') {
                    startRow = i;
                    startCol = j;
                }
            }
        }
        boolean[] ways = new boolean[targets.size()];
        boolean[] keys = new boolean[26];
        int ans = shortestPathAllKeysProcess(grid, targets, ways, startRow, startCol, keys, keyNum);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int shortestPathAllKeysProcess(String[] grid, List<int[]> targets, boolean[] ways, int curRow, int curCol, boolean[] keys, int keyNum) {
        if (keyNum == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int rowLen = grid.length, colLen = grid[0].length();
        for (int i = 0; i < targets.size(); i++) {
            if (!ways[i]) {
                ways[i] = true;
                int[] next = targets.get(i);
                int p = shortestPathAllKeysProcess(grid, keys, curRow, curCol, next[0], next[1]);
                if (p != Integer.MAX_VALUE) {
                    int k = grid[next[0]].charAt(next[1]) - 'a';
                    keys[k] = true;
                    int q = shortestPathAllKeysProcess(grid, targets, ways, next[0], next[1], keys, keyNum - 1);
                    if (q != Integer.MAX_VALUE) {
                        ans = Math.min(ans, p + q);
                    }
                    keys[k] = false;
                }
                ways[i] = false;
            }
        }
        return ans;
    }

    public static int shortestPathAllKeys_02(String[] grid) {
        int ans = 0, keyNum = 0, rowLen = grid.length, colLen = grid[0].length();
        int startRow = 0, startCol = 0;
        boolean[] keys = new boolean[26];

        for (int i = 0; i < rowLen; i++) {
            String str = grid[i];
            for (int j = 0; j < colLen; j++) {
                char c = str.charAt(j);
                if (c >= 'a' && c <= 'z') {
                    ++keyNum;
                } else if (c == '@') {
                    startRow = i;
                    startCol = j;
                }
            }
        }
        for (int i = 0; i < keyNum; i++) {
            int[] p = shortestPathAllKeysProcess(grid, keys, startRow, startCol);
            if (p[2] == -1) {
                return -1;
            }
            keys[grid[p[0]].charAt(p[1]) - 'a'] = true;
            startRow = p[0];
            startCol = p[1];
            ans += p[2];
        }
        return ans;
    }

    public static int[] shortestPathAllKeysProcess(String[] grid, boolean[] keys, int startRow, int startCol) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        int rowLen = grid.length, colLen = grid[0].length();
        boolean[][] way = new boolean[rowLen][colLen];
        queue.offer(new int[]{startRow, startCol, 0});
        way[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int row = cur[0];
            int col = cur[1];
            int c = grid[row].charAt(col);
            if (c >= 'a' && c <= 'z' && !keys[c - 'a']) {
                return cur;
            }
            for (int[] next : T) {
                int nr = row + next[0];
                int nc = col + next[1];
                if (nr < 0 || nr >= rowLen || nc < 0 || nc >= colLen || way[nr][nc]) {
                    continue;
                }
                char ncr = grid[nr].charAt(nc);
                if (ncr == '#' || ncr >= 'A' && ncr <= 'Z' && !keys[ncr - 'A']) {
                    continue;
                }
                queue.offer(new int[]{nr, nc, cur[2] + 1});
                way[nr][nc] = true;
            }
        }
        return new int[]{0, 0, -1};
    }

    public static int shortestPathAllKeysProcess(String[] grid, boolean[] keys, int startRow, int startCol, int endRow, int endCol) {
        int rowLen = grid.length, colLen = grid[0].length();
        if (endRow < 0 || endRow >= rowLen || endCol < 0 || endCol >= colLen) {
            return Integer.MAX_VALUE;
        }
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] way = new boolean[rowLen][colLen];
        queue.offer(new int[]{startRow, startCol, 0});
        way[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int row = cur[0];
            int col = cur[1];
            int c = grid[row].charAt(col);
            if (row == endRow && col == endCol && !keys[c - 'a']) {
                return cur[2];
            }
            for (int[] next : T) {
                int nr = row + next[0];
                int nc = col + next[1];
                if (nr < 0 || nr >= rowLen || nc < 0 || nc >= colLen || way[nr][nc]) {
                    continue;
                }
                char ncr = grid[nr].charAt(nc);
                if (ncr == '#' || ncr >= 'A' && ncr <= 'Z' && !keys[ncr - 'A']) {
                    continue;
                }
                queue.offer(new int[]{nr, nc, cur[2] + 1});
                way[nr][nc] = true;
            }
        }
        return Integer.MAX_VALUE;
    }
}
