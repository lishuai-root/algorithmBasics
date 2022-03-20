package leetcode;

import java.util.Arrays;

/**
 * @description: 给定一个数组，偶数长度。数组分为两部分。arr[0, len / 2], arr[(len / 2) + 1, len]
 * <p>
 * 将数组的两部分调整成为 arr[0, (len / 2) + 1, 2, (len / 2) + 2...]
 * @author: LISHUAI
 * @createDate: 2021/6/29 21:37
 * @version: 1.0
 */

public class Code_009 {

    public static void main(String[] args) {

        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        process(arr);

        printArray(arr);

//        fn_002();

    }

    private static void fn_002() {
        for (int i = 0; i < 5000000; i++) {
            int[] arr = generateArray();
            int[] nums = new int[arr.length];

            for (int j = 0; j < arr.length; j++) {
                nums[j] = arr[j];
            }

            printArray(nums);
            wiggleSort(arr);
            process(nums);
            if (!isValidWiggle(arr)) {
                System.out.println("ooops!");
                printArray(arr);
                break;
            }

            if (!isValidWiggle(nums)) {
                System.out.println("ooops!");
                printArray(arr);
                break;
            }
        }
    }

    private static void fn_001() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        process(arr);

        for (int i : arr)
            System.out.print(i + "   ");
    }

    /**
     * 这个方法是我自己写的，其他方法都是左大神写的
     *
     * @param arr
     */
    private static void process(int[] arr) {

        if (arr == null || arr.length < 2 || (arr.length & 1) != 0)
            return;

        int lens = arr.length >> 1;

        System.out.println("lens : " + lens);

        arr[lens] = arr[lens] ^ arr[0];
        arr[0] = arr[lens] ^ arr[0];
        arr[lens] = arr[lens] ^ arr[0];

        arr[lens] = arr[lens] ^ arr[1];
        arr[1] = arr[lens] ^ arr[1];
        arr[lens] = arr[lens] ^ arr[1];

        int s = arr.length - ((lens + 1) >> 1);
        System.out.println("s : " + s);
//        s = (lens & 1) == 0 ? s - 1 : s;

        for (int i = lens + 1; i < arr.length; i++) {

            if (i < s) {
                arr[i] = arr[i] ^ arr[(i - lens) << 1];
                arr[(i - lens) << 1] = arr[i] ^ arr[(i - lens) << 1];
                arr[i] = arr[i] ^ arr[(i - lens) << 1];

                arr[i - 1] = arr[i - 1] ^ arr[((i - lens) << 1) + 1];
                arr[((i - lens) << 1) + 1] = arr[i - 1] ^ arr[((i - lens) << 1) + 1];
                arr[i - 1] = arr[i - 1] ^ arr[((i - lens) << 1) + 1];

//                arr[i] = arr[i] ^ arr[i - 1];
//                arr[i - 1] = arr[i] ^ arr[i - 1];
//                arr[i] = arr[i] ^ arr[i - 1];
            } else {
                arr[i] = arr[i] ^ arr[(i - lens) << 1];
                arr[(i - lens) << 1] = arr[i] ^ arr[(i - lens) << 1];
                arr[i] = arr[i] ^ arr[(i - lens) << 1];
            }

        }

    }


    // 数组的长度为len，调整前的位置是i，返回调整之后的位置
    // 下标不从0开始，从1开始
    public static int modifyIndex1(int i, int len) {
        if (i <= len / 2) {
            return 2 * i;
        } else {
            return 2 * (i - (len / 2)) - 1;
        }
    }

    // 数组的长度为len，调整前的位置是i，返回调整之后的位置
    // 下标不从0开始，从1开始
    public static int modifyIndex2(int i, int len) {
        return (2 * i) % (len + 1);
    }

    // 主函数
    // 数组必须不为空，且长度为偶数
    public static void shuffle(int[] arr) {
        if (arr != null && arr.length != 0 && (arr.length & 1) == 0) {
            shuffle(arr, 0, arr.length - 1);
        }
    }

    // 在arr[L..R]上做完美洗牌的调整（arr[L..R]范围上一定要是偶数个数字）
    public static void shuffle(int[] arr, int L, int R) {
        while (R - L + 1 > 0) { // 切成一块一块的解决，每一块的长度满足(3^k)-1
            int len = R - L + 1;
            int base = 3;
            int k = 1;
            // 计算小于等于len并且是离len最近的，满足(3^k)-1的数
            // 也就是找到最大的k，满足3^k <= len+1
            while (base <= (len + 1) / 3) { // base > (N+1)/3
                base *= 3;
                k++;
            }
            // 3^k -1
            // 当前要解决长度为base-1的块，一半就是再除2
            int half = (base - 1) / 2;
            // [L..R]的中点位置
            int mid = (L + R) / 2;
            // 要旋转的左部分为[L+half...mid], 右部分为arr[mid+1..mid+half]
            // 注意在这里，arr下标是从0开始的
            rotate(arr, L + half, mid, mid + half);
            // 旋转完成后，从L开始算起，长度为base-1的部分进行下标连续推
            cycles(arr, L, base - 1, k);
            // 解决了前base-1的部分，剩下的部分继续处理
            L = L + base - 1; // L ->     [] [+1...R]
        }
    }

    // 从start位置开始，往右len的长度这一段，做下标连续推
    // 出发位置依次为1,3,9...
    public static void cycles(int[] arr, int start, int len, int k) {
        // 找到每一个出发位置trigger，一共k个
        // 每一个trigger都进行下标连续推
        // 出发位置是从1开始算的，而数组下标是从0开始算的。
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int preValue = arr[trigger + start - 1];
            int cur = modifyIndex2(trigger, len);
            while (cur != trigger) {
                int tmp = arr[cur + start - 1];
                arr[cur + start - 1] = preValue;
                preValue = tmp;
                cur = modifyIndex2(cur, len);
            }
            arr[cur + start - 1] = preValue;
        }
    }

    // [L..M]为左部分，[M+1..R]为右部分，左右两部分互换
    public static void rotate(int[] arr, int L, int M, int R) {
        reverse(arr, L, M);
        reverse(arr, M + 1, R);
        reverse(arr, L, R);
    }

    // [L..R]做逆序调整
    public static void reverse(int[] arr, int L, int R) {
        while (L < R) {
            int tmp = arr[L];
            arr[L++] = arr[R];
            arr[R--] = tmp;
        }
    }

    public static void wiggleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        // 假设这个排序是额外空间复杂度O(1)的，当然系统提供的排序并不是，你可以自己实现一个堆排序
        Arrays.sort(arr);
        if ((arr.length & 1) == 1) {
            shuffle(arr, 1, arr.length - 1);
        } else {
            shuffle(arr, 0, arr.length - 1);
            for (int i = 0; i < arr.length; i += 2) {
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }
        }
    }

    // for test
    public static boolean isValidWiggle(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if ((i & 1) == 1 && arr[i] < arr[i - 1]) {
                return false;
            }
            if ((i & 1) == 0 && arr[i] > arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static int[] generateArray() {
        int len = (int) (Math.random() * 10) * 2;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }


}
