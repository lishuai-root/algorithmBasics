package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 * <p>
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 * <p>
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
 * <p>
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 * @author: LISHUAI
 * @createDate: 2022/8/31 18:39
 * @version: 1.0
 */

public class LeetCode_417 {

    private static final int[][] TEMP = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public static void main(String[] args) {
        int[][] heights = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        List<List<Integer>> lists = pacificAtlantic(heights);
        for (List<Integer> list : lists) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rLen = heights.length, cLen = heights[rLen - 1].length;
        int[][] atlantic = new int[rLen][cLen];
        int[][] pacific = new int[rLen][cLen];
        boolean[][] bs = new boolean[rLen][cLen];
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                int i1 = addAtlantic(heights, atlantic, i, j, rLen, cLen, bs);
                if (i1 == 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }

//        for (int i = rLen - 1; i >= 0; i--) {
//            for (int j = cLen - 1; j >= 0; j--) {
//                addAtlantic(heights, pacific, i, j, -1, -1, bs);
//            }
//        }

//        List<List<Integer>> ans = new ArrayList<>();
//        for (int i = 0; i < rLen; i++) {
//            for (int j = 0; j < cLen; j++) {
//                if (atlantic[i][j] == 3) {
//                    List<Integer> list = new ArrayList<>();
//                    list.add(i);
//                    list.add(j);
//                    ans.add(list);
//                }
//            }
//        }
        return ans;
    }

    private static int addAtlantic(int[][] heights, int[][] atlantic, int row, int col, int rowBase, int colBase, boolean[][] bs) {

//        if (atlantic[row][col] >= 1) {
//            return atlantic[row][col];
//        }
//        atlantic[row][col] = 2;
        bs[row][col] = true;
        int ans = 0;
        for (int[] ints : TEMP) {
            int r = row + ints[0];
            int c = col + ints[1];
            if (r >= heights.length || r < 0 || c >= heights[r].length || c < 0) {
                if (r == rowBase || c == colBase) {
                    ans |= 1;
                } else {
                    ans |= 2;
                }
                continue;
            }
            if (!bs[r][c] && heights[r][c] <= heights[row][col]) {

                int p = addAtlantic(heights, atlantic, r, c, rowBase, colBase, bs);

                ans |= p;
                if (ans == 3) {
                    break;
                }
            }
        }
        bs[row][col] = false;
//        atlantic[row][col] = ans;
        return ans;
    }
}
