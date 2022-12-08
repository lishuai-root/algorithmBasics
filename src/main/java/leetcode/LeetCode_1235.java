package leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 * <p>
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
 * <p>
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 * @author: LISHUAI
 * @createDate: 2022/11/30 2:14
 * @version: 1.0
 */

public class LeetCode_1235 {

    private static int[][] cache;
    private static boolean[][] flag;

    public static void main(String[] args) {
//        System.out.println((long) (50000L * 50000L));
//        System.out.println(((long) (50000L * 50000L)) / 1024 / 1024 / 1024 * 4);
//        System.out.println(1024 * 16);
//        int[] startTime = {1, 2, 3, 3}, endTime = {3, 4, 5, 6}, profit = {50, 10, 40, 70};
//        int[] startTime = {5, 1, 2, 3, 3}, endTime = {34, 3, 4, 5, 6}, profit = {140, 50, 10, 40, 70};

//        int[] startTime = {341, 22, 175, 424, 574, 687, 952, 439, 51, 562, 962, 890, 250, 47, 945, 914, 835, 937, 419, 343, 125, 809, 807, 959, 403, 861, 296, 39, 802, 562, 811, 991, 209, 375, 78, 685, 592, 409, 369, 478, 417, 162, 938, 298, 618, 745, 888, 463, 213, 351, 406, 840, 779, 299, 90, 846, 58, 235, 725, 676, 239, 256, 996, 362, 819, 622, 449, 880, 951, 314, 425, 127, 299, 326, 576, 743, 740, 604, 151, 391, 925, 605, 770, 253, 670, 507, 306, 294, 519, 184, 848, 586, 593, 909, 163, 129, 685, 481, 258, 764},
//                endTime = {462, 101, 820, 999, 900, 692, 991, 512, 655, 578, 996, 979, 425, 893, 975, 960, 930, 991, 987, 524, 208, 901, 841, 961, 878, 882, 412, 795, 937, 807, 957, 994, 963, 716, 608, 774, 681, 637, 635, 660, 750, 632, 948, 771, 943, 801, 985, 476, 532, 535, 929, 943, 837, 565, 375, 854, 174, 698, 820, 710, 566, 464, 997, 551, 884, 844, 830, 916, 970, 965, 585, 631, 785, 632, 892, 954, 803, 764, 283, 477, 970, 616, 794, 911, 771, 797, 776, 686, 895, 721, 917, 920, 975, 984, 996, 471, 770, 656, 977, 922},
//                profit = {85, 95, 14, 72, 17, 3, 86, 65, 50, 50, 42, 75, 40, 87, 35, 78, 47, 74, 92, 10, 100, 29, 55, 57, 51, 34, 10, 96, 14, 71, 63, 99, 8, 37, 16, 71, 10, 71, 83, 88, 68, 79, 27, 87, 3, 58, 56, 43, 89, 31, 16, 9, 49, 84, 62, 30, 35, 7, 27, 34, 24, 33, 100, 25, 90, 79, 58, 21, 31, 30, 61, 46, 36, 45, 85, 62, 91, 54, 28, 63, 50, 69, 48, 36, 77, 39, 19, 97, 20, 39, 48, 72, 37, 67, 72, 46, 54, 37, 53, 30};
        int[] startTime = getArr(50000);
        int[] endTime = getArr(50000);
        int[] profit = getArr(50000);
        long start = System.currentTimeMillis();
//        Arrays.sort(startTime);
//        System.out.println(System.currentTimeMillis() - start);
//        System.out.println(startTime[1] + " - " + startTime[56]);
//        int[] startTime = {1, 2, 3, 4, 6}, endTime = {3, 5, 10, 6, 9}, profit = {20, 20, 100, 70, 60};
//        int[] startTime = {1, 1, 1}, endTime = {2, 3, 4}, profit = {5, 6, 4};
//        int i = jobScheduling(startTime, endTime, profit);
//        System.out.println(i);
//        System.out.println((new Solution().jobScheduling(startTime, endTime, profit)));
//        System.out.println(jobScheduling_Info_dp(startTime, endTime, profit));
//        System.out.println(jobScheduling_dp(startTime, endTime, profit));
//        System.out.println(jobScheduling_Info(startTime, endTime, profit));
        Arrays.sort(startTime);
//        System.out.println(startTime[0] + " - " + startTime[1]);
//        int[] mat = new int[startTime.length];
//        for (int i = 0; i < mat.length; i++) {
//            mat[i] = i;
//        }
//        sort(mat, startTime, endTime, profit);
//        System.out.println(startTime[mat[0]] + " - " + startTime[mat[1]]);
    }

