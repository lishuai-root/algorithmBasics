package leetcode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @description: There is a 1-based binary matrix where 0 represents land and 1 represents water. You are given integers row and col representing the number of rows and columns in the matrix, respectively.
 * <p>
 * Initially on day 0, the entire matrix is land. However, each day a new cell becomes flooded with water. You are given a 1-based 2D array cells, where cells[i] = [ri, ci] represents that on the ith day, the cell on the rith row and cith column (1-based coordinates) will be covered with water (i.e., changed to 1).
 * <p>
 * You want to find the last day that it is possible to walk from the top to the bottom by only walking on land cells. You can start from any cell in the top row and end at any cell in the bottom row. You can only travel in the four cardinal directions (left, right, up, and down).
 * <p>
 * Return the last day where it is possible to walk from the top to the bottom by only walking on land cells.
 * @author: LiShuai
 * @createDate: 2023/6/30 23:10
 * @version: 1.0
 */

public class LeetCode_1970 {

    private static final int[][] T = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

    public static void main(String[] args) {
//        String[][] strs = {
//                {" ", "1", "1", "1", "1", " "},
//                {"1", "1", " ", "1", "1", " "}};
        int row = 25, col = 4;
        int[][] cells = {{17, 2}, {1, 4}, {12, 2}, {23, 3}, {4, 2}, {1, 2}, {21, 2}, {13, 3}, {7, 4}, {3, 1}, {24, 3}, {16, 1}, {19, 1}, {7, 1}, {18, 1}, {6, 2}, {21, 3}, {22, 2}, {15, 2}, {1, 3}, {5, 2}, {4, 4}, {20, 4}, {24, 4}, {20, 1}, {14, 3}, {10, 3}, {23, 4}, {6, 4}, {17, 3}, {20, 2}, {1, 1}, {17, 4}, {18, 2}, {10, 1}, {25, 1}, {11, 4}, {12, 4}, {7, 2}, {3, 2}, {2, 1}, {18, 3}, {14, 2}, {24, 2}, {25, 2}, {7, 3}, {19, 4}, {9, 3}, {16, 3}, {11, 3}, {18, 4}, {5, 1}, {11, 2}, {13, 2}, {14, 1}, {9, 2}, {12, 3}, {21, 1}, {16, 2}, {19, 2}, {24, 1}, {6, 3}, {22, 4}, {15, 4}, {11, 1}, {6, 1}, {13, 4}, {3, 3}, {5, 4}, {22, 1}, {12, 1}, {22, 3}, {9, 1}, {16, 4}, {20, 3}, {8, 3}, {9, 4}, {19, 3}, {2, 2}, {2, 3}, {10, 4}, {3, 4}, {4, 3}, {8, 2}, {2, 4}, {17, 1}, {13, 1}, {4, 1}, {25, 4}, {25, 3}, {23, 2}, {15, 1}, {21, 4}, {14, 4}, {8, 1}, {23, 1}, {8, 4}, {15, 3}, {5, 3}, {10, 2}};
        int i = latestDayToCross(row, col, cells);
        System.out.println(i);
    }

    public static int latestDayToCross(int row, int col, int[][] cells) {
        Set<Info> other = new HashSet<Info>();
        Set<Info> left = new HashSet<Info>();
        Set<Info> right = new HashSet<Info>();

        for (int i = 0; i < cells.length; i++) {
            int[] cur = cells[i];
            Info info = new Info(cur[0], cur[1]);
            info.left = cur[1];
            info.right = cur[1];
            for (int[] n : T) {
                int r = cur[0] + n[0];
                int c = cur[1] + n[1];
                info.row = r;
                info.col = c;
                if (left.contains(info)) {
                    info.left = 1;
                }
                if (right.contains(info)) {
                    info.right = col;
                }
                if (info.left == 1 && info.right == col) {
                    return i;
                }
            }
            info.row = cur[0];
            info.col = cur[1];
            if (info.left == 1) {
                left.add(info);
                latestDayToCrossProcess(other, left, info, info);
            } else if (info.right == col) {
                right.add(info);
                latestDayToCrossProcess(other, right, info, info);
            } else {
                other.add(info);
            }
        }
        return -1;
    }

    private static void latestDayToCrossProcess(Set<Info> other, Set<Info> target, Info cur, Info info) {
        if (other.isEmpty()) {
            return;
        }
        int row = cur.row;
        int col = cur.col;
        for (int[] n : T) {
            cur.row = row + n[0];
            cur.col = col + n[1];
            if (other.remove(cur)) {
                Info i = new Info(cur.row, cur.col);
                i.left = info.left;
                i.right = info.right;
                target.add(i);
                latestDayToCrossProcess(other, target, i, info);
            }
        }
        cur.row = row;
        cur.col = col;
    }

    static class Info {

        int row, col;
        int left, right;

        public Info(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return row == info.row && col == info.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
