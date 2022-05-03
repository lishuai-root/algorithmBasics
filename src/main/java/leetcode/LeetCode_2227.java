package leetcode;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @description: You are given a character array keys containing unique characters and a string array values containing strings of length 2.
 * You are also given another string array dictionary that contains all permitted original strings after decryption.
 * You should implement a data structure that can encrypt or decrypt a 0-indexed string.
 * <p>
 * A string is encrypted with the following process:
 * <p>
 * For each character c in the string, we find the index i satisfying keys[i] == c in keys.
 * Replace c with values[i] in the string.
 * Note that in case a character of the string is not present in keys, the encryption process cannot be carried out, and an empty string "" is returned.
 * <p>
 * A string is decrypted with the following process:
 * <p>
 * For each substring s of length 2 occurring at an even index in the string, we find an i such that values[i] == s. If there are multiple valid i,
 * we choose any one of them. This means a string could have multiple possible strings it can decrypt to.
 * Replace s with keys[i] in the string.
 * Implement the Encrypter class:
 * <p>
 * Encrypter(char[] keys, String[] values, String[] dictionary) Initializes the Encrypter class with keys, values, and dictionary.
 * String encrypt(String word1) Encrypts word1 with the encryption process described above and returns the encrypted string.
 * int decrypt(String word2) Returns the number of possible strings word2 could decrypt to that also appear in dictionary.
 * <p>
 * Encrypter encrypter = new Encrypter([['a', 'b', 'c', 'd'], ["ei", "zf", "ei", "am"], ["abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad"]);
 * encrypter.encrypt("abcd"); // return "eizfeiam".
 * // 'a' maps to "ei", 'b' maps to "zf", 'c' maps to "ei", and 'd' maps to "am".
 * encrypter.decrypt("eizfeiam"); // return 2.
 * // "ei" can map to 'a' or 'c', "zf" maps to 'b', and "am" maps to 'd'.
 * // Thus, the possible strings after decryption are "abad", "cbad", "abcd", and "cbcd".
 * // 2 of those strings, "abad" and "abcd", appear in dictionary, so the answer is 2.
 * @author: LISHUAI
 * @createDate: 2022/4/12 22:13
 * @version: 1.0
 */

public class LeetCode_2227 {

    private static int count = 0, ccc = 0;

    public static void main(String[] args) throws Exception {
//        char[] keys = {'a', 'b', 'c', 'd'};
//        String[] values = {"ei", "zf", "ei", "am"}, dictionary = {"abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad"};
        char[] keys = {'d', 's', 'y', 'i', 'h', 'j', 't', 'x', 'f', 'o', 'm', 'w', 'e', 'k', 'q', 'l', 'b', 'r', 'c', 'g', 'v', 'u', 'p', 'n', 'a', 'z'};
        String[] values = {"mi", "wr", "lr", "qj", "gi", "ic", "ju", "ur", "zg", "er", "te", "ev", "tx", "wn", "fw", "lo", "jc", "vy", "ne", "zb", "qx", "xe", "xt", "qx", "ai", "wf"},
                dictionary = {"ycedrwbfur"};

//        Encrypter encrypter = new Encrypter(keys, values, dictionary);
//        String abcd = encrypter.encrypt("abcd");
//        System.out.println(abcd);
//        int decrypt = encrypter.decrypt(abcd);
//        System.out.println(decrypt);

        fn_001(keys, values, dictionary);
    }

    private static void fn_001(char[] keys, String[] values, String[] dictionary) throws Exception {
        String filePath = "src/main/resources/leetCode_2227.txt";
        Scanner scanner = new Scanner(new File(filePath));
        List<String> invoke = new ArrayList<>();
        List<String> args = new ArrayList<>();
        List<String> tmp = invoke;
        String line;

        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if (line.startsWith("---")) {
                tmp = args;
                continue;
            }
            tmp.add(line);
        }

        Encrypter encrypter = new Encrypter(keys, values, dictionary);
        Class<? extends Encrypter> aClass = encrypter.getClass();
        System.out.println("invoke size : " + invoke.size() + ", args size : " + args.size());
        long start = System.currentTimeMillis();
        for (int i = 0; i < invoke.size(); i++) {
            String methodName = invoke.get(i);
            String methodArg = args.get(i);
            Method method = aClass.getMethod(methodName, methodArg.getClass());
            method.setAccessible(true);

            Object invoke1 = method.invoke(encrypter, methodArg);
            encrypter.addSize();
//            System.out.println(invoke1.toString());
        }
        long end = System.currentTimeMillis();
        System.out.println("times : " + (end - start));
        System.out.println(encrypter.getSize());
        System.out.println(count);
        System.out.println(ccc);
    }


    static class Encrypter {

        PrefixTree treeRoot;
        String[] keysMap;
        Map<String, List<Character>> map;
        private int size = 0;

        public Encrypter(char[] keys, String[] values, String[] dictionary) {
            treeRoot = makePrefixTree(dictionary);
            keysMap = new String[26];
            map = new HashMap<>();
            for (int i = 0; i < keys.length; i++) {
                keysMap[keys[i] - 'a'] = values[i];
                if (!map.containsKey(values[i])) {
                    map.put(values[i], new ArrayList<>());
                }
                map.get(values[i]).add(keys[i]);
            }
        }

        private static PrefixTree makePrefixTree(String[] words) {
            PrefixTree root = new PrefixTree(), node;
            for (String s : words) {
                char[] chars = s.toCharArray();
                node = root;
                for (char c : chars) {
                    int cs = c - 'a';
                    if (node.values[cs] == null) {
                        node.values[cs] = new PrefixTree();
                    }
                    node = node.values[cs];
                    node.count++;
                }
                node.endCount++;
                node.isEnd = true;
            }
            return root;
        }

        public void addSize() {
            ++size;
        }

        public int getSize() {
            return size;
        }

        public String encrypt(String word1) {

            StringBuilder sbr = new StringBuilder();
            for (char c : word1.toCharArray()) {
                ++ccc;
                if (keysMap[c - 'a'] == null) {
                    return "";
                }
                sbr.append(keysMap[c - 'a']);
            }
            return sbr.toString();
        }

        public int decrypt(String word2) {
            return decryptProcess(word2, 0, treeRoot);
        }

        private int decryptProcess(String word2, int index, PrefixTree node) {
            ++count;
            if (index == word2.length()) {
                if (node.isEnd) {
                    return node.endCount;
                }
                return 0;
            }

            List<Character> list = map.get(word2.substring(index, index + 2));


            int ans = 0;
            if (list != null) {
                for (char c : list) {
                    if (node.values[c - 'a'] != null) {
                        ans += decryptProcess(word2, index + 2, node.values[c - 'a']);
                    }
                }
            }
            return ans;
        }

        static class PrefixTree {
            PrefixTree[] values = new PrefixTree[26];
            int count = 0, endCount;
            boolean isEnd = false;

            public PrefixTree() {

            }
        }
    }


}
