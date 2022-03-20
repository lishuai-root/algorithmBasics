package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/21 18:58
 * @version: 1.0
 */

public class LeetCode_60 {

    public static void main(String[] args) throws InterruptedException {
//        String permutation = new LeetCode_60().getPermutation(9, 28533);
//
//        System.out.println(permutation);

//        new LeetCode_60().fn_001();
//        fn_002();

        String permutation_03 = getPermutation_03(5, 14);

        System.out.println(permutation_03);

        permutation_03 = getPermutation_03(5, 15);

        System.out.println(permutation_03);

        permutation_03 = getPermutation_03(5, 16);

        System.out.println(permutation_03);

        permutation_03 = getPermutation_03(5, 17);

        System.out.println(permutation_03);

        permutation_03 = getPermutation_03(5, 24);

        System.out.println(permutation_03);
        permutation_03 = getPermutation_03(5, 25);

        System.out.println(permutation_03);
    }

    private static void fn_002() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {

            long start = 0, time = 0;
            for (int i = 0; i < 1000; i++) {

                int v = Math.max((int) (Math.random() * 12), 2);

                int sum = 1;

                for (int j = 1; j <= v; j++) {
                    sum *= j;
                }

                int k = new Random().nextInt(sum - 1) + 1;

                start = System.currentTimeMillis();
                String str1 = getPermutation_03(v, k);
                time += System.currentTimeMillis() - start;

            }

            System.out.println("time_001 : " + time);

            latch.countDown();

            LockSupport.park();

        });
        t1.start();

        Thread t2 = new Thread(() -> {
            long start = 0, time = 0;
            for (int i = 0; i < 1000; i++) {

                int v = Math.max((int) (Math.random() * 12), 2);

                int sum = 1;

                for (int j = 1; j <= v; j++) {
                    sum *= j;
                }

                int k = new Random().nextInt(sum - 1) + 1;

                start = System.currentTimeMillis();
                String str1 = getPermutation(v, k);
                time += System.currentTimeMillis() - start;

            }

            System.out.println("time_002 : " + time);

            latch.countDown();

            LockSupport.park();
        });
        t2.start();

        latch.await();

        LockSupport.unpark(t1);

        LockSupport.unpark(t2);

    }

    public static String getPermutation_03(int n, int k) {


        boolean[] bs = new boolean[n + 1];

        int j = n, all = 1;

        for (int i = 1; i < n; i++) {
            all *= i;
        }

        StringBuffer buffer = new StringBuffer();

        int jump = 0, yu = k;

        while (true) {

            jump = yu / all;

            yu = yu % all;

            if (yu == 0) {
                int m = 0;

                for (int i = 1; i <= n; i++) {
                    if (!bs[i])
                        m++;

                    if (m == jump) {
                        buffer.append(i);
                        bs[i] = true;
                        break;
                    }
                }

                for (int i = n; i > 0; i--) {
                    if (!bs[i]) {
                        buffer.append(i);
                        bs[i] = true;
                    }
                }

                break;
            }

            if (yu != 0) {
                int index = 0;

                for (int i = 1; i <= n; i++) {
                    if (!bs[i])
                        index++;

                    if (index > jump) {
                        buffer.append(i);
                        bs[i] = true;
                        break;
                    }
                }

            }

            all /= --j;

        }

        return buffer.toString();
    }

    public static String getPermutation(int n, int k) {

        int[] arr = new int[n];

        boolean[] bs = new boolean[n];

        for (int i = 0; i < n; i++) {

            arr[i] = i + 1;
        }

        String str = "";

        List<String> list = new ArrayList<>();

        process(arr, list, bs, str);

        Collections.sort(list);

        return list.get(k - 1);
    }

    public static void process(int[] arr, List<String> list, boolean[] bs, String str) {

        if (str.length() == arr.length)
            list.add(str);

        for (int i = 0; i < arr.length; i++) {

            if (!bs[i]) {

                bs[i] = true;

                process(arr, list, bs, str + arr[i]);

                bs[i] = false;
            }

        }

    }

    private void fn_001() {

        long time_001 = 0, time_002 = 0, start_001, start_002;

        for (int i = 0; i < 1000; i++) {

            int v = Math.max((int) (Math.random() * 20), 2);

            int sum = 1;

            for (int j = 1; j <= v; j++) {
                sum *= j;
            }

            int k = new Random().nextInt(sum - 1) + 1;

            start_001 = System.currentTimeMillis();
            String str1 = getPermutation_03(v, k);
            time_001 += System.currentTimeMillis() - start_001;

            start_002 = System.currentTimeMillis();
            String str2 = getPermutation(v, k);
            time_002 += System.currentTimeMillis() - start_002;

            if (!str1.equals(str2)) {

                System.out.println("str1 : " + str1);

                System.out.println("str2 : " + str2);

                System.out.println();
            }
        }

        System.out.println("time_001 : " + (time_001));

        System.out.println("time_002 : " + (time_002));

    }

    public String getPermutation_05(int n, int k) {


        boolean[] bs = new boolean[n + 1];

        int j = n, all = 1;

        for (int i = 1; i < n; i++) {
            all *= i;
        }

        StringBuffer buffer = new StringBuffer();

        int jump = 0, yu = k;

        while (true) {

            jump = yu / all;

            yu = yu % all;

            if (yu == 0) {
                int m = 0;

                for (int i = 1; i <= n; i++) {
                    if (!bs[i])
                        m++;


                    if (m == jump) {
                        buffer.append(i);
                        bs[i] = true;
                        break;
                    }
                }


                for (int i = n; i > 0; i--) {
                    if (!bs[i]) {
                        buffer.append(i);
                        bs[i] = true;
                    }
                }

                break;
            } else {
                int index = 0;

                for (int i = 1; i <= n; i++) {
                    if (!bs[i])
                        index++;

                    if (index > jump) {
                        buffer.append(i);
                        bs[i] = true;
                        break;
                    }
                }
                if (yu == 1) {

                    for (int i = 1; i <= n; i++) {
                        if (!bs[i]) {
                            buffer.append(i);
                            bs[i] = true;
                        }
                    }

                    break;
                }

            }

            all /= --j;

        }

        return buffer.toString();
    }

    public String getPermutation_04(int n, int k) {


        boolean[] bs = new boolean[n + 1];

        int j = n, all = 1;

        for (int i = 1; i < n; i++) {
            all *= i;
        }

        StringBuffer buffer = new StringBuffer();

        int jump = 0, yu = k;

        while (true) {

            jump = yu / all;

            yu = yu % all;
//            System.out.println("jump : " + jump);
//            System.out.println("yu : " + yu);
//            System.out.println("all : " + all);

            if (yu == 0) {
                int m = 0;

                for (int i = 1; i <= n; i++) {
                    if (!bs[i])
                        m++;

                    if (m == jump) {
                        buffer.append(i);
                        bs[i] = true;
                        break;
                    }
                }

                for (int i = n; i > 0; i--) {
                    if (!bs[i]) {
                        buffer.append(i);
                        bs[i] = true;
                    }
                }

                break;
            }

            if (yu != 0) {
                int index = 0;

                for (int i = 1; i <= n; i++) {
                    if (!bs[i])
                        index++;

                    if (index > jump) {
                        buffer.append(i);
                        bs[i] = true;
                        break;
                    }
                }


//                buffer.append(index);
//                bs[index] = true;
            }

            all /= --j;
//            System.out.println("str : " + str);
//            System.out.println();
        }

        return buffer.toString();
    }

    public String getPermutation_02(int n, int k) {

        int[] arr = new int[n];

        boolean[] bs = new boolean[n];

        int j = n, all = 1;

        for (int i = 1; i <= n; i++) {

            arr[i - 1] = i;

            all *= i;
        }

        all /= n;

        StringBuffer buffer = new StringBuffer();

        int jump = 0, yu = k;

        while (true) {

            jump = yu / all;

            yu = yu % all;
//            System.out.println("jump : " + jump);
//            System.out.println("yu : " + yu);
//            System.out.println("all : " + all);

            if (yu == 0) {
                int m = 0;

                for (int i = 0; i < n; i++) {
                    if (!bs[i])
                        m++;

                    if (m == jump) {
                        buffer.append(arr[i]);
                        bs[i] = true;
                        break;
                    }
                }

                for (int i = n - 1; i >= 0; i--) {
                    if (!bs[i]) {
                        buffer.append(arr[i]);
                        bs[i] = true;
                    }
                }

                break;
            }

            if (yu != 0) {
                int index = 0;

                while (true) {

                    while (bs[index])
                        index++;

                    if (!bs[index] && jump > 0) {
                        jump--;
                        index++;
                    }

                    while (bs[index])
                        index++;

                    if (jump == 0)
                        break;
                }


                buffer.append(arr[index]);
                bs[index] = true;
            }

            all /= --j;
//            System.out.println("str : " + str);
//            System.out.println();
        }

        return buffer.toString();
    }
}

