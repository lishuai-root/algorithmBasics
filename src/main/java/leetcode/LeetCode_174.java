package leetcode;

import java.util.PriorityQueue;

/**
 * @description: The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of m x n rooms laid out in a 2D grid.
 * Our valiant knight was initially positioned in the top-left room and must fight his way through dungeon to rescue the princess.
 * <p>
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below,
 * he dies immediately.
 * <p>
 * <p>Some of the rooms are guarded by demons (represented by negative integers), so the knight loses health upon entering these rooms;
 * * other rooms are either empty (represented as 0) or contain magic orbs that increase the knight's health (represented by positive integers).
 * *
 * To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 * <p>
 * Return the knight's minimum initial health so that he can rescue the princess.
 * <p>
 * Note that any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 * @author: LISHUAI
 * @createDate: 2022/1/29 22:09
 * @version: 1.0
 */

public class LeetCode_174 {

    public static void main(String[] args) {

//        int[][] dungeon = {{-3, 5}};
        int[][] dungeon = {
                {1, -3, 3},
                {0, -2, 0},
                {-3, -3, -3}
        };

        int i = calculateMinimumHP(dungeon);

        int i1 = calculateMinimumHP_02(dungeon);

        System.out.println(i);
        System.out.println(i1);
    }


    public static int calculateMinimumHP_02(int[][] dungeon) {

        int rLen = dungeon.length, cLen = dungeon[0].length;

        int[][] dp = new int[rLen][cLen];

        dp[rLen - 1][cLen - 1] = Math.min(0, dungeon[rLen - 1][cLen - 1]);


        for (int i = cLen - 2; i >= 0; i--) {

            dp[rLen - 1][i] = dp[rLen - 1][i + 1] < 0 ? dungeon[rLen - 1][i] + dp[rLen - 1][i + 1] : Math.min(0, dungeon[rLen - 1][i]);

//            if (dp[rLen - 1][i + 1] < 0) {
//
//                dp[rLen - 1][i] = dungeon[rLen - 1][i] + dp[rLen - 1][i + 1];
//            } else {
//
//                dp[rLen - 1][i] = Math.min(0, dungeon[rLen - 1][i]);
//            }
        }

        for (int i = rLen - 2; i >= 0; i--) {

            dp[i][cLen - 1] = dp[i + 1][cLen - 1] < 0 ? dungeon[i][cLen - 1] + dp[i + 1][cLen - 1] : Math.min(0, dungeon[i][cLen - 1]);

//            if (dp[i + 1][cLen - 1] < 0) {
//
//                dp[i][cLen - 1] = dungeon[i][cLen - 1] + dp[i + 1][cLen - 1];
//            } else {
//
//                dp[i][cLen - 1] = Math.min(0, dungeon[i][cLen - 1]);
//            }
        }


        for (int i = rLen - 2; i >= 0; i--) {

            for (int j = cLen - 2; j >= 0; j--) {

//                int r = Math.min(0, dungeon[i][j]), c = Math.min(0, dungeon[i][j]);
//
//                if (dp[i][j + 1] < 0) {
//
//                    r = dungeon[i][j] + dp[i][j + 1];
//                }
//
//                if (dp[i + 1][j] < 0) {
//
//                    c = dungeon[i][j] + dp[i + 1][j];
//                }

//                int r = dp[i][j + 1] < 0 ? dungeon[i][j] + dp[i][j + 1] : Math.min(0, dungeon[i][j]);
//
//                int c = dp[i + 1][j] < 0 ? dungeon[i][j] + dp[i + 1][j] : Math.min(0, dungeon[i][j]);
//
//                dp[i][j] = Math.max(r, c);

                dp[i][j] = Math.max((dp[i][j + 1] < 0 ? dungeon[i][j] + dp[i][j + 1] : Math.min(0, dungeon[i][j])),
                        (dp[i + 1][j] < 0 ? dungeon[i][j] + dp[i + 1][j] : Math.min(0, dungeon[i][j])));
            }
        }

//        for (int[] ints : dp) {
//
//            for (int i : ints) {
//
//                System.out.print(i + " ");
//            }
//            System.out.println();
//        }

        return Math.max(-dp[0][0] + 1, 1);
    }

    public static int calculateMinimumHP(int[][] dungeon) {

        PriorityQueue<Info> queue = new PriorityQueue<>((a, b) -> {

            int result = a.cur - b.cur;

            if (result == 0) {

                result = a.ans - b.ans;
            }

            return -result;
        });

        int curNum = dungeon[0][0];

        Info info = new Info(0, dungeon[0][0], 0, 0);

        info.cur = Math.min(info.cur, info.ans);


        queue.offer(info);

        while (!queue.isEmpty()) {

            info = queue.poll();

            if (info.row == dungeon.length - 1 && info.col == dungeon[0].length - 1) {

                return Math.max(-info.cur + 1, 1);
            }

            if (info.row + 1 < dungeon.length) {

                Info i = new Info(1, info.ans + dungeon[info.row + 1][info.col], info.row + 1, info.col);

                i.cur = Math.min(info.cur, i.ans);

                queue.offer(i);
            }

            if (info.col + 1 < dungeon[0].length) {

                Info i = new Info(1, info.ans + dungeon[info.row][info.col + 1], info.row, info.col + 1);

                i.cur = Math.min(info.cur, i.ans);

                queue.offer(i);
            }
        }

        return Math.max(-info.cur + 1, 1);
    }


    static class Info {

        // cur:需要多少血，ans:当前剩余多少血
        int cur = 1, ans = 0, row, col;

        public Info() {
        }

        public Info(int cur, int ans, int row, int col) {
            this.cur = cur;
            this.ans = ans;
            this.row = row;
            this.col = col;
        }
    }
}
