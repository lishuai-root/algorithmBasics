package test;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/12/19 21:40
 * @version: 1.0
 */

public abstract class Parent {

    protected void childMethod() {

        System.out.println("parent child method!");
    }


    protected void doCheck() {

        childMethod();

        System.out.println("parent do check!");
    }

    abstract Object testChildMethod(Object o);

}
