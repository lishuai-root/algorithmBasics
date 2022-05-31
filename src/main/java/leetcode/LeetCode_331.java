package leetcode;

/**
 * @description: One way to serialize a binary tree is to use preorder traversal. When we encounter a non-null node,
 * we record the node's value. If it is a null node, we record using a sentinel value such as '#'.
 * <p>
 * <p>
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where '#' represents a null node.
 * <p>
 * Given a string of comma-separated values preorder, return true if it is a correct preorder traversal serialization of a binary tree.
 * <p>
 * It is guaranteed that each comma-separated value in the string must be either an integer or a character '#' representing null pointer.
 * <p>
 * You may assume that the input format is always valid.
 * <p>
 * For example, it could never contain two consecutive commas, such as "1,,3".
 * Note: You are not allowed to reconstruct the tree.
 * @author: LISHUAI
 * @createDate: 2022/4/14 21:11
 * @version: 1.0
 */

public class LeetCode_331 {

    public static void main(String[] args) {
        String preorder = "9,#,92,#,#";
        boolean validSerialization = isValidSerialization(preorder);

        System.out.println(validSerialization);
    }

    public static boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        return isValidSerializationProcess(nodes, 0) == nodes.length - 1;
    }

    private static int isValidSerializationProcess(String[] chars, int index) {
        if (index >= chars.length) {
            return index;
        }
        if ("#".equals(chars[index])) {
            return index;
        }

        return isValidSerializationProcess(chars, isValidSerializationProcess(chars, ++index) + 1);
    }

}






