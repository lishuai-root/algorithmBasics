package leetcode;

import java.util.*;

/**
 * @description: You are given two integer arrays nums1 and nums2.
 * We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.
 * <p>
 * We may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:
 * <p>
 * nums1[i] == nums2[j], and
 * the line we draw does not intersect any other connecting (non-horizontal) line.
 * Note that a connecting line cannot intersect even at the endpoints (i.e., each number can only belong to one connecting line).
 * <p>
 * Return the maximum number of connecting lines we can draw in this way.
 * @author: LISHUAI
 * @createDate: 2021/12/21 21:20
 * @version: 1.0
 */

public class LeetCode_1035 {

    private static Map<String, Integer> map;

    private static Map<Integer, List<Integer>> listMap;

    public static void main(String[] args) {

//        int[] nums1 = makeArray(100, 1000), nums2 = makeArray(100, 1000);
        int[] nums1 = {1, 3, 7, 1, 7, 5}, nums2 = {1, 9, 2, 5, 1};

        int i = maxUncrossedLines_03(nums1, nums2);

        int i1 = maxUncrossedLines_02(nums1, nums2);

        System.out.println(i);

        System.out.println(i1);
    }

    private static int[] makeArray(int size, int e) {

        int[] arr = new int[size];

        Random r = new Random();

        for (int i = 0; i < size; i++) {

            arr[i] = r.nextInt(e);
        }

        return arr;
    }

    public static int maxUncrossedLines(int[] nums1, int[] nums2) {

        map = new HashMap<>();

        listMap = new HashMap<>();

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums2.length; i++) {

            if ((list = listMap.get(nums2[i])) == null) {

                list = new ArrayList<>();

                listMap.put(nums2[i], list);
            }

            list.add(i);
        }

        return process_02(nums1, 0, nums1.length, nums2, 0, nums2.length);
    }

    private static int process(int[] nums1, int left, int right, int[] nums2, int start, int end, int sum) {

        if (start >= end || left >= right) {

            return sum;
        }

        int ans = process(nums1, left + 1, right, nums2, start, end, sum);

        while (start < end && nums1[left] != nums2[start]) {

            start++;
        }

        if (start != end) {

            ans = Math.max(ans, process(nums1, left + 1, right, nums2, start + 1, end, sum + 1));
        }

        return ans;
    }

    private static int process_02(int[] nums1, int left, int right, int[] nums2, int start, int end) {

        if (start >= end || left >= right) {

            return 0;
        }

        String line = left + "-" + start;

        if (map.containsKey(line)) {

            return map.get(line);
        }

        int ans = process_02(nums1, left + 1, right, nums2, start, end);

        List<Integer> list = listMap.get(nums1[left]);

        for (int i = 0; list != null && i < list.size(); i++) {

            if (list.get(i) >= start) {

                ans = Math.max(ans, process_02(nums1, left + 1, right, nums2, list.get(i) + 1, end) + 1);

                break;
            }
        }

//        while (start < end && nums1[left] != nums2[start]) {
//
//            start++;
//        }


//        if (start != -1) {
//
//            ans = Math.max(ans, process_02(nums1, left + 1, right, nums2, start + 1, end) + 1);
//        }

        map.put(line, ans);

        return ans;
    }

    public static int maxUncrossedLines_02(int[] nums1, int[] nums2) {

        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i < nums1.length + 1; i++) {
            for (int j = 1; j < nums2.length + 1; j++) {
                if (nums1[i - 1] == nums2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static int maxUncrossedLines_03(int[] nums1, int[] nums2) {

        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = nums1.length - 1; i >= 0; i--) {
            for (int j = nums2.length - 1; j >= 0; j--) {

                dp[i][j] = dp[i + 1][j];
//                System.out.println(i);

                for (int k = j; k < nums2.length; k++) {

                    if (nums1[i] == nums2[k]) {

                        dp[i][j] = Math.max(dp[i][j], dp[i + 1][k + 1] + 1);

                        break;
                    }
                }

            }
        }
        return dp[0][0];
    }
}
