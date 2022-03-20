package leetcode;

import java.util.*;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/7/26 8:27
 * @version: 1.0
 */

public class LeetCode_126_02 {

    public static void main(String[] args) {
        fn_001();
    }

    public static void fn_001() {

        String start = "cet";

        String end = "ism";

        List<String> list = new ArrayList<String>(Arrays.asList(new String[]{"kid", "tag", "pup", "ail", "tun", "woo", "erg", "luz", "brr", "gay", "sip", "kay", "per", "val", "mes", "ohs", "now", "boa", "cet", "pal", "bar", "die", "war", "hay", "eco", "pub", "lob", "rue", "fry", "lit", "rex", "jan", "cot", "bid", "ali", "pay", "col", "gum", "ger", "row", "won", "dan", "rum", "fad", "tut", "sag", "yip", "sui", "ark", "has", "zip", "fez", "own", "ump", "dis", "ads", "max", "jaw", "out", "btu", "ana", "gap", "cry", "led", "abe", "box", "ore", "pig", "fie", "toy", "fat", "cal", "lie", "noh", "sew", "ono", "tam", "flu", "mgm", "ply", "awe", "pry", "tit", "tie", "yet", "too", "tax", "jim", "san", "pan", "map", "ski", "ova", "wed", "non", "wac", "nut", "why", "bye", "lye", "oct", "old", "fin", "feb", "chi", "sap", "owl", "log", "tod", "dot", "bow", "fob", "for", "joe", "ivy", "fan", "age", "fax", "hip", "jib", "mel", "hus", "sob", "ifs", "tab", "ara", "dab", "jag", "jar", "arm", "lot", "tom", "sax", "tex", "yum", "pei", "wen", "wry", "ire", "irk", "far", "mew", "wit", "doe", "gas", "rte", "ian", "pot", "ask", "wag", "hag", "amy", "nag", "ron", "soy", "gin", "don", "tug", "fay", "vic", "boo", "nam", "ave", "buy", "sop", "but", "orb", "fen", "paw", "his", "sub", "bob", "yea", "oft", "inn", "rod", "yam", "pew", "web", "hod", "hun", "gyp", "wei", "wis", "rob", "gad", "pie", "mon", "dog", "bib", "rub", "ere", "dig", "era", "cat", "fox", "bee", "mod", "day", "apr", "vie", "nev", "jam", "pam", "new", "aye", "ani", "and", "ibm", "yap", "can", "pyx", "tar", "kin", "fog", "hum", "pip", "cup", "dye", "lyx", "jog", "nun", "par", "wan", "fey", "bus", "oak", "bad", "ats", "set", "qom", "vat", "eat", "pus", "rev", "axe", "ion", "six", "ila", "lao", "mom", "mas", "pro", "few", "opt", "poe", "art", "ash", "oar", "cap", "lop", "may", "shy", "rid", "bat", "sum", "rim", "fee", "bmw", "sky", "maj", "hue", "thy", "ava", "rap", "den", "fla", "auk", "cox", "ibo", "hey", "saw", "vim", "sec", "ltd", "you", "its", "tat", "dew", "eva", "tog", "ram", "let", "see", "zit", "maw", "nix", "ate", "gig", "rep", "owe", "ind", "hog", "eve", "sam", "zoo", "any", "dow", "cod", "bed", "vet", "ham", "sis", "hex", "via", "fir", "nod", "mao", "aug", "mum", "hoe", "bah", "hal", "keg", "hew", "zed", "tow", "gog", "ass", "dem", "who", "bet", "gos", "son", "ear", "spy", "kit", "boy", "due", "sen", "oaf", "mix", "hep", "fur", "ada", "bin", "nil", "mia", "ewe", "hit", "fix", "sad", "rib", "eye", "hop", "haw", "wax", "mid", "tad", "ken", "wad", "rye", "pap", "bog", "gut", "ito", "woe", "our", "ado", "sin", "mad", "ray", "hon", "roy", "dip", "hen", "iva", "lug", "asp", "hui", "yak", "bay", "poi", "yep", "bun", "try", "lad", "elm", "nat", "wyo", "gym", "dug", "toe", "dee", "wig", "sly", "rip", "geo", "cog", "pas", "zen", "odd", "nan", "lay", "pod", "fit", "hem", "joy", "bum", "rio", "yon", "dec", "leg", "put", "sue", "dim", "pet", "yaw", "nub", "bit", "bur", "sid", "sun", "oil", "red", "doc", "moe", "caw", "eel", "dix", "cub", "end", "gem", "off", "yew", "hug", "pop", "tub", "sgt", "lid", "pun", "ton", "sol", "din", "yup", "jab", "pea", "bug", "gag", "mil", "jig", "hub", "low", "did", "tin", "get", "gte", "sox", "lei", "mig", "fig", "lon", "use", "ban", "flo", "nov", "jut", "bag", "mir", "sty", "lap", "two", "ins", "con", "ant", "net", "tux", "ode", "stu", "mug", "cad", "nap", "gun", "fop", "tot", "sow", "sal", "sic", "ted", "wot", "del", "imp", "cob", "way", "ann", "tan", "mci", "job", "wet", "ism", "err", "him", "all", "pad", "hah", "hie", "aim", "ike", "jed", "ego", "mac", "baa", "min", "com", "ill", "was", "cab", "ago", "ina", "big", "ilk", "gal", "tap", "duh", "ola", "ran", "lab", "top", "gob", "hot", "ora", "tia", "kip", "han", "met", "hut", "she", "sac", "fed", "goo", "tee", "ell", "not", "act", "gil", "rut", "ala", "ape", "rig", "cid", "god", "duo", "lin", "aid", "gel", "awl", "lag", "elf", "liz", "ref", "aha", "fib", "oho", "tho", "her", "nor", "ace", "adz", "fun", "ned", "coo", "win", "tao", "coy", "van", "man", "pit", "guy", "foe", "hid", "mai", "sup", "jay", "hob", "mow", "jot", "are", "pol", "arc", "lax", "aft", "alb", "len", "air", "pug", "pox", "vow", "got", "meg", "zoe", "amp", "ale", "bud", "gee", "pin", "dun", "pat", "ten", "mob"}));

        List<List<String>> ladders = findLadders(start, end, list);

        for (int i = 0; i < ladders.size(); i++) {

            List<String> strings = ladders.get(i);

            for (String s : strings) {
                System.out.print(s + " ---> ");
            }

            System.out.println();

        }
    }

