package leetcode;

import kotlin.Pair;

import java.util.*;

/**
 * @description: You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * <p>
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 * <p>
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 * @author: LiShuai
 * @createDate: 2023/6/27 20:45
 * @version: 1.0
 */

public class LeetCode_373 {

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 2};
        int[] nums2 = {1, 2, 3};
        int k = 10;
        List<List<Integer>> lists = kSmallestPairs(nums1, nums2, k);
        System.out.println(lists.toString());
    }

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        TreeMap<List<Integer>, Integer> lists = new TreeMap<>((a, b) -> {
            if ((a.get(0) + a.get(1)) != (b.get(0) + b.get(1))) {
                return (a.get(0) + a.get(1)) - (b.get(0) + b.get(1));
            }
            return a.get(2) - b.get(2);
        });
        int len = Math.min(k, Math.max(nums1.length, nums2.length));
        int l1 = Math.min(len, nums1.length);
        int l2 = Math.min(len, nums2.length);
        int size = 0, index = 0;
        for (int i = 0; i < l2; i++) {
            for (int j = 0; j < l1; j++) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(nums1[j]);
                list.add(nums2[i]);
                list.add(index++);
                lists.put(list, lists.getOrDefault(list, 0) + 1);
                ++size;
                if (size > k) {
                    List<Integer> l = lists.lastKey();
                    if (lists.get(l) > 1) {
                        lists.put(l, lists.get(l) - 1);
                    } else {
                        lists.remove(l);
                    }
                    --size;
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>(k);
        for (List<Integer> list : lists.keySet()) {
            int s = lists.get(list);
            for (int i = 0; i < s; i++) {
                ArrayList<Integer> l = new ArrayList<>();
                l.add(list.get(0));
                l.add(list.get(1));
                ans.add(l);
            }
        }
        return ans;
    }

    public List<List<Integer>> kSmallestPairs_other(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        List<List<Integer>> ans = new ArrayList<>();
        Set<Pair<Integer, Integer>> visited = new HashSet<>();

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        minHeap.offer(new int[]{nums1[0] + nums2[0], 0, 0});
        visited.add(new Pair<Integer, Integer>(0, 0));

        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int i = top[1];
            int j = top[2];

            ans.add(List.of(nums1[i], nums2[j]));

            if (i + 1 < m && !visited.contains(new Pair<Integer, Integer>(i + 1, j))) {
                minHeap.offer(new int[]{nums1[i + 1] + nums2[j], i + 1, j});
                visited.add(new Pair<Integer, Integer>(i + 1, j));
            }

            if (j + 1 < n && !visited.contains(new Pair<Integer, Integer>(i, j + 1))) {
                minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
                visited.add(new Pair<Integer, Integer>(i, j + 1));
            }
        }

        return ans;
    }
}
