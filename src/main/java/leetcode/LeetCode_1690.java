package leetcode;

/**
 * @description: Alice and Bob take turns playing a game, with Alice starting first.
 * <p>
 * There are n stones arranged in a row. On each player's turn, they can remove either the leftmost stone or the rightmost stone from the row and receive points equal to the sum of the remaining stones' values in the row. The winner is the one with the higher score when there are no stones left to remove.
 * <p>
 * Bob found that he will always lose this game (poor Bob, he always loses), so he decided to minimize the score's difference. Alice's goal is to maximize the difference in the score.
 * <p>
 * Given an array of integers stones where stones[i] represents the value of the ith stone from the left, return the difference in Alice and Bob's score if they both play optimally.
 * @author: LISHUAI
 * @createDate: 2022/6/26 23:28
 * @version: 1.0
 */

public class LeetCode_1690 {

    public static void main(String[] args) {
//        int[] stones = {5, 3, 1, 4, 2};
        int[] stones = {7, 90, 5, 1, 100, 10, 10, 2};
        int i = stoneGameVII(stones);
        System.out.println(i);
    }

    public static int stoneGameVII(int[] stones) {
        long sum = 0;
        for (int i : stones) {
            sum += i;
        }
        return stoneGameVIIProcess(stones, 0, stones.length - 1, sum, 0, 0, 0);
    }

    private static int stoneGameVIIProcess(int[] stones, int left, int right, long sum, int bobSum, int aliceSum, int k) {
        if (left >= right) {
            return aliceSum - bobSum;
        }

        int ans;
        long p1, p2, p3, p4;
        p1 = sum - stones[left];
        p3 = p1 - Math.min(stones[left + 1], stones[right]);
        p2 = sum - stones[right];
        p4 = p2 - Math.min(stones[left], stones[right - 1]);
        if (k == 0) {
            if (p1 - p3 > p2 - p4) {
                ans = stoneGameVIIProcess(stones, left + 1, right, sum - stones[left], bobSum, aliceSum + stones[left], 1);
            } else if (p1 - p3 < p2 - p4) {
                ans = stoneGameVIIProcess(stones, left, right - 1, sum - stones[left], bobSum, aliceSum + stones[right], 1);
            } else if (p1 > p2) {
                ans = stoneGameVIIProcess(stones, left + 1, right, sum - stones[left], bobSum, aliceSum + stones[left], 1);
            } else {
                ans = stoneGameVIIProcess(stones, left, right - 1, sum - stones[left], bobSum, aliceSum + stones[right], 1);
            }
        } else {
            if (p1 - p3 > p2 - p4) {
                ans = stoneGameVIIProcess(stones, left + 1, right, sum - stones[left], bobSum + stones[left], aliceSum, 0);
            } else if (p1 - p3 < p2 - p4) {
                ans = stoneGameVIIProcess(stones, left, right - 1, sum - stones[left], bobSum + stones[right], aliceSum, 0);
            } else if (p1 > p2) {
                ans = stoneGameVIIProcess(stones, left + 1, right, sum - stones[left], bobSum + stones[left], aliceSum, 0);
            } else {
                ans = stoneGameVIIProcess(stones, left, right - 1, sum - stones[left], bobSum + stones[right], aliceSum, 0);
            }
        }

        return ans;
    }
}
