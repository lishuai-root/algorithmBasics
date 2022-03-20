package leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/8/12 19:31
 * @version: 1.0
 */

public class LeetCode_149 {

    public static void main(String[] args) {

//        int[][] ints = {{7, 3}, {19, 19}, {-16, 3}, {13, 17}, {-18, 1}, {-18, -17}, {13, -3}, {3, 7}, {-11, 12}, {7, 19}, {19, -12}, {20, -18}, {-16, -15}, {-10, -15}, {-16, -18}, {-14, -1}, {18, 10}, {-13, 8}, {7, -5}, {-4, -9}, {-11, 2}, {-9, -9}, {-5, -16}, {10, 14}, {-3, 4}, {1, -20}, {2, 16}, {0, 14}, {-14, 5}, {15, -11}, {3, 11}, {11, -10}, {-1, -7}, {16, 7}, {1, -11}, {-8, -3}, {1, -6}, {19, 7}, {3, 6}, {-1, -2}, {7, -3}, {-6, -8}, {7, 1}, {-15, 12}, {-17, 9}, {19, -9}, {1, 0}, {9, -10}, {6, 20}, {-12, -4}, {-16, -17}, {14, 3}, {0, -1}, {-18, 9}, {-15, 15}, {-3, -15}, {-5, 20}, {15, -14}, {9, -17}, {10, -14}, {-7, -11}, {14, 9}, {1, -1}, {15, 12}, {-5, -1}, {-17, -5}, {15, -2}, {-12, 11}, {19, -18}, {8, 7}, {-5, -3}, {-17, -1}, {-18, 13}, {15, -3}, {4, 18}, {-14, -15}, {15, 8}, {-18, -12}, {-15, 19}, {-9, 16}, {-9, 14}, {-12, -14}, {-2, -20}, {-3, -13}, {10, -7}, {-2, -10}, {9, 10}, {-1, 7}, {-17, -6}, {-15, 20}, {5, -17}, {6, -6}, {-11, -8}};

        int[][] ints = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};

        int i = maxPoints(ints);

        System.out.println(i);

//        Random r = new Random();
//
//        int x, y;
//
//        int[][] ints = new int[100][2];
//
//        for (int i = 0; ; i++) {
//
//
//            for (int j = 0; j < ints.length; j++) {
//
//                x = r.nextInt(10000);
//
//                y = r.nextInt(10000);
//
//                if (Math.random() > 0.5)
//
//                    ints[j][0] = x * -1;
//
//                if (Math.random() > 0.5)
//
//                    ints[j][1] = y * -1;
//
//            }
//
//            int m = maxPoints(ints);
//
//            int i1 = maxPoints_02(ints);
//
//            if (m != i1) {
//
//                System.out.println("error m : " + m + "    i1 + : " + i1);
//            }
//
//        }

    }

    /**
     * 错误的
     *
     * @param points
     * @return
     */
    public static int maxPoints(int[][] points) {

        if (points.length == 0)
            return 0;

        if (points.length == 1)
            return 1;

        int result = 0, x, y, g;

        String res = "";

        HashMap<String, Integer> map = new HashMap<>();

        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < points.length; i++) {

            set.clear();

//            map.clear();

//            System.out.println("-------------------");

            for (int j = 0; j < i; j++) {

//                res = (Math.abs(points[i][1]) - Math.abs(points[j][1])) * 1.00 / ((Math.abs(points[i][0]) - Math.abs(points[j][0])) * 1.00);

                x = points[j][0] - points[i][0];

                y = points[j][1] - points[i][1];

                if (x == 0) {

                    res = "-" + points[j][0];
                } else if (y == 0) {

                    res = "=" + points[j][1];
                } else {
                    g = gcd(x, y);

                    res = (x / g + "/" + y / g) + (points[i][1] + points[i][0]);
                }


                System.out.println("i :" + i + "   j : " + j);

                System.out.println("res : " + res);

                if (!set.contains(res)) {

                    map.put(res, map.getOrDefault(res, 1) + 1);

                    result = Math.max(result, map.get(res));

                    set.add(res);
                }

                System.out.println("max : " + map.get(res));

                System.out.println("---------------");
            }

        }

        return result;
    }


    //  获取最大公约数
    public static int gcd(int x, int y) {
        if (y == 0)
            return x;
        else
            return gcd(y, x % y);
    }


    public static int maxPoints_02(int[][] points) {

        if (points.length == 0)
            return 0;

        if (points.length == 1)
            return 1;

        int result = 0, x, y, g;

        String res = "";

        HashMap<String, Integer> map = new HashMap<>();

        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < points.length; i++) {

//            set.clear();

            map.clear();

//            System.out.println("-------------------");

            for (int j = i + 1; j < points.length; j++) {

//                res = (Math.abs(points[i][1]) - Math.abs(points[j][1])) * 1.00 / ((Math.abs(points[i][0]) - Math.abs(points[j][0])) * 1.00);

                x = points[j][0] - points[i][0];

                y = points[j][1] - points[i][1];

                if (x == 0) {

                    res = "-";
                } else if (y == 0) {

                    res = "=";
                } else {
                    g = gcd(x, y);

                    res = x / g + "/" + y / g;
                }


                System.out.println("i :" + i + "   j : " + j);

                System.out.println("res : " + res);

//                if (!set.contains(res)) {

                map.put(res, map.getOrDefault(res, 1) + 1);

                result = Math.max(result, map.get(res));

//                    set.add(res);
//                }

                System.out.println("max : " + map.get(res));

                System.out.println("---------------");
            }

        }

        return result;
    }
}


