package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/24 21:19
 * @version: 1.0
 */

public class Code_005 {


    public static void main(String[] args) {
        int i = fn_001(999);

        int process = process(999);

        System.out.println(i);

        System.out.println(process);
    }

    private static int fn_001(int num) {

        StringBuffer buffer = new StringBuffer();

        for (int i = 1; i <= num; i++) {
            buffer.append(i);
        }

        String str = buffer.toString();

        int len = str.length(), result = 0;

        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == '1')
                result++;
        }

        return result;
    }

    public static int process(int num) {

        int result = 0, n = 0, m = 0;

        n = num / 100;

        System.out.println("n : " + n);

        m = num % 100;

        System.out.println("m : " + m);

//        result = n >= 1 ? n * 20 + 1 + m : n * 20;

        if (n >= 2)
            result += n * 20 + 100;
        else if (n >= 1)
            result += n * 20 + 1 + m;
        System.out.println("result : " + result);

        if (m >= 20)
            result = result + 11 + (m / 10 - 2);
        else if (m >= 10)
            result = result + 1 + (m - 10);
        else if (m >= 1)
            result++;

        return result;
    }
}
