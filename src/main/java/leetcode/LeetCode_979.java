package leetcode;

/**
 * @description: You are given the root of a binary tree with n nodes where each node in the tree has node.val coins.
 * There are n coins in total throughout the whole tree.
 * <p>
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.
 * A move may be from parent to child, or from child to parent.
 * <p>
 * Return the minimum number of moves required to make every node have exactly one coin.
 * @author: LISHUAI
 * @createDate: 2022/5/24 18:16
 * @version: 1.0
 */

public class LeetCode_979 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        int i = distributeCoins(root);
        System.out.println(i);
    }

    public static int distributeCoins(TreeNode root) {
        Info dfs = dfs(root);
//        Ans ans = distributeCoinsProcess(dfs, 0, 0);
//        return ans.road;
        int[] ans = distributeCoinsProcess(dfs, 0);
        return ans[1];
    }

    private static Ans distributeCoinsProcess(Info info, int road, int preVal) {
        if (info == null) {
            return new Ans();
        }

        int curValSize = info.curValSize + preVal - 1;
        if (curValSize >= 0) {
            info.curValSize = 1;
        } else {
            curValSize = 0;
        }
        int maxNext = 0, minNext = 0;
        Info max, min;
        if (info.rightValSize - info.rightSize > info.leftValSize - info.leftSize) {
            max = info.right;
            min = info.left;
            maxNext = Math.max(maxNext, info.rightSize - info.rightValSize);
            minNext = Math.max(minNext, info.leftSize - info.leftValSize);
        } else {
            max = info.left;
            min = info.right;
            maxNext = Math.max(maxNext, info.leftSize - info.leftValSize);
            minNext = Math.max(minNext, info.rightSize - info.rightValSize);
        }
        if (curValSize < maxNext) {
            maxNext = curValSize;
            curValSize = 0;
        } else {
            curValSize -= maxNext;
        }
        Ans maxAns = distributeCoinsProcess(max, maxNext, maxNext);
        curValSize = curValSize + maxAns.reSize;
        if (curValSize < minNext) {
            minNext = curValSize;
            curValSize = 0;
        } else {
            curValSize -= minNext;
        }
        Ans minAns = distributeCoinsProcess(min, minNext, minNext);
        Ans curAns = new Ans();

        curAns.reSize = minAns.reSize + curValSize;
        if (info.curValSize != 1) {
            curAns.reSize--;
        }
        curAns.road = minAns.road + maxAns.road + road + curAns.reSize;
        return curAns;
    }

    private static int[] distributeCoinsProcess(Info info, int preVal) {
        if (info == null) {
            return new int[2];
        }
        int curVal = info.curValSize + preVal - 1;
        int leftSize = Math.max(0, info.leftSize - info.leftValSize);
        int rightSize = Math.max(0, info.rightSize - info.rightValSize);
        curVal -= (leftSize + rightSize);
        int[] left = distributeCoinsProcess(info.left, leftSize);
        int[] right = distributeCoinsProcess(info.right, rightSize);
        left[0] += right[0] + curVal;
        left[1] += right[1] + preVal + left[0];
        return left;
    }


    private static Info dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info info = new Info();
        Info left = dfs(root.left);
        Info right = dfs(root.right);
        if (left != null) {
            info.leftSize = left.leftSize + left.rightSize + 1;
            info.leftValSize = left.leftValSize + left.rightValSize + left.curValSize;
            info.left = left;
        }

        if (right != null) {
            info.rightSize = right.leftSize + right.rightSize + 1;
            info.rightValSize = right.leftValSize + right.rightValSize + right.curValSize;
            info.right = right;
        }

        info.curValSize = root.val;
        return info;
    }

    static class Ans {
        int reSize, road;

        public Ans() {
        }
    }

    static class Info {
        Info left, right;
        int leftSize, rightSize;
        int leftValSize, rightValSize, curValSize;

        public Info() {
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
