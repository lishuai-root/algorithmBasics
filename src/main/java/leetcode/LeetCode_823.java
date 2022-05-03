package leetcode;

import java.util.*;

/**
 * @description: Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
 * <p>
 * We make a binary tree using these integers, and each number may be used for any number of times.
 * Each non-leaf node's value should be equal to the product of the values of its children.
 * <p>
 * Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2022/4/4 15:05
 * @version: 1.0
 */

public class LeetCode_823 {
    private static final int SILK = 1000000007;
    private static Long[] cache;
    private static Map<Integer, Integer> map;

    public static void main(String[] args) {
//        int[] arr = {2, 4};
//        int[] arr = {2, 4, 5, 10};
//        int[] arr = {46, 144, 5040, 4488, 544, 380, 4410, 34, 11, 5, 3063808, 5550, 34496, 12, 540, 28, 18, 13, 2, 1056, 32710656, 31, 91872, 23, 26, 240, 18720, 33, 49, 4, 38, 37, 1457, 3, 799, 557568, 32, 1400, 47, 10, 20774, 1296, 9, 21, 92928, 8704, 29, 2162, 22, 1883700, 49588, 1078, 36, 44, 352, 546, 19, 523370496, 476, 24, 6000, 42, 30, 8, 16262400, 61600, 41, 24150, 1968, 7056, 7, 35, 16, 87, 20, 2730, 11616, 10912, 690, 150, 25, 6, 14, 1689120, 43, 3128, 27, 197472, 45, 15, 585, 21645, 39, 40, 2205, 17, 48, 136};
        int[] arr = {60588000, 36158400, 950040, 162450288, 446000100, 1807920, 31590000, 360, 166153680, 6, 91213584, 34186320, 915124000, 68600, 6497400, 10, 194304, 951035904, 393822000, 23522400, 14400, 821376000, 80, 881118000, 576000, 3660480, 106801200, 180690300, 5100, 1573200, 36288000, 38, 197643600, 221852925, 8820, 36000, 822294528, 13935240, 236307456, 17409600, 443741760, 62868, 548800000, 25271064, 34848, 344137500, 8383500, 186, 45, 100980, 722358000, 63000000, 16442400, 1579500, 684, 12970692, 243540, 232560, 149153400, 437400, 16215, 713460, 75524400, 118800, 1575, 106920, 10150, 13770000, 33999966, 177811200, 64680, 2880768, 632459520, 8675100, 498700800, 213572700, 18657600, 22, 6993, 49, 177147000, 6480, 20, 3910368, 85201200, 1764, 120093120, 16022160, 290822400, 6219200, 29376000, 235290, 20979000, 5, 384, 959140, 443944800, 11303040, 952560, 47736000, 33404280, 39936, 11864320, 144270720, 12868224, 18289152, 87230682, 145860, 262548, 10276500, 12, 612, 179010000, 622987200, 108475200, 1012, 60, 14880, 10924200, 2285568, 149178000, 1573936, 195695500, 213269760, 24975, 12000, 3706560, 168382080, 114724, 87650640, 8019000, 8035200, 8621844, 514500, 155, 132612480, 1676700, 627318720, 39330, 17, 926100000, 283360, 69949440, 856575, 4092, 130, 720, 958035000, 5396820, 2668, 6606336, 169708000, 2352240, 26, 40608000, 324576, 3220, 4945920, 187697664, 350, 3965520, 40500, 9000, 142560, 39580800, 447051000, 19077120, 36605250, 775, 19404, 87514000, 2673, 9720, 4942080, 56752500, 118216665, 858, 18225, 3124800, 1530, 380160, 24554880, 2762100, 646945200, 173210400, 22376250, 731076192, 161, 44705100, 145981440, 18223660, 215424, 878526000, 22883952, 92092, 7306200, 32, 2708160, 566957475, 17305344, 119556000, 411402240, 1292544, 454480, 2208, 27498240, 249230520, 88058880, 2108, 527800, 455, 49846104, 801900, 252450, 7644, 129540320, 2241675, 1330, 71089200, 2709630, 43545600, 497250, 4972968, 826277760, 249091200, 68827500, 60900, 271282240, 13910400, 88609950, 46189440, 3088800, 582912000, 4284000, 304980000, 32736, 992, 52992, 454545000, 14064960, 72967500, 892584000, 61678260, 3410, 49104000, 840931520, 107805600, 200475, 35384580, 4289408, 599079390, 777, 465696, 7956000, 540960, 3385200, 8741250, 17748, 2528240, 2248480, 83076840, 366282000, 15120000, 6871956, 15586560, 992673792, 367200, 65577600, 635796000, 150, 62524000, 7551600, 1716660, 85932, 209986560, 6167826, 1557192, 20702500, 157320, 14427072, 553402080, 203290560, 3830112, 1500, 120992256, 89280, 66943800, 8937984, 30, 32457600, 75140, 874, 34398000, 390600, 955500, 32542560, 51068160, 6624, 545368824, 316461600, 236670, 598067712, 97538400, 3240000, 664801200, 6120, 477487296, 445419000, 38318280, 148800, 7, 1055600, 9128700, 6472800, 13176240, 535680, 7998, 345960, 1262250, 78693120, 1364, 15, 13800, 406000, 42, 23, 703872, 787939152, 24288, 37795758, 6300000, 145704960, 124, 46920, 3142656, 31725, 92, 186300, 1470, 73500, 3495250, 196416, 639878400, 65800, 1015680, 131176500, 37264500, 243984000, 19227000, 917280, 9384000, 81840, 454406400, 795217500, 147420000, 6976800, 14651280, 116, 11960, 153740160, 799948800, 5184000, 501686640, 1020, 773500, 145763550, 26417664, 11446380, 13, 14817600, 406125720, 7068600, 730667520, 3371004, 2677752, 2692305, 70615314, 1771, 388411200, 20175075, 14742000, 139991040, 302400, 40131360, 21740796, 5996025, 205615179, 469193472, 23220, 8286300, 19706400, 2812992, 72886425, 3072, 6557760, 551779200, 960, 74592, 185427840, 40068864, 883872000, 85250, 3499200, 2210, 402772500, 1274, 106260, 4392080, 953146480, 90744192, 132699840, 4270560, 361746000, 342720, 673536600, 2418, 234600, 967255800, 34500, 8910, 196040, 374190336, 9790200, 694747200, 5385600, 907200, 493426080, 804683880, 13820544, 7999488, 4879680, 88400, 13765500, 28, 377, 172339200, 97152, 427680, 41731200, 92610, 13950000, 564200, 2520, 74970, 11603592, 16, 18200, 201386250, 474525, 531468, 1860, 51840, 677376, 59520, 4457400, 8912376, 6742008, 11013975, 566280, 330832, 110707200, 172727100, 382200, 942480, 10200, 3210480, 2033600, 11289564, 6667920, 675347400, 79994880, 27676800, 158100, 133722000, 66769920, 332041248, 25, 1545600, 21450, 655966080, 11814660, 223200, 4151040, 1016064, 35, 1607040, 924, 586333020, 174787200, 63756, 18957120, 788900000, 351900000, 1380, 5441800, 374, 44200, 38640, 8307684, 109707264, 2178, 7440, 6577200, 29435, 264600000, 12960000, 2646, 891691416, 475656192, 214033920, 431708160, 70340256, 404736, 39104, 787059000, 3208500, 220103100, 191268, 66960000, 18500832, 856016640, 173901000, 1238688, 157489920, 54782, 550946880, 242573760, 257040, 282720, 117645000, 165369600, 13110, 434079360, 9133344, 621000, 174037500, 126960, 6147900, 137491200, 29559600, 700, 185220000, 317520000, 200344320, 14036, 26601120, 3535488, 3649940, 16240, 61659000, 430848, 779483250, 363528000, 419764800, 729933750, 386661600, 111481920, 35700, 6561000, 68695200, 70, 297, 83700, 990, 113076810, 461538, 787529952, 4324, 20727, 40350150, 218700, 846720, 540, 29889000, 1016600, 277704000, 62734080, 14856192, 210924000, 589248000, 5760, 222393600, 30180600, 166059400, 31021056, 98208, 21, 21168, 98208000, 13464, 1555200, 16077600, 288, 4332042, 320550912, 204989400, 785664, 67897440, 65472, 16228800, 70560, 80352, 8, 9520896, 71280, 4862, 44, 12545280, 657395200, 39, 64152000, 283719996, 247443840, 924159600, 20286000, 268065, 138983850, 9300, 5286120, 61534200, 496601280, 875033280, 6370, 5940, 364, 29412, 261360, 1248, 477290880, 5880, 669600, 82174400, 53568, 483000, 36, 18, 29728512, 12057760, 3046680, 44660, 368874000, 167040, 4276800, 83462400, 11050, 638, 34300000, 27, 509110272, 437132430, 81200, 33, 6277500, 113601600, 14850, 10789200, 157216500, 20348928, 10701900, 365904000, 1728, 1782960, 1255800, 38321400, 3542, 6229872, 40864824, 514483200, 43, 1159200, 413100, 1127100, 8019, 198251550, 47, 30950400, 2115, 46, 45900, 233280000, 125736, 200508, 5742, 222456, 442520, 245188944, 162288000, 5589000, 76923, 2461536, 415457280, 715936320, 179289600, 14826240, 17150000, 13759200, 63240, 7001280, 663000, 22776600, 221925825, 920700, 1421000, 1715616, 30198960, 1766100, 2480, 290, 460, 1556640, 1015, 145411200, 11350500, 824199480, 539028000, 11865150, 882000, 19, 830297000, 159840, 7632800, 81053840, 68250, 77873400, 123965952, 164826900, 23040, 347200, 51323580, 30294000, 140777000, 1023, 147556500, 273420, 13665960, 2760, 21780, 77552640, 3245760, 341433225, 930, 12740, 441, 960498, 184988160, 219240000, 125854344, 13986, 174960, 53978400, 2163168, 456840000, 1513400, 179676000, 90810720, 28569600, 4923072, 807003000, 49758720, 47404500, 480, 42340320, 837000000, 3, 3720, 15847920, 1400, 1715000, 251160, 198, 504708750, 8932000, 6311250, 1458240, 96, 37235712, 911400, 255816, 142084800, 39346560, 2384640, 38491200, 1872000, 899640, 14586, 294624, 37, 239760, 469476000, 1015200, 531454000, 411840, 36352800, 15367680, 273454272, 63987840, 22416750, 24500, 171535, 683384832, 47586240, 82800, 112266, 12746370, 429545025, 875769840, 125468160, 74, 39412800, 45904320, 9313920, 1589760, 24, 570240, 72912000, 107880, 1758120, 7980, 2614640, 34, 12987000, 3124044, 728640, 21420, 4, 22855680, 21600, 99, 11, 203076720, 90132, 687447000, 5049000, 747225, 37620, 13363200, 209265525, 171169600, 461039040, 36946, 79197560, 35280, 108100, 22032000, 343062720, 29141775, 906782240, 297600, 298378080, 486129600, 979104000, 22151360, 74576700, 1647030, 58870, 828100, 1064, 58605120, 12182400, 860288, 41, 62107500, 9, 310495680, 71951360, 29, 105995232, 13838, 1617000, 832431600, 328671, 510, 3826550, 689018400, 10959300, 48, 3294060, 22720320, 132022800, 24663600, 40, 520, 6756750, 429624000, 79497600, 11427840, 489167910, 54734592, 10847520, 887284800, 223948800, 735139125, 3478200, 470235, 649152000, 86619456, 932536800, 2976, 675, 491756100, 73780, 1142784, 5488560, 1473780, 301693392, 8819902, 88357500, 40392, 579600, 457113600, 2, 692841600, 77189112, 49538412, 18086640, 104976000, 35162400, 4320, 435, 107892000, 14, 832, 257094000, 345, 323389440, 813440, 13392000, 29760, 8391600, 599400, 1713600, 154560, 7380480, 127429200, 198360, 8625, 459345600, 13117650, 1309440, 85680, 41972175, 75710880, 87339648, 55296000, 1054, 10098, 269414640, 787644000, 34560, 253, 721353600, 28072, 5984, 148764, 84630, 25966080, 141120, 46368, 101680, 31, 82197720, 3312400, 2980800, 18247680, 12453120, 9108, 3050400};
        int i = numFactoredBinaryTrees(arr);
        System.out.println(i);
//        874417692
//        269431649
//        938580568
//        1000000007
//        1_000_000_007
//        System.out.println((SILK - 1) * (SILK - 1));
    }

    public static int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length / 2; i++) {
            arr[i] = arr[i] ^ arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[i] ^ arr[arr.length - i - 1];
            arr[i] = arr[i] ^ arr[arr.length - i - 1];
        }
        map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        cache = new Long[arr.length];
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans += numFactoredBinaryTreesProcess(arr, i);
            ans = ans % SILK;
        }
        return (int) ans;
    }

    public static Long numFactoredBinaryTreesProcess(int[] arr, int index) {

        if (cache[index] != null) {
            return cache[index];
        }
        long ans = 1L;
        Set<Integer> set = new HashSet<>();
        for (int i = index + 1; i < arr.length; i++) {
            if (arr[index] % arr[i] == 0 && map.containsKey(arr[index] / arr[i]) && !set.contains(arr[i])) {
                ans += numFactoredBinaryTreesProcess(arr, i) *
                        numFactoredBinaryTreesProcess(arr, map.get(arr[index] / arr[i]));
                ans = ans % SILK;
                if (arr[i] == arr[index] / arr[i]) {
                    set.add(arr[index] / arr[i]);
                }
            }
            if (arr[index] / arr[i] >= arr[index]) {
                break;
            }
        }
        cache[index] = ans;
        return ans;
    }
}
