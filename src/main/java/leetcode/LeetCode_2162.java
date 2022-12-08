package leetcode;

/**
 * @description: A generic microwave supports cooking times for:
 * <p>
 * at least 1 second.
 * at most 99 minutes and 99 seconds.
 * To set the cooking time, you push at most four digits. The microwave normalizes what you push as four digits by prepending zeroes. It interprets the first two digits as the minutes and the last two digits as the seconds. It then adds them up as the cooking time. For example,
 * <p>
 * You push 9 5 4 (three digits). It is normalized as 0954 and interpreted as 9 minutes and 54 seconds.
 * You push 0 0 0 8 (four digits). It is interpreted as 0 minutes and 8 seconds.
 * You push 8 0 9 0. It is interpreted as 80 minutes and 90 seconds.
 * You push 8 1 3 0. It is interpreted as 81 minutes and 30 seconds.
 * You are given integers startAt, moveCost, pushCost, and targetSeconds. Initially, your finger is on the digit startAt. Moving the finger above any specific digit costs moveCost units of fatigue. Pushing the digit below the finger once costs pushCost units of fatigue.
 * <p>
 * There can be multiple ways to set the microwave to cook for targetSeconds seconds but you are interested in the way with the minimum cost.
 * <p>
 * Return the minimum cost to set targetSeconds seconds of cooking time.
 * <p>
 * Remember that one minute consists of 60 seconds.
 * @author: LISHUAI
 * @createDate: 2022/12/2 2:58
 * @version: 1.0
 */

public class LeetCode_2162 {

    public static void main(String[] args) {
//        int startAt = 1, moveCost = 2, pushCost = 1, targetSeconds = 600;
//        int startAt = 1, moveCost = 9403, pushCost = 9402, targetSeconds = 6008;
        int startAt = 0, moveCost = 1, pushCost = 2, targetSeconds = 76;
//        int startAt = 0, moveCost = 821, pushCost = 389, targetSeconds = 33;
//        int startAt = 0, moveCost = 9, pushCost = 18, targetSeconds = 460;
        int i = minCostSetTime(startAt, moveCost, pushCost, targetSeconds);
        System.out.println(i);
//        System.out.println(6008 / 60);
//        System.out.println(6008 - 60 * 99);
//        System.out.println(((moveCost + pushCost) << 2) - moveCost);
        long start = System.currentTimeMillis();
        System.out.println(minCostSetTime_02(startAt, moveCost, pushCost, targetSeconds));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
//        int[] curs = new int[4];
//        return minCostSetTimeProcess(startAt, moveCost, pushCost, targetSeconds, curs, 0);
        return minCostSetTimeProcess(startAt, moveCost, pushCost, targetSeconds, 0);
    }

    public static int minCostSetTimeProcess(int startAt, int moveCost, int pushCost, int targetSeconds, int cur) {
        if (cur > 9999) {
            return Integer.MAX_VALUE;
        }
        int curTime = (cur / 100 * 60) + (cur % 100);
        if (curTime == targetSeconds) {
            return 0;
        }
        if (curTime > targetSeconds) {
            return Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= 9; i++) {
            if (cur * 10 + i == cur) {
                continue;
            }
            int p = minCostSetTimeProcess(i, moveCost, pushCost, targetSeconds, cur * 10 + i);
            if (p != Integer.MAX_VALUE) {
                p += (i == startAt ? pushCost : moveCost + pushCost);
                ans = Math.min(ans, p);
            }
        }
        return ans;
    }

    public static int minCostSetTimeProcess(int startAt, int moveCost, int pushCost, int targetSeconds, int[] curs, int index) {
        int curTimes = 0;
        if (index == 1) {
            curTimes = curs[0];
        } else if (index == 2) {
            curTimes = curs[0] * 10 + curs[1];
        } else if (index == 3) {
            curTimes = curs[0] * 60 + curs[1] * 10 + curs[2];
        } else if (index == 4) {
            curTimes = (curs[0] * 10 + curs[1]) * 60 + curs[2] * 10 + curs[3];
            return curTimes == targetSeconds ? 0 : Integer.MAX_VALUE;
        }
        if (curTimes == targetSeconds) {
            return 0;
        }
        if (curTimes > targetSeconds) {
            return Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= 9; i++) {
            curs[index] = i;
            int p = minCostSetTimeProcess(i, moveCost, pushCost, targetSeconds, curs, index + 1);
            if (p != Integer.MAX_VALUE) {
                p += (i == startAt ? pushCost : moveCost + pushCost);
                ans = Math.min(ans, p);
            }
        }
        return ans;
    }

    public static int minCostSetTime_02(int startAt, int moveCost, int pushCost, int targetSeconds) {
        if (targetSeconds < 10) {
            return startAt == targetSeconds ? pushCost : moveCost + pushCost;
        }

        int ans = Integer.MAX_VALUE, k = Math.min(targetSeconds / 60, 99);
        while (true) {
            int curP = 0;
            int c = targetSeconds - k * 60;
            if (c > 99 || k < 0) {
                break;
            }
            int q = k * 100 + c;
            int p = q % 10, s;
            while (q != 0) {
                s = q % 10;
                q /= 10;
                if (s != p) {
                    curP += moveCost;
                }
                p = s;
                curP += pushCost;
            }
            if (p != startAt) {
                curP += moveCost;
            }
            ans = Math.min(ans, curP);
            k--;
        }
        return ans;
    }
}
