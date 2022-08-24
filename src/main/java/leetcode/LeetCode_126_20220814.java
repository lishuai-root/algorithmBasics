package leetcode;

import java.util.*;

/**
 * @description: A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 * @author: LISHUAI
 * @createDate: 2022/8/14 12:53
 * @version: 1.0
 */

public class LeetCode_126_20220814 {

    private static Map<String, List<List<String>>> cache;

    private static int count = 0;

    public static void main(String[] args) {
        String start = "aaaaa";
        String end = "ggggg";
        List<String> list = new ArrayList<String>(Arrays.asList(new String[]{"aaaaa", "caaaa", "cbaaa", "daaaa", "dbaaa", "eaaaa", "ebaaa", "faaaa", "fbaaa", "gaaaa", "gbaaa", "haaaa", "hbaaa", "iaaaa", "ibaaa", "jaaaa", "jbaaa", "kaaaa", "kbaaa", "laaaa", "lbaaa", "maaaa", "mbaaa", "naaaa", "nbaaa", "oaaaa", "obaaa", "paaaa", "pbaaa", "bbaaa", "bbcaa", "bbcba", "bbdaa", "bbdba", "bbeaa", "bbeba", "bbfaa", "bbfba", "bbgaa", "bbgba", "bbhaa", "bbhba", "bbiaa", "bbiba", "bbjaa", "bbjba", "bbkaa", "bbkba", "bblaa", "bblba", "bbmaa", "bbmba", "bbnaa", "bbnba", "bboaa", "bboba", "bbpaa", "bbpba", "bbbba", "abbba", "acbba", "dbbba", "dcbba", "ebbba", "ecbba", "fbbba", "fcbba", "gbbba", "gcbba", "hbbba", "hcbba", "ibbba", "icbba", "jbbba", "jcbba", "kbbba", "kcbba", "lbbba", "lcbba", "mbbba", "mcbba", "nbbba", "ncbba", "obbba", "ocbba", "pbbba", "pcbba", "ccbba", "ccaba", "ccaca", "ccdba", "ccdca", "cceba", "cceca", "ccfba", "ccfca", "ccgba", "ccgca", "cchba", "cchca", "cciba", "ccica", "ccjba", "ccjca", "cckba", "cckca", "cclba", "cclca", "ccmba", "ccmca", "ccnba", "ccnca", "ccoba", "ccoca", "ccpba", "ccpca", "cccca", "accca", "adcca", "bccca", "bdcca", "eccca", "edcca", "fccca", "fdcca", "gccca", "gdcca", "hccca", "hdcca", "iccca", "idcca", "jccca", "jdcca", "kccca", "kdcca", "lccca", "ldcca", "mccca", "mdcca", "nccca", "ndcca", "occca", "odcca", "pccca", "pdcca", "ddcca", "ddaca", "ddada", "ddbca", "ddbda", "ddeca", "ddeda", "ddfca", "ddfda", "ddgca", "ddgda", "ddhca", "ddhda", "ddica", "ddida", "ddjca", "ddjda", "ddkca", "ddkda", "ddlca", "ddlda", "ddmca", "ddmda", "ddnca", "ddnda", "ddoca", "ddoda", "ddpca", "ddpda", "dddda", "addda", "aedda", "bddda", "bedda", "cddda", "cedda", "fddda", "fedda", "gddda", "gedda", "hddda", "hedda", "iddda", "iedda", "jddda", "jedda", "kddda", "kedda", "lddda", "ledda", "mddda", "medda", "nddda", "nedda", "oddda", "oedda", "pddda", "pedda", "eedda", "eeada", "eeaea", "eebda", "eebea", "eecda", "eecea", "eefda", "eefea", "eegda", "eegea", "eehda", "eehea", "eeida", "eeiea", "eejda", "eejea", "eekda", "eekea", "eelda", "eelea", "eemda", "eemea", "eenda", "eenea", "eeoda", "eeoea", "eepda", "eepea", "eeeea", "ggggg", "agggg", "ahggg", "bgggg", "bhggg", "cgggg", "chggg", "dgggg", "dhggg", "egggg", "ehggg", "fgggg", "fhggg", "igggg", "ihggg", "jgggg", "jhggg", "kgggg", "khggg", "lgggg", "lhggg", "mgggg", "mhggg", "ngggg", "nhggg", "ogggg", "ohggg", "pgggg", "phggg", "hhggg", "hhagg", "hhahg", "hhbgg", "hhbhg", "hhcgg", "hhchg", "hhdgg", "hhdhg", "hhegg", "hhehg", "hhfgg", "hhfhg", "hhigg", "hhihg", "hhjgg", "hhjhg", "hhkgg", "hhkhg", "hhlgg", "hhlhg", "hhmgg", "hhmhg", "hhngg", "hhnhg", "hhogg", "hhohg", "hhpgg", "hhphg", "hhhhg", "ahhhg", "aihhg", "bhhhg", "bihhg", "chhhg", "cihhg", "dhhhg", "dihhg", "ehhhg", "eihhg", "fhhhg", "fihhg", "ghhhg", "gihhg", "jhhhg", "jihhg", "khhhg", "kihhg", "lhhhg", "lihhg", "mhhhg", "mihhg", "nhhhg", "nihhg", "ohhhg", "oihhg", "phhhg", "pihhg", "iihhg", "iiahg", "iiaig", "iibhg", "iibig", "iichg", "iicig", "iidhg", "iidig", "iiehg", "iieig", "iifhg", "iifig", "iighg", "iigig", "iijhg", "iijig", "iikhg", "iikig", "iilhg", "iilig", "iimhg", "iimig", "iinhg", "iinig", "iiohg", "iioig", "iiphg", "iipig", "iiiig", "aiiig", "ajiig", "biiig", "bjiig", "ciiig", "cjiig", "diiig", "djiig", "eiiig", "ejiig", "fiiig", "fjiig", "giiig", "gjiig", "hiiig", "hjiig", "kiiig", "kjiig", "liiig", "ljiig", "miiig", "mjiig", "niiig", "njiig", "oiiig", "ojiig", "piiig", "pjiig", "jjiig", "jjaig", "jjajg", "jjbig", "jjbjg", "jjcig", "jjcjg", "jjdig", "jjdjg", "jjeig", "jjejg", "jjfig", "jjfjg", "jjgig", "jjgjg", "jjhig", "jjhjg", "jjkig", "jjkjg", "jjlig", "jjljg", "jjmig", "jjmjg", "jjnig", "jjnjg", "jjoig", "jjojg", "jjpig", "jjpjg", "jjjjg", "ajjjg", "akjjg", "bjjjg", "bkjjg", "cjjjg", "ckjjg", "djjjg", "dkjjg", "ejjjg", "ekjjg", "fjjjg", "fkjjg", "gjjjg", "gkjjg", "hjjjg", "hkjjg", "ijjjg", "ikjjg", "ljjjg", "lkjjg", "mjjjg", "mkjjg", "njjjg", "nkjjg", "ojjjg", "okjjg", "pjjjg", "pkjjg", "kkjjg", "kkajg", "kkakg", "kkbjg", "kkbkg", "kkcjg", "kkckg", "kkdjg", "kkdkg", "kkejg", "kkekg", "kkfjg", "kkfkg", "kkgjg", "kkgkg", "kkhjg", "kkhkg", "kkijg", "kkikg", "kkljg", "kklkg", "kkmjg", "kkmkg", "kknjg", "kknkg", "kkojg", "kkokg", "kkpjg", "kkpkg", "kkkkg", "ggggx", "gggxx", "ggxxx", "gxxxx", "xxxxx", "xxxxy", "xxxyy", "xxyyy", "xyyyy", "yyyyy", "yyyyw", "yyyww", "yywww", "ywwww", "wwwww", "wwvww", "wvvww", "vvvww", "vvvwz", "avvwz", "aavwz", "aaawz", "aaaaz"}));

//        String start = "hit";
//        String end = "cog";
//        List<String> list = new ArrayList<String>(Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));


//        List<String> list = new ArrayList<String>(Arrays.asList(new String[]{"aaaaa"}));
        List<List<String>> ladders = findLadders(start, end, list);
        System.out.println(ladders.size());
        for (List<String> l : ladders) {
            for (String line : l) {
                System.out.print(line + " -> ");
            }
            System.out.println();
        }
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        PrefixTree root = makePrefixTree(wordList);
        List<List<String>> ans = new ArrayList<>();
        List<String> list = new ArrayList<>();
        cache = new HashMap<>();
        String line = "dog";
//        for (int i = 0; i < line.length(); i++) {
//            System.out.println(hasStr(root, line.substring(i)));
//            root = root.values[line.charAt(i) - 'a'];
//        }
        findLaddersProcess(beginWord, endWord, root, ans, list);
        return ans;
    }

