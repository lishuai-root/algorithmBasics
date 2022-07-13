package stringSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/16 22:56
 * @version: 1.0
 */

public class StringAllArrangement {

    public static void main(String[] args) {

//        String str = "abc";
//
//        List<String> strings = arrangements(str);
//
//        for (String s : strings)
//            System.out.println(s);

//        String s = makeString(10);
//
//        System.out.println(s);
//
////        arrangements(s);
////        stringAllArrang(s);
//
//        long start = System.currentTimeMillis();
//
//        List<String> strings = stringAllArrang(s);
//        long end = System.currentTimeMillis();
//        System.out.println("set : " + (end - start) + "   set size : " + strings.size());
//
//
//        start = System.currentTimeMillis();
//        List<String> strs = arrangements(s);
//        end = System.currentTimeMillis();
//
//        System.out.println("index : " + (end - start) + "   index size : " + strs.size());
//
//        if (strings.size() != strs.size())
//            System.out.println("error !!! ");

        fn_001();
    }

    private static void fn_001() {
        Stack<String> strings = new Stack<>();

        strings.push("1");
        strings.push("2");
        strings.push("3");
        strings.push("4");
        strings.push("5");
        strings.push("6");

        System.out.println("size : " + strings.size());
        int size = strings.size();

        for (int i = 0; i < size; i++) {
            System.out.println(strings.pop());
        }


        System.out.println("====================");

        strings.push("1");
        strings.push("2");
        strings.push("3");
        strings.push("4");
        strings.push("5");
        strings.push("6");

        String s = orderStack(strings);
        strings.push(s);

        for (int i = 0; i < size; i++)
            System.out.println(strings.pop());
    }


    private static String orderStack(Stack<String> stack) {
        String pop = stack.pop();
        if (!stack.isEmpty()) {
            String result = orderStack(stack);

            stack.push(pop);
            return result;
        }
        return pop;
    }

    private static String makeString(int len) {

        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < len; i++) {

            buffer.append((char) ((int) (Math.random() * 10) + 97));
        }

        return buffer.toString();

    }

    /**
     * 输入字符串,返回字符串的全排列
     *
     * @param string
     * @return
     */
    private static List<String> stringAllArrang(String string) {

        ArrayList<String> list = new ArrayList<>();

        ArrayList<Character> characters = new ArrayList<>();


        char[] chars = string.toCharArray();

        for (char c : chars)
            characters.add(c);

        String path = "";

        presses(characters, path, list);

        return list;
    }

    private static void presses(List<Character> chars, String str, List<String> list) {

        if (chars.isEmpty() || chars.size() == 0)
            list.add(str);

        else {

            int size = chars.size();

            for (int i = 0; i < size; i++) {

                char c = chars.remove(i);

                presses(chars, str + c, list);

                chars.add(i, c);

            }

        }

    }

    private static List<String> arrangements(String str) {

        char[] chars = str.toCharArray();

        boolean[] booleans = new boolean[chars.length];

        Arrays.fill(booleans, true);

        ArrayList<String> list = new ArrayList<>();

        String path = "";

        presses(chars, booleans, path, list);

        return list;
    }

    private static void presses(char[] chars, boolean[] index, String str, List<String> list) {

        if (str.length() == chars.length)
            list.add(str);

        else {

            for (int i = 0; i < chars.length; i++) {

                if (!index[i])
                    continue;

                index[i] = false;

                presses(chars, index, str + chars[i], list);

                index[i] = true;
            }
        }

    }

}
