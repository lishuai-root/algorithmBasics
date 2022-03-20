package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: We run a preorder depth-first search (DFS) on the root of a binary tree.
 * <p>
 * At each node in this traversal, we output D dashes (where D is the depth of this node),
 * then we output the value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.
 * <p>
 * If a node has only one child, that child is guaranteed to be the left child.
 * <p>
 * Given the output traversal of this traversal, recover the tree and return its root.
 * @author: LISHUAI
 * @createDate: 2022/1/11 21:25
 * @version: 1.0
 */

public class LeetCode_1028 {

    private static int index;


    public static void main(String[] args) {

        String str = "1-401--349--90---88";


        TreeNode treeNode = recoverFromPreorder(str);

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.offer(treeNode);

        int size;

        while (!queue.isEmpty()) {

            size = queue.size();

            for (int i = 0; i < size; i++) {

                treeNode = queue.pop();

                if (treeNode == null) {

                    System.out.print("null ");
                } else {

                    System.out.print(treeNode.val + " ");

                    queue.offer(treeNode.left);

                    queue.offer(treeNode.right);
                }
            }

            System.out.println();
        }
    }

    public static TreeNode recoverFromPreorder(String traversal) {

        List<List<Integer>> lists = splitCustom(traversal);

        List<TreeNode> treeNodeList = new ArrayList<>();

        List<Integer> l = lists.get(0);

        TreeNode root = new TreeNode(l.get(0)), node;

        treeNodeList.add(root);

        int level = 1, size, m, n;

        while (!treeNodeList.isEmpty() && level < lists.size()) {

            size = treeNodeList.size();

            l = lists.get(level);

            m = 0;

            n = l.size() - size;

            for (int i = 0; i < size; i++) {

                node = treeNodeList.remove(0);

                if (m >= l.size()) {

                    continue;
                }

                node.left = new TreeNode(l.get(m++));

                treeNodeList.add(node.left);

                if (l.size() > size && n > 0) {

                    node.right = new TreeNode(l.get(m++));

                    treeNodeList.add(node.right);

                    n--;
                }

//                if (l.size() <= size) {
//                    if (m + size < l.size()) {
//
//                        node.right = new TreeNode(l.get(m + size));
//
//                        treeNodeList.add(node.right);
//                    }
//                } else {
//
//                    if (l.size() - m > size - i - 1) {
//
//                        node.right = new TreeNode(l.get(m++));
//
//                        treeNodeList.add(node.right);
//                    }
//                }

            }

            level++;
        }

        return root;

//        index = 0;
//
//        return recoverFromPreorderProcess(lists, 0);
    }

    private static TreeNode process(List<Integer> list) {

        if (index >= list.size() || list.get(index++) == Integer.MIN_VALUE) {

            return null;
        }

        TreeNode node = new TreeNode(list.get(index++));

        node.left = process(list);

        node.right = process(list);

        return node;
    }

    private static TreeNode recoverFromPreorderProcess(List<List<Integer>> list, int level) {

        if (level >= list.size() || list.get(level).size() == 0) {

            return null;
        }

        TreeNode node = new TreeNode(list.get(level).remove(0));

        node.left = recoverFromPreorderProcess(list, level + 1);

        node.right = recoverFromPreorderProcess(list, level + 1);

        return node;
    }

    private static List<List<Integer>> splitCustom(String str) {

        char c = '-';

        List<List<Integer>> list = new ArrayList<>();

        List<Integer> l;

        int index = 0, start = 0;

        for (int i = 0; i < str.length(); ) {

            start = i;

            while (start < str.length() && str.charAt(start) == c) {

                start++;
            }

            index = start + 1;

            while (index < str.length() && str.charAt(index) != c) {

                index++;
            }

            while (start - i >= list.size()) {

                list.add(new ArrayList<Integer>());
            }

            l = list.get(start - i);


            l.add(Integer.parseInt(str.substring(start, index)));


            i = index;
        }

        return list;
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
