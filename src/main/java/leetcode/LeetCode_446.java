package leetcode;

import kotlin.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description: Given an integer array nums, return the number of all the arithmetic subsequences of nums.
 * <p>
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * <p>
 * For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
 * For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
 * A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
 * <p>
 * For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
 * The test cases are generated so that the answer fits in 32-bit integer.
 * @author: LISHUAI
 * @createDate: 2022/2/21 23:02
 * @version: 1.0
 */

public class LeetCode_446 {

    private static Map<String, Integer> cache;
    private static int result = 0;
    HashMap<Pair<Integer, Long>, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
//        int[] nums = {7, 7, 7, 7, 7};
//        int[] nums = {2, 4, 6, 8, 10};
//        int[] nums = {0, 1, 2, 2, 2};
//        int[] nums = {0, 2000000000, -294967296};
//        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
//        System.out.println(nums.length);
//        int[] nums = makeArray(1000);
        int[] nums = {2147483638, 2147483639, 2147483640, 2147483641, 2147483642, 2147483643, 2147483644, 2147483645, 2147483646, 2147483647, -2147483648, -2147483647, -2147483646, -2147483645, -2147483644, -2147483643, -2147483642, -2147483641, -2147483640, -2147483639};
        System.out.println(2147483647 + 1);
//        int[] nums = new int[1000];
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = i;
////            System.out.print(i + " ");
//        }
//        System.out.println();
        int i = numberOfArithmeticSlices_02(nums);
        System.out.println(i);
        System.out.println(numberOfArithmeticSlices(nums));
        System.out.println(result);
//        int i = numberOfArithmeticSlicesProcess_dp(nums, 3, 2, 2);
//        System.out.println(i);
//        System.out.println(numberOfArithmeticSlicesProcess_02(nums, 3, 2, 2));
        //2781846
//        int i1 = arraySum(5);
//        System.out.println("sum - " + i1);
//        System.out.println(arraySum_dp(5, 3));
//        System.out.println(arraySum_dp(28, 3));
        //268435049
    }

    private static int[] makeArray(int len) {
        int[] nums = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            nums[i] = random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        return nums;
    }

    public static int numberOfArithmeticSlices_02(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> temp = new HashMap<>();
        cache = new HashMap<>();
        int ans = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            int c = 0;
            for (int j = nums.length - 1; j > i; j--) {
                temp.put(nums[j], temp.getOrDefault(nums[j], 0) + 1);
                map.put(nums[j], map.get(nums[j]) - 1);
                int sub = nums[j] - nums[i];
                if (sub != 0 && (nums[i] >>> 31) == ((nums[j]) >>> 31)) {
//                    int p = getSize(nums, j, nums[j] - nums[i], null);
                    int p = 0;
//                    if (cache.containsKey(sub + "/" + (j + 1))) {
//                        p = Math.toIntExact(cache.get(sub + "/" + j));
//                    } else {
                    p = numberOfArithmeticSlicesProcess_02(nums, j + 1, j, sub);
//                    }

//                    cache.put(sub + "/" + (j + 1), p);
                    ans += p;
                } else if (c == 0) {
                    ans += arraySum_dp(map.get(nums[j]) + 1, 2);
                    c++;
                }
            }
            temp.put(nums[i], temp.getOrDefault(nums[i], 0) + 1);
            Map<Integer, Integer> t = map;
            map = temp;
            temp = t;
        }
