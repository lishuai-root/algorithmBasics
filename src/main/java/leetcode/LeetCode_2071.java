package leetcode;

import java.util.Arrays;

/**
 * @description: You have n tasks and m workers. Each task has a strength requirement stored in a 0-indexed integer array tasks, with the ith task requiring tasks[i] strength to complete. The strength of each worker is stored in a 0-indexed integer array workers, with the jth worker having workers[j] strength. Each worker can only be assigned to a single task and must have a strength greater than or equal to the task's strength requirement (i.e., workers[j] >= tasks[i]).
 * <p>
 * Additionally, you have pills magical pills that will increase a worker's strength by strength. You can decide which workers receive the magical pills, however, you may only give each worker at most one magical pill.
 * <p>
 * Given the 0-indexed integer arrays tasks and workers and the integers pills and strength, return the maximum number of tasks that can be completed.
 * @author: LiShuai
 * @createDate: 2023/7/27 21:54
 * @version: 1.0
 */

public class LeetCode_2071 {

    public static void main(String[] args) {
        int[] tasks = {1943, 2068, 4077, 7832, 8061, 6939, 6263, 8917, 8008, 5348, 8837, 4753, 4607, 7638, 9000, 7222, 4552, 1123, 9225, 6896, 4154, 6303, 3186, 2325, 9994, 5855, 8851, 7377, 1930, 1187, 5094, 2689, 8852, 1507, 1567, 9575, 1193, 1557, 8840, 9075, 5032, 3642, 6917, 7968, 5310, 2315, 7516, 4776, 3091, 7027, 1788, 2007, 2651, 6112, 4264, 5644, 3585, 9408, 7410, 9605, 8151, 1538, 6905, 6759, 4518, 3444, 5036, 1589, 3902, 3037, 1468, 9179, 3000, 5339, 6805, 7394, 9418, 9262, 2888, 4708, 3402, 5554, 8714, 7393, 2848, 5946, 9808, 4301, 6675, 8564, 6300, 4359, 9506, 1946, 9673, 7412, 1164, 2986, 2198, 5144, 3763, 4782, 8835, 6994, 8035, 3332, 2342, 5243, 3150, 9084, 6519, 9798, 7682, 9919, 7473, 7686, 9978, 8092, 9897, 3985, 9874, 5842, 9740, 2145, 2426, 7069, 8963, 9250, 4142, 9434, 1895, 6559, 3233, 8431, 6278, 6748, 7305, 4359, 2144, 8009, 4890, 6486, 7464, 8645, 1704, 5915, 9586, 1394, 7504, 2124, 3150, 2051, 5026, 7612, 3715, 5757, 4355, 6394, 3202, 2777, 3949, 2349, 7398, 3029, 3081, 5116, 5078, 8048, 9934, 4348, 8518, 5201, 1203, 7935, 5006, 6388, 8680, 3427, 6048, 1957, 4026, 4618, 4080};
        int[] words = {875, 2347, 939, 3664, 3926, 4555, 1947, 4406, 4601, 3502, 4964, 1307, 4232, 2968, 4572, 3139, 2788, 1847, 1208, 2019, 4184, 1664, 1747, 3690, 4333, 891, 686, 1959, 2218, 4972, 806, 741, 1490, 4529, 2909, 925, 2040, 1234, 1264, 1135, 3640, 1455, 2933, 3699, 2856, 3074, 4579, 2458, 2090, 833, 4140, 4534, 2336, 4363, 1948, 4546, 4155, 3735, 3577, 2780, 4874, 1747, 4844, 3482, 3053, 3534, 549, 4500, 2237, 2128, 1554, 3210, 4161, 2211, 950, 3732, 2182, 1148, 4368, 4050, 1452, 1015, 3192, 4318, 3908, 2590, 1103, 2811, 2821, 690, 2718, 3360, 2659, 3315, 579, 3108, 2979, 3903, 4367, 1906, 4964, 889, 4803, 825, 2270, 4794, 4825, 4485, 4461, 1639, 3857, 1330, 3169, 2425, 3694, 1980, 2268, 3002, 2177, 3225, 2499, 2517, 1916, 2844, 760, 2167, 1786, 3179, 3222, 1432, 3775, 4747, 1764, 690, 3223, 4684, 890, 2701, 1045, 3034, 1381, 1011, 2150, 4798, 2247, 1334, 3058, 934, 2895, 1484, 2784, 3341, 4412, 1748, 625, 2610, 3488, 4810, 669, 4275, 4929, 1014, 2104, 3111};
        int pills = 122, strength = 3131;
        int i = maxTaskAssign(tasks, words, pills, strength);
        System.out.println(i);
    }

    /**
     * from teacher zuo method
     *
     * @param tasks
     * @param workers
     * @param pills
     * @param strength
     * @return
     */
    public static int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        int[] help = new int[tasks.length];
        int l = 0;
        int r = tasks.length;
        int m, ans = 0;
        Arrays.sort(tasks);
        Arrays.sort(workers);
        while (l <= r) {
            m = (l + r) / 2;
            if (yeah2(tasks, 0, m - 1, workers, workers.length - m, workers.length - 1, strength, help) <= pills) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static int yeah2(int[] tasks, int tl, int tr, int[] workers, int wl, int wr, int strength, int[] help) {
        if (wl < 0) {
            return Integer.MAX_VALUE;
        }
        int l = 0;
        int r = 0;
        int ti = tl;
        int ans = 0;
        // help : 辅助队列，双端队列！双端队列用数组实现！
        for (int wi = wl; wi <= wr; wi++) {
            //       4 6 7
            //       0 1 2
            //           ti   ti    ti
            // 工人  6
            // help : 0 1
            //        0 1 2 3 ...
            //        l   r
            for (; ti <= tr && tasks[ti] <= workers[wi]; ti++) {
                help[r++] = ti;
            }
            if (l < r && tasks[help[l]] <= workers[wi]) {
                l++;
            } else {
                // workers[wi] + strength
                for (; ti <= tr && tasks[ti] <= workers[wi] + strength; ti++) {
                    help[r++] = ti;
                }
                if (l < r) {
                    ans++;
                    r--;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }
        return ans;
    }
}
