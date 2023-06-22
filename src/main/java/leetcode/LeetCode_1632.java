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
        int rLen = matrix.length, cLen = matrix[rLen - 1].length;
        int[][] ans = new int[rLen][cLen];
        Info[][] temp = new Info[rLen][cLen];
        int[] rt = new int[rLen];
        int[] ct = new int[cLen];
        Info[] ri = new Info[rLen];
        Info[] ci = new Info[cLen];
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> matrix[a[0]][a[1]] - matrix[b[0]][b[1]]);
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                queue.offer(new int[]{i, j});
            }
        }
        while (!queue.isEmpty()) {
            int[] curs = queue.poll();
            int r, c;
            Info ttrr = temp[curs[0]][rt[curs[0]]];
            Info ttcc = temp[ct[curs[1]]][curs[1]];
            if (ttrr == ttcc || ttcc == null || (ttrr != null && ttrr.value > ttcc.value)) {
                r = curs[0];
                c = rt[curs[0]];
            } else {
                r = ct[curs[1]];
                c = curs[1];
            }
//            if (temp[curs[0]][rt[curs[0]]] > temp[ct[curs[1]]][curs[1]]) {
//                r = curs[0];
//                c = rt[curs[0]];
//            } else {
//                r = ct[curs[1]];
//                c = curs[1];
//            }
            Info info = temp[r][c];
            if (matrix[curs[0]][curs[1]] == matrix[r][c]) {
                if (info == null) {
                    info = new Info(1);
                    temp[r][c] = info;
                }
                temp[curs[0]][curs[1]] = info;
            } else {
                if (info == null) {
                    info = new Info(1);
                } else {
                    info = new Info(info.value + 1);
                }
                temp[curs[0]][curs[1]] = info;
            }
//            ans[curs[0]][curs[1]] = (matrix[curs[0]][curs[1]] == matrix[r][c] ? Math.max(1, ans[r][c]) : Math.max(1, ans[r][c] + 1));
            rt[curs[0]] = curs[1];
            ct[curs[1]] = curs[0];
            ri[curs[0]] = info;
            ci[curs[1]] = info;
        }
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                ans[i][j] = temp[i][j].value;
            }
        }
        return ans;
    }

    public int[][] matrixRankTransform_other(int[][] m) {
        int M = m.length, N = m[0].length;
        int[][] a = new int[M][N];
        int[] maxRankRow = new int[M];
        int[] maxRankCol = new int[N];
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                map.putIfAbsent(m[i][j], new ArrayList<>());
                map.get(m[i][j]).add(new int[]{i, j});
            }
        }

        // go from the lowest value key to the highest
        for (int k : map.keySet()) {
            // repeat for each value until we used all cells with the same number
            // on each step we find cells connected by row/column and calculate their rank
            while (map.get(k).size() > 0) {
                Set<Integer> rowsUsed = new HashSet<>();
                Set<Integer> colsUsed = new HashSet<>();
                List<int[]> allSame = map.get(k);

                // get the first cell as the root and find all connected cells
                int[] root = allSame.get(0);
                rowsUsed.add(root[0]);
                colsUsed.add(root[1]);
                boolean[] used = new boolean[allSame.size()];
                used[0] = true;
                // continue until we found all connected
                while (true) {
                    int added = 0;
                    for (int i = 1; i < allSame.size(); i++) {
                        int[] n = allSame.get(i);
                        if (used[i]) continue;
                        // if the cell is in the same row or column with the root or any one that is already connected with the root
                        if (rowsUsed.contains(n[0]) || colsUsed.contains(n[1])) {
                            rowsUsed.add(n[0]);
                            colsUsed.add(n[1]);
                            used[i] = true;
                            added++;
                        }
                    }
                    if (added == 0) break;
                }
                List<int[]> connected = new ArrayList<>();
                List<int[]> left = new ArrayList<>();
                for (int i = 0; i < allSame.size(); i++) {
                    if (used[i]) connected.add(allSame.get(i));
                    else left.add(allSame.get(i));
                }
                // put all that are not connected back to the map
                map.put(k, left);

                int rank = Integer.MIN_VALUE;

                // calculate the maximum rank of all connected cells
                for (int[] n : connected) {
                    rank = Math.max(rank, Math.max(maxRankRow[n[0]], maxRankCol[n[1]]) + 1);
                }
                // update maxRank for all cols and rows and set the rank as answer for each connected cell
                for (int[] n : connected) {
                    maxRankRow[n[0]] = maxRankCol[n[1]] = rank;
                    a[n[0]][n[1]] = rank;
                }
            }
        }
        return a;
    }

    static class Info {
        int value;

        public Info(int value) {
            this.value = value;
        }
    }
}
