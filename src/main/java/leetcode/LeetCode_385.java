package leetcode;

import java.util.List;

/**
 * @description: Given a string s represents the serialization of a nested list,
 * implement a parser to deserialize it and return the deserialized NestedInteger.
 * <p>
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 * @author: LISHUAI
 * @createDate: 2022/5/8 19:18
 * @version: 1.0
 */

public class LeetCode_385 {
    private static int index;

    public static void main(String[] args) {
//        String s = "[123,456,[788,799,833],[[]],10,[]]";
//        String s = "1";
        String s = "[1,2,3]";
        System.out.println(s.length());
        NestedInteger deserialize = deserialize(s);
        System.out.println(index);
    }

    public static NestedInteger deserialize(String s) {
        index = 0;
        NestedInteger nestedInteger = deserializeProcess(s);
        if (nestedInteger.isInteger()) {
            return nestedInteger;
        }
//        return nestedInteger.getList().get(0);
        return null;
    }

    private static NestedInteger deserializeProcess(String s) {
        if (index >= s.length()) {
            return new NestedInteger();
        }
        NestedInteger nestedInteger = new NestedInteger();
        int pre, size = 0;

        while (index < s.length()) {
            pre = index;
            while (index < s.length() && s.charAt(index) != ',' && s.charAt(index) != '[' && s.charAt(index) != ']') {
                index++;
            }
            if (index >= s.length() || s.charAt(index) == ']') {
                if (s.charAt(index - 1) == ']' || s.charAt(index - 1) == '[') {
                    index++;
                    break;
                }
                if (size == 0) {
                    nestedInteger.setInteger(Integer.parseInt(s.substring(pre, index)));
                } else {
                    nestedInteger.add(new NestedInteger(Integer.parseInt(s.substring(pre, index))));
                }
                size++;
                index++;
                break;
            }
            if (s.charAt(index) == ',') {
                if (s.charAt(index - 1) != ']' && s.charAt(index - 1) != '[') {
                    nestedInteger.add(new NestedInteger(Integer.parseInt(s.substring(pre, index))));
                    size++;
                }

                index++;
            }

            if (s.charAt(index) == '[') {
                index++;
                nestedInteger.add(deserializeProcess(s));
            }
        }
        return nestedInteger;
    }

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation

    static class NestedInteger {
        // Constructor initializes an empty nested list.
        public NestedInteger() {

        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {

        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return false;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return null;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {

        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return null;
        }
    }
}
