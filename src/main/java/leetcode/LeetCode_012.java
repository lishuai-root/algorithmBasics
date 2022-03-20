package leetcode;

import java.util.Map;

/**
 * @description: Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII,
 * which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However,
 * the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four. The same principle applies to the number nine,
 * which is written as IX. There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral.
 * @author: LISHUAI
 * @createDate: 2022/2/13 19:47
 * @version: 1.0
 */

public class LeetCode_012 {

//    private static int[] arr = {1, 5, 10, 50, 100, 500, 1000};

    private static Map<Integer, String> mapTable;
    private static String[] strs = new String[1001];

    static {
//        mapTable = new HashMap<>();
//        mapTable.put(0, "");
//        mapTable.put(1, "I");
//        mapTable.put(5, "V");
//        mapTable.put(10, "X");
//        mapTable.put(50, "L");
//        mapTable.put(100, "C");
//        mapTable.put(500, "D");
//        mapTable.put(1000, "M");

        strs[0] = "";
        strs[1] = "I";
        strs[5] = "V";
        strs[10] = "X";
        strs[50] = "L";
        strs[100] = "C";
        strs[500] = "D";
        strs[1000] = "M";
    }

    public static void main(String[] args) {
        int num = 1994;

        String s = intToRoman_02(num);

        System.out.println(s);
        System.out.println(Integer.MAX_VALUE);
    }

    public static String intToRoman(int num) {

        int yu, chu, index = 1000;
        StringBuffer sb = new StringBuffer();

        while (num / index < 0) {
            index /= 10;
        }
        while (num != 0) {

            yu = num % index;
            chu = num / index;

            if (chu >= 5) {

                if (chu == 9) {
                    sb.append(mapTable.get(index))
                            .append(mapTable.get(index * 10));
                    chu = 0;
                } else {

                    sb.append(mapTable.get(index * 5));
                    chu = chu % 5;
                }
            } else {

                if (chu == 4) {
                    sb.append(mapTable.get(index))
                            .append(mapTable.get(index * 5));
                    chu = 0;
                } else {

                    chu = chu % 4;
                }
            }

            while (chu > 0) {
                sb.append(mapTable.get(index));
                chu--;
            }

            num = yu;
            index /= 10;
        }

        return sb.toString();
    }

    public static String intToRoman_02(int num) {

        int yu, chu, index = 1000;
        StringBuffer sb = new StringBuffer();
        while (num / index < 0) {
            index /= 10;
        }

        while (num != 0) {

            yu = num % index;
            chu = num / index;

            if (chu == 9) {

                sb.append(strs[index])
                        .append(strs[index * 10]);
                chu = 0;
            } else if (chu == 4) {

                sb.append(strs[index])
                        .append(strs[index * 5]);
                chu = 0;
            }

            sb.append(strs[index * 5 * (chu / 5)]);
            chu = chu % 5;

            while (chu > 0) {
                sb.append(strs[index]);
                chu--;
            }

            num = yu;
            index /= 10;
        }

        return sb.toString();
    }
}
