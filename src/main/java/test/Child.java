package test;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/12/19 21:40
 * @version: 1.0
 */

public class Child extends Parent {

    public void check() {

        doCheck();
    }

    @Override
    protected void childMethod() {
        System.out.println("child method!");
    }

    @Override
    protected void doCheck() {
        childMethod();
        System.out.println("child do check!");
    }
}
