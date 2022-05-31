package leetcode;

import java.util.*;

/**
 * @description: Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].
 * <p>
 * The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:
 * <p>
 * The rank is an integer starting from 1.
 * If two elements p and q are in the same row or column, then:
 * If p < q then rank(p) < rank(q)
 * If p == q then rank(p) == rank(q)
 * If p > q then rank(p) > rank(q)
 * The rank should be as small as possible.
 * The test cases are generated so that answer is unique under the given rules.
 * @author: LISHUAI
 * @createDate: 2022/5/13 21:22
 * @version: 1.0
 */

public class LeetCode_1632 {

    public static void main(String[] args) {
//        int[][] matrix = {{1, 2}, {3, 4}};
//        int[][] matrix = {{7, 7}, {7, 7}};
//        int[][] matrix = {{20, -21, 14}, {-19, 4, 19}, {22, -47, 24}, {-19, 4, 19}};
        int[][] matrix = {
                {-24, -9, -14, -15, 44, 31, -46, 5, 20, -5, 34},
                {9, -40, -49, -50, 17, 40, 35, 30, -39, 36, -49},
                {-18, -43, -40, -5, -30, 9, -28, -41, -6, -47, 12},
                {11, 42, -23, 20, 35, 34, -39, -16, 27, 34, -15},
                {32, 27, -30, 29, -48, 15, -50, -47, -28, -21, 38},
                {45, 48, -1, -18, 9, -4, -13, 10, 9, 8, -41},
                {-42, -35, 20, -17, 10, 5, 36, 47, 6, 1, 8},
                {3, -50, -23, 16, 31, 2, -39, 36, -25, -30, 37},
                {-48, -41, 18, -31, -48, -1, -42, -3, -8, -29, -2},
                {17, 0, 31, -30, -43, -20, -37, -6, -43, 8, 19},
                {42, 25, 32, 27, -2, 45, 12, -9, 34, 17, 32}
        };
        int[][] ints = matrixRankTransform(matrix);
        for (int[] curs : ints) {
            for (int c : curs) {
                System.out.print(c + " ");
            }
            System.out.println();
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(1, 3);
        System.out.println(map.get(1));
    }

    public static int[][] matrixRankTransform(int[][] matrix) {
        int rowLen = matrix.length, colLen = matrix[0].length;
        int[][] ans = new int[rowLen][colLen];
        int[] rowMax = new int[rowLen];
        int[] colMax = new int[colLen];
        List<Map<Integer, Integer>> rowList = new ArrayList<>();
        List<Map<Integer, Integer>> colList = new ArrayList<>();
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return matrix[a[0]][a[1]] - matrix[b[0]][b[1]];
        });

        for (int i = 0; i < rowLen; i++) {
            rowList.add(new HashMap<>());
            for (int j = 0; j < colLen; j++) {
                queue.offer(new int[]{i, j});
                if (colList.size() <= j) {
                    colList.add(new HashMap<>());
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int p = Math.max(ans[r][rowMax[r]], ans[colMax[c]][c]);
            if (p == 0) {
                ans[r][c] = 1;
            } else {
                if (ans[r][rowMax[r]] >= ans[colMax[c]][c]) {
                    if (matrix[r][c] == matrix[r][rowMax[r]]) {
                        ans[r][c] = ans[r][rowMax[r]];
                    } else {
                        ans[r][c] = ans[r][rowMax[r]] + 1;
                    }

                } else {
                    if (matrix[r][c] == matrix[colMax[c]][c]) {
                        ans[r][c] = ans[colMax[c]][c];
                    } else {
                        ans[r][c] = ans[colMax[c]][c] + 1;
                    }
                }
            }

            rowMax[r] = c;
            colMax[c] = r;
        }

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                int p = Math.max(ans[i][j], Math.max(rowList.get(i).getOrDefault(matrix[i][j], 0),
                        colList.get(j).getOrDefault(matrix[i][j], 0)));
                rowList.get(i).put(matrix[i][j], p);
                colList.get(j).put(matrix[i][j], p);
            }
        }

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                ans[i][j] = Math.max(rowList.get(i).get(matrix[i][j]), colList.get(j).get(matrix[i][j]));
            }
        }
        return ans;
    }
}
