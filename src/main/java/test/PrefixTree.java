package test;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/23 21:07
 * @version: 1.0
 */

public class PrefixTree {

    private final TreeNode head = new TreeNode();

    public PrefixTree() {

    }

    public PrefixTree(String[] words) {

        doMakeTree(words);
    }

    public boolean contains(String word) {

        return searchCount(word) != 0;
    }

    public int insertWord(String word) {

        return insert(word);
    }

    public boolean isEmpty() {

        return head.pass == 0;
    }

    public int removeWord(String word) {

        return remove(word);
    }

    public int size() {

        return head.pass;
    }

    public List<String> orderSingle() {

        List<String> list = new ArrayList<>();

        TreeNode root = copyTree(this.head);

        StringBuilder sb = new StringBuilder();

        orderProcess(root, 0, sb, list);

        return list;
    }


    private void orderProcess(TreeNode node, int lovel, StringBuilder sb, List<String> list) {

        if (node == null) {

            return;
        }

        for (int i = 0; i < node.nexts.length; i++) {


            if (node.nexts[i] != null) {

                sb.append((char) (i + 'a'));

                if (node.nexts[i].isWord) {

                    list.add(sb.toString());
                }

                orderProcess(node.nexts[i], lovel + 1, sb, list);

                sb.deleteCharAt(sb.length() - 1);
            }
        }

    }


    public boolean removeWord(String[] words) {

        for (String s : words) {

            remove(s);
        }

        return true;
    }

    public int searchCount(String str) {

        TreeNode node;

        int p;

        char[] chars = str.toCharArray();

        node = head;

        for (char c : chars) {

            p = c - 'a';

            if (node.nexts[p] == null) {

                return 0;
            }

            node = node.nexts[p];
        }

        return node.end;
    }

    public int searchPrefix(String word) {

        TreeNode node = head;

        char[] chars = word.toCharArray();

        int p;

        for (char c : chars) {

            p = c - 'a';

            if (node.nexts[p] == null) {

                return 0;
            }

            node = node.nexts[p];
        }

        return node.pass;
    }


    public void makeTree(String[] words) {

        doMakeTree(words);
    }

    public TreeNode copyTree(TreeNode root) {

        return copyTreeProcess(root);
    }

    private TreeNode copyTreeProcess(TreeNode root) {

        if (root == null) {

            return null;
        }

        TreeNode node = new TreeNode();

        node.pass = root.pass;

        node.end = root.end;

        node.isWord = root.isWord;

        for (int i = 0; i < root.nexts.length; i++) {

            if (root.nexts[i] != null) {

                node.nexts[i] = copyTreeProcess(root.nexts[i]);
            }
        }

        return node;
    }

    private TreeNode doMakeTree(String[] words) {

        TreeNode node;

        char[] chars;

        int p;

        for (String w : words) {

            insert(w);
        }

        return head;
    }

    private int insert(String word) {

        TreeNode node = head;

        int p;

        char[] chars = word.toCharArray();

        for (char c : chars) {

            p = c - 'a';

            if (node.nexts[p] == null) {

                node.nexts[p] = new TreeNode();
            }

            node.pass++;

            node = node.nexts[p];
        }

        node.end++;

        node.isWord = true;

        return node.end;
    }

    private int remove(String word) {

        if (searchCount(word) == 0) {

            return 0;
        }

        TreeNode node = head;

        int p;

        char[] chars = word.toCharArray();

        for (char c : chars) {

            p = c - 'a';

            node.pass--;

            if (node.pass == 0) {

                return 0;
            }

            node = node.nexts[p];
        }

        return --node.end;
    }

    private class TreeNode {

        TreeNode[] nexts;

        int pass, end;

        boolean isWord;

        public TreeNode() {

            nexts = new TreeNode[26];

            isWord = false;
        }
    }
}
