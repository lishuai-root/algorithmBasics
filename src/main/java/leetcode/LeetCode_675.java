package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are asked to cut off all the trees in a forest for a golf event.
 * The forest is represented as an m x n matrix. In this matrix:
 * <p>
 * 0 means the cell cannot be walked through.
 * 1 represents an empty cell that can be walked through.
 * A number greater than 1 represents a tree in a cell that can be walked through, and this number is the tree's height.
 * In one step, you can walk in any of the four directions: north, east, south, and west.
 * If you are standing in a cell with a tree, you can choose whether to cut it off.
 * <p>
 * You must cut off the trees in order from shortest to tallest. When you cut off a tree,
 * the value at its cell becomes 1 (an empty cell).
 * <p>
 * Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees. If you cannot cut off all the trees, return -1.
 * <p>
 * You are guaranteed that no two trees have the same height, and there is at least one tree needs to be cut off.
 * <p>
 * The trees in the bottom row cannot be accessed as the middle row is blocked.
 * @author: LISHUAI
 * @createDate: 2022/4/3 22:16
 * @version: 1.0
 */

public class LeetCode_675 {
    private static final int[][] cache = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


    public static void main(String[] args) {
        List<List<Integer>> lists = makeList();
        int i = cutOffTree(lists);

        System.out.println("----------------");
        for (List<Integer> list : lists) {
            for (int j : list) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println("----------------");
        System.out.println(i);

    }

    private static List<List<Integer>> makeList() {
//        int[][] ints = {{1, 2, 3}, {0, 0, 0}, {7, 6, 5}};
//        int[][] ints = {{2, 3, 4}, {0, 0, 5}, {8, 7, 6}};
//        int[][] ints = {{1, 2, 3}, {0, 0, 4}, {7, 6, 5}};
        int[][] ints = {{54581641, 64080174, 24346381, 69107959}, {86374198, 61363882, 68783324, 79706116}, {668150, 92178815, 89819108, 94701471}, {83920491, 22724204, 46281641, 47531096}, {89078499, 18904913, 25462145, 60813308}};
        List<List<Integer>> list = new ArrayList<>();
        System.out.println("=====================");
        for (int[] arr : ints) {
            ArrayList<Integer> l = new ArrayList<>();
            list.add(l);
            for (int i : arr) {

                l.add(i);
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("=====================");
        return list;
    }

    public static int cutOffTree(List<List<Integer>> forest) {

        int sum = isTree(forest);
        if (sum == -1) {
            System.out.println("---------------");
            return -1;
        }

        System.out.println("sum : " + sum);

//        System.out.println("-------------------------");
//        for (List<Integer> list : forest) {
//            for (int l : list) {
//                System.out.print(l + " ");
//                if (l > 1) {
////                    return -1;
//                    i = -1;
//                }
//            }
//            System.out.println();
//        }
//        System.out.println("-------------------------");
        boolean[][] bs = new boolean[forest.size()][forest.get(0).size()];
        return cutOffTreeProcess(forest, 0, 0, 0, sum - 1, bs);
    }

    private static int cutOffTreeProcess(List<List<Integer>> forest, int row, int col, int pace, int sum, boolean[][] bs) {
//        System.out.println("---------");

        if (sum <= 0) {
            System.out.println("++++++++++++++++");
            for (List<Integer> list : forest) {
                for (int i : list) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
            System.out.println("++++++++++++++++");
            return pace;
        }

        int ans = Integer.MAX_VALUE, r = row, c = col, b = forest.get(row).get(col);
        forest.get(row).set(col, 1);
        for (int[] ints : cache) {
            r = row + ints[0];
            c = col + ints[1];
            if (r < 0 || r >= forest.size() || c < 0 || c >= forest.get(row).size() || forest.get(r).get(c) <= 1 || forest.get(r).get(c) < b) {
                continue;
            }

            ans = Math.min(ans, cutOffTreeProcess(forest, r, c, pace + 1, sum - 1, bs));

        }

        if (ans == Integer.MAX_VALUE) {
            for (int[] ints : cache) {
                r = row + ints[0];
                c = col + ints[1];
                if (r < 0 || r >= forest.size() || c < 0 || c >= forest.get(row).size() || forest.get(r).get(c) != 1 || bs[r][c]) {
                    continue;
                }
//                System.out.println("............");
                bs[r][c] = true;
//                forest.get(row).set(col, 1);
                ans = Math.min(ans, cutOffTreeProcess(forest, r, c, pace + 1, sum, bs));
                bs[r][c] = false;
//                forest.get(row).set(col, b);
            }
        }
        forest.get(row).set(col, b);
        return ans;
    }

    private static int isTree(List<List<Integer>> list) {
        int r = list.size(), c = list.get(0).size(), ans = 0;
        int[][] bs = new int[r][c];
        isTreeProcess(list, 0, 0, bs);

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (list.get(i).get(j) > 1) {
                    if (bs[i][j] == 0) {
                        return -1;
                    }
                    ans++;
                }
            }
        }
        return ans;
    }

    private static void isTreeProcess(List<List<Integer>> list, int row, int col, int[][] bs) {
        if (row < 0 || row >= list.size() || col < 0 || col >= list.get(row).size() || bs[row][col] > 0) {
            return;
        }

        if (list.get(row).get(col) == 0) {
            bs[row][col] = 2;
            return;
        }
        bs[row][col] = 1;
        for (int[] ints : cache) {
            isTreeProcess(list, row + ints[0], col + ints[1], bs);
        }
    }
}
