package leetcode;

/**
 * @description: You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
 * <p>
 * For each move, you could choose any m (1 <= m <= n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time.
 * <p>
 * Given an integer array machines representing the number of dresses in each washing machine from left to right on the line, return the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.
 * @author: LISHUAI
 * @createDate: 2022/6/13 6:11
 * @version: 1.0
 */

public class LeetCode_517 {

    public static void main(String[] args) {
        int[] machines = {4, 0, 0, 4};
        int minMoves = findMinMoves(machines);
        System.out.println(minMoves);
    }


    /**
     * other people method
     *
     * @param machines
     * @return
     */
    public static int findMinMoves(int[] machines) {
        int total = 0;
        for (int machine : machines) {
            total += machine;
        }
        if (total % machines.length != 0) {
            return -1;
        }
        int goal = total / machines.length;

        int maxChanges = 0, totalSoFar = 0;
        for (int i = 0; i < machines.length; i++) {
            totalSoFar += machines[i];            //Sum of dresses in machines so far [0...i]

            //So far have too many dresses or need more dresses
            maxChanges = Math.max(maxChanges, Math.abs((i + 1) * goal - totalSoFar));

            //Current machine has too many dresses
            maxChanges = Math.max(maxChanges, machines[i] - goal);
        }

        return maxChanges;
    }
}
