package util;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/12/10 23:24
 * @version: 1.0
 */

public class ListsUtils {

    @SneakyThrows
    public static ListNode makeList(int[] lists, Class zClass) {

        notNull(zClass, "ListNode class must not be null.");

        isAssignableFrom(ListNode.class, zClass);

        int count = -1;

        Constructor constructor = null;

        Constructor[] constructors = zClass.getDeclaredConstructors();

        for (Constructor c : constructors) {

            count = c.getParameterCount();

            Class[] types = c.getParameterTypes();

            if ((count == 1 && int.class.equals(types[0])) || count == 0) {

                constructor = c;

                break;
            }
        }

        notNull(constructor, "constructor must not be null.");

        constructor.setAccessible(true);

        ListNode head, tail;

        if (count == 0) {

            head = (ListNode) constructor.newInstance();

            tail = head;

            for (int i : lists) {

                tail.next = (ListNode) constructor.newInstance();

                tail = tail.next;

                tail.val = i;
            }
        } else {

            head = (ListNode) constructor.newInstance(Integer.MIN_VALUE);

            tail = head;

            for (int i : lists) {

                tail.next = (ListNode) constructor.newInstance(i);

                tail = tail.next;
            }
        }

        return head.next;
    }

    public static void notNull(Object o, String message) {

        if (o == null) {

            throw new NullPointerException(message);
        }
    }

    public static void isAssignableFrom(Class<ListNode> p, Class<ListNode> c) {

        if (!p.isAssignableFrom(c)) {

            throw new ClassCastException("not cast to " + p.getName() + " from " + c.getName());
        }
    }
}