    private static void findLaddersProcess(String beginWord, String endWord, PrefixTree root,
                                           List<List<String>> ans, List<String> list) {

        list.add(beginWord);
        if (beginWord.equals(endWord)) {

//            for (int i = 0; i < list.size(); i++) {
//                List<List<String>> ls = cache.computeIfAbsent(list.get(i), k -> new ArrayList<>());
//                List<String> l = new ArrayList<>();
//                for (int j = i + 1; j < list.size(); j++) {
//                    l.add(list.get(j));
//                }
//                ls.add(l);
//            }

            if (ans.isEmpty()) {
                ans.add(new ArrayList<>(list));
            } else {
                if (ans.get(0).size() > list.size()) {
                    ans.clear();
                    ans.add(new ArrayList<>(list));
                } else if (ans.get(0).size() == list.size()) {
                    ans.add(new ArrayList<>(list));
                }
            }

            list.remove(list.size() - 1);
            return;
        }

        char[] chars = beginWord.toCharArray();
        PrefixTree node = root;

        for (int i = 0; node != null && i < chars.length; i++) {
            char t = chars[i];
            int tmp = node.bit;
            int rb;

            while (tmp != 0) {
                rb = (-tmp & tmp);
                tmp ^= rb;
                rb = 31 - Integer.numberOfLeadingZeros(rb);
                chars[i] = (char) (rb + 'a');
                if (remove(node, chars, i)) {
                    String s = String.valueOf(chars);
                    findLaddersProcess(s, endWord, root, ans, list);
                    add(root, s);
                }
            }

//            for (int j = 0; j < 26; j++) {
//                if (t - 'a' == j) {
//                    continue;
//                }
//                chars[i] = (char) (j + 'a');
//                if (remove(node, chars, i)) {
//                    String s = String.valueOf(chars);
//                    findLaddersProcess(s, endWord, root, ans, list);
//                    add(root, s);
//                }
//            }
            node = node.values[t - 'a'];
            chars[i] = t;
        }
        if (!cache.containsKey(beginWord)) {
            cache.put(beginWord, new ArrayList<>());
        }
        list.remove(list.size() - 1);
    }