    private static int[] getArr(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    public static int jobScheduling_Info(int[] startTime, int[] endTime, int[] profit) {
        Info[] infos = new Info[startTime.length + 1];
        infos[0] = new Info(0, 0, 0);
        for (int i = 0; i < startTime.length; i++) {
            infos[i + 1] = new Info(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(infos, (a, b) -> {
            int c = a.start - b.start;
            if (c == 0) {
                c = a.end - b.end;
            }
            return c;
        });
        return jobScheduling_Info_process(infos, 0, 1);
    }

    private static int jobScheduling_Info_process(Info[] infos, int pre, int cur) {
        if (cur >= infos.length) {
            return 0;
        }
        int p1 = jobScheduling_Info_process(infos, pre, cur + 1);
        int p2 = 0;
        if (infos[pre].end <= infos[cur].start) {
            p2 = jobScheduling_Info_process(infos, cur, cur + 1) + infos[cur].profit;
        }
        return Math.max(p2, p1);
    }

    public static int jobScheduling_Info_dp(int[] startTime, int[] endTime, int[] profit) {
        Info[] infos = new Info[startTime.length];
        for (int i = 0; i < startTime.length; i++) {
            infos[i] = new Info(startTime[i], endTime[i], profit[i]);
        }
//        Arrays.sort(infos, (a, b) -> {
//            int c = a.start - b.start;
//            if (c == 0) {
//                c = a.end - b.end;
//            }
//            return c;
//        });
        sortInfo(infos);
        int len = infos.length, ans = 0;
//        dp[0] = infos[0].profit;
        System.out.println("------------");
        for (int i = 0; i < len; i++) {
//            dp[i] = infos[i].profit;
//            ans = Math.max(ans, dp[i]);
            ans = Math.max(ans, infos[i].profit);
            for (int j = i - 1; j >= 0; j--) {
                if (infos[i].start >= infos[j].end) {
//                    dp[i] = Math.max(dp[i], dp[j] + infos[i].profit);
//                    dp[i] = Math.max(dp[i], dp[j] + infos[i].profit);
//                    ans = Math.max(ans, dp[i]);
                    infos[i].profit = infos[j].profit + infos[i].profit;
                    ans = Math.max(ans, infos[i].profit);
                    break;
                }

            }
//            infos[i].profit = ans;
            Arrays.sort(infos, 0, i + 1, (a, b) -> {
                int c = a.profit - b.profit;
                if (c == 0) {
                    c = b.end - a.end;
                }
                return c;
            });
        }
        return ans;
    }

//    private static int getMid(Info[] infos, int left, int right, int target) {
//        int mid = -1;
//        while (left <= right) {
//            mid = (left + right) >> 1;
//            if (mid )
//        }
//    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[] mat = new int[startTime.length];
        for (int i = 0; i < mat.length; i++) {
            mat[i] = i;
        }
//        long start = System.currentTimeMillis();
        sort(mat, startTime, endTime, profit);
//        System.out.println(System.currentTimeMillis() - start);
        cache = new int[startTime.length][startTime.length];
        flag = new boolean[startTime.length][startTime.length];
        return jobSchedulingProcess(mat, startTime, endTime, profit, -1, 0);
    }

    private static int jobSchedulingProcess(int[] mat, int[] startTime, int[] endTime, int[] profit, int pre, int cur) {
        if (cur >= startTime.length) {
            return 0;
        }
        if (flag[pre + 1][cur]) {
            return cache[pre + 1][cur];
        }
        int p1 = 0, p2 = 0;
        p1 = jobSchedulingProcess(mat, startTime, endTime, profit, pre, cur + 1);
        if (pre == -1 || endTime[mat[pre]] <= startTime[mat[cur]]) {
            p2 = jobSchedulingProcess(mat, startTime, endTime, profit, cur, cur + 1) + profit[mat[cur]];
        }
        cache[pre + 1][cur] = Math.max(p1, p2);
        flag[pre + 1][cur] = true;
        return Math.max(p1, p2);
    }

    public static int jobScheduling_dp(int[] startTime, int[] endTime, int[] profit) {
        startTime = arrayIncremental(startTime);
        endTime = arrayIncremental(endTime);
        profit = arrayIncremental(profit);
        int len = startTime.length;
        int[] mat = new int[len];
        for (int i = 0; i < mat.length; i++) {
            mat[i] = i;
        }
        long start = System.currentTimeMillis();
        sort(mat, startTime, endTime, profit);
//        System.out.println(System.currentTimeMillis() - start);
//        System.out.println(Runtime.getRuntime().maxMemory() >> 20);
//        System.out.println(Runtime.getRuntime().totalMemory() >> 20);
//        System.out.println(Runtime.getRuntime().freeMemory() >> 20);
        int[][] dp = new int[len + 1][len + 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j > i; j--) {
                int p1 = dp[i][j + 1];
                int p2 = 0;
                if (endTime[mat[i]] <= startTime[mat[j]]) {
                    p2 = dp[j][j + 1] + profit[mat[j]];
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[0][1];
    }

    private static int[] arrayIncremental(int[] arr) {
        int len = arr.length;
        int[] newArr = new int[len + 1];
        System.arraycopy(arr, 0, newArr, 1, len);
        return newArr;
    }

    public static void sortInfo(Info[] arr) {
        sortInfo(arr, 0, arr.length - 1);
    }

    private static void sortInfo(Info[] arr, int lo, int hi) {
        if (hi <= lo)  //   当起始下标和结束下标相等时，数组只有一个元素，天然有序
            return;
        int j = partitionInfo(arr, lo, hi);     // 拆分数组，返回分割元素的下标
        sortInfo(arr, lo, j - 1);   //  对左子数组进行拆分排序
        sortInfo(arr, j + 1, hi);   //  对右子数组进行差分排序
    }

    private static int partitionInfo(Info[] arr, int lo, int hi) {
        int i = lo, j = hi + 1;     //  i作为左指针，从左侧开始遍历数组。 j作为右指针，从右侧开始遍历数组
        int mid = (lo + hi) >>> 1;
//        int v = arr[mid];    //  使用数组的第一个元素作为分割元素
        while (true) {
//            while (arr[++i] < v) if (i == hi) break;    //  从左侧开始查找大于分割元素的数组元素
//            while (arr[--j] > v) /*if (j == lo) break*/ ;    //  从右侧开始查找小于分割元素的数组元素
            while (compareInfo(arr, ++i, mid) < 0) if (i == hi) break;
            while (compareInfo(arr, --j, mid) > 0) /*if (j == lo) break*/ ;
            if (i >= j) break;  //  当左右指针相遇时，就是遍历一边数组结束。此次拆分结束
            //  将左侧寻找到的大于分割元素的数组元素和右侧寻找到的小于分割元素的数组元素，交换位置。
            //  使小于分割元素或大于分割元素的数组元素各处于数组的一侧
            lessInfo(arr, i, j);
        }
        lessInfo(arr, mid, j);   //  遍历一边数组之后将分割元素放到合适的位置。使分割元素的左侧都小于分割元素，右侧都大于分割元素
        return j;
    }

    private static void lessInfo(Info[] arr, int lo, int hi) {
        if (lo >= hi || lo < 0 || hi >= arr.length)
            return;
        Info info = arr[lo];
        arr[lo] = arr[hi];
        arr[hi] = info;
    }

    private static int compareInfo(Info[] infos, int l, int r) {
        int c = infos[l].start - infos[r].start;
        if (c == 0) {
            c = infos[l].end - infos[r].end;
        }
        return c;
    }

    public static void sort(int[] arr, int[] startTime, int[] endTime, int[] profit) {
        sort(arr, 0, arr.length - 1, startTime, endTime, profit);
    }

    private static void sort(int[] arr, int lo, int hi, int[] startTime, int[] endTime, int[] profit) {
        if (hi <= lo)  //   当起始下标和结束下标相等时，数组只有一个元素，天然有序
            return;
        int j = partition(arr, lo, hi, startTime, endTime, profit);     // 拆分数组，返回分割元素的下标
        sort(arr, lo, j - 1, startTime, endTime, profit);   //  对左子数组进行拆分排序
        sort(arr, j + 1, hi, startTime, endTime, profit);   //  对右子数组进行差分排序
    }

    private static int partition(int[] arr, int lo, int hi, int[] startTime, int[] endTime, int[] profit) {
        int i = lo, j = hi + 1;     //  i作为左指针，从左侧开始遍历数组。 j作为右指针，从右侧开始遍历数组
        int mid = (lo + hi) >>> 1;
        int v = arr[mid];    //  使用数组的第一个元素作为分割元素
        while (true) {
//            while (arr[++i] < v) if (i == hi) break;    //  从左侧开始查找大于分割元素的数组元素
//            while (arr[--j] > v) /*if (j == lo) break*/ ;    //  从右侧开始查找小于分割元素的数组元素
            while (compare(startTime, endTime, profit, arr[++i], v) < 0) if (i == hi) break;
            while (compare(startTime, endTime, profit, arr[--j], v) > 0) /*if (j == lo) break*/ ;
            if (i >= j) break;  //  当左右指针相遇时，就是遍历一边数组结束。此次拆分结束
            //  将左侧寻找到的大于分割元素的数组元素和右侧寻找到的小于分割元素的数组元素，交换位置。
            //  使小于分割元素或大于分割元素的数组元素各处于数组的一侧
            less(arr, i, j);
        }
        less(arr, mid, j);   //  遍历一边数组之后将分割元素放到合适的位置。使分割元素的左侧都小于分割元素，右侧都大于分割元素
        return j;
    }

    private static void less(int[] arr, int lo, int hi) {
        if (lo >= hi || lo < 0 || hi >= arr.length)
            return;
//        int m = 0;
//        m = arr[lo];
//        arr[lo] = arr[hi];
//        arr[hi] = m;
        arr[lo] = arr[lo] ^ arr[hi];
        arr[hi] = arr[lo] ^ arr[hi];
        arr[lo] = arr[lo] ^ arr[hi];
    }

    private static int compare(int[] startTime, int[] endTime, int[] profit, int l, int r) {
        int c = startTime[l] - startTime[r];
        if (c == 0) {
            c = endTime[l] - endTime[r];
        }
        return c;
    }

    static class Info {
        public int start, end, profit;

        public Info(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

    static class Solution {
        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            int n = profit.length;
            Job[] jobs = new Job[n];
            for (int i = 0; i < startTime.length; i++) {
                jobs[i] = (new Job(startTime[i], endTime[i], profit[i]));
            }

            int dp[] = new int[jobs.length];
            Arrays.sort(jobs, (a, b) -> (a.end - b.end));

            dp[0] = jobs[0].profit;
            for (int i = 1; i < jobs.length; i++) {
                dp[i] = Math.max(jobs[i].profit, dp[i - 1]);
                for (int j = i - 1; j >= 0; j--) {
                    if (jobs[j].end <= jobs[i].start) {
                        dp[i] = Math.max(dp[i], jobs[i].profit + dp[j]);
                        break;
                    }
                }
            }
            int max = Integer.MIN_VALUE;
            for (int val : dp) {
                max = Math.max(val, max);
            }
            return max;
        }

        class Job {
            public int start, end, profit;

            public Job(int s, int e, int p) {
                this.start = s;
                this.end = e;
                this.profit = p;
            }
        }
    }
}
