package leetcode;

/**
 * @description: You are given a 0-indexed binary string floor, which represents the colors of tiles on a floor:
 * <p>
 * floor[i] = '0' denotes that the ith tile of the floor is colored black.
 * On the other hand, floor[i] = '1' denotes that the ith tile of the floor is colored white.
 * You are also given numCarpets and carpetLen. You have numCarpets black carpets, each of length carpetLen tiles. Cover the tiles with the given carpets such that the number of white tiles still visible is minimum. Carpets may overlap one another.
 * <p>
 * Return the minimum number of white tiles still visible.
 * @author: LISHUAI
 * @createDate: 2022/12/12 15:33
 * @version: 1.0
 */

public class LeetCode_2209 {

    private static int[] cache;
    private static int cacheIndex;

    public static void main(String[] args) {
        String floor = "10110101";
        int numCarpets = 2, carpetLen = 2;
        int i = minimumWhiteTiles(floor, numCarpets, carpetLen);
        System.out.println(i);
        System.out.println(minimumWhiteTiles_dp(floor, numCarpets, carpetLen));
    }

    public static int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        char[] chars = floor.toCharArray();
        int[] preSum = new int[chars.length];
        preSum[0] = (chars[0] == '1' ? 1 : 0);
        for (int i = 1; i < chars.length; i++) {
            preSum[i] = preSum[i - 1] + (chars[i] - '0');
        }
        int i = minimumWhiteTilesProcess(preSum, numCarpets, carpetLen, 0);
        return preSum[preSum.length - 1] - i;
    }

    private static int minimumWhiteTilesProcess(int[] preSum, int numCarpets, int carpetLen, int sum, int index) {
        if (index >= preSum.length || numCarpets <= 0 || sum <= 0) {
            return Math.max(0, sum);
        }

        int next = Math.min(index + carpetLen, preSum.length);
        int c = index > 0 ? preSum[next - 1] - preSum[index - 1] : preSum[next - 1];
        int p1 = minimumWhiteTilesProcess(preSum, numCarpets - 1, carpetLen, sum - c, next);
        int p2 = minimumWhiteTilesProcess(preSum, numCarpets, carpetLen, sum, index + 1);
        return Math.min(p1, p2);
    }

    private static int minimumWhiteTilesProcess(int[] preSum, int numCarpets, int carpetLen, int index) {
        if (index >= preSum.length || numCarpets <= 0) {
            return 0;
        }

        int next = Math.min(index + carpetLen, preSum.length);
        int c = index > 0 ? preSum[next - 1] - preSum[index - 1] : preSum[next - 1];
        int p1 = minimumWhiteTilesProcess(preSum, numCarpets - 1, carpetLen, next) + c;
        int p2 = minimumWhiteTilesProcess(preSum, numCarpets, carpetLen, index + 1);
        return Math.max(p1, p2);
    }

    public static int minimumWhiteTiles_dp(String floor, int numCarpets, int carpetLen) {
        char[] chars = floor.toCharArray();
        int len = chars.length;
        int[] preSum = new int[len];
        preSum[0] = (chars[0] == '1' ? 1 : 0);
        for (int i = 1; i < chars.length; i++) {
            preSum[i] = preSum[i - 1] + (chars[i] - '0');
        }
        int[][] dp = new int[numCarpets + 1][len + 1];

        for (int i = numCarpets - 1; i >= 0; i--) {
            for (int j = len - 1; j >= 0; j--) {
                int next = Math.min(j + carpetLen, preSum.length);
                int c = j > 0 ? preSum[next - 1] - preSum[j - 1] : preSum[next - 1];
                dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][next] + c);
            }
        }
        return preSum[preSum.length - 1] - dp[0][0];
    }
}
