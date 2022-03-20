package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/24 19:32
 * @version: 1.0
 */

public class Code_004 {

    public static void main(String[] args) {
//        int i = fn_001(100);

        long start, end;

        start = System.currentTimeMillis();
        int ways = ways(100);
        end = System.currentTimeMillis();

        System.out.println("ways : " + (end - start));

        start = System.currentTimeMillis();
        int i1 = process_002(100);
        end = System.currentTimeMillis();

        System.out.println("process_002 : " + (end - start));

//        System.out.println(i);
        System.out.println(ways);
        System.out.println(i1);
    }

    private static int fn_001(int num) {

        if (num <= 0)
            return 0;

        if (num == 1)
            return 1;

        return process(1, num);
    }

    private static int process(int prve, int rest) {

        if (prve == rest || rest == 0)
            return 1;

        if (rest < prve)
            return 0;

        int size = 0;

        for (int i = prve; i <= rest; i++) {

            size += process(i, rest - i);
        }

        return size;
    }

    private static int process_002(int num) {

        if (num < 0)
            return 0;

        if (num == 1)
            return 1;

        int[][] arr = new int[num + 1][num + 1];

        for (int i = 0; i <= num; i++) {

            arr[i][0] = 1;
            arr[i][i] = 1;
        }

        for (int prve = num - 1; prve > 0; prve--) {

            for (int j = prve + 1; j <= num; j++) {

                int size = 0;

                for (int i = prve; i <= j; i++) {

                    size += arr[i][j - i];
                }

                arr[prve][j] = size;
            }
        }


        return arr[1][num];
    }

    public static int ways(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process1(1, n);
    }

    // 上一个拆出来的数是pre
    // 还剩rest需要去拆
    // 返回拆解的方法数
    public static int process1(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int first = pre; first <= rest; first++) {
            ways += process(first, rest - first);
        }
        return ways;
    }
}
