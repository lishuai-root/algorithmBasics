package stringSort;

import lombok.*;

/**
 * @description: 前缀树
 * @author: LISHUAI
 * @createDate: 2021/6/7 19:40
 * @version: 1.0
 */
public class PrefixTree {

    private TreeNode root;

    public PrefixTree() {
        root = new TreeNode();
    }

    /**
     * 给前缀树中添加一个字符串
     *
     * @param value
     * @return
     */
    public String put(String value) {
        if (value == null || value.length() < 1)
            return null;
        return putVal(this.root, value);
    }

    private String putVal(TreeNode root, String value) {

        int c;

        int length = value.length();

        TreeNode node = root;

        for (int i = 0; i < length; i++) {
            c = value.charAt(i);

            if ((c -= 97) < 0)
                c -= (65 - 123);

            if (node.next[c] == null)
                node.next[c] = new TreeNode(0, 0);
            node.pass++;
            node = node.next[c];
        }

        node.pass++;

        node.end++;

        return value;
    }

    /**
     * 查询指定前缀在前缀树中出现的次数
     *
     * @param pre
     * @return
     */
    public int search(String pre) {
        if (pre == null)
            return -1;

        return search(this.root, pre);
    }

    private int search(TreeNode root, String pre) {

        TreeNode node;

        if ((node = searchContains(root, pre)) == null)
            return 0;

        return node.pass;
    }

    /**
     * 删除指定字符串，并返回删除后该字符串剩余的次数
     *
     * @param value
     * @return
     */
    public int delete(String value) {
        if (value == null || search(value) <= 0)
            return -1;

        return deleteVal(this.root, value);
    }

    private int deleteVal(TreeNode root, String value) {

        TreeNode node = root;

        int length = value.length(), c, i = 0;

        for (; i < length; i++) {

            c = value.charAt(i);

            if ((c -= 97) < 0)
                c -= (65 - 123);

            if (--node.next[c].pass == 0) {
                node.next[c] = null;
                break;
            }
            node = node.next[c];

        }

        node.end--;

        return length == i ? node.end : 0;
    }

    public int searchPre(String pre) {
        if (pre == null)
            return -1;

        return searchPre(this.root, pre);
    }

    private int searchPre(TreeNode root, String pre) {

        TreeNode node;

        if ((node = searchContains(root, pre)) == null)
            return 0;

        return node.pass;
    }

    private TreeNode searchContains(TreeNode root, String pre) {

        TreeNode node = root;

        int length = pre.length(), c;

        for (int i = 0; i < length; i++) {

            c = pre.charAt(i);

            if ((c -= 97) < 0)
                c -= (65 - 123);

            if (node.next[c] == null)
                return null;

            node = node.next[c];

        }

        return node;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @RequiredArgsConstructor
    private static class TreeNode {

        @NonNull
        private int pass;

        @NonNull
        private int end;

        private TreeNode[] next = new TreeNode[26 * 2];

    }
}
