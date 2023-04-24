package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a callable function f(x, y) with a hidden formula and a value z, reverse engineer the formula and return all positive integer pairs x and y where f(x,y) == z. You may return the pairs in any order.
 * <p>
 * While the exact formula is hidden, the function is monotonically increasing, i.e.:
 * <p>
 * f(x, y) < f(x + 1, y)
 * f(x, y) < f(x, y + 1)
 * The function interface is defined like this:
 * <p>
 * interface CustomFunction {
 * public:
 * // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
 * int f(int x, int y);
 * };
 * We will judge your solution as follows:
 * <p>
 * The judge has a list of 9 hidden implementations of CustomFunction, along with a way to generate an answer key of all valid pairs for a specific z.
 * The judge will receive two inputs: a function_id (to determine which implementation to test your code with), and the target z.
 * The judge will call your findSolution and compare your results with the answer key.
 * If your results match the answer key, your solution will be Accepted.
 * @author: LISHUAI
 * @createDate: 2022/12/9 19:20
 * @version: 1.0
 */

public class LeetCode_1237 {

    // other method
    public static List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        int x = 1;
        int y = 1000;
        while (x <= 1000 && y >= 1) {
            if (customfunction.f(x, y) == z) {
                List<Integer> list1 = new ArrayList<>();
                list1.add(x);
                list1.add(y);
                res.add(list1);
                x++;
                y--;
            } else if (customfunction.f(x, y) < z) {
                x++;
            } else {
                y--;
            }
        }
        return res;
    }

    // This is the custom function interface.
    // You should not implement it, or speculate about its implementation
    static interface CustomFunction {
        // Returns f(x, y) for any given positive integers x and y.
        // Note that f(x, y) is increasing with respect to both x and y.
        // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
        public int f(int x, int y);
    }
}
