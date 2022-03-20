package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @description: Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 * @author: LISHUAI
 * @createDate: 2021/11/28 16:34
 * @version: 1.0
 */

public class LeetCode_347 {

    public static void main(String[] args) {

        int[] arr = new int[]{4, 1, -1, 2, -1, 2, 3};

        int[] ints = topKFrequent(arr, 2);

        for (int i : ints) {

            System.out.println(i);
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {

        int[] result = new int[k];

        int index = -1;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {

            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Integer i : map.keySet()) {

            if (index != k - 1) {

                result[++index] = i;

                int cur = index;

                while (index > 0 && map.get(result[index]) > map.get(result[index - 1])) {

                    int r = result[index];

                    result[index] = result[index - 1];

                    result[index - 1] = r;

                    index--;
                }

                index = cur;

                continue;
            }

            if (map.get(i) > map.get(result[index])) {

                result[index] = i;

                while (index > 0 && map.get(result[index]) > map.get(result[index - 1])) {

                    int r = result[index];

                    result[index] = result[index - 1];

                    result[index - 1] = r;

                    index--;
                }

                index = k - 1;
            }
        }


        return result;
    }

    public static int[] topKFrequent_02(int[] nums, int k) {
        HashMap<Integer, Node> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new Node(num));
            } else {
                map.get(num).count++;
            }
        }
        PriorityQueue<Node> heap = new PriorityQueue<>(new CountComparator());
        for (Node node : map.values()) {
            if (heap.size() < k || (heap.size() == k && node.count > heap.peek().count)) {
                heap.add(node);
            }
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int[] ans = new int[k];
        int index = 0;
        while (!heap.isEmpty()) {
            ans[index++] = heap.poll().num;
        }
        return ans;
    }

    public static class Node {
        public int num;
        public int count;

        public Node(int k) {
            num = k;
            count = 1;
        }
    }

    public static class CountComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.count - o2.count;
        }

    }
}
