package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
 * <p>
 * Recall that:
 * <p>
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
 * The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.
 * @author: LISHUAI
 * @createDate: 2022/5/17 20:15
 * @version: 1.0
 */

public class LeetCode_1123 {


    public static void main(String[] args) {
//        int[] nums = {3, 5, 1, 6, 2, 0, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, 7, 4};
        int[] nums = {1, 25, 2, Integer.MAX_VALUE, 49, 18, 3, 112, 59, 33, 29, 4, 5, Integer.MAX_VALUE, 128, 125, 153, 75, 43, Integer.MAX_VALUE, 38, 7, 8, 34, 6, 196, Integer.MAX_VALUE, Integer.MAX_VALUE, 180, Integer.MAX_VALUE, 295, 237, 96, 53, 55, 51, 40, 10, 135, 11, 12, 56, 199, 9, 47, 253, Integer.MAX_VALUE, 209, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 286, Integer.MAX_VALUE, Integer.MAX_VALUE, 159, 230, 113, 200, 71, 52, 85, 63, 124, 21, Integer.MAX_VALUE, 145, 15, 19, 14, 16, 78, 225, Integer.MAX_VALUE, Integer.MAX_VALUE, 13, 41, 62, 103, 265, Integer.MAX_VALUE, 261, 256, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 263, Integer.MAX_VALUE, 175, 168, Integer.MAX_VALUE, Integer.MAX_VALUE, 193, 118, Integer.MAX_VALUE, 229, Integer.MAX_VALUE, Integer.MAX_VALUE, 77, 74, 192, Integer.MAX_VALUE, 23, 70, 299, Integer.MAX_VALUE, 20, 87, 31, 22, 28, 17, 156, 208, Integer.MAX_VALUE, 102, Integer.MAX_VALUE, Integer.MAX_VALUE, 80, 50, Integer.MAX_VALUE, 65, Integer.MAX_VALUE, 221, 122, 201, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 277, 250, 198, 240, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 141, 137, Integer.MAX_VALUE, Integer.MAX_VALUE, 129, 114, 109, 143, Integer.MAX_VALUE, Integer.MAX_VALUE, 30, 24, 291, 79, Integer.MAX_VALUE, Integer.MAX_VALUE, 121, Integer.MAX_VALUE, 97, Integer.MAX_VALUE, 178, Integer.MAX_VALUE, 26, Integer.MAX_VALUE, 32, Integer.MAX_VALUE, 39, 37, Integer.MAX_VALUE, 172, Integer.MAX_VALUE, Integer.MAX_VALUE, 212, Integer.MAX_VALUE, Integer.MAX_VALUE, 146, Integer.MAX_VALUE, 60, 86, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 202, Integer.MAX_VALUE, Integer.MAX_VALUE, 289, 169, 235, 163, Integer.MAX_VALUE, Integer.MAX_VALUE, 292, Integer.MAX_VALUE, 213, Integer.MAX_VALUE, 173, 152, 160, 67, 76, 44, 27, Integer.MAX_VALUE, Integer.MAX_VALUE, 81, 206, Integer.MAX_VALUE, 272, 132, Integer.MAX_VALUE, Integer.MAX_VALUE, 271, 69, 35, 66, Integer.MAX_VALUE, 64, Integer.MAX_VALUE, Integer.MAX_VALUE, 127, Integer.MAX_VALUE, 248, Integer.MAX_VALUE, Integer.MAX_VALUE, 188, 186, 68, 95, 166, Integer.MAX_VALUE, 219, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 226, Integer.MAX_VALUE, Integer.MAX_VALUE, 177, 181, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 194, 218, 158, Integer.MAX_VALUE, 231, 184, 83, Integer.MAX_VALUE, Integer.MAX_VALUE, 110, 73, Integer.MAX_VALUE, 36, 72, 89, 131, 223, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 210, 164, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 84, 45, 167, 157, 106, 138, 148, 161, Integer.MAX_VALUE, 298, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 189, 93, Integer.MAX_VALUE, 115, 98, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 279, 269, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 183, Integer.MAX_VALUE, Integer.MAX_VALUE, 220, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 185, Integer.MAX_VALUE, 222, 264, Integer.MAX_VALUE, 136, 42, 57, Integer.MAX_VALUE, 130, 195, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 234, Integer.MAX_VALUE, 262, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 116, 88, 54, 46, Integer.MAX_VALUE, Integer.MAX_VALUE, 182, Integer.MAX_VALUE, 242, 187, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 300, Integer.MAX_VALUE, 170, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 251, 133, 174, 120, 241, Integer.MAX_VALUE, 139, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 244, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 245, Integer.MAX_VALUE, 48, 90, 144, 257, 259, 233, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 282, 254, 134, 101, 91, 147, 117, 228, 58, Integer.MAX_VALUE, 255, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 142, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 274, Integer.MAX_VALUE, 273, 82, 126, 94, 92, 215, 246, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 275, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 179, 123, Integer.MAX_VALUE, 100, 99, 290, Integer.MAX_VALUE, Integer.MAX_VALUE, 205, Integer.MAX_VALUE, Integer.MAX_VALUE, 61, 247, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 151, 276, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 154, 105, 150, 149, 140, 104, Integer.MAX_VALUE, 155, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 266, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 191, 214, Integer.MAX_VALUE, 297, Integer.MAX_VALUE, 107, 108, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 249, 278, 238, Integer.MAX_VALUE, 252, 260, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 165, Integer.MAX_VALUE, 243, Integer.MAX_VALUE, Integer.MAX_VALUE, 216, 293, 288, Integer.MAX_VALUE, 217, 224, Integer.MAX_VALUE, Integer.MAX_VALUE, 176, Integer.MAX_VALUE, Integer.MAX_VALUE, 203, 227, 236, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 162, 111, 232, 171, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 190, 197, Integer.MAX_VALUE, 270, Integer.MAX_VALUE, Integer.MAX_VALUE, 294, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 284, Integer.MAX_VALUE, Integer.MAX_VALUE, 267, Integer.MAX_VALUE, 268, 211, 207, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 119, Integer.MAX_VALUE, 239, 283, 285, 280, Integer.MAX_VALUE, Integer.MAX_VALUE, 258, 281, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 296, 287, Integer.MAX_VALUE, Integer.MAX_VALUE, 204};
//        int[] nums = {1, 2, 3, Integer.MAX_VALUE, 4, 6, Integer.MAX_VALUE, 15, 5, 10, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 7, 11, Integer.MAX_VALUE, 8, 12, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 9, 13, 14};
        TreeNode root = makeTree(nums);
        TreeNode node = lcaDeepestLeaves(root);
        System.out.println(node.val);
//        TreeNode node1 = getNode(root, 84);
//        show(node1);
    }

