package leetcode;

import java.util.*;

/**
 * @description: A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList,
 * return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 * @author: LISHUAI
 * @createDate: 2022/2/12 11:50
 * @version: 1.0
 */

public class LeetCode_127 {
    private static Map<String, Integer> map;

    public static void main(String[] args) {

//        String beginWord = "hit", endWord = "cog";
        String beginWord = "cet", endWord = "ism";
//        String beginWord = "ymain", endWord = "oecij";
//        String[] s = new String[]{"ymann", "yycrj", "oecij", "ymcnj", "yzcrj", "yycij", "xecij", "yecij", "ymanj", "yzcnj", "ymain"};

        String[] s = new String[]{"kid", "tag", "pup", "ail", "tun", "woo", "erg", "luz", "brr", "gay", "sip", "kay", "per", "val", "mes", "ohs", "now", "boa", "cet", "pal", "bar", "die", "war", "hay", "eco", "pub", "lob", "rue", "fry", "lit", "rex", "jan", "cot", "bid", "ali", "pay", "col", "gum", "ger", "row", "won", "dan", "rum", "fad", "tut", "sag", "yip", "sui", "ark", "has", "zip", "fez", "own", "ump", "dis", "ads", "max", "jaw", "out", "btu", "ana", "gap", "cry", "led", "abe", "box", "ore", "pig", "fie", "toy", "fat", "cal", "lie", "noh", "sew", "ono", "tam", "flu", "mgm", "ply", "awe", "pry", "tit", "tie", "yet", "too", "tax", "jim", "san", "pan", "map", "ski", "ova", "wed", "non", "wac", "nut", "why", "bye", "lye", "oct", "old", "fin", "feb", "chi", "sap", "owl", "log", "tod", "dot", "bow", "fob", "for", "joe", "ivy", "fan", "age", "fax", "hip", "jib", "mel", "hus", "sob", "ifs", "tab", "ara", "dab", "jag", "jar", "arm", "lot", "tom", "sax", "tex", "yum", "pei", "wen", "wry", "ire", "irk", "far", "mew", "wit", "doe", "gas", "rte", "ian", "pot", "ask", "wag", "hag", "amy", "nag", "ron", "soy", "gin", "don", "tug", "fay", "vic", "boo", "nam", "ave", "buy", "sop", "but", "orb", "fen", "paw", "his", "sub", "bob", "yea", "oft", "inn", "rod", "yam", "pew", "web", "hod", "hun", "gyp", "wei", "wis", "rob", "gad", "pie", "mon", "dog", "bib", "rub", "ere", "dig", "era", "cat", "fox", "bee", "mod", "day", "apr", "vie", "nev", "jam", "pam", "new", "aye", "ani", "and", "ibm", "yap", "can", "pyx", "tar", "kin", "fog", "hum", "pip", "cup", "dye", "lyx", "jog", "nun", "par", "wan", "fey", "bus", "oak", "bad", "ats", "set", "qom", "vat", "eat", "pus", "rev", "axe", "ion", "six", "ila", "lao", "mom", "mas", "pro", "few", "opt", "poe", "art", "ash", "oar", "cap", "lop", "may", "shy", "rid", "bat", "sum", "rim", "fee", "bmw", "sky", "maj", "hue", "thy", "ava", "rap", "den", "fla", "auk", "cox", "ibo", "hey", "saw", "vim", "sec", "ltd", "you", "its", "tat", "dew", "eva", "tog", "ram", "let", "see", "zit", "maw", "nix", "ate", "gig", "rep", "owe", "ind", "hog", "eve", "sam", "zoo", "any", "dow", "cod", "bed", "vet", "ham", "sis", "hex", "via", "fir", "nod", "mao", "aug", "mum", "hoe", "bah", "hal", "keg", "hew", "zed", "tow", "gog", "ass", "dem", "who", "bet", "gos", "son", "ear", "spy", "kit", "boy", "due", "sen", "oaf", "mix", "hep", "fur", "ada", "bin", "nil", "mia", "ewe", "hit", "fix", "sad", "rib", "eye", "hop", "haw", "wax", "mid", "tad", "ken", "wad", "rye", "pap", "bog", "gut", "ito", "woe", "our", "ado", "sin", "mad", "ray", "hon", "roy", "dip", "hen", "iva", "lug", "asp", "hui", "yak", "bay", "poi", "yep", "bun", "try", "lad", "elm", "nat", "wyo", "gym", "dug", "toe", "dee", "wig", "sly", "rip", "geo", "cog", "pas", "zen", "odd", "nan", "lay", "pod", "fit", "hem", "joy", "bum", "rio", "yon", "dec", "leg", "put", "sue", "dim", "pet", "yaw", "nub", "bit", "bur", "sid", "sun", "oil", "red", "doc", "moe", "caw", "eel", "dix", "cub", "end", "gem", "off", "yew", "hug", "pop", "tub", "sgt", "lid", "pun", "ton", "sol", "din", "yup", "jab", "pea", "bug", "gag", "mil", "jig", "hub", "low", "did", "tin", "get", "gte", "sox", "lei", "mig", "fig", "lon", "use", "ban", "flo", "nov", "jut", "bag", "mir", "sty", "lap", "two", "ins", "con", "ant", "net", "tux", "ode", "stu", "mug", "cad", "nap", "gun", "fop", "tot", "sow", "sal", "sic", "ted", "wot", "del", "imp", "cob", "way", "ann", "tan", "mci", "job", "wet", "ism", "err", "him", "all", "pad", "hah", "hie", "aim", "ike", "jed", "ego", "mac", "baa", "min", "com", "ill", "was", "cab", "ago", "ina", "big", "ilk", "gal", "tap", "duh", "ola", "ran", "lab", "top", "gob", "hot", "ora", "tia", "kip", "han", "met", "hut", "she", "sac", "fed", "goo", "tee", "ell", "not", "act", "gil", "rut", "ala", "ape", "rig", "cid", "god", "duo", "lin", "aid", "gel", "awl", "lag", "elf", "liz", "ref", "aha", "fib", "oho", "tho", "her", "nor", "ace", "adz", "fun", "ned", "coo", "win", "tao", "coy", "van", "man", "pit", "guy", "foe", "hid", "mai", "sup", "jay", "hob", "mow", "jot", "are", "pol", "arc", "lax", "aft", "alb", "len", "air", "pug", "pox", "vow", "got", "meg", "zoe", "amp", "ale", "bud", "gee", "pin", "dun", "pat", "ten", "mob"};
//        String[] s = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = new ArrayList<>();
        wordList.addAll(Arrays.asList(s));
        long start = System.currentTimeMillis();
        int i = ladderLength_03(beginWord, endWord, wordList);
        long end = System.currentTimeMillis();
        System.out.println("times old : " + (end - start));

        start = System.currentTimeMillis();
        int i1 = ladderLength_02(beginWord, endWord, wordList);
        end = System.currentTimeMillis();
        System.out.println("times new : " + (end - start));

        System.out.println(i);

        System.out.println(i1);

    }


