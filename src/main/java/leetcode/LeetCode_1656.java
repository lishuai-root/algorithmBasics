package leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: There is a stream of n (idKey, value) pairs arriving in an arbitrary order, where idKey is an integer between 1 and n and value is a string. No two pairs have the same id.
 * <p>
 * Design a stream that returns the values in increasing order of their IDs by returning a chunk (list) of values after each insertion. The concatenation of all the chunks should result in a list of the sorted values.
 * <p>
 * Implement the OrderedStream class:
 * <p>
 * OrderedStream(int n) Constructs the stream to take n values.
 * String[] insert(int idKey, String value) Inserts the pair (idKey, value) into the stream, then returns the largest possible chunk of currently inserted values that appear next in the order.
 * @author: LiShuai
 * @createDate: 2023/9/2 20:56
 * @version: 1.0
 */

public class LeetCode_1656 {

    class OrderedStream {
        String[] values;
        int ptr;

        public OrderedStream(int n) {
            values = new String[n];
            ptr = 0;
        }

        public List<String> insert(int idKey, String value) {
            values[idKey - 1] = value;
            if (idKey - 1 == ptr) {
                LinkedList<String> list = new LinkedList<>();
                while (ptr < values.length && values[ptr] != null) {
                    list.addLast(values[ptr++]);
                }
                return list;
            }
            return Collections.emptyList();
        }
    }
}
