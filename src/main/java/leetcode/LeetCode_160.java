package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: Given the heads of two singly linked-lists headA and headB,
 * return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 * @author: LISHUAI
 * @createDate: 2021/11/26 20:07
 * @version: 1.0
 */

public class LeetCode_160 {

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        int ALen = 0, BLen = 0, c;

        ListNode nodeA = headA, nodeB = headB;

        while (nodeA != null) {

            ALen++;

            nodeA = nodeA.next;
        }

        while (nodeB != null) {

            BLen++;

            nodeB = nodeB.next;
        }

        nodeA = headA;

        nodeB = headB;

        if (ALen > BLen) {

            c = ALen - BLen;

            while (c > 0) {

                nodeA = nodeA.next;

                c--;
            }

        } else if (BLen > ALen) {

            c = BLen - ALen;

            while (c > 0) {

                nodeB = nodeB.next;

                c--;
            }
        }

        while (nodeA != nodeB && nodeA != null && nodeB != null) {

            nodeA = nodeA.next;

            nodeB = nodeB.next;
        }

        return nodeA;
    }

    public static ListNode getIntersectionNode_02(ListNode headA, ListNode headB) {

        Set<ListNode> set = new HashSet<>();

        while (headA != null) {

            set.add(headA);

            headA = headA.next;
        }

        while (headB != null) {

            if (set.contains(headB)) {

                return headB;
            }

            headB = headB.next;
        }

        return null;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
