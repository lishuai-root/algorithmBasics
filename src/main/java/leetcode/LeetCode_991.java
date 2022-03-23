package leetcode;

import java.util.*;

/**
 * @description: There is a broken calculator that has the integer startValue on its display initially.
 * In one operation, you can:
 * <p>
 * multiply the number on display by 2, or
 * subtract 1 from the number on display.
 * Given two integers startValue and target, return the minimum number of operations needed to display target on the calculator.
 * @author: LISHUAI
 * @createDate: 2022/3/23 18:31
 * @version: 1.0
 */

public class LeetCode_991 {

    private static Map<Integer, Integer> map;
    private static int maxNum;

    public static void main(String[] args) {
//        int startValue = 2, target = 3;
//        int startValue = 5, target = 8;
        int startValue = 3, target = 10;
//        int startValue = 1, target = 1000000000;
//        1000000000
//        536870912
//        1073741824
        maxNum = brokenCalc_02(startValue, target);
        System.out.println("maxNum : " + maxNum);
        int i = brokenCalc(startValue, target);
        System.out.println(i);

//        int i1 = brokenCalcProcess(startValue, target, 0, i);
//        System.out.println("i1 : " + i1);
//        int size = 0;
//        while (target > 1) {
//            size++;
//            target >>>= 1;
//        }
//        System.out.println(size);
//
//        int m = 1;
//        size = 0;
//        while (m < 1000000000) {
//            size++;
//            m <<= 1;
//        }
//        System.out.println(size);
//        System.out.println(m);
    }

    public static int brokenCalc(int startValue, int target) {
        Set<Integer> set = new HashSet<>();
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return Math.abs(a[0] - target);
        });
        int ans = Integer.MAX_VALUE;

        queue.offer(new int[]{startValue, 0});

        while (!queue.isEmpty() && maxNum > 0) {
            maxNum--;
//            System.out.println(queue.size());
            int[] cur = queue.poll();
//            System.out.println(cur[1]);
            if (cur[0] == target) {
                ans = Math.min(ans, cur[1]);
//                System.out.println(queue.size());
                break;
            }

//            if ((ans == Integer.MAX_VALUE)) {
            if (!set.contains(cur[0] << 1)) {
                set.add(cur[0] << 1);
                queue.add(new int[]{cur[0] << 1, cur[1] + 1});
            }

            if (!set.contains(cur[0] - 1) && cur[0] > target >>> 1 && (cur[0] - 1) << 1 > target >> 1) {
                set.add(cur[0] - 1);
                queue.add(new int[]{cur[0] - 1, cur[1] + 1});
            }
//            }


//            if (!set.contains(cur[0])) {
//                set.add(cur[0]);
//                if (ans == Integer.MAX_VALUE) {
//                    queue.add(new int[]{cur[0] << 1, cur[1] + 1});
//                    if (cur[0] << 1 > target) {
//                        queue.add(new int[]{cur[0] - 1, cur[1] + 1});
//                    }
//                }
//            }
        }

        return ans;
    }

    private static int brokenCalcProcess(int startValue, int target, int pre, int limit) {
        if (pre > limit) {
            return Integer.MAX_VALUE;
        }
//        System.out.println(startValue);
        if (startValue == target) {
            return 0;
        }

        if (startValue > target) {
            return startValue - target;
        }

//        if (map.containsKey(startValue)) {
//            return map.get(startValue);
//        }
//        int ans;
//        if (map.containsKey(startValue << 1)) {
//            ans = map.get(startValue << 1) + 1;
//        } else {
//            ans = brokenCalcProcess(startValue << 1, target, pre + 1, limit);
//        }
//
//        if ((startValue - 1) << 1 != startValue && startValue << 1 < target) {
//
//            if (map.containsKey(startValue - 1)) {
//                ans = Math.min(map.get(startValue - 1) + 1, ans);
//            } else {
//                ans = Math.min(ans, brokenCalcProcess(startValue - 1, target, pre + 1, limit));
//            }
//
//        }
//
//        map.put(startValue, ans + 1);
        return Math.min(brokenCalcProcess(startValue << 1, target, pre + 1, limit), brokenCalcProcess(startValue - 1, target, pre + 1, limit)) + 1;
    }


    public static int brokenCalc_02(int startValue, int target) {
        int ans = 0;

        while (target > startValue) {
            ans++;
            if ((target & 1) == 1) {
                target++;
                ans++;
            }
            target >>= 1;
        }
        return ans + startValue - target;
    }

}


//try doing this for many inpts, u will find a pattern :-
//when target is even then we can reduce steps by making it target/2;
//when target is odd we have to find the even number which is close to target, in which we can perform our operation ie target-1.
//when target is less than start then we simply return start - target
//Example:- start= 3, target = 2
//we can simply perform start -1 to reach target.
//Please upvote if u find my code easy to understand