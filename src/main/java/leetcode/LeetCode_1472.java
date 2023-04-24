package leetcode;

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

        private String[] history;
        private int index, size;

        public BrowserHistory(String homepage) {
            index = -1;
            history = new String[16];
            history[++index] = homepage;
            size = index;
        }

        public void visit(String url) {
            if (index >= history.length - 1) {
                resize();
            }
            history[++index] = url;
            size = index;
        }

        public String back(int steps) {
            index = Math.max(0, index - steps);
            return history[index];
        }

        public String forward(int steps) {
            index = Math.min(size, index + steps);
            return history[index];
        }

        private void resize() {
            String[] temp = new String[history.length << 1];
            System.arraycopy(history, 0, temp, 0, history.length);
            history = temp;
        }
    }
}
