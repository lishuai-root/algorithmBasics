package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description: You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.
 * <p>
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
 * <p>
 * Return the maximum coins you can collect by bursting the balloons wisely.
 * @author: LiShuai
 * @createDate: 2023/9/9 21:54
 * @version: 1.0
 */

public class LeetCode_312 {

    private static PreTree root;

    public static void main(String[] args) {
//        int[] nums = {3, 1, 5, 8};
//        int[] nums = {1, 5};
        int[] nums = makeArr(300);
        int i = maxCoins(nums);
        System.out.println(i);
    }

    private static int[] makeArr(int len) {
        int[] nums = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            nums[i] = random.nextInt(100);
        }
        return nums;
    }

    public static int maxCoins(int[] nums) {
        root = new PreTree();
        return maxCoinsProcess(nums);
    }

    private static int maxCoinsProcess(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int value = root.getValue(nums);
        if (value != 0) {
            return value;
        }
        int len = nums.length;
        int[] next = new int[len - 1];
        System.arraycopy(nums, 1, next, 0, next.length);
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int p = i - 1 < 0 ? 1 : nums[i - 1];
            int n = i + 1 >= len ? 1 : nums[i + 1];
            int ap = p * nums[i] * n;
            ans = Math.max(ans, maxCoinsProcess(next) + ap);
            if (i < next.length) {
                next[i] = nums[i];
            }
        }
        root.insertValue(nums, ans);
        return ans;
    }

    static class PreTree {
        Map<Integer, PreTree> map;
        int value;

        boolean isEnd;

        public PreTree() {
            map = new HashMap<Integer, PreTree>();
            value = 0;
            isEnd = false;
        }

        public int getValue(int[] nums) {
            PreTree node = this;
            for (int i : nums) {
                if (!node.map.containsKey(i)) {
                    return 0;
                }
                node = node.map.get(i);
            }
            return node.isEnd ? node.value : 0;
        }

        public void insertValue(int[] nums, int val) {
            PreTree node = this;
            for (int i : nums) {
                if (!node.map.containsKey(i)) {
                    node.map.put(i, new PreTree());
                }
                node = node.map.get(i);
            }
            node.isEnd = true;
            node.value = val;
        }
    }
}
