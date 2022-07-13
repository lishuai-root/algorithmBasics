package bitOperation;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/5 16:32
 * @version: 1.0
 */
public class TowArrSum {

    private static int[] sortArr = new int[]{2, 4, 5, 7, 3, 4, 6, 8};


    public static void main(String[] args) {
//        int merge = merge(sortArr, 0, 3, sortArr.length - 1, 2, 5);
//        System.out.println(merge);

//        int i = fn_001(sortArr, 3, 5);
//        System.out.println(i);
//        int i = fn_002(sortArr, 3, 5);
//        System.out.println(i);

        test(1000000, -7599, 66298);

    }

    private static void test(int size, int lower, int upper) {

        int[] arr = new int[size];

        double random;

        for (int i = 0; i < size; i++) {
            random = Math.random();
            if (random > 0.5)
                random *= -1;
            arr[i] = (int) (random * (size << 2));
        }

//        for (int i = 0; i < size; i++) {
//            System.out.print(arr[i] + "   ");
//        }
//        System.out.println();


        long start = System.currentTimeMillis();
        int i = fn_001(arr, lower, upper);
        long end = System.currentTimeMillis();

        System.out.println("001 time : " + (end - start));
        System.out.println(i);


        start = System.currentTimeMillis();
        i = countRangeSum(arr, lower, upper);
        end = System.currentTimeMillis();

        System.out.println("countRangeSum time : " + (end - start));
        System.out.println(i);

        start = System.currentTimeMillis();
        i = fn_002(arr, lower, upper);
        end = System.currentTimeMillis();

        System.out.println("002 time : " + (end - start));
        System.out.println(i);


    }

    private static int fn_002(int[] arr, int lower, int upper) {

        int result = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum <= upper && sum >= lower)
                    result++;
            }
        }

        return result;

    }

    /**
     * 给定一个数组，返回该数组中有多少个连续子数组之和在lower和upper之间
     *
     * @param arr
     * @param lower
     * @param upper
     * @return
     */
    private static int fn_001(int[] arr, int lower, int upper) {

        long[] sum = new long[arr.length];

        sum[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {

            sum[i] = arr[i] + sum[i - 1];
        }

//        for (int i = 0; i < sum.length; i++) {
//            System.out.print(sum[i] + "   ");
//        }

        System.out.println();

        return presage(sum, 0, sum.length - 1, lower, upper);
    }

    private static int presage(long[] sum, int l, int r, int lower, int upper) {

        if (l == r) {
            if (sum[l] >= lower && sum[l] <= upper)
                return 1;
            else
                return 0;
        }


        int low = l + (r - l) / 2;

        int left = presage(sum, l, low, lower, upper);

        int right = presage(sum, low + 1, r, lower, upper);

        int mergeNum = merges(sum, l, low, r, lower, upper);

        return left + right + mergeNum;
    }

    private static int merges(long[] arr, int l, int m, int r, int lower, int upper) {


        int wl = l, wr = l, asc = 0;

        for (int i = m + 1; i <= r; i++) {

            long min = arr[i] - upper, max = arr[i] - lower;

            while (wl <= m && arr[wl] < min)
                wl++;

            while (wr <= m && arr[wr] <= max)
                wr++;

            asc += wr - wl;
        }

        long[] prov = new long[r - l + 1];

        int j = m + 1, i = 0, le = l;

        while (l <= m && j <= r) {
            if (arr[l] < arr[j])
                prov[i++] = arr[l++];
            else
                prov[i++] = arr[j++];
        }

        while (l <= m)
            prov[i++] = arr[l++];

        while (j <= r)
            prov[i++] = arr[j++];

        for (int k = 0; k < prov.length; k++) {
            arr[le++] = prov[k];
        }

        return asc;
    }


    //------------------------------------------------------------------------------------------//

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) {
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        int M = L + ((R - L) >> 1);
        return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper)
                + merge(sum, L, M, R, lower, upper);
    }

    public static int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L;
        int windowR = L;
        // [windowL, windowR)
        for (int i = M + 1; i <= R; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (windowR <= M && arr[windowR] <= max) {
                windowR++;
            }
            while (windowL <= M && arr[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }
}





















