package bitOperation;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/5 22:07
 * @version: 1.0
 * 归并排序附加题、随机快速排序   02:10:00
 */
public class TowArrSum_002 {

    public static void main(String[] args) {

    }

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

}