    private static boolean hasStr(PrefixTree root, String line) {
        for (int i = 0; i < line.length(); i++) {
            int c = line.charAt(i) - 'a';
            if (root.values[c] == null) {
                return false;
            }
            root = root.values[c];
        }
        return root.isEnd;
    }

    private static boolean remove(PrefixTree root, char[] chars, int index) {

        while (root != null && index < chars.length) {
            int c = chars[index++] - 'a';
            root = root.values[c];
        }
        boolean ans = false;
        if (root != null && root.isEnd) {
            ans = true;
            root.isEnd = false;
        }
        return ans;
    }

    private static PrefixTree makePrefixTree(List<String> wordList) {
        PrefixTree root = new PrefixTree();

        for (String line : wordList) {
            add(root, line);
        }
        return root;
    }

    private static void add(PrefixTree root, String line) {
        for (int i = 0; i < line.length(); i++) {
            int c = line.charAt(i) - 'a';
            if (root.values[c] == null) {
                root.values[c] = new PrefixTree();
            }
            root.bit |= (1 << c);
            root = root.values[c];
        }
        root.isEnd = true;
    }

    static class PrefixTree {
        PrefixTree[] values;
        boolean isEnd;
        int bit;

        public PrefixTree() {
            this.values = new PrefixTree[26];
            this.isEnd = false;
            this.bit = 0;
        }
    }
}
