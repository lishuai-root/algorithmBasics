package leetcode;

import java.util.ArrayList;

/**
 * @description: You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1 represents a hectare of farmland.
 * <p>
 * To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland.
 * These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not four-directionally adjacent to another farmland in a different group.
 * <p>
 * land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right corner of land is (m-1, n-1).
 * Find the coordinates of the top left and bottom right corner of each group of farmland. A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].
 * <p>
 * Return a 2D array containing the 4-length arrays described above for each group of farmland in land. If there are no groups of farmland, return an empty array.
 * You may return the answer in any order.
 * <p>
 * Groups of farmland are rectangular in shape.
 * @author: LISHUAI
 * @createDate: 2022/3/3 19:44
 * @version: 1.0
 */

public class LeetCode_1992 {

    private static int rowLen, colLen;

    public static void main(String[] args) {
        int[][] land = {{1, 0, 0}, {0, 1, 1}, {0, 1, 1}};
        int[][] farmland = findFarmland(land);
        for (int[] ints : farmland) {
            for (int i : ints) {
                System.out.print(i + "  ");
            }
            System.out.println();
        }
    }

    public static int[][] findFarmland(int[][] land) {
        rowLen = land.length;
        colLen = land[0].length;
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (land[i][j] == 1) {
                    list.add(findFarmlandProcess(land, i, j));
                }
            }
        }

        return list.toArray(new int[0][0]);
    }

    private static int[] findFarmlandProcess(int[][] land, int row, int col) {

        int[] ans = {row, col, row, col};

        for (int i = row; i < rowLen && land[i][col] == 1; i++) {
            int j = col;
            for (; j < colLen && land[i][j] == 1; j++) {
                land[i][j] = 0;
            }
            ans[2] = i;
            ans[3] = j - 1;
        }

        return ans;
    }
}

















