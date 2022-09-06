package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @description: We are given n different types of stickers. Each sticker has a lowercase English word on it.
 * <p>
 * You would like to spell out the given string target by cutting individual letters from your collection of stickers and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
 * <p>
 * Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.
 * <p>
 * Note: In all test cases, all words were chosen randomly from the 1000 most common US English words, and target was chosen as a concatenation of two random words.
 * @author: LISHUAI
 * @createDate: 2022/8/25 20:23
 * @version: 1.0
 */

public class LeetCode_691 {

    public static void main(String[] args) {
//        String[] stickers = {"with", "example", "science"};
//        String target = "thehat";
//        String[] stickers = {"notice", "possible"};
//        String target = "basicbasic";
        String[] stickers = {"final", "figure", "danger", "fish", "some", "product", "son", "seed", "crease", "rail", "even", "death", "end", "sit", "live", "behind", "start", "enough", "much", "between", "test", "is", "happy", "we", "north", "complete", "month", "reach", "excite", "stay", "job", "fell", "letter", "noun", "seat", "exact", "than", "ago", "protect", "kept", "this", "plain", "flow", "face", "bird", "sand", "rock", "roll", "root", "fact"};
        String target = "lakeblue";
//        String[] stickers = {"seven", "old", "stream", "century", "energy", "period", "an", "proper", "together", "sight", "carry", "milk", "appear", "winter", "field", "rather", "caught", "danger", "lake", "shall", "machine", "few", "other", "test", "got", "wing", "map", "finish", "though", "observe", "log", "they", "foot", "path", "eat", "glad", "must", "bar", "did", "of", "clear", "work", "rule", "quotient", "produce", "clean", "wild", "grass", "example", "left"};
//        String target = "weresurprise";
        int i = minStickers_other(stickers, target);
        System.out.println(i);
    }

    public static int minStickers(String[] stickers, String target) {
        int[] count = new int[26];
        int cur = 0;
        for (int i = 0; i < target.length(); i++) {
            cur |= (1 << target.charAt(i) - 'a');
            count[target.charAt(i) - 'a']++;
        }
        Map<String, Integer> map = new HashMap<>(stickers.length);
        for (String line : stickers) {
            int c = 0;
            for (int i = 0; i < line.length(); i++) {
                c |= (1 << line.charAt(i) - 'a');
            }
            map.put(line, c);
        }
        int i = minStickersProcess(stickers, count, 0, cur, map);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    private static int minStickersProcess(String[] stickers, int[] count, int index, int cur, Map<String, Integer> map) {
        if (cur == 0) {
            return 0;
        }
        if (index >= stickers.length) {
            return Integer.MAX_VALUE;
        }
        int p1 = minStickersProcess(stickers, count, index + 1, cur, map);
        int p2 = Integer.MAX_VALUE;
        if ((map.get(stickers[index]) & cur) != 0) {
            String line = stickers[index];
            for (int i = 0; i < line.length(); i++) {
                int c = line.charAt(i) - 'a';
                count[c]--;
                if (count[c] <= 0 && (cur & (1 << c)) != 0) {
                    cur ^= (1 << c);
                }
            }
            p2 = minStickersProcess(stickers, count, index, cur, map);
            for (int i = 0; i < line.length(); i++) {
                count[line.charAt(i) - 'a']++;
            }
        }
        if (p2 != Integer.MAX_VALUE) {
            ++p2;
        }
        return Math.min(p1, p2);
    }


    public static int minStickers_bfs(String[] stickers, String target) {
        int[] sum = new int[28];
        int[] count = new int[stickers.length];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[27] - b[27];
        });
        int cur = 0;

        for (int i = 0; i < target.length(); i++) {
            sum[target.charAt(i) - 'a']++;
            cur |= (1 << target.charAt(i) - 'a');
        }
        for (int i = 0; i < stickers.length; i++) {
            int c = 0;
            String line = stickers[i];
            for (int j = 0; j < line.length(); j++) {
                c |= (1 << line.charAt(j) - 'a');
            }
            count[i] = c;
        }
        sum[26] = cur;
        sum[27] = 0;

        queue.offer(sum);

        while (!queue.isEmpty()) {
            int[] curs = queue.poll();
            if (curs[26] == 0) {
                return curs[27];
            }

            for (int i = 0; i < stickers.length; i++) {
                if ((count[i] & curs[26]) != 0) {
                    sum = Arrays.copyOf(curs, curs.length);
                    String line = stickers[i];
                    for (int j = 0; j < line.length(); j++) {
                        int c = line.charAt(j) - 'a';
                        sum[c]--;
                        if (sum[c] <= 0 && (sum[26] & (1 << c)) != 0) {
                            sum[26] ^= (1 << c);
                        }
                    }
                    sum[27]++;
                    queue.offer(sum);
                }
            }
        }
        return -1;
    }

    public static int minStickers_other(String[] stickers, String target) {
        int n = stickers.length;
        int[][] chs = new int[n][26];

        for (int i = 0; i < n; i++) { //prepare chs map for all the stickers
            String sticker = stickers[i];
            for (char ch : sticker.toCharArray()) {
                chs[i][ch - 'a']++;
            }
        }
        int ret = minStickersHelper(chs, target, new HashMap<>());
        return ret == Integer.MAX_VALUE - 1 ? -1 : ret; //if any target char is unmatched by all of the sticker return -1
    }

    private static int minStickersHelper(int[][] chArr, String target, Map<String, Integer> memo) {
        //Base
        if (target.length() <= 0)
            return 0;
        if (memo.containsKey(target))
            return memo.get(target);

        int min = Integer.MAX_VALUE - 1; //Integer.MAX_VALUE-1 to avoid integer overflow
        for (int[] chs : chArr) {
            int len = target.length();
            String rem = getRemainingStr(chs.clone(), target);
            if (rem.length() == len) //if none of the target char is matched with this sticker
                continue;
            min = Math.min(min, 1 + minStickersHelper(chArr, rem, memo));
        }
        memo.put(target, min);
        return min;
    }

    private static String getRemainingStr(int[] chs, String target) { //consume all candidate chars from this sticker
        StringBuilder sb = new StringBuilder();
        if (chs[target.charAt(0) - 'a'] == 0) //if this sticker doesn't have very first target char
            return target;
        for (char ch : target.toCharArray()) {
            if (chs[ch - 'a'] > 0)
                chs[ch - 'a']--;
            else
                sb.append(ch);
        }
        return sb.toString();
    }
}
