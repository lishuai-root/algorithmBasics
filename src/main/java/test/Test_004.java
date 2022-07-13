package test;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/22 21:00
 * @version: 1.0
 */

public class Test_004 {

    private static final int MAP_SIZE = 16;

    private static final String FILE_PATH = "E:\\资料\\算法\\大厂班代码\\coding-for-great-offer-main\\src\\" +
            "class01\\Code01_CordCoverMaxPoint.java";

    public static void main(String[] args) {
//        B b = new B();
//        System.out.println(b.p);
//
//        b.mn();

//        fn_006();

//        File file = new File(FILE_PATH);
//
//        System.out.println(file.getAbsolutePath());

        new Test_004().fn_008();

    }

    private static void fn_006() {

        File file = new File(FILE_PATH);

        boolean delete = doDeleteFile(file);

        System.out.println(delete);

        Properties properties = new Properties();


    }

    /**
     * 递归删除文件或文件夹
     *
     * @param file
     * @return
     */
    private static boolean doDeleteFile(File file) {

        if (file == null || !file.exists()) {

            return true;
        }

        if (file.isFile()) {

            return file.delete();
        }

        boolean result = true;

        File[] files = file.listFiles();

        for (File f : files) {

            result = doDeleteFile(f) && result;
        }

        result = file.delete() && result;

        return result;
    }

    private static boolean doDeleteFileWhile(File file) {

        if (file == null || !file.exists()) {

            return true;
        }

        if (file.isFile()) {

            return file.delete();
        }

        boolean result = true;

        File[] files = file.listFiles();

        File[] fs = null;

        File df;

        Stack<File> fileStack = new Stack<>();

        for (File f : files) {

            if (!f.exists()) {

                continue;
            }

            if (f.isFile()) {

                result = f.delete() && result;

                continue;
            }

            fileStack.push(f);

            while (!fileStack.isEmpty()) {

                df = fileStack.pop();

                fs = df.listFiles();

                if (fs == null || fs.length < 1) {

                    result = df.delete() && result;

                    continue;
                }

                for (File cf : fs) {

                    if (!cf.exists()) {

                        continue;
                    }

                    if (cf.isFile()) {

                        result = f.delete() && result;

                        continue;
                    }

                    fileStack.push(cf);
                }

            }

            result = f.delete() && result;
        }


        return result;
    }

    @SneakyThrows
    private static void fn_005() {

//        ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<>();
//
//        Object lishuai = hashMap.computeIfAbsent("lishuai", m -> new Object());
//
//        System.out.println(hashMap.get("lishuai"));
//
//        Object shuaishuai = hashMap.computeIfPresent("lishuai", (n, m) -> n + m);
//
//        System.out.println(hashMap.get("lishuai"));

        int result = 1;

//        System.out.println(Class.forName("java.long.Thread"));

        System.out.println(char.class.getName());

        System.out.println(char.class.getClassLoader());

        Object[] ob = new Object[10];

        System.out.println(ob);

        int[] arr = new int[10];

        System.out.println(arr);

        String[] str = new String[1];

        System.out.println(str);

        ArrayList<String> strings = new ArrayList<>();

        strings.add("lishu");

        System.out.println(strings.getClass().getName());

//        Demo<String> stringDemo = new Demo<>();

//        System.out.println(stringDemo);

        LinkedList<String> strings1 = new LinkedList<>();

        System.out.println(strings1);

        Object o = Array.newInstance(String.class, 0);

        System.out.println(o.getClass());

        Object o1 = Array.newInstance(int.class, 0);

        System.out.println(o1.getClass());

    }

    private static void fn_004() {

        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "lishuai");

        map.put(1, "li");

        System.out.println(map.get(1));

    }

    private static void fn_003() {

        int i = 0;

        long start = System.currentTimeMillis();

        while (i > -1) {

            i++;
        }

        long end = System.currentTimeMillis();

        System.out.println(i);

        System.out.println("time : " + (end - start));
    }

    private static void fn_002() {

        String username = System.getProperty("user.name");

        System.out.println(username);

        Map<String, String> getenv = System.getenv();

        Set<String> strings = getenv.keySet();

        Iterator<String> iterator = strings.iterator();

        while (iterator.hasNext()) {

            String next = iterator.next();

            username = getenv.get(next);

            System.out.println(next + " - " + username);
        }
    }

    private static void fn_001() {
        int a = 2147483647 + 1;

        int b = 4;

        System.out.println(a);

        System.out.println(a & (b - 1));

        System.out.println(Integer.MAX_VALUE);
    }

    @SneakyThrows
    private static void fn_008() {

        Class<Test_006> aClass = Test_006.class;

        Object o = null;

        Constructor<?>[] constructors = aClass.getConstructors();

        for (Constructor constructor : constructors) {

            o = constructor.newInstance();
        }

        System.out.println(o);
    }

    private void fn_007() {

        A a = new A();

        System.out.println(a);
    }

    class B extends A {

        public String p = "child str";

        public B() {

            System.out.println("create a B Object !!! ");
        }

        @Override
        public void mn() {
            System.out.println("child method");
        }
    }

    class A {

        public String p = "parent str";

        public void mn() {
            System.out.println("parent method");
        }
    }

    class Demo<T> implements List {

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @NotNull
        @Override
        public Iterator iterator() {
            return null;
        }

        @Override
        public void forEach(Consumer action) {
            List.super.forEach(action);
        }

        @NotNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }


        @Override
        public boolean add(Object o) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean addAll(@NotNull Collection c) {
            return false;
        }

        @Override
        public boolean removeIf(Predicate filter) {
            return List.super.removeIf(filter);
        }

        @Override
        public boolean addAll(int index, @NotNull Collection c) {
            return false;
        }

        @Override
        public void replaceAll(UnaryOperator operator) {
            List.super.replaceAll(operator);
        }

        @Override
        public void sort(Comparator c) {
            List.super.sort(c);
        }

        @Override
        public void clear() {

        }

        @Override
        public Object get(int index) {
            return null;
        }

        @Override
        public Object set(int index, Object element) {
            return null;
        }

        @Override
        public void add(int index, Object element) {

        }

        @Override
        public Object remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @NotNull
        @Override
        public ListIterator listIterator() {
            return null;
        }

        @NotNull
        @Override
        public ListIterator listIterator(int index) {
            return null;
        }

        @NotNull
        @Override
        public List subList(int fromIndex, int toIndex) {
            return null;
        }

        @Override
        public Spliterator spliterator() {
            return List.super.spliterator();
        }

        @Override
        public Stream stream() {
            return List.super.stream();
        }

        @Override
        public Stream parallelStream() {
            return List.super.parallelStream();
        }

        @Override
        public boolean retainAll(@NotNull Collection c) {
            return false;
        }

        @Override
        public boolean removeAll(@NotNull Collection c) {
            return false;
        }

        @Override
        public boolean containsAll(@NotNull Collection c) {
            return false;
        }

        @NotNull
        @Override
        public Object[] toArray(@NotNull Object[] a) {
            return new Object[0];
        }
    }
}
