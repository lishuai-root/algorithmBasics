package leetcode;

import java.util.Arrays;

/**
 * @description: Given an array nums that represents a permutation of integers from 1 to n. We are going to construct a binary search tree (BST) by inserting the elements of nums in order into an initially empty BST. Find the number of different ways to reorder nums so that the constructed BST is identical to that formed from the original array nums.
 * <p>
 * For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right child. The array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.
 * Return the number of ways to reorder nums such that the BST formed is identical to the original BST formed from nums.
 * <p>
 * Since the answer may be very large, return it modulo 109 + 7.
 * @author: LiShuai
 * @createDate: 2023/6/19 21:35
 * @version: 1.0
 */

public class LeetCode_1569 {

    private static final int t = 1000000007;

    public static void main(String[] args) {
//        int[] nums = {3, 1, 2, 5, 4, 6};
//        int[] nums = {9, 4, 2, 1, 3, 6, 5, 7, 8, 14, 11, 10, 12, 13, 16, 15, 17, 18};
//        int[] nums = {3, 4, 5, 1, 2};
        int[] nums = {2, 1, 3};
        int i = numOfWays(nums);
        System.out.println(i);
        System.out.println(numOfWays_02(nums));
        System.out.println(numOfWays_03(nums));
//        System.out.println(numOfWays_04(nums));
    }

    public static int numOfWays(int[] nums) {
        int len = nums.length;
        TreeNode root = new TreeNode(nums[0]);
        for (int i = 1; i < len; i++) {
            root = addNode(root, nums[i]);
        }
        int[] ints = numOfWays_tree_process(root);
        return ints[1] - 1;
    }

    private static int[] numOfWays_tree_process(TreeNode root) {
        if (root == null) {
            return new int[]{0, 1};
        }
        int[] left = numOfWays_tree_process(root.left);
        int[] right = numOfWays_tree_process(root.right);
        long i = numOfWays_dp(left[0], right[0]);
        i = (i * left[1] % t) * right[1] % t;
        return new int[]{left[0] + right[0] + 1, (int) i};
    }

    private static long numOfWaysProcess(Long[][] cache, int a, int b) {
        if (cache[a][b] != null) {
            return cache[a][b];
        }
        if (a == 0 || b == 0) {
            cache[a][b] = 1L;
        } else {
            cache[a][b] = (numOfWaysProcess(cache, a - 1, b) + numOfWaysProcess(cache, a, b - 1)) % t;
        }
        return cache[a][b];
    }

    public static long numOfWays_dp(int a, int b) {
        long[] dp = new long[b + 1];
        for (int i = 0; i <= b; i++) {
            dp[i] = 1;
        }

        for (int i = a - 1; i >= 0; i--) {
            for (int j = b - 1; j >= 0; j--) {
                dp[j] = (dp[j] + dp[j + 1]) % t;
            }
        }
        return dp[0];
    }

    public static int numOfWays_02(int[] nums) {
        int len = nums.length;
        TreeNode root = new TreeNode(nums[0]);
        for (int i = 1; i < len; i++) {
            root = addNode(root, nums[i]);
        }
        int a = 0, b = nums[0];
        for (int i : nums) {
            if (i < b) {
                ++a;
            }
        }
        a = Math.max(a, len - a - 1);
        int[][] dp = numOfWays_dp_cache(a, a);
        int[] ints = numOfWays_tree_process(root, dp);
        return ints[1] - 1;
    }

    public static int numOfWays_03(int[] nums) {
        int len = nums.length;
        TreeNode root = new TreeNode(nums[0]);
        for (int i = 1; i < len; i++) {
            root = addNode(root, nums[i]);
        }
        int a = 0, b = nums[0];
        for (int i : nums) {
            if (i < b) {
                ++a;
            }
        }
        a = Math.max(a, len - a - 1);
        int[][] dp = new int[a + 1][a + 1];
        for (int i = 0; i <= a; i++) {
            dp[0][i] = 1;
            dp[i][0] = 1;
        }
        int[] ints = numOfWays_tree_process_02(root, dp);
        return ints[1] - 1;
    }

    private static int[] numOfWays_tree_process(TreeNode root, int[][] dp) {
        if (root == null) {
            return new int[]{0, 1};
        }
        int[] left = numOfWays_tree_process(root.left, dp);
        int[] right = numOfWays_tree_process(root.right, dp);
        int i = (int) ((((long) dp[left[0]][right[0]]) * left[1] % t) * right[1] % t);
        return new int[]{left[0] + right[0] + 1, i};
    }

    private static int[] numOfWays_tree_process_02(TreeNode root, int[][] dp) {
        if (root == null) {
            return new int[]{0, 1};
        }
        int[] left = numOfWays_tree_process_02(root.left, dp);
        int[] right = numOfWays_tree_process_02(root.right, dp);
        int i = (int) ((((long) numOfWays_dp(dp, left[0], right[0])) * left[1] % t) * right[1] % t);
        return new int[]{left[0] + right[0] + 1, i};
    }

    public static int numOfWays_dp(int[][] dp, int a, int b) {

        if (dp[a][b] == 0) {
            dp[a][b] = (numOfWays_dp(dp, a - 1, b) + numOfWays_dp(dp, a, b - 1)) % t;
        }
        return dp[a][b];
    }

    public static int[][] numOfWays_dp_cache(int a, int b) {
        int[][] dp = new int[a + 1][b + 1];
        for (int i = 0; i <= b; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i <= a; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= a; i++) {
            for (int j = 1; j <= b; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % t;
            }
        }
        return dp;
    }

    private static TreeNode addNode(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (root.val > value) {
            root.left = addNode(root.left, value);
        } else {
            root.right = addNode(root.right, value);
        }
        return root;
    }

    public static int numOfWays_04(int[] nums) {
        int[] tree = makeTree(nums);
        int len = tree.length;
        int[] target = new int[len];
        Arrays.fill(target, 1);
        int a = 0, b = nums[0];
        for (int i : nums) {
            if (i < b) {
                ++a;
            }
        }
        a = Math.max(a, nums.length - a - 1);
        int[][] dp = new int[a + 1][a + 1];
        for (int i = 0; i <= a; i++) {
            dp[0][i] = 1;
            dp[i][0] = 1;
        }
        for (int i = len - 1; i >= 0; --i) {
            if (tree[i] != 0) {
                int left = ((i << 1) + 1 < len ? target[(i << 1) + 1] : 1);
                int right = ((i << 1) + 2 < len ? target[(i << 1) + 2] : 1);
                target[i] = (int) (((long) left * right) % t);
                if ((i << 1) + 1 < len && (i << 1) + 2 < len) {
                    int p = numOfWays_dp(dp, tree[(i << 1) + 1], tree[(i << 1) + 2]);
                    target[i] = (int) (((long) p * target[i]) % t);
                }
            }
        }
        return target[0] - 1;
    }

    private static int[] makeTree(int[] nums) {
        int len = nums.length;
        int[] tree = new int[len << 1];
        int[] count = new int[tree.length];

        for (int num : nums) {
            int index = 0;
            while (tree[index] != 0) {
                ++count[index];
                if (tree[index] < num) {
                    index = (index << 1) + 1;
                } else {
                    index = (index << 1) + 2;
                }
            }
            count[index] = 1;
            tree[index] = num;
        }
        return count;
    }

    static class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int value) {
            this.val = value;
        }
    }
}
