package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/23 22:56
 * @version: 1.0
 */

public class TreeText {

    Child child;

    public TreeText(Child c) {
        this.child = c;
    }

    public static void main(String[] args) {

        String[] words = new String[]{"huihui", "huihui", "shuaishuai", "aaa"};


//        PrefixTree prefixTree = new PrefixTree();
//
//        prefixTree.insertWord("lishuai");
//
//        System.out.println(prefixTree.isEmpty());
//
//        System.out.println(prefixTree.searchCount("lishuai"));
//
//        System.out.println(prefixTree.size());
//
//        System.out.println("--------------");
//
//        prefixTree.makeTree(words);
//
//        System.out.println(prefixTree.size());
//
//        System.out.println(prefixTree.searchCount("huihui"));
//
//        PrefixTree tree = new PrefixTree(words);
//
//
//        System.out.println(tree.size());
//
//        System.out.println(tree.isEmpty());
//
//        System.out.println(tree.size());
//
//        tree.removeWord("huihui");
//
//        System.out.println(tree.size());
//
//        System.out.println(tree.searchCount("huihui"));
////
////
//        System.out.println(tree.searchPrefix("huihu"));
//
//        List<String> list = tree.orderSingle();
//
//        for (String str : list) {
//
//            System.out.println(str);
//        }
        String[] strings = makeStringArray(100000, 100);

        String s = strings[strings.length - 1];


        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(strings));


        PrefixTree tree = new PrefixTree(strings);

        long start = System.currentTimeMillis();

        boolean bl = arrayList.contains(s);

        long end = System.currentTimeMillis();

        System.out.println("boolean : " + bl + ", times : " + (end - start));

        start = System.currentTimeMillis();

        bl = tree.contains(s);

        end = System.currentTimeMillis();

        System.out.println("boolean : " + bl + ", times : " + (end - start));
    }

    private static String[] makeStringArray(int size, int strLen) {

        String[] arr = new String[size];

        StringBuilder sb = new StringBuilder();

        Random r = new Random();

        int c;

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < strLen; j++) {

                c = r.nextInt(26) + 'a';

                sb.append((char) c);
            }

            arr[i] = sb.toString();

            sb.delete(0, sb.length());
        }

        return arr;
    }

}
