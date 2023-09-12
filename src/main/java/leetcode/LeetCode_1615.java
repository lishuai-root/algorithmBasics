package leetcode;

/**
 * @description: There is an infrastructure of n cities with some number of roads connecting these cities. Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.
 * <p>
 * The network rank of two different cities is defined as the total number of directly connected roads to either city. If a road is directly connected to both cities, it is only counted once.
 * <p>
 * The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.
 * <p>
 * Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.
 * @author: LiShuai
 * @createDate: 2023/8/20 0:42
 * @version: 1.0
 */

public class LeetCode_1615 {

    public static int maximalNetworkRank(int n, int[][] roads) {
        int[] cache = new int[n];
        boolean[][] vised = new boolean[n][n];
        for (int[] edge : roads) {
            cache[edge[0]]++;
            cache[edge[1]]++;
            vised[edge[0]][edge[1]] = true;
            vised[edge[1]][edge[0]] = true;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int c = vised[i][j] || vised[j][i] ? cache[i] + cache[j] - 1 : cache[i] + cache[j];
                ans = Math.max(ans, c);
            }
        }
        return ans;
    }
}
