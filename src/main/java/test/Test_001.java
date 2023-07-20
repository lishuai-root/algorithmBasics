package test;

import bitOperation.ArrayQueue;
import lombok.SneakyThrows;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/1 19:47
 * @version: 1.0
 */
public class Test_001 extends ClassLoader {

    private static LinkedList<Object> list = new LinkedList<Object>();

    private static Test_001 test = new Test_001();

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
//        fn_002();
        String line = "阿a拉贡哈房间看电视安德森快乐到家啊了反对撒赖科技经费和拉大风蓝色咖啡和埃里克还是狄拉克符号爱丽丝反对黄金大神了和拉伸领导看附件half的会计师啊";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 4000000; i++) {
            byte[] bytes = line.getBytes(StandardCharsets.UTF_8);
            String s = new String(bytes, 0, 5, StandardCharsets.UTF_8);
            if (s.getBytes(StandardCharsets.UTF_8).length > 5) {
                s = s.substring(0, s.length() - 1);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("time : " + (end - start));
    }

    private static void fn_001() {

        ArrayQueue queue = new ArrayQueue();

        for (int i = 0; i < 15; i++) {
            queue.pull(i);

        }
    }

    private static void fn_002() throws
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        File file = new File("E:\\All_workspace\\IDEA_workspace\\algorithmBasics\\target\\classes\\leetcode");

        fn_003(file);

//        LeetCode_295.MedianFinder medianFinder = new LeetCode_295.MedianFinder();

        Class<?> aClass = getClassObject("leetcode.Load");

        Constructor<?>[] constructors = aClass.getConstructors();

        for (Constructor constructor : constructors) {

            if (constructor.getParameterCount() == 0) {

                constructor.setAccessible(true);

                Object o = constructor.newInstance();

                System.out.println(o);

                Method main = aClass.getMethod("main", String[].class);

//                                main.setAccessible(true);

                String[] args = {};

                main.invoke(o, (Object) args);

                return;

            }
        }
    }

    private static Class<?> getClassObject(String className) throws
            InvocationTargetException, InstantiationException, IllegalAccessException {

        return test.findLoadedClass(className);
    }

    @SneakyThrows
    private static void fn_003(File file) {


        if (!file.exists())
            return;

        if (file.isFile()) {
            String absolutePath = file.getAbsolutePath();

//            System.out.println("absolutePath : " + absolutePath);

            if (absolutePath.contains("bitOperation") || absolutePath.contains("test"))
                return;

            if (absolutePath.endsWith(".class")) {
//                System.out.println("--- : " + absolutePath);
                Class<?> aClass = test.findClass(absolutePath.split("classes\\\\")[1]);
                if (aClass != null) {

                    System.out.println(aClass.getClassLoader());

                    System.out.println("class name : " + aClass.getName());

                    Constructor<?>[] constructors = aClass.getConstructors();

                    for (Constructor constructor : constructors) {

                        if (constructor.getParameterCount() == 0) {

                            constructor.setAccessible(true);

                            Object o = constructor.newInstance();

                            System.out.println(o);


//                            if (aClass.getName().endsWith("LeetCode_295")) {
//
//                                Method main = aClass.getMethod("main", String[].class);
//
////                                main.setAccessible(true);
//
//                                String[] args = {};
//
//                                main.invoke(o, (Object) args);
//
//                                return;
//                            }
                        }
                    }

                }

            }
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (int i = 0; i < files.length; i++) {
//                System.out.println("child file : " + files[i].getAbsolutePath());
                fn_003(files[i]);
            }
        }
    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {


        if (this.findLoadedClass(name.split("\\.")[0].replace("\\", ".")) != null) {

            return this.findLoadedClass(name.split("\\.")[0].replace("\\", "."));
        }

        String fileName = "E:\\All_workspace\\IDEA_workspace\\algorithmBasics\\target\\classes\\" + name;

//        System.out.println("name : " + name);
        System.out.println("fileName ： " + fileName);
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;

        File file = new File(fileName);

        if (!file.exists())
            return null;

        try {
            inputStream = new FileInputStream(file);
            outputStream = new ByteArrayOutputStream();

            int b = -1;
            byte[] bytes = new byte[1024];

            while ((b = inputStream.read(bytes)) > 0)
                outputStream.write(bytes, 0, b);

            byte[] bytes1 = outputStream.toByteArray();

//            System.out.println("*** : " + name.replace("\\", "."));

            String replace = name.replace("\\", ".");

//            System.out.println("^^^ : " + replace.substring(0, replace.lastIndexOf(".")));

            return defineClass(replace.substring(0, replace.lastIndexOf(".")), bytes1, 0, bytes1.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("00000 ： " + name.replace("\\", "."));
        return super.findClass(name.replace("\\", "."));
    }
}
