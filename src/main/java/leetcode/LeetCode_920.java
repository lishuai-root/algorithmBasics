package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: Your music player contains n different songs. You want to listen to goal songs (not necessarily different) during your trip. To avoid boredom, you will create a playlist so that:
 * <p>
 * Every song is played at least once.
 * A song can only be played again only if k other songs have been played.
 * Given n, goal, and k, return the number of possible playlists that you can create. Since the answer can be very large, return it modulo 109 + 7.
 * @author: LiShuai
 * @createDate: 2023/8/6 18:30
 * @version: 1.0
 */

public class LeetCode_920 {

    public static int mod = 1000000007;
    public static int limit = 100;
    // 阶乘表
    public static long[] fac = new long[limit + 1];
    // 阶乘结果的乘法逆元表
    public static long[] inv = new long[limit + 1];

    static {
        fac[0] = inv[0] = 1;
        for (int i = 1; i <= limit; i++) {
            fac[i] = ((long) i * fac[i - 1]) % mod;
        }
        // 费马小定理计算乘法逆元
//		for (int i = 1; i <= limit; i++) {
//			inv[i] = power(fac[i], mod - 2);
//		}
        // 费马小定理计算乘法逆元，优化如下
        // 这一块叫：阶乘的逆元倒推
        inv[limit] = power(fac[limit], mod - 2);
        for (int i = limit; i > 1; i--) {
            inv[i - 1] = ((long) i * inv[i]) % mod;
        }
    }

    public static void main(String[] args) {
        int n = 16, goal = 16, k = 4;
        int i = numMusicPlaylists_teacher(n, goal, k);
        System.out.println(i);
    }

    public static int numMusicPlaylists(int n, int goal, int k) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -(k + 1));
        Map<Integer, Integer> map = new HashMap<>();
        return numMusicPlaylistsProcess(n, goal, k, 0, cache, map);
    }

    private static int numMusicPlaylistsProcess(int n, int goal, int k, int index, int[] cache, Map<Integer, Integer> map) {
        if (index >= goal) {
            return map.size() == n ? 1 : 0;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (index - cache[i] > k) {
                int p = cache[i];
                cache[i] = index;
                map.put(i, map.getOrDefault(i, 0) + 1);
                ans += numMusicPlaylistsProcess(n, goal, k, index + 1, cache, map);
                ans %= 1000000007;
                cache[i] = p;
                int integer = map.get(i);
                if (integer == 1) {
                    map.remove(i);
                } else {
                    map.put(i, integer - 1);
                }
            }
        }
        return ans;
    }

    // x的n次方，% mod之后，是多少？
    public static long power(long x, int n) {
        long ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = (ans * x) % mod;
            }
            x = (x * x) % mod;
            n >>= 1;
        }
        return ans;
    }

    // n * logn
    public static int numMusicPlaylists_teacher(int n, int l, int k) {
        long cur, ans = 0, sign = 1;
        for (int i = 0; i <= n - k; i++, sign = sign == 1 ? (mod - 1) : 1) {
            // cur ->
            // fac[n] -> n! % mod 的结果！
            // inv[i] -> i! 的逆元！
            // inv[n - k - i] -> (n - k - i)! 的逆元
            // sign * 1 -> 1
            //      * -1 -> mod - 1
            cur = (sign * power(n - k - i, l - k)) % mod;
            cur = (cur * fac[n]) % mod;
            cur = (cur * inv[i]) % mod;
            cur = (cur * inv[n - k - i]) % mod;
            ans = (ans + cur) % mod;
        }
        return (int) ans;
    }
}