    public static List<List<String>> findLadders(String start, String end, List<String> list) {
        list.add(start);
        HashMap<String, List<Node>> nexts = getNexts(list);

//        reList(nexts);

        Node start1 = new Node(start, 0);

        Node end1 = new Node(end, 0);

        List<List<String>> lists = getDistances_02(start1, end1, nexts);

        return lists;
//        return null;
    }

    public static void reList(HashMap<String, List<Node>> nexts) {

        Set<String> strings = nexts.keySet();

        Iterator<String> iterator = strings.iterator();

        while (iterator.hasNext()) {

            String next = iterator.next();

            System.out.print(next + " : ");

            List<Node> nodes = nexts.get(next);

            for (Node n : nodes) {
                System.out.print(n.value + "   ");
            }

            System.out.println();
        }
    }


    //
    public static HashMap<String, List<Node>> getNexts(List<String> words) {
        HashSet<String> dict = new HashSet<>(words);
        HashMap<String, List<Node>> nexts = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            nexts.put(words.get(i), getNext(new Node(words.get(i), 0), dict));
        }
        return nexts;
    }

    // word, 在表中，有哪些邻居，把邻居们，生成list返回
    public static List<Node> getNext(Node word, HashSet<String> dict) {
        ArrayList<Node> res = new ArrayList<Node>();
        char[] chs = word.value.toCharArray();
        for (char cur = 'a'; cur <= 'z'; cur++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] != cur) {
                    char tmp = chs[i];
                    chs[i] = cur;
                    if (dict.contains(String.valueOf(chs))) {

                        Node node = new Node(String.valueOf(chs), Integer.MAX_VALUE, word);

                        res.add(node);
                    }
                    chs[i] = tmp;
                }
            }
        }
        return res;
    }

    // 生成距离表，从start开始，根据邻居表，宽度优先遍历，对于能够遇到的所有字符串，生成(字符串，距离)这条记录，放入距离表中
    public static HashMap<String, Integer> getDistances(String start, HashMap<String, List<String>> nexts) {
        HashMap<String, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        HashSet<String> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (String next : nexts.get(cur)) {
                if (!set.contains(next)) {
                    distances.put(next, distances.get(cur) + 1);
                    queue.add(next);
                    set.add(next);
                }
            }
        }
        return distances;
    }

    public static List<List<String>> getDistances_02(Node start, Node end, HashMap<String, List<Node>> nexts) {

        int w = -1;


        List<List<String>> list = new ArrayList<>();


        Queue<Node> queue = new LinkedList<>();

        queue.add(start);

        HashSet<Node> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()) {

            Node n = queue.poll();

            String cur = n.value;

            System.out.println("cur : " + cur);

//            if (cur.equals(end.value)) {
//
//                System.out.println("w : " + w);
//
//                System.out.println("n.wight : " + n.wight);
//
//                if (w == -1) {
//
//                    w = n.wight + 1;
//                }
//
////                if (n.wight + 1 <= w) {
//
//                List<String> l = new ArrayList<>(w + 1);
//
//                l.add(0, n.value);
//
//                Node node = n.from, no = null;
//
//                while (node != null) {
//
//                    l.add(0, node.value);
//
//                    node = node.from;
//
////                            node.from = null;
////
////                            node = no;
//                }
//
//                list.add(l);
////                }
//
////                continue;
//            }

            for (Node next : nexts.get(cur)) {
//                System.out.println("--------");

//                System.out.println("next : " + next.value);
//
//                System.out.println("n.wight : " + n.wight + "   " + next.value);


//                if (!next.equals(end) && w == -1) {

//                if (next.wight > n.wight + 1) {
//
//                    next.wight = n.wight + 1;
//
//                    next.from = n;
//                }

                next.from = n;

                if (set.contains(next) && set.contains(n))
                    continue;

                set.add(next);

                queue.add(next);

//                System.out.println("=========");

//                } else {

//                System.out.println("w : " + w);

//                if (!next.value.equals(end.value))
//                    continue;

//                System.out.println(" w : " + w);

//
//                if (w == -1) {
//
//                    w = n.wight + 1;
//                }
//
//                if (n.wight + 1 <= w) {
//
//                    List<String> l = new ArrayList<>(w + 1);
//
//                    l.add(0, next.value);
//
//                    Node node = n, no = null;
//
//                    while (node != null) {
//
//                        l.add(0, node.value);
//
//                        node = node.from;
//
////                            node.from = null;
////
////                            node = no;
//                    }
//
//                    list.add(l);
//                }

//                }
            }
        }
        return list;
    }

    // cur 当前来到的字符串 可变
    // to 目标，固定参数
    // nexts 每一个字符串的邻居表
    // cur 到开头距离5 -> 到开头距离是6的支路 distances距离表
    // path : 来到cur之前，深度优先遍历之前的历史是什么
    // res : 当遇到cur，把历史，放入res，作为一个结果
    public static void getShortestPaths(String cur, String to, HashMap<String, List<String>> nexts,
                                        HashMap<String, Integer> distances, LinkedList<String> path, List<List<String>> res) {
        path.add(cur);
        if (to.equals(cur)) {
            res.add(new LinkedList<String>(path));
        } else {
            for (String next : nexts.get(cur)) {
                if (distances.get(next) == distances.get(cur) + 1) {
                    getShortestPaths(next, to, nexts, distances, path, res);
                }
            }
        }
        path.pollLast();
    }

    public void relate(Node node, HashMap<String, List<Node>> nexts) {

        List<Node> nodes = nexts.get(node.value);

        for (Node n : nodes) {


        }

    }


    //    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
    public static class Node {

        public Node to;

        public Node from;

        public int wight;

        public String value;

        public Node(String value) {
            this.value = value;
        }

        public Node(String value, int wight) {
            this.value = value;

            this.wight = wight;
        }

        public Node(String value, int wight, Node from) {
            this.value = value;

            this.wight = wight;

            this.from = from;
        }


    }
}
