package leetcode;

/**
 * @description: Given a binary tree with the following rules:
 * <p>
 * root.val == 0
 * If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
 * If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
 * Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.
 * <p>
 * Implement the FindElements class:
 * <p>
 * FindElements(TreeNode* root) Initializes the object with a contaminated binary tree and recovers it.
 * bool find(int target) Returns true if the target value exists in the recovered binary tree.
 * @author: LISHUAI
 * @createDate: 2022/1/2 22:47
 * @version: 1.0
 */

public class LeetCode_1261 {

    public static void main(String[] args) {
        System.out.println(100031 / 32);
    }

    static class FindElements {

//        Set<Integer> set;

        int[] bits = new int[3125];

        public FindElements(TreeNode root) {

//            set = new HashSet<>();

            process(root, 0);
        }

        public boolean find(int target) {

            int i = target >> 5;

            return (bits[i] & (1 << (target & 31))) != 0;
        }


        private void process(TreeNode root, int val) {

            if (root == null) {

                return;
            }

            root.val = val;

//            set.add(val);

            int i = val >> 5;

            if (i <= 3125) {

                bits[i] |= 1 << (val & 31);
            }

            process(root.left, val * 2 + 1);

            process(root.right, val * 2 + 2);
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
