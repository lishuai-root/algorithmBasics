package util;

import java.util.Random;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/14 22:14
 * @version: 1.0
 */

public class AlgorithmUtils {

    public static int[] makeArray(int size) {

        int[] ints = new int[size];

        Random r = new Random();


        for (int i = 0; i < size; i++) {

            ints[i] = r.nextInt(size * 10);
        }

        return ints;
    }
}
