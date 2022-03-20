package leetcode;

import java.util.*;
import java.util.stream.Stream;

/**
 * @description: Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
 * <p>
 * Implement the FreqStack class:
 * <p>
 * FreqStack() constructs an empty frequency stack.
 * void push(int val) pushes an integer val onto the top of the stack.
 * int pop() removes and returns the most frequent element in the stack.
 * If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.
 * @author: LISHUAI
 * @createDate: 2022/3/19 16:48
 * @version: 1.0
 */

public class LeetCode_895 {

    public static void main(String[] args) {
        FreqStack_03 freqStack = new FreqStack_03();
        int[] arr = new int[]{5, 7, 5, 7, 4, 5};
        for (int i : arr) {
            freqStack.push(i);
        }
//        freqStack.show();
        freqStack.showMap();
        System.out.println(freqStack.r);
        System.out.println("-----------------");
//        freqStack.pop();
//        freqStack.show();
        freqStack.showMap();
        System.out.println("------------------");
        for (int i = 0; i < 4; i++) {
            System.out.println(freqStack.pop());
        }
        freqStack.showMap();

        TreeSet<Integer> set = new TreeSet<>();

    }

    static class FreqStack_03 {

        TreeSet<Integer> set;
        Map<Integer, List<Integer>> map;
        int r;

        public FreqStack_03() {
            set = new TreeSet<Integer>((a, b) -> {
                List<Integer> l1 = map.get(a);
                List<Integer> l2 = map.get(b);
                int ans = l1.size() - l2.size();
                if (ans == 0) {
                    ans = l1.get(l1.size() - 1) - l2.get(l2.size() - 1);
                }
                return -ans;
            });
            map = new HashMap<>();
            r = 0;
        }


        public void showMap() {
            Iterator<Integer> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                int i = iterator.next();
                System.out.print(i + " : ");
                List<Integer> list = map.get(i);
                for (int j : list) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }

        }

        public void push(int val) {
            List<Integer> list = map.get(val);
            if (list == null) {
                list = new ArrayList<>();
                map.put(val, list);
            } else {
                set.remove(val);
            }
            list.add(r++);
            set.add(val);
        }

        public int pop() {

            int val = set.first();
            set.remove(val);
            List<Integer> list = map.get(val);
            list.remove(list.size() - 1);

            if (!list.isEmpty()) {
                set.add(val);
            } else {
                map.remove(val);
            }
            return val;
        }


        class mNode {
            int value;
            List<Integer> list;

            public mNode(int value, List<Integer> list) {
                this.value = value;
                this.list = list;
            }
        }
    }


    static class FreqStack {

        PriorityQueue<Integer> queue;
        Map<Integer, List<Integer>> map;
        int r;

        public FreqStack() {
            queue = new PriorityQueue<Integer>((a, b) -> {
                List<Integer> l1 = map.get(a);
                List<Integer> l2 = map.get(b);
                int ans = l1.size() - l2.size();
                if (ans == 0) {
                    ans = l1.get(l1.size() - 1) - l2.get(l2.size() - 1);
                }
                return -ans;
            });
            map = new HashMap<>();
            r = 0;
        }

        public void show() {
            Stream<Integer> stream = queue.stream();
            stream.forEach(System.out::println);
        }

        public void showMap() {
            Iterator<Integer> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                int i = iterator.next();
                System.out.print(i + " : ");
                List<Integer> list = map.get(i);
                for (int j : list) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }

        }

        public void push(int val) {
            List<Integer> list = map.get(val);
            if (list == null) {
                list = new ArrayList<>();
                map.put(val, list);
            } else {
                queue.remove(val);
            }
            list.add(r++);
            queue.offer(val);
        }

        public int pop() {

            int val = queue.poll();
            List<Integer> list = map.get(val);
            list.remove(list.size() - 1);

            if (!list.isEmpty()) {
                queue.offer(val);
            } else {
                map.remove(val);
            }
            return val;
        }
    }

    class FreqStack_02 {

        private Queue<int[]> queue;
        private Map<Integer, Integer> map;
        private int index;

        public FreqStack_02() {
            index = 0;
            queue = new PriorityQueue<>((i, j) -> j[1] == i[1] ? j[2] - i[2] : j[1] - i[1]);
            map = new HashMap<>();
        }

        public void push(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            queue.offer(new int[]{val, map.get(val), index});
            index++;
        }

        public int pop() {
            int[] top = queue.poll();
            map.put(top[0], top[1] - 1);
            return top[0];
        }
    }
}
