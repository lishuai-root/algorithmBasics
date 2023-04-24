package leetcode;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/8/3 20:54
 * @version: 1.0
 */

public class Test {
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = Test.class.getDeclaredMethod("testMethod", new Class<?>[]{String.class, int.class});
        System.out.println(method.hashCode());
        Method method1 = Test.class.getDeclaredMethod("testMethod", new Class<?>[]{String.class, int.class});
        System.out.println(method.hashCode());
        System.out.println(method == method1);
        System.out.println(method.equals(method1));
    }

    public String testMethod(String name, int age) {
        return "";
    }
}
