package leetcode;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given an integer array, nums, and an integer k.
 * nums comprises of only 0's and 1's. In one move, you can choose two adjacent indices and swap their values.
 * <p>
 * Return the minimum number of moves required so that nums has k consecutive 1's.
 * @author: LISHUAI
 * @createDate: 2022/5/3 19:56
 * @version: 1.0
 */

public class LeetCode_1703 {

    private static int size = 0;

    public static void main(String[] args) throws Exception {
//        int[] nums = {1, 0, 0, 1, 0, 1};
//        int k = 2;
//        int[] nums = {1, 1, 0, 1};
//        int k = 2;
//        int[] nums = {0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1};
//        int k = 3;
        int[] nums = {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 9;
//        int[] nums = getArr();
//        int k = 25000;
        System.out.println("nums len : " + nums.length);
        long start = System.currentTimeMillis();
        int i = minMoves_04(nums, k);
        long end = System.currentTimeMillis();
        System.out.println(i + " : " + (end - start));

        start = System.currentTimeMillis();
        int i2 = minMoves_03(nums, k);
        end = System.currentTimeMillis();
        System.out.println(i2 + " : " + (end - start));
        System.out.println("size : " + size);
    }

    private static int[] getArr() throws Exception {
        FileReader reader = new FileReader(new File("src/main/resources/leetCode_1703"));
        char[] chars = new char[1024 * 1024 * 10];
        int read = reader.read(chars);
        String line = String.valueOf(chars, 0, read);
        String[] split = line.split(",");
        int[] ans = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            if ("1".equals(split[i])) {
                ans[i] = 1;
            } else {
                ans[i] = 0;
            }
        }
        return ans;
    }

    public static int minMoves(int[] nums, int k) {
        int left, right, max = 0, ans = Integer.MAX_VALUE;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int j = i;
            while (j < nums.length && nums[j] == 1) {
                j++;
            }
            if (j - i > max) {
                max = j - i;
                list.clear();
                list.add(new int[]{i, j - 1});
            } else if (j - i == max) {
                list.add(new int[]{i, j - 1});
            }
            i = j;
        }
        if (max >= k) {
            return 0;
        }

        for (int[] ints : list) {
            left = ints[0];
            right = ints[1];
            int c = ints[1] - ints[0] + 1;
            int cur = 0;
            while (left >= 0 && right < nums.length && c < k) {
                while (--left >= 0 && nums[left] == 0) ;
                while (++right < nums.length && nums[right] == 0) ;

                if (left < 0 && right >= nums.length) {
                    break;
                }
                int l = Integer.MAX_VALUE, r = Integer.MAX_VALUE;
                if (left >= 0) {
                    l = ints[0] - left - 1;
                }

                if (right < nums.length) {
                    r = right - ints[1] - 1;
                }

                if (l < r) {
                    ints[0]--;
                    cur += l;
                    right--;
                } else {
                    ints[1]++;
                    cur += r;
                    left++;
                }
                c++;
            }
            if (c == k) {
                ans = Math.min(ans, cur);
            }
        }
        return ans;
    }

    public static int minMoves_02(int[] nums, int k) {
        int left, right, max = 0, ans = Integer.MAX_VALUE;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int j = i;
            while (j < nums.length && nums[j] == 1) {
                j++;
            }
            ans = Math.min(ans, findMin(nums, i, j - 1, k));
            if (ans == 0) {
                break;
            }
            i = j;
        }

        return ans;
    }

    private static int findMin(int[] nums, int left, int right, int k) {
        int[] ints = new int[]{left, right};
        int c = right - left + 1;
        int cur = 0;
        while (left >= 0 && right < nums.length && c < k) {
            while (--left >= 0 && nums[left] == 0) ;
            while (++right < nums.length && nums[right] == 0) ;

            if (left < 0 && right >= nums.length) {
                break;
            }
            int l = Integer.MAX_VALUE, r = Integer.MAX_VALUE;
            if (left >= 0) {
                l = ints[0] - left - 1;
            }

            if (right < nums.length) {
                r = right - ints[1] - 1;
            }

            if (l < r) {
                ints[0]--;
                cur += l;
                right--;
            } else {
                ints[1]++;
                cur += r;
                left++;
            }
            c++;
        }
        return cur;
    }


    public static int minMoves_03(int[] nums, int k) {
        int[] arr = new int[nums.length];
        int index = 0, ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                arr[index++] = i;
            }
        }
        System.out.println("index : " + index);
        for (int i = 0; i < index; ) {
            int j = i + 1;
            while (j < index && arr[j] == arr[j - 1] + 1) {
                j++;
            }
            int minK = findMinK(arr, i, j - 1, index, k);
            ans = Math.min(ans, minK);
            if (ans == 0) {
                break;
            }
            i = j;
        }
        return ans;
    }

    private static int findMinK(int[] nums, int left, int right, int end, int k) {
        size++;
        int ans = 0, lt = nums[left], rt = nums[right], cur = right - left + 1;

        while ((--left >= 0 | ++right < end) && cur < k) {
            int l = Integer.MAX_VALUE, r = Integer.MAX_VALUE;

            if (left >= 0) {
                l = lt - nums[left] - 1;
            }
            if (right < end) {
                r = nums[right] - rt - 1;
            }

            if (l < r) {
                lt--;
                ans += l;
                right--;
            } else {
                rt++;
                ans += r;
                left++;
            }
            cur++;
        }
        return ans;
    }


    public static int minMoves_04(int[] nums, int k) {
        int[] arr = new int[nums.length];
        int index = 0, ans = Integer.MAX_VALUE, left = 0, right = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                arr[index++] = i;
            }
        }
        int[][] dp = new int[index][2];

//        System.out.print(0 + " ");
//        int q = 0;
//        for (int i = 1; i < index; i++) {
//            q += (arr[i] - arr[i - 1]);
//            System.out.print(q + " ");
//        }
//        System.out.println();
//        System.out.println("------");
//        for (int i = 0; i < index; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();
        dp[0][0] = 0;


        int x = arr[0], y = arr[0];
        for (int i = 0; i < k; i++) {
            sum += (arr[i] - x++);
        }
        ans = sum;

        right = k;
        while (right < index) {
            sum -= (arr[++left] - y++);
            sum += (arr[right++] - x++);
            ans = Math.min(ans, sum);
        }


        return ans;
    }

    class Solution {
        public int minMoves(int[] nums, int k) {
            if (k <= 1) return 0;
            List<Integer> ones = new ArrayList<>();
            ones.add(0);
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 1) ones.add(i);
            }
            int[] prefix = new int[ones.size()];
            for (int i = 1; i < prefix.length; i++) {
                prefix[i] = prefix[i - 1] + ones.get(i);
            }
            int ans = Integer.MAX_VALUE;
            int radius = (k - 1) / 2;
            for (int i = 1; i <= ones.size() - k; i++) {
                /*
                 * Left Boundary -> i
                 * Right Boundary -> i + k - 1
                 * Mid Index = i + radius
                 */
                int midIndex = i + radius;
                int leftCost = prefix[midIndex - 1] - prefix[i - 1];
                int rightCost = prefix[i + k - 1] - prefix[midIndex];
                int totalCost = rightCost - leftCost - ((k % 2 == 0) ? ones.get(midIndex) : 0);
                ans = Math.min(ans, totalCost);
            }
            ans -= radius * (radius + 1);
            if (k % 2 == 0) ans -= k / 2;
            return ans;
        }
    }
}