    // 有问题
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

        int index = -1;

        Set<String> wordSet = new HashSet<>();

        for (int i = 0; i < wordList.size(); i++) {

            if (endWord.equals(wordList.get(i))) {

                index = i;
            }

            if (beginWord.equals(wordList.get(i))) {

                continue;
            }

            wordSet.add(wordList.get(i));
        }

        if (index == -1) {

            return 0;
        }

        map = new HashMap<>();
        map.put(endWord, 1);

        int i = ladderLengthProcess(beginWord, endWord, wordSet);

        return i == Integer.MAX_VALUE ? 0 : i;
    }

    private static int ladderLengthProcess(String beginWord, String endWord, Set<String> wordSet) {

        if (map.containsKey(beginWord)) {

            return map.get(beginWord);
        }

        int ans = Integer.MAX_VALUE, len = beginWord.length();

        String line;

        for (int i = 0; i < beginWord.length(); i++) {

            for (int j = 0; j < 26; j++) {

                if (beginWord.charAt(i) != (j + 'a') &&
                        wordSet.contains((line = beginWord.substring(0, i) + ((char) (j + 'a')) + beginWord.substring(i + 1, len)))) {

                    wordSet.remove(line);

                    int i1;

                    if (line.equals(endWord)) {

                        return 1;
                    }

                    if (map.containsKey(line)) {

                        i1 = map.get(line);
                    } else {
                        i1 = ladderLengthProcess(line, endWord, wordSet);
                    }
                    if (i1 != Integer.MAX_VALUE) {

                        i1++;
                    }


                    ans = Math.min(ans, i1);

                    wordSet.add(line);
                }
            }
        }
        if (!map.containsKey(beginWord) || ans < map.get(beginWord)) {
            map.put(beginWord, ans);
        }
        return ans;
    }

    private static void showMap(Map<String, Integer> map) {

        Set<String> objects = map.keySet();

        for (Object o : objects) {

            System.out.println(o + " : " + map.get(o).toString());
        }
    }


    public static int ladderLength_02(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) {
            return 0;
        }

        wordSet.remove(beginWord);

        List<String> list = new ArrayList<>();

        list.add(beginWord);
        return ladderLengthProcess_02(endWord, wordSet, list, 1);
    }

    private static int ladderLengthProcess_02(String endWord, Set<String> wordSet, List<String> list, int rows) {

        if (list.size() == 0) {
            return 0;
        }

        List<String> l = new ArrayList<>();

        String line;

        for (String s : list) {

            for (int i = 0; i < s.length(); i++) {
                char[] chars = s.toCharArray();
                char c = chars[i];
                for (char j = 'a'; j <= 'z'; j++) {

                    if (c != j) {
                        chars[i] = j;
                        line = String.valueOf(chars);
                        if (line.equals(endWord)) {
                            return rows + 1;
                        }

                        if (wordSet.contains(line)) {
                            l.add(line);
                            wordSet.remove(line);
                        }
                    }
                }
            }
        }

        return ladderLengthProcess_02(endWord, wordSet, l, rows + 1);
    }


    public static int ladderLength_03(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        int rows = 1;
        Set<String> startSet = new HashSet<>();
        Set<String> set = new HashSet<>(), ss;
        startSet.add(beginWord);
        String line;
        while (!startSet.isEmpty()) {
            for (String s : startSet) {
                for (int i = 0; i < s.length(); i++) {
                    char[] chars = s.toCharArray();
                    char c = chars[i];
                    for (char j = 'a'; j <= 'z'; j++) {

                        if (c != j) {
                            chars[i] = j;

                            line = String.valueOf(chars);
                            if (line.equals(endWord)) {
                                return rows + 1;
                            }

                            if (wordSet.contains(line)) {
                                set.add(line);
                                wordSet.remove(line);
                            }
                        }
                    }
                }
//                startSet.remove(s);
            }
            startSet.clear();
            rows++;
            ss = startSet;
            startSet = set;
            set = ss;
        }

        return 0;
    }

    static class treeNode {

        char value, parent;

        treeNode[] child = new treeNode[26];

        int roadSize = 0, end = 0, endSize = 0;
    }
}
