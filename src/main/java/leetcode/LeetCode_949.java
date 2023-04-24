package leetcode;

/**
 * @description: Given an array arr of 4 digits, find the latest 24-hour time that can be made using each digit exactly once.
 * <p>
 * 24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59. The earliest 24-hour time is 00:00, and the latest is 23:59.
 * <p>
 * Return the latest 24-hour time in "HH:MM" format. If no valid time can be made, return an empty string.
 * @author: LISHUAI
 * @createDate: 2023/4/13 22:38
 * @version: 1.0
 */

public class LeetCode_949 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        String s = largestTimeFromDigits(arr);
        System.out.println(s);
        System.out.println(compare("23:59", "43:21"));
    }

    public static String largestTimeFromDigits(int[] arr) {
        int[] ans = new int[arr.length];
        return largestTimeFromDigitsProcess(arr, ans, 0);
    }

    private static String largestTimeFromDigitsProcess(int[] arr, int[] ans, int k) {
        if (k == ans.length) {
            int q = ans[0] == 2 ? 3 : 9;
            if (ans[0] <= 2 && ans[1] <= q && ans[2] <= 5 && ans[3] <= 9) {
                return "" + ans[0] + ans[1] + ":" + ans[2] + ans[3];
            } else {
                return "";
            }
        }
        String as = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                continue;
            }
            ans[k] = arr[i];
            arr[i] = -1;
            String s = largestTimeFromDigitsProcess(arr, ans, k + 1);
            if (compare(s, as)) {
                as = s;
            }
            arr[i] = ans[k];
        }
        return as;
    }

    private static boolean compare(String m, String n) {
        if (m.length() > n.length()) {
            return true;
        }
        if (m.length() < n.length()) {
            return false;
        }
        for (int i = 0; i < m.length(); i++) {
            if (m.charAt(i) > n.charAt(i)) {
                return true;
            } else if (m.charAt(i) < n.charAt(i)) {
                return false;
            }
        }
        return false;
    }
}
