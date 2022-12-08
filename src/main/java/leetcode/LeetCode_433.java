package leetcode;

import java.util.*;

/**
 * @description: A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 * <p>
 * Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.
 * <p>
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 * <p>
 * Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.
 * <p>
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 * @author: LISHUAI
 * @createDate: 2022/12/1 1:21
 * @version: 1.0
 */

public class LeetCode_433 {

    private static final char[] TEMP = {'A', 'C', 'G', 'T'};
    private static Map<String, Integer> cache;
    private static Set<String> exists;
    private static int count;

    public static void main(String[] args) {
//        String startGene = "AACCGGTT", endGene = "AACCGGTA";
//        String[] bank = {"AACCGGTA"};
//        String startGene = "AACCGGTT", endGene = "AAACGGTA";
//        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        String startGene = "AAAAAAAA", endGene = "CCCCCCCC";
        String[] bank = {"AAAAAAAA", "AAAAAAAC", "AAAAAACC", "AAAAACCC", "AAAACCCC", "AACACCCC", "ACCACCCC", "ACCCCCCC", "CCCCCCCA", "CCCCCCCC"};

        int vans = minMutation(startGene, endGene, bank);
        System.out.println(vans);
    }

    public static int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> set = new HashSet<>(List.of(bank));
        cache = new HashMap<>();
        exists = new HashSet<>();
        exists.add(startGene);
        int i = minMutationProcess(startGene.toCharArray(), endGene, set);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    private static int minMutationProcess(char[] curGene, String endGene, Set<String> bank) {
        if (endGene.equals(String.valueOf(curGene))) {
            return 0;
        }
        if (cache.containsKey(String.valueOf(curGene))) {
            return cache.get(String.valueOf(curGene));
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < curGene.length; i++) {
            char pc = curGene[i];
            for (char c : TEMP) {
                curGene[i] = c;
                String s = String.valueOf(curGene);
                if (bank.contains(s) && !exists.contains(s)) {
                    exists.add(s);
                    int p = minMutationProcess(curGene, endGene, bank);
                    if (p != Integer.MAX_VALUE) {
                        ++p;
                    }
                    ans = Math.min(ans, p);
//                    cache.put(s, p);
                }
            }
            curGene[i] = pc;
        }
        cache.put(String.valueOf(curGene), ans);
        return ans;
    }
}
