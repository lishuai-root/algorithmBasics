package leetcode;

import java.util.Arrays;

/**
 * @description: You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
 * <p>
 * numberOfBoxesi is the number of boxes of type i.
 * numberOfUnitsPerBoxi is the number of units in each box of the type i.
 * You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
 * <p>
 * Return the maximum total number of units that can be put on the truck.
 * @author: LISHUAI
 * @createDate: 2022/7/3 22:16
 * @version: 1.0
 */

public class LeetCode_1710 {

    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> {
            return b[1] - a[1];
        });

        int ans = 0;
        for (int i = 0; i < boxTypes.length && truckSize > 0; i++) {
            int size = Math.min(boxTypes[i][0], truckSize);
            ans += (size * boxTypes[i][1]);
            truckSize -= size;
        }
        return ans;
    }
}
