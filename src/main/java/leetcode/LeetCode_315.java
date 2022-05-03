package leetcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @description: You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * @author: LISHUAI
 * @createDate: 2022/4/25 19:52
 * @version: 1.0
 */

public class LeetCode_315 {

    private static Node treeRoot;

    public static void main(String[] args) throws FileNotFoundException {
//        int[] nums = {5, 2, 6, 1};
//        int[] nums = {1, 9, 7, 8, 5};
//        List<Integer> list = countSmaller_02(nums);
//        for (int i : list) {
//            System.out.println(i);
//        }
        Scanner scanner = new Scanner(new File("src/main/resources/bigNums.txt"));
        String line = scanner.nextLine();
        String[] split = line.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        System.out.println(nums.length);

        List<Integer> list = countSmaller_02(nums);
        System.out.println(list.size());
    }

    public static List<Integer> countSmaller(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int c = nums[i], s = 0;
            for (int k : map.keySet()) {
                if (k >= c) {
                    continue;
                }
                s += map.get(k);
            }
            ans.add(s);
            map.put(nums[i], map.get(nums[i]) - 1);
        }

        return ans;
    }

    public static List<Integer> countSmaller_02(int[] nums) {
        treeRoot = makeTree(nums);
        List<Integer> ans = new ArrayList<>();
        System.out.println("treeRoot left : " + treeRoot.leftSize + ", treeRoot right : " + treeRoot.rightSize);

        System.out.println(treeHight(treeRoot));
        for (int i = 0; i < nums.length; i++) {
//            ans.add(nums.length - i - getMin(root, nums[i], 0));
//            System.out.println(i);
            ans.add(getMin(treeRoot, nums[i], 0));
        }
        return ans;
    }

    private static void showTree(Node root) {

    }

    private static int treeHight(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(treeHight(root.left), treeHight(root.right)) + 1;
    }

    private static Node makeTree(int[] nums) {
        Node root = null;
//        int right = nums.length >>> 1, left = right - 1;
//        if ((nums.length & 1) == 1) {
//            root = add(root, nums[right++]);
//        }
//
//        while (left >= 0 && right < nums.length) {
//            root = add(root, nums[left--]);
//            root = add(root, nums[right++]);
//        }
//        return root;
        makeTreeProcess(nums, 0, nums.length - 1);
        return treeRoot;
    }

    private static void makeTreeProcess(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        int c = left + (right - left) / 2;
        treeRoot = add(treeRoot, nums[c]);
        makeTreeProcess(nums, left, c - 1);
        makeTreeProcess(nums, c + 1, right);
    }

//    private static Node makeTreeProcess(int[] nums, int left, int right) {
//        if (left > right) {
//            return null;
//        }
//        int c = left + (right - left) / 2;
//        Node root = new Node(nums[c]);
//        root.curSize = 1;
//        root.left = makeTreeProcess(nums, left, c - 1);
//        root.right = makeTreeProcess(nums, c + 1, right);
//        root.leftSize = root.left == null ? 0 : root.left.leftSize + root.left.rightSize + root.left.curSize;
//        root.rightSize = root.right == null ? 0 : root.right.leftSize + root.right.rightSize + root.right.curSize;
//        return root;
//    }

    private static int getMin(Node root, int val, int size) {
        int c = 0, pre = 0;
        Node node = root;
        while (root != null) {
            pre = c;
            c = root.val - val;
            node = root;

            if (c > 0) {
                root.leftSize--;
                root = root.left;
            } else if (c < 0) {
                size += root.leftSize + root.curSize;
                root.rightSize--;
                root = root.right;
            } else {
                root.curSize--;
                break;
            }
        }
        int ans = size + root.leftSize;
//        if (root.curSize == 0) {
//            treeRoot = delete(treeRoot, val);
//        }

        return ans;
    }

    private static Node deleteMin(Node root) {
        if (root.left == null)
            return root.right;
        root.left = deleteMin(root.left);
        root.leftSize = root.left == null ? 0 : root.left.leftSize + root.left.rightSize + root.left.curSize;
        root.rightSize = root.right == null ? 0 : root.right.leftSize + root.right.rightSize + root.right.curSize;
        return root;
    }

    private static Node add(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            root.curSize = 1;
            return root;
        }
        int c = root.val - val;
        if (c > 0) {
            root.leftSize++;
            root.left = add(root.left, val);
        } else if (c < 0) {
            root.rightSize++;
            root.right = add(root.right, val);
        } else {
            root.curSize++;
        }
        return root;
    }

    private static Node min(Node root) {
        if (root == null)
            return null;
        if (root.left == null)
            return root;
        return min(root.left);
    }

    private static Node delete(Node root, int val) {
        if (root == null)
            return null;
        int i = root.val - val;
        if (i > 0)  //  如果要删除的节点大于当前节点，在右侧继续寻找
            root.right = delete(root.right, val);
        else if (i < 0) //  如果要删除的节点小于当前节点，在左侧继续寻找
            root.left = delete(root.left, val);
        else {      // 找到要删除的节点，即当前节点
            if ((root.left == null && root.right == null))
                return null;
            if (root.left == null)  // 如果当前节点的左子节点为空，此时其父节点，当前节点，其右子节点可以视为链表的形式。直接用其右子节点代替当前节点的位置即可
                return root.right;
            if (root.right == null) // 同上
                return root.left;
            //  当前节点同时存在左右字节点
            Node x = root;  // 保存当前节点
            root = min(root.right); //  获取当前节点右侧的最小节点
            root.right = deleteMin(x.right);    //  删除当前节点右侧的最小节点，并用最小节点的右子节点引用当前节点的右子节点
            root.left = x.left; //  用最小几点的左子节点引用当前节点的左子节点
        }

        root.leftSize = root.left == null ? 0 : root.left.leftSize + root.left.rightSize + root.left.curSize;
        root.rightSize = root.right == null ? 0 : root.right.leftSize + root.right.rightSize + root.right.curSize;
        return root;    //  返回当前节点（已经被替换后的当前节点，即要删除的节点的左侧最小节点）
    }


    static class Node {
        int val, leftSize = 0, rightSize = 0, curSize = 0;
        Node left, right;

        public Node(int val) {
            this.val = val;
        }
    }
}
