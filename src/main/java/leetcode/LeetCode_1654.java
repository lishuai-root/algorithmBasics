package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @description: A certain bug's home is on the x-axis at position x. Help them get there from position 0.
 * <p>
 * The bug jumps according to the following rules:
 * <p>
 * It can jump exactly a positions forward (to the right).
 * It can jump exactly b positions backward (to the left).
 * It cannot jump backward twice in a row.
 * It cannot jump to any forbidden positions.
 * The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.
 * <p>
 * Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i], and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home. If there is no possible sequence of jumps that lands the bug on position x, return -1.
 * @author: LISHUAI
 * @createDate: 2022/5/16 22:57
 * @version: 1.0
 */

public class LeetCode_1654 {

    public static void main(String[] args) {
//        int[] forbidden = {1, 6, 2, 14, 5, 17, 4};
//        int a = 16, b = 9, x = 7;
        int[] forbidden = {162, 118, 178, 152, 167, 100, 40, 74, 199, 186, 26, 73, 200, 127, 30, 124, 193, 84, 184, 36, 103, 149, 153, 9, 54, 154, 133, 95, 45, 198, 79, 157, 64, 122, 59, 71, 48, 177, 82, 35, 14, 176, 16, 108, 111, 6, 168, 31, 134, 164, 136, 72, 98};
        int a = 29, b = 98, x = 80;
        int i = minimumJumps_02(forbidden, a, b, x);
        System.out.println(i);

//        LinkedList<Integer> queue = new LinkedList<>();
//        queue.offer(1);
//        queue.offer(2);
//        queue.offer(3);
//        queue.offer(4);
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());

        System.out.println(Solution.minimumJumps(forbidden, a, b, x));
    }

    public static int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> set = new HashSet<>();
        for (int i : forbidden) {
            set.add(i);
        }
        int i = minimumJumpsProcess(set, 0, a, b, x, false);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    private static int minimumJumpsProcess(Set<Integer> set, int cur, int a, int b, int x, boolean bl) {
//        System.out.println(cur);
        if (cur < 0 || cur > 400) {
            return Integer.MAX_VALUE;
        }
        if (cur == x) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        if (bl && !set.contains(cur - b)) {
            ans = Math.min(ans, minimumJumpsProcess(set, cur - b, a, b, x, false));
        }
        if (!set.contains(cur + a)) {
            ans = Math.min(ans, minimumJumpsProcess(set, cur + a, a, b, x, true));
        }

        return ans == Integer.MAX_VALUE ? ans : ans + 1;
    }

    public static int minimumJumps_02(int[] forbidden, int a, int b, int x) {
        int ans = -1;
        int limit = 2000 + 2 * b + 1;
        boolean[] bls = new boolean[limit];
        for (int i : forbidden) {
            bls[i] = true;
        }

        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});
        bls[0] = true;
        while (!queue.isEmpty()) {
            int[] curs = queue.poll();

            if (curs[0] == x) {
                ans = curs[1];
                break;
            }
            if (curs[2] != 1 && curs[0] - b > 0 && !bls[curs[0] - b]) {

                bls[curs[0] - b] = true;
                queue.offer(new int[]{curs[0] - b, curs[1] + 1, 1});
            }
            if (curs[0] + a < limit && !bls[curs[0] + a]) {

                bls[curs[0] + a] = true;
                queue.offer(new int[]{curs[0] + a, curs[1] + 1, 0});
            }

        }
        return ans;
    }

    static class Solution {
        public static int minimumJumps(int[] forbidden, int a, int b, int x) {
            int limit = 2000 + 2 * b + 1;
            boolean[] visited = new boolean[limit];
            for (int num : forbidden) {
                visited[num] = true;
            }
            int step = 0;
            Queue<Pair> q = new LinkedList<>();
            q.add(new Pair(0, false));
            visited[0] = true;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    Pair p = q.poll();
                    int pos = p.pos;

                    boolean dir = p.dir;
                    if (pos == x) {
                        return step;
                    }

                    if (dir == false) {
                        int backward = pos - b;

                        if (backward > 0 && !visited[backward]) {
                            q.offer(new Pair(backward, true));
                            visited[backward] = true;
                        }
                    }

                    int forward = pos + a;

                    if (forward < limit && !visited[forward]) {
                        q.offer(new Pair(forward, false));
                        visited[forward] = true;
                    }


                }
                step++;
            }
            return -1;
        }

        static class Pair {
            int pos;
            boolean dir;

            public Pair(int pos, boolean dir) {
                this.pos = pos;
                this.dir = dir;
            }
        }
    }
}
