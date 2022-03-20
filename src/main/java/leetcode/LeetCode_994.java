package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @description: You are given an m x n grid where each cell can have one of three values:
 * <p>
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1.
 * @author: LISHUAI
 * @createDate: 2021/11/29 23:50
 * @version: 1.0
 */

public class LeetCode_994 {

    public static void main(String[] args) {

        int[][] arr = new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}};

        int i = orangesRotting_02(arr);

        System.out.println(i);
    }

    public static int orangesRotting(int[][] grid) {

        Set<String> set = new HashSet<>();

        int fresh = 0;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == 2) {

                    set.add(i + "-" + j);
                } else if (grid[i][j] == 1) {

                    fresh++;
                }
            }
        }

        return process(grid, 0, fresh, set);
    }

    private static int process(int[][] grid, int minute, int fresh, Set<String> set) {

        if (fresh == 0) {

            return minute;
        }

        int oldSize = fresh, row, col;

        Set<String> newSet = new HashSet<>();

        for (String line : set) {

            row = Integer.parseInt(line.split("-")[0]);

            col = Integer.parseInt(line.split("-")[1]);

            if (row + 1 < grid.length && grid[row + 1][col] == 1) {

                grid[row + 1][col] = 2;

                newSet.add((row + 1) + "-" + col);

                fresh--;
            }

            if (row - 1 >= 0 && grid[row - 1][col] == 1) {

                grid[row - 1][col] = 2;

                newSet.add((row - 1) + "-" + col);

                fresh--;
            }

            if (col + 1 < grid[0].length && grid[row][col + 1] == 1) {

                grid[row][col + 1] = 2;

                newSet.add(row + "-" + (col + 1));

                fresh--;
            }

            if (col - 1 >= 0 && grid[row][col - 1] == 1) {

                grid[row][col - 1] = 2;

                newSet.add(row + "-" + (col - 1));

                fresh--;
            }
        }


        if (fresh == oldSize) {

            return -1;
        }

        return process(grid, minute + 1, fresh, newSet);
    }


    public static int orangesRotting_02(int[][] grid) {

        Queue<int[]> queue = new LinkedList<>();

        int fresh = 0, minute = 0, oldFresh = 0, row, col, size;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == 2) {

                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {

                    fresh++;
                }
            }
        }

        while (fresh != 0) {

            oldFresh = fresh;

            size = queue.size();

            while (size-- > 0) {

                int[] poll = queue.poll();

                row = poll[0];

                col = poll[1];

                if (row + 1 < grid.length && grid[row + 1][col] == 1) {

                    grid[row + 1][col] = 2;

                    queue.offer(new int[]{row + 1, col});

                    fresh--;
                }

                if (row - 1 >= 0 && grid[row - 1][col] == 1) {

                    grid[row - 1][col] = 2;

                    queue.offer(new int[]{row - 1, col});

                    fresh--;
                }

                if (col + 1 < grid[0].length && grid[row][col + 1] == 1) {

                    grid[row][col + 1] = 2;

                    queue.offer(new int[]{row, col + 1});

                    fresh--;
                }

                if (col - 1 >= 0 && grid[row][col - 1] == 1) {

                    grid[row][col - 1] = 2;

                    queue.offer(new int[]{row, col - 1});

                    fresh--;
                }

            }

            if (oldFresh == fresh) {

                return -1;
            }

            minute++;
        }

        return minute;
    }

    public static int orangesRotting_03(int[][] grid) {

        Set<String> set = new HashSet<>();

        int fresh = 0, minute = 0, oldFresh = 0;

        boolean[][] bl = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == 2) {

                    set.add(i + "-" + j);

                    bl[i][j] = true;
                } else if (grid[i][j] == 1) {

                    fresh++;
                }
            }
        }

        while (fresh != 0) {

            oldFresh = fresh;

            for (int i = 0; i < grid.length; i++) {

                for (int j = 0; j < grid[0].length; j++) {

                    if (bl[i][j]) {

                        bl[i][j] = false;

                        if (i + 1 < grid.length && grid[i + 1][j] == 1) {

                            grid[i + 1][j] = 2;

                            bl[i + 1][j] = true;

                            fresh--;
                        }

                        if (i - 1 >= 0 && grid[i - 1][j] == 1) {

                            grid[i - 1][j] = 2;

                            bl[i - 1][j] = true;

                            fresh--;
                        }

                        if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {

                            grid[i][j + 1] = 2;

                            bl[i][j + 1] = true;

                            fresh--;
                        }

                        if (j - 1 >= 0 && grid[i][j - 1] == 1) {

                            grid[i][j - 1] = 2;

                            bl[i][j - 1] = true;

                            fresh--;
                        }
                    }
                }
            }

            if (oldFresh == fresh) {

                return -1;
            }

            minute++;
        }

        return minute;
    }


    /**
     * other people method
     *
     * @param grid
     * @return
     */
    public int orangesRotting_04(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int tot = 0, ans = 0, curr_tot = 0;
        int m = grid.length, n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // find all the oranges
                if (grid[i][j] != 0) {
                    tot++;
                }
                // find all rotten oranges and add them to queue
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        // helper arrays to check in four directions from current cell
        int dx[] = {1, -1, 0, 0};
        int dy[] = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int size = q.size();
            curr_tot += size;// count number of rotten oranges present in q
            while (size-- > 0) {
                int[] orange = q.poll();
                // for current  rotten orange, check all its adjacent cells
                for (int i = 0; i < 4; i++) {
                    int x = orange[0] + dx[i];
                    int y = orange[1] + dy[i];
                    // if the adjacent cell is a valid cell then continue                       // otherwise return
                    if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) continue;
                    // mark the orange as rotten and push it to q
                    grid[x][y] = 2;
                    q.offer(new int[]{x, y});
                }
            }
            // if there are elements present in q, that means we have to
            // continue to process q , hence more time , hence ans++
            if (!q.isEmpty()) ans++;
        }

        // if curr_tot!=tot, that means we have skipped some orange because of that orange isolation, hence return -1
        return (curr_tot == tot) ? ans : -1;

    }

}
