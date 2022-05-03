package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an array people where people[i] is the weight of the ith person,
 * and an infinite number of boats where each boat can carry a maximum weight of limit.
 * Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.
 * <p>
 * Return the minimum number of boats to carry every given person.
 * @author: LISHUAI
 * @createDate: 2022/3/24 19:43
 * @version: 1.0
 */

public class LeetCode_881 {
    public static void main(String[] args) {
        int[] people = {3, 5, 3, 4};
        int limit = 5;
        int i = numRescueBoats(people, limit);
        System.out.println(i);
    }

    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0, right = people.length - 1, ans = 0;
        while (left < right) {
            if (people[right] + people[left] <= limit) {
                left++;
            }
            right--;
            ans++;
        }

        if (left == right) {
            ans++;
        }
        return ans;
    }

}

