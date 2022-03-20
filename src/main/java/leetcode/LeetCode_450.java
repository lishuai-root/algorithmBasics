package leetcode;

/**
 * @description: Given a root node reference of a BST and a key,
 * delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 * <p>
 * Basically, the deletion can be divided into two stages:
 * <p>
 * Search for a node to remove.
 * If the node is found, delete the node.
 * @author: LISHUAI
 * @createDate: 2021/12/13 22:17
 * @version: 1.0
 */

public class LeetCode_450 {

    public static TreeNode deleteNode(TreeNode root, int key) {

        return delete(root, key);
    }


    /**
     * 根据key在指定根节点的树中删除节点，该删除的方法是，使用要删除节点右侧的最小节点代替该节点的位置
     * 1. 在树中找出key对应的节点
     * 2. 找到该节点右侧的最小节点
     * 3. 记录该节点右侧最小的节点，然后删除最小节点，用最小节点的右节点指向要删除节点的右节点
     * 4. 用最小节点的左节点指向要删除节点的左节点，用要删除节点的父节点指向该最小节点。此时最小节点就已经代替了要删除节点的位置，
     * 而要删除的节点已经在树中不被引用，也就是删除了
     * *****************************************************************************
     * <p>
     * 如果不考虑维护节点的个数，那么可以直接使用要删除节点右侧最小节点的key和value直接覆盖要删除的节点的key和value，然后删除最小节点即可
     * <p>
     * *****************************************************************************
     * <p>
     * 如果当前节点不是要删除的节点，返回的就是当前节点，返回值的接收者为其父节点（具体为夫节点的左节点还是右节点，根据方法调用时的参数决定）
     * 如果当前节点就是要删除的节点，返回的删除当前节点后代替该位置的节点（即要删除节点右侧的最小节点）
     *
     * @param root 根节点
     * @param key  要删除的key
     * @return
     */
    private static TreeNode delete(TreeNode root, int key) {
        if (root == null)
            return null;
        int i = key - root.val;
        if (i > 0)  //  如果要删除的节点大于当前节点，在右侧继续寻找
            root.right = delete(root.right, key);
        else if (i < 0) //  如果要删除的节点小于当前节点，在左侧继续寻找
            root.left = delete(root.left, key);
        else {      // 找到要删除的节点，即当前节点
            if ((root.left == null && root.right == null))
                return null;
            if (root.left == null)  // 如果当前节点的左子节点为空，此时其父节点，当前节点，其右子节点可以视为链表的形式。直接用其右子节点代替当前节点的位置即可
                return root.right;
            if (root.right == null) // 同上
                return root.left;
            //  当前节点同时存在左右字节点
            TreeNode x = min(root.right); //  获取当前节点右侧的最小节点
            x.val = x.val ^ root.val;
            root.val = x.val ^ root.val;
            x.val = x.val ^ root.val;
            root.right = deleteMin(root.right);    //  删除当前节点右侧的最小节点，并用最小节点的右子节点引用当前节点的右子节点
        }

        return root;    //  返回当前节点（已经被替换后的当前节点，即要删除的节点的左侧最小节点）
    }

    /**
     * 遍历找到最小节点，用该节点的父节点直接引用该节点的右节点即可（因为是最小节点，所以不会存在左节点）
     *
     * @param root
     * @return
     */
    private static TreeNode deleteMin(TreeNode root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = deleteMin(root.left);

        return root;
    }

    /**
     * 返回树中key最小的节点，其实也就是最左侧的节点
     *
     * @param root 树的根节点
     * @return 最小的节点
     */
    private static TreeNode min(TreeNode root) {
        if (root == null)
            return null;
        if (root.left == null)
            return root;
        return min(root.left);
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
