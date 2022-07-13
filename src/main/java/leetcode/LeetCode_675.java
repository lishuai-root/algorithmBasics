package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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

    private static final int[][] TMP = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};


    public static void main(String[] args) {
        int[][] nums = {
                {54581641, 64080174, 24346381, 69107959},
                {86374198, 61363882, 68783324, 79706116},
                {668150, 92178815, 89819108, 94701471},
                {83920491, 22724204, 46281641, 47531096},
                {89078499, 18904913, 25462145, 60813308}
        };
        List<List<Integer>> lists = makeList(nums);
        System.out.println(cutOffTree(lists));
    }

    private static List<List<Integer>> makeList(int[][] nums) {
        List<List<Integer>> list = new ArrayList<>();
        for (int[] ints : nums) {
            List<Integer> l = new ArrayList<>();
            for (int i : ints) {
                l.add(i);
            }
            list.add(l);
        }
        return list;
    }

    public static int cutOffTree(List<List<Integer>> forest) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return forest.get(a[0]).get(a[1]) - forest.get(b[0]).get(b[1]);
        });

        int[] cur = {0, 0}, next;
        int lenR = forest.size(), lenC = forest.get(lenR - 1).size();
        for (int i = 0; i < lenR; i++) {
            for (int j = 0; j < lenC; j++) {
                if (forest.get(i).get(j) > 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            next = queue.poll();
            int k = isConnect(forest, cur, next);
            if (k == -1) {
                return -1;
            }
            ans += k;
            cur = next;
        }
        return ans;
    }

    private static int isConnect(List<List<Integer>> forest, int[] start, int[] end) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[2] - b[2];
        });
        queue.offer(new int[]{start[0], start[1], 0});
        int lenR = forest.size(), lenC = forest.get(lenR - 1).size();
        boolean[][] bls = new boolean[lenR][lenC];

        while (!queue.isEmpty()) {
            int[] curs = queue.poll();
            if (curs[0] == end[0] && curs[1] == end[1]) {
                return curs[2];
            }

            for (int[] ints : TMP) {
                int r = curs[0] + ints[0];
                int c = curs[1] + ints[1];
                if (r >= 0 && r < lenR && c >= 0 && c < lenC && !bls[r][c]) {
                    if (forest.get(r).get(c) != 0) {
                        bls[r][c] = true;
                        queue.offer(new int[]{r, c, curs[2] + 1});
                    }
                }
            }
        }
        return -1;
    }
}
