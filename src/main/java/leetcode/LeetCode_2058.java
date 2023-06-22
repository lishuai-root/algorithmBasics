package leetcode;

/**
 * @description: A critical point in a linked list is defined as either a local maxima or a local minima.
 * <p>
 * A node is a local maxima if the current node has a value strictly greater than the previous node and the next node.
 * <p>
 * A node is a local minima if the current node has a value strictly smaller than the previous node and the next node.
 * <p>
 * Note that a node can only be a local maxima/minima if there exists both a previous node and a next node.
 * <p>
 * Given a linked list head, return an array of length 2 containing [minDistance, maxDistance] where minDistance is the minimum distance between any two distinct critical points and maxDistance is the maximum distance between any two distinct critical points. If there are fewer than two critical points, return [-1, -1].
 * @author: LISHUAI
 * @createDate: 2023/5/17 21:30
 * @version: 1.0
 */

public class LeetCode_2058 {

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1, pre = -1;
        int index = 0;
        ListNode preNode = null;
        int[] ans = {Integer.MAX_VALUE, -1};

        while (head != null) {
            if (preNode != null && head.next != null) {
                if ((head.val > preNode.val && head.val > head.next.val) || (head.val < preNode.val && head.val < head.next.val)) {
                    if (pre != -1) {
                        ans[0] = Math.min(ans[0], index - pre);
                        ans[1] = index - first;
                    }
                    if (first == -1) {
                        first = index;
                    }
                    pre = index;
                }
            }
            preNode = head;
            head = head.next;
            index++;
        }
        ans[0] = (ans[0] == Integer.MAX_VALUE ? -1 : ans[0]);
        return ans;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
