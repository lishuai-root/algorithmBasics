package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 * <p>
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 * <p>
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * <p>
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 * @author: LISHUAI
 * @createDate: 2023/3/8 22:20
 * @version: 1.0
 */

public class LeetCode_875 {

    public static void main(String[] args) {
//        int[] piles = {3, 6, 7, 11};
//        int h = 8;
//        int[] piles = {30, 11, 23, 4, 20};
//        int h = 6;
//        int[] piles = {332484035, 524908576, 855865114, 632922376, 222257295, 690155293, 112677673, 679580077, 337406589, 290818316, 877337160, 901728858, 679284947, 688210097, 692137887, 718203285, 629455728, 941802184};
//        int h = 823855818;
        int[] piles = {1, 1, 1, 999999999};
        int h = 10;
        int i = minEatingSpeed_02(piles, h);
        System.out.println(i);
    }

    public static int minEatingSpeed_02(int[] piles, int h) {
        int left = 1, right = 1;
        for (int i : piles) {
            right = Math.max(i, right);
        }

        int ans = right;
        if (piles.length == h) {
            return ans;
        }
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int q = 0;
            for (int pile : piles) {
                q += ((pile + mid - 1) / mid);
                if (q > h) {
                    break;
                }
            }
            if (q > h) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }
        return ans;
    }

    public static int minEatingSpeed(int[] piles, int h) {
        if (piles.length == 1) {
            return (piles[0] + h - 1) / h;
        }
        Queue<int[]> queue = new PriorityQueue<>(piles.length, (a, b) -> {
            int c = (b[0] + b[1] - 1) / b[1] - (a[0] + a[1] - 1) / a[1];
            if (c == 0) {
                c = b[0] - a[0];
            }
            return c;
        });
        long sum = 0;
        for (int pile : piles) {
            sum += pile;
            queue.offer(new int[]{pile, 1});
        }
        if (sum <= h) {
            return 1;
        }
        if (piles.length == h) {
            return queue.peek()[0];
        }

        int k = h - piles.length;
        while (k > 0) {
            int[] poll = queue.poll();
            poll[1]++;
            queue.offer(poll);
            k--;
        }
        int[] poll = queue.poll();
        int ans = (poll[0] + poll[1] - 1) / poll[1];
        return ans;
    }
}
