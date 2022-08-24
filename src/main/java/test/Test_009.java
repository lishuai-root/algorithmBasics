package test;

import java.lang.reflect.Constructor;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2022/8/16 22:46
 * @version: 1.0
 */

public class Test_009 {

    public static void main(String[] args) {
        Constructor<?>[] declaredConstructors = TreeText.class.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            System.out.println(constructor.isSynthetic() + " - " + constructor.getParameterCount());
        }
    }
}
