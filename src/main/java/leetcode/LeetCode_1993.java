package leetcode;

import java.util.*;

/**
 * @description: You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i] is the parent of the ith node.
 * The root of the tree is node 0, so parent[0] = -1 since it has no parent. You want to design a data structure that allows users to lock, unlock, and upgrade nodes in the tree.
 * <p>
 * The data structure should support the following functions:
 * <p>
 * Lock: Locks the given node for the given user and prevents other users from locking the same node. You may only lock a node using this function if the node is unlocked.
 * Unlock: Unlocks the given node for the given user. You may only unlock a node using this function if it is currently locked by the same user.
 * Upgrade: Locks the given node for the given user and unlocks all of its descendants regardless of who locked it. You may only upgrade a node if all 3 conditions are true:
 * The node is unlocked,
 * It has at least one locked descendant (by any user), and
 * It does not have any locked ancestors.
 * Implement the LockingTree class:
 * <p>
 * LockingTree(int[] parent) initializes the data structure with the parent array.
 * lock(int num, int user) returns true if it is possible for the user with id user to lock the node num, or false otherwise. If it is possible, the node num will become locked by the user with id user.
 * unlock(int num, int user) returns true if it is possible for the user with id user to unlock the node num, or false otherwise. If it is possible, the node num will become unlocked.
 * upgrade(int num, int user) returns true if it is possible for the user with id user to upgrade the node num, or false otherwise. If it is possible, the node num will be upgraded.
 * @author: LISHUAI
 * @createDate: 2021/12/23 19:48
 * @version: 1.0
 */

public class LeetCode_1993 {

    static class LockingTree {

        Map<Integer, List<Integer>> map;

        int[] locks;

        int[] parent;

        public LockingTree(int[] parent) {

            this.parent = parent;

            locks = new int[parent.length];

            map = makeList(parent);
        }

        private static Map<Integer, List<Integer>> makeList(int[] parent) {

            Map<Integer, List<Integer>> map = new HashMap<>();

            List<Integer> list;

            for (int i = 1; i < parent.length; i++) {

                if ((list = map.get(parent[i])) == null) {

                    list = new ArrayList<>();

                    map.put(parent[i], list);
                }

                list.add(i);
            }

            return map;
        }

        public boolean lock(int num, int user) {

            if (locks[num] != 0) {

                return false;
            }

            locks[num] = user;

            return true;
        }

        public boolean unlock(int num, int user) {

            if (locks[num] != user) {

                return false;
            }

            locks[num] = 0;

            return true;
        }

        public boolean upgrade(int num, int user) {

            if (locks[num] != 0) {

                return false;
            }

            int p = parent[num];

            while (p != -1) {

                if (locks[p] != 0) {

                    return false;
                }

                p = parent[p];
            }

            Stack<Integer> stack = new Stack<>();

            if (map.containsKey(num)) {

                stack.addAll(map.get(num));
            }

            p = num;

            while (!stack.isEmpty()) {

                p = stack.pop();

                if (map.containsKey(p)) {

                    stack.addAll(map.get(p));
                }

                if (locks[p] != 0) {

                    break;
                }
            }

            if (stack.isEmpty() && locks[p] == 0) {

                return false;
            }

            locks[p] = 0;

            while (!stack.isEmpty()) {

                p = stack.pop();

                if (map.containsKey(p)) {

                    stack.addAll(map.get(p));
                }

                if (locks[p] != 0) {

                    locks[p] = 0;
                }
            }

            locks[num] = user;

            return true;
        }
    }

    static class LockingTree_02 {

        private TreeNode root;

        private Map<Integer, List<Integer>> map;

        private int[] locks;

        private int[] parent;

        public LockingTree_02(int[] parent) {

            this.parent = parent;

            locks = new int[parent.length];

            map = makeList(parent);

            root = makeTree(parent, map, 0);

        }

        private static Map<Integer, List<Integer>> makeList(int[] parent) {

            Map<Integer, List<Integer>> map = new HashMap<>();

            List<Integer> list;

            for (int i = 1; i < parent.length; i++) {

                if ((list = map.get(parent[i])) == null) {

                    list = new ArrayList<>();

                    map.put(parent[i], list);
                }

                list.add(i);
            }

            return map;
        }

        private static TreeNode makeTree(int[] parent, Map<Integer, List<Integer>> map, int cur) {

            return process(parent, map, cur, null);
        }

        private static TreeNode process(int[] parent, Map<Integer, List<Integer>> map, int cur, TreeNode p) {

            if (!map.containsKey(cur)) {

                return null;
            }

            TreeNode node = new TreeNode(cur, p);

            List<Integer> list = map.get(cur);

            int left = Math.min(list.get(0), list.get(1));

            int right = Math.max(list.get(0), list.get(1));

            node.left = process(parent, map, left, node);

            node.right = process(parent, map, right, node);

            return node;
        }

        public boolean lock(int num, int user) {

            if (locks[num] != 0) {

                return false;
            }

            locks[num] = user;

            return true;
        }

        public boolean unlock(int num, int user) {

            if (locks[num] != user) {

                return false;
            }

            locks[num] = 0;

            return true;
        }

        public boolean upgrade(int num, int user) {

            if (locks[num] != 0) {

                return false;
            }

            int p = parent[num];

            while (p != -1) {

                if (locks[p] != 0) {

                    return false;
                }

                p = parent[p];
            }

            return false;
        }

        static class TreeNode {

            TreeNode left, right, parent;

            int lock;

            int val;

            TreeNode() {
            }

            TreeNode(int val, TreeNode parent) {

                this.val = val;

                this.parent = parent;
            }
        }
    }
}