    private static void show(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            System.out.println(root.val);
            return;
        }
        show(root.left);
        show(root.right);
    }

    private static TreeNode getNode(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        }

        TreeNode left = getNode(root.left, val);
        if (left != null) {
            return left;
        }
        return getNode(root.right, val);
    }

    private static void show(TreeNode root, int level, int val) {
        if (root == null) {
            return;
        }
        if (root.val == val) {
            System.out.println(level);
            return;
        }
        show(root.left, level + 1, val);
        show(root.right, level + 1, val);
        System.out.println(root.val);
    }

    private static TreeNode makeTree(int[] nums) {
        if (nums.length < 1) {
            return null;
        }
        TreeNode root = new TreeNode(nums[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        int index = 0;
        TreeNode node;
        queue.offer(root);

        while (!queue.isEmpty() && index < nums.length) {
            node = queue.poll();

            if (index * 2 + 1 < nums.length && nums[index * 2 + 1] != Integer.MAX_VALUE) {
                node.left = new TreeNode(nums[index * 2 + 1]);
                queue.offer(node.left);
            }

            if (index * 2 + 2 < nums.length && nums[index * 2 + 2] != Integer.MAX_VALUE) {
                node.right = new TreeNode(nums[index * 2 + 2]);
                queue.offer(node.right);
            }
            index++;
        }
        return root;
    }

    public static TreeNode lcaDeepestLeaves(TreeNode root) {
        Info info = lcaDeepestLeavesProcess(root, 1);
        return info.parent;
    }

    private static Info lcaDeepestLeavesProcess(TreeNode root, int level) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            return new Info(level, root);
        }

        Info left = lcaDeepestLeavesProcess(root.left, level + 1);
        Info right = lcaDeepestLeavesProcess(root.right, level + 1);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if (left.level == right.level) {
            left.parent = root;
            return left;
        }
        return left.level >= right.level ? left : right;
    }


    static class Info {
        int level;
        TreeNode parent;

        public Info(int level, TreeNode parent) {
            this.level = level;
            this.parent = parent;
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
