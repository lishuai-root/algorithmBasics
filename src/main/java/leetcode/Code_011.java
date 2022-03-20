package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/8/8 21:16
 * @version: 1.0
 */

public class Code_011 {


    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int process = process(i);

            System.out.println("i : " + i + "   p : " + process);
        }

    }

    /**
     * 小虎买苹果问题
     *
     * @param num
     * @return
     */
    public static int process(int num) {

        int yu = 0;

        int maxNum = 0, minNum = 0;

        final int min = 6, max = 8;

        maxNum = num / max;

        yu = num % max;

        while (yu != 0 && maxNum > 0) {

//            if (maxNum > 0) {

            maxNum--;

            yu += max;
//            }

            minNum += yu / min;

            yu %= min;
        }

        if (yu >= 6) {

            minNum += yu / min;

            yu %= min;
        }

        return yu == 0 ? maxNum + minNum : -1;
    }
}