//        System.out.println(cache.get(1 + "/" + 3));
        return ans;
    }

    private static int numberOfArithmeticSlicesProcess_dp(int[] nums, int index, int pre, int sub) {
        int len = nums.length;
        int[][] dp = new int[len + 1][len + 1];
        for (int i = len - 1; i >= index; i--) {
            for (int j = i - 1; j >= pre; j--) {
                int p1 = dp[i + 1][j];
                int p2 = 0;
                if (nums[i] - nums[j] == sub) {
                    p2 = dp[i + 1][i] + 1;
                }
                dp[i][j] = p1 + p2;
            }
        }
        return dp[index][pre];
    }

    private static int numberOfArithmeticSlicesProcess_02(int[] nums, int index, int pre, int sub) {
        if (index == nums.length) {
            return 0;
        }

        int p1 = 0, p2 = 0;
        p1 = numberOfArithmeticSlicesProcess_02(nums, index + 1, pre, sub);
        if ((nums[index] >>> 31) == (nums[pre] >>> 31) && nums[index] - nums[pre] == sub) {
            if (cache.containsKey(sub + "/" + (index + 1))) {
                p2 = cache.get(sub + "/" + (index + 1)) + 1;
            } else {
                p2 = numberOfArithmeticSlicesProcess_02(nums, index + 1, index, sub) + 1;
            }
        }
        if (!cache.containsKey(sub + "/" + index)) {
            cache.put(sub + "/" + index, (p1 + p2));
        }
        return (p1 + p2);
    }

    private static int getSize(int[] nums, int index, int sub, Map<String, Integer> map) {
        int ans = 0;
        int pre = index;
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[pre] + sub == nums[i]) {
                pre = i;
                ans += map.getOrDefault(sub + "/" + i, 1);
            }
        }
        return ans;
    }

    public static int numberOfArithmeticSlices(int[] nums) {
        cache = new HashMap<>();
        return numberOfArithmeticSlicesProcess(nums, 0, 0, 0, 0);
    }

    private static int numberOfArithmeticSlicesProcess(int[] nums, int index, int pre, int sub, int count) {
        if (index == nums.length) {
            return 0;
        }
        int p1 = 0, p2 = 0;
        p1 = numberOfArithmeticSlicesProcess(nums, index + 1, pre, sub, count);
        if (count == 0) {
            p2 = numberOfArithmeticSlicesProcess(nums, index + 1, index, sub, count + 1);
        } else if (count == 1 || ((nums[index] >>> 31) == (nums[pre] >>> 31) && nums[index] - nums[pre] == sub)) {
//            if (count >= 2 && cache.containsKey((nums[index] - nums[pre]) + "/" + (index + 1))) {
//                p2 = cache.get((nums[index] - nums[pre]) + "/" + (index + 1)) + 1;
//            } else {
            p2 = numberOfArithmeticSlicesProcess(nums, index + 1, index, nums[index] - nums[pre], count + 1);
//            }

            if (count >= 2) {
                p2 += 1;
            }
        }
        if (!cache.containsKey(sub + "/" + index)) {
            cache.put(sub + "/" + index, p1 + p2);
        }
        return p1 + p2;
    }

    private static int arraySum(int size) {
        return arraySumProcess(size, 0, 0);
    }

    private static int arraySumProcess(int size, int index, int count) {
        if (index == size) {
            return count >= 3 ? 1 : 0;
        }
        int p1 = arraySumProcess(size, index + 1, count);
        int p2 = arraySumProcess(size, index + 1, count + 1);
        return p1 + p2;
    }

    private static int arraySum_dp(int size, int p) {
        if (size <= 0) {
            return 0;
        }
        int[][] dp = new int[size + 1][size + 1];
        for (int i = p; i <= size; i++) {
            dp[i][size] = 1;
        }
        for (int i = size - 1; i >= 0; i--) {
            for (int j = size - 1; j >= 0; j--) {
                dp[i][j] = dp[i][j + 1] + dp[i + 1][j + 1];
            }
        }
        return dp[0][0];
    }

    public int numberOfArithmeticSlices_other(int[] A) {
        int n = A.length;
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                total += dfs(j + 1, j, (long) A[j] - A[i], A);
            }
        }
        return total;
    }

    int dfs(int index, int last, long diff, int[] A) {
        if (index == A.length)
            return 0;

        Pair<Integer, Long> hash = new Pair(last, diff);
        if (memo.get(hash) != null) {
            return memo.get(hash);
        }

        int local = 0;
        for (int next = index; next < A.length; next++) {
            if ((long) A[next] - A[last] == diff) {
                local += dfs(next + 1, next, diff, A) + 1;
            }
        }
        memo.put(hash, local);
        return local;
    }

    public int numberOfArithmeticSlices_other_dp(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer>[] map = new HashMap[n];
        for (int i = 0; i < n; i++) {
            map[i] = new HashMap<>();
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long cd = (long) arr[i] - (long) arr[j];
                if (cd <= Integer.MIN_VALUE || cd >= Integer.MAX_VALUE)
                    continue;
                int asj = map[j].getOrDefault((int) cd, 0);
                int asi = map[i].getOrDefault((int) cd, 0);
                ans += asj;
                map[i].put((int) cd, asi + asj + 1);
            }
        }
        return ans;
    }

//    class Solution {
//        public:
//
//        int numberOfArithmeticSlices(vector<int>&a) {
//            long long ans = 0, n = a.size();
//            vector<unordered_map<long, long>> dp (n);
//            for (int i = 0; i < n; ++i) {
//                for (int j = 0; j < i; ++j) {
//                    long long diff = ( long long)a[i] - ( long long)a[j];
//
//                    dp[i][diff]++;
//                    if (dp[j].count(diff)) {
//                        dp[i][diff] += dp[j][diff];
//                        ans += dp[j][diff];
//                    }
//                }
//            }
//            return ans;
//        }
//    }
//
//    ;
}
