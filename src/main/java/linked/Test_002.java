package linked;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.UUID;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/13 12:05
 * @version: 1.0
 */

public class Test_002 {

    public static void main(String[] args) {
        fn_003();
    }


    private static void fn_003() {

        int a = 23;

        int b = 483;

        int size = (b + "").length();

        System.out.println(a << 1);

        System.out.println((a << size) ^ b);

    }

    @SneakyThrows
    private static void fn_002() {

        String uuid = null;

        HashSet<String> hashSet = new HashSet<>();

        for (int i = 0; i < 10000000; i++) {

            hashSet.add(UUID.randomUUID().toString());
        }

        System.out.println(hashSet.contains(UUID.randomUUID().toString()));

        System.in.read();

    }

    @SneakyThrows
    private static void fn_001() {

        Person person = new Person();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(person);

        objectOutputStream.flush();

        byte[] bytes = byteArrayOutputStream.toByteArray();

        System.out.println(bytes.length);

        for (byte aByte : bytes) {

            System.out.print(aByte + "  ");

        }

        System.out.println();

        String s = new String(bytes, StandardCharsets.UTF_8);

        System.out.println("String : " + s);

        byte[] bytes1 = s.getBytes(StandardCharsets.UTF_8);

        for (byte b : bytes1) {

            System.out.print(b + "  ");
        }

        System.out.println();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        Object o = objectInputStream.readObject();

        System.out.println("first Serializable : " + o);

        byteArrayInputStream = new ByteArrayInputStream(bytes1);

        objectInputStream = new ObjectInputStream(byteArrayInputStream);

        o = objectInputStream.readObject();

        System.out.println("two Serializable : " + o);


    }


    private static class Person implements Serializable {

        long serialVersionUID = 1;
    }

}
