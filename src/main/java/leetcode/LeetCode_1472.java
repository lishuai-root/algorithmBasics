package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You have a browser of one tab where you start on the homepage and you can visit another url,
 * get back in the history number of steps or move forward in the history number of steps.
 * <p>
 * Implement the BrowserHistory class:
 * <p>
 * BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 * void visit(string url) Visits url from the current page. It clears up all the forward history.
 * string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.
 * string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x,
 * you will forward only x steps. Return the current url after forwarding in history at most steps.
 * @author: LISHUAI
 * @createDate: 2022/5/31 22:50
 * @version: 1.0
 */

public class LeetCode_1472 {
    class BrowserHistory {

        List<String> list = new ArrayList<>();
        int index;

        public BrowserHistory(String homepage) {
            list.add(homepage);
            index = 0;
        }

        public void visit(String url) {
            list.add(url);
            index = list.size() - 1;
        }

        public String back(int steps) {
            int i = Math.min(steps, index);
            index -= i;
            return list.get(i);
        }

        public String forward(int steps) {
            int i = Math.min(steps, list.size() - index - 1);
            index += i;
            return list.get(i);
        }
    }
}
