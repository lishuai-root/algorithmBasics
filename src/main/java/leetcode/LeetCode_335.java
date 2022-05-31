package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given an array of integers distance.
 * <p>
 * You start at point (0,0) on an X-Y plane and you move distance[0] meters to the north,
 * then distance[1] meters to the west, distance[2] meters to the south, distance[3] meters to the east, and so on.
 * In other words, after each move, your direction changes counter-clockwise.
 * <p>
 * Return true if your path crosses itself, and false if it does not.
 * @author: LISHUAI
 * @createDate: 2022/5/12 22:09
 * @version: 1.0
 */

public class LeetCode_335 {

    public static void main(String[] args) {
//        int[] distance = {2, 1, 1, 2};
//        int[] distance = {1, 2, 3, 4};
//        int[] distance = {1, 1, 1, 1};
        int[] distance = {1, 1, 2, 1, 1};
        boolean selfCrossing = isSelfCrossing(distance);
        System.out.println(selfCrossing);
    }

    public static boolean isSelfCrossing(int[] distance) {
        List<int[]> list_H = new ArrayList<>();
        List<int[]> list_S = new ArrayList<>();
        int curX = 0, curY = 0, next, preX = 0, preY = 0, curP = 0;

        for (int i = 0; i < distance.length; i++) {
            int[] curs;
            if ((i & 3) == 0) {
                curY = preY + distance[i];
                curX = preX;
                if (list_H.size() > 1) {
                    if (list_H.size() == 2) {
                        curs = list_H.get(0);
                    } else {
                        curs = list_H.remove(0);
                    }
                    if (curY >= curs[1] && preY <= curs[1] &&
                            curX <= Math.max(curs[0], curs[2]) && curX >= Math.min(curs[0], curs[2])) {
                        return true;
                    }
                }
                list_S.add(new int[]{curX, curY, preX, preY});
            } else if ((i & 3) == 1) {
                curY = preY;
                curX = preX - distance[i];
                if (list_S.size() > 1) {
                    if (list_S.size() == 2) {
                        curs = list_S.get(0);
                    } else {
                        curs = list_S.remove(0);
                    }
                    if (curX <= curs[0] && preX >= curs[0] &&
                            curY <= Math.max(curs[1], curs[3]) && curY >= Math.min(curs[1], curs[3])) {
                        return true;
                    }
                }
                list_H.add(new int[]{curX, curY, preX, preY});
            } else if ((i & 3) == 2) {
                curY = preY - distance[i];
                curX = preX;
                if (list_H.size() > 1) {
                    if (list_H.size() == 2) {
                        curs = list_H.get(0);
                    } else {
                        curs = list_H.remove(0);
                    }
                    if (curY <= curs[1] && preY >= curs[1] &&
                            curX <= Math.max(curs[0], curs[2]) && curX >= Math.min(curs[0], curs[2])) {
                        return true;
                    }
                }
                list_S.add(new int[]{curX, curY, preX, preY});
            } else if ((i & 3) == 3) {
                curY = preY;
                curX = preX + distance[i];
                if (list_S.size() > 1) {
                    if (list_S.size() == 2) {
                        curs = list_S.get(0);
                    } else {
                        curs = list_S.remove(0);
                    }
                    if (curX >= curs[0] && preX <= curs[0] &&
                            curY <= Math.max(curs[1], curs[3]) && curY >= Math.min(curs[1], curs[3])) {
                        return true;
                    }
                }
                list_H.add(new int[]{curX, curY, preX, preY});
            }

            preX = curX;
            preY = curY;
        }
        return false;
    }
}
