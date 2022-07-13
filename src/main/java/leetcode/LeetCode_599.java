package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
 * <p>
 * You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers,
 * output all of them with no order requirement. You could assume there always exists an answer.
 * @author: LISHUAI
 * @createDate: 2022/6/6 11:03
 * @version: 1.0
 */

public class LeetCode_599 {

    public static void main(String[] args) {
        String[] list1 = {"rlp", "pqgpslc", "appfwpvsgtoksqftblahcw", "apzlflrbdcxullotet", "krxgbrvxdqkuzvvnrrujtdug", "kpcyawlmziebttbbrcg", "qjflwnmzxuzllvccfqjc", "ywjmnnsiravbzawlig", "gvpvumrdyinousb", "xhnabhvouhytsmndftneyfkvoqx", "lhpztsu", "gghcvodsuua", "cwsppyc", "zeaasfqgnwzhdmwnca", "dyazaxyaovpqdbexpc", "thsrauboelzmtonrithrm", "yukuigmsshyglugwxulqjgtplstnw", "oaekysaeryijhzzzawdhvlre", "rbvqtjzisitxvgcxhjrhnzljsykxwm", "ornktqdrwdfwjdmdswtywiw", "onfhohjslluzj", "twaoit", "kmtfjuzjddz", "qaagygsrgdauvkzyz", "kgwyolaxuppmnibtisbbrfpxehgl", "nsrvwvrgkftvxzlsmieu", "pvruvcdsbn", "zgemuflzyxyjbovcuqjhvqlqrru", "zxcwsqn", "ydqpfdpxlmn", "s", "wdlozmuacafzrxlqhmn", "czkq", "ssdfzqbabyvsnvqteduwpadukca", "txcibdksoshpxfvggsiylvczv", "gwawbsbxoehkymishdswtkwzntjy", "eiuepbxzjci", "ooqhvtuerrzbawmmr", "ucpqasmjrqadniwufzomhqfm", "waalgaeibyjzzehixmvwenfbgejktw", "enrwvvhtdifsyzo", "qbdjelevtvdipcadhrtaye", "ubfpdxvxlwmrheevacuynzjo", "cqehkrmxvnf", "wifbmludvghzbihhptx", "ias", "nbphjcypjbeedha", "es", "brcueozyomooyutzsgmf", "jhhzdakxuss", "weo", "vnnzjmawkltil", "vjbwocedgqitrbwjmgkbrnhzczdboh", "rejrwyptitorfmjrrvzorvnbqoxnvq", "khzjckhsiiafirjxxcsrwjqteigpp", "qmsixiiryly", "ojnqezwhcxjrlexrbc", "adajfjljw", "luufqo", "bgbwyztsabvpwzuhuslexadlk", "gq", "apgsajczcydtjusrhaqk", "cvjczlsdpnrmmtfw", "bmacsaeygfg", "dlqitakrzp", "jgetetqnzmpxbw", "jrnaxezvoljeubxzayqgnpqgfh", "xndogmyxeegmtdgkw", "aaylpuvtxhvyw", "nbhnyxnhqbvckxcridzcufv", "wtnjdnmdppadgoyo", "sfl", "lpohpbmhnmahmkhrbeu", "bukmtafnnmknxe", "manwbvjwyb", "cvyguiivdnoqffbgmlizppqtoolu", "lyngnwxxbrffod", "ivwppr", "evbqscchnbihacakixytvtzdqqczar", "ubetssfgesdxnopiuuyyqjkiwrv", "px", "xgbtgmhadhrbumyzsjqfmvbl", "w", "ondidbedxhfmfbhumcprqwpffigu", "ddbkoorrhvrhiwaffq", "njmqchbuhoec", "gjzlbfsgunkobrcoaofr", "z", "vrkrelwzepnyrtqvslubvrz", "heysnnrxvfwuxrwmkwolnqingwvr", "wpeuagu", "sdgxiwlisbnzkfnigeni", "dvmooauawmozelrqljertfvuj", "brclqrmnkphjtycm", "wvnnicwdisxvevhw", "dumygckmcyzt", "cdepvdwvikhfcxrjergekuzvb", "iilqhoegzcqvihjvspwfbur", "tyzayxsvnvzbnin", "oxcywtwmuyiljvkzykocfzzro", "qaqcuuwobyvyn", "umcjmkjsjetxwdzzzwlzlyk", "dldhetvehpeqaiidhanipunbmy", "dyzlyilaocljgj", "bhbadfavtwt", "rkbuhqqpwa", "gbjkovssfjkvzhafkoqfkolstwj", "kanjama", "tcdgeymshgmlmvirttdahjxrcn", "zwnxlqznpimsox", "rkivn", "dx", "xmwpxmtzm", "hirefxbzztrpxgqxref", "rldyzofydogfjmfvbbsv", "kpcvcwcksxrkw", "okdlahsmaeijflmvyhjibnzuut", "xtmpuvx", "rvuq", "hrmpfjdodgceuuqstcjzhqphhanvk", "ibrlnrxbiswp", "rypzwwefg", "g", "tzuhkbmehchkpodhnyoyrxkms", "jycoizdi", "esketgcan", "tyyhxmjvpf", "mfxrzdrghsjxiigkakp", "h", "xltc", "afnrnhwpx", "zqqhwcitrogqfrbszi", "ba", "carwbgywfwlnmiiqjkunj", "ueubrttdsiopaethspjjbxfmnho", "itbiwlq", "hgawjo", "eiduibrkpbypnrz", "prueapirmjplmxijci", "ibbtxsuxh", "mjf", "iuqqyfrmytqujud", "oclaajxuwx", "eszvdlxhnzuaufaibpjgkbjuu", "zvtujrwwfdvqkjgijfbrifgz", "nvdfcdjjtaliihmny", "qahmmjsxmhxijct", "k", "wzqdcxechlnijggbjzaguqh", "tarpefvawnpcwrjivpxzxman", "ofhcbpcsqsypihrkquyqrlen", "sbquicbilyppdwxnlv", "dyycwxbeousfvlhpydnvprgt", "kgqeeumbuugiocdgnkhhbicezoawdx", "hja", "sfdfidjbumnhhtauvgnjisvm", "ldgnojsagonsuvmwwsfmwpegut", "bjdouy", "it", "yjwyhjzwvt", "vpeqfwjguxhszxbhgadcpydeilhmri", "czsmumukkmrgzdzceqicjnrpa", "xrrfdjeanyrhhynexlgnx", "siipb", "ssgbvyoeomtc", "y", "wjbgtoaddogtep", "bpjuunwtulahfnxlqqpwckxggw", "zbqshnamqteoifkujfi", "rgzdzpyhgburcv", "fbozbqffanxt", "uynqbaokaiswwxcmz", "zcb", "dlkvmrpdxvrtwopmjvmsdezlle", "jtqbevcqucjshepp", "ctvioezgmbyeenwkdl", "mdgbwqgeyoxm", "oaei", "omzgqfkznwgrqdabnddmowvcemg", "lfkbuipudxuhaqmsbiqszaj", "fpyjvstlxbcmynyuhxyed", "yuzwztv", "plrlhh", "sqojgiutqm", "mmgemyotqsxzgf", "haajh", "ivzadtgsfceooncwfxsz", "hotrpqprixgr", "cko", "lltfycyfbqcilaugjzcuzzswzcvdmj", "bwuajardtmrwzstsulpghdv", "ugzpkmorpodygbhlkmszhiipnre", "ttcpptn", "lp", "pepgfgkkaqzjwxgj", "lceadpbiryixjkfymzy", "hlppmbbibjsjs", "ejsqrp", "cfxjwqremswqovkpyjyugeusmjtx", "ajvjgy", "kpwwciywswcypejw", "ypfvbgaxnxsrxecpfx", "sveixcrblornybldkopguoyroakqz", "lsomxaljaneosplbhljnwkcoqbcgj", "e", "njmoypgtgrlhgjndwamxiivkc", "ubcmoplveqskcsjcphvofdsmn", "snlwvddqwg", "iozgghfcc", "sv", "rpg", "lythiwalepyrrrqfwavlymvhnozz", "bgpemjjw", "wkftybvdjgexvesrjasf", "qckquzyxxudistzodun", "voufzvmupjkysmpws", "mljnjmslyigkxuxtojoxgvznspft", "fv", "thuvxkatrjihzmbbploee", "fukiv", "iuxupaotcvyveuhfn", "qahvhdagbzdngfovsyexbitnlgf", "fi", "yryomshrcsazyhfjwrsyxiuphpf", "se", "olmmuxddmlildgeqgdcs", "zkxsjpbpwptdk"};
        String[] list2 = {"ggqjhrgbvvddajpodobvfvr", "ieuxhctpkinykg", "yqggmlsdsixdln", "gqfenbkndzacqpzauftysyoy", "cpcqcspet", "lkxvozpilekiac", "uvuxobnvjrwexwclrna", "xbsipetqxebzhu", "nzlummxw", "mxrobe", "bmrs", "njvclnptuaujhgakvcbbt", "utlinrpsnonlga", "yjlopgtyjbtagvh", "wlqhjkjtwydupexxvvnho", "dinqfv", "srhxmzeu", "pdjtehcfimuxglmtpekvyjuhfrpatt", "nvzoerfofjkisillkkjixfbrv", "pnkxwnpnsua", "lxkvbmwtpzmbbnegmzqvdpurrfws", "scihntusfiajqkhkvbbuojubrpcp", "fp", "zowdyumjrbdod", "vydpv", "oapbnbhzikhfkqsqaarpkj", "ofvechgzoussvikwedkr", "xtlth", "ifggjtredxqhmefzny", "pfezbh", "cadtljlytwuuwykldfktpyvk", "ng", "ijoeiwamqlivgiupxa", "oypigx", "quibzjgofc", "beabxgfgzideee", "ilxvbddppcuzll", "myqnxjlxcmxoocyppmjeswfftgt", "gvxzjznoss", "ffzhmqnyjemgkgab", "lcnqmjnjkfyffoctuakoeqgq", "qjbeptfiyczjjdlumez", "digjfxmozn", "vudmiwqwmmbvuuldoag", "csitbxoeuiyygdkoraxukceee", "jli", "krpmgnkaecmoptyhejsv", "ryeltlcdze", "evpzxejsmpqapnnmpcwnvmm", "sgcfskioxzyudniqyzhvpiwaktm", "uienumuvxmykgkurlgbsyjpoow", "qaml", "vkgjoujdvrepumtosnowfd", "mgvcrtnunvv", "pxheljxwfb", "yonfgf", "psjhygedemqsftlz", "tdjoeipokbdks", "eyorfygai", "wcdxkcudazxtfyrpkqshypsntl", "giupagkroqedpqbfacclki", "enuujbwpltmoqrewmoeiccrfujn", "hxkctl", "idskdvrxkquclqonzwxnzajyba", "ozkdbotp", "rjvzfnqgdoumuqjeqwgychq", "aexktsiqkgtoionlta", "yovirzikwelxoaqn", "ljeptrxlhgpfztcwbenhianddyddm", "qxmcvgljsmuzjujfdarrk", "tdho", "vwwkcddgz", "ukfokcjkxulfkmspewepxeylvskxfy", "tcnbxvyrrtyytmjbvdn", "sijdhjmzhguoiba", "vhnchxzlkxdkrsncxmqre", "pzehaxxyshtiuj", "uvijawpzxtbawlbctpxjtsp", "rus", "nxidhtlvagrxquezdcxgxcfmkfegwv", "vhkaf", "mpsuwcclurxxcmyncisifdvlxz", "rsp", "gsajbbbrvbs", "nhgvopckcmppcfjfzmpdyhrhr", "jsyrxprccmibyfmadoezamgfosdx", "okawejzhqpvghyku", "rbajahllmekoaotsbl", "taemlqbkuapcydeytbzftyasqrwy", "hjwrjwag", "dvvyipeyfsokpcgwhuziojuc", "kvvjf", "djjjxnkuhqqozifklqaowxatjhf", "aieeztyrzldpvuzchlkr", "wscxksuls", "wubwhongwycrpqyyz", "f", "hwhiwjmaywdknilhpiotjwunzq", "bfavwrmuatusl", "imwegvxfxhldccjvm", "vi", "dbdduqpqw", "ucpdcdgqx", "pshshxp", "eqhroeadl", "chsqrpqebougiauncaounujgobk", "rgfxispmxpkeuqupc", "wyhqxllxqpjxlgistydwnmr", "xmwwrrrzzde", "ackpagsiqezherosxzkatllxdvyarh", "swd", "pu", "cpjcejyiwxnnttns", "olkecvdhfgrugfkmuhexlisvjg", "qkamc", "ynvidulfptduatmtx", "cabijkozssycxciirxcplufknfqeo", "cbcuwnwuwiadlibol", "ennwhmrcxg", "v", "bpgfvuohzzk", "lfyhgtyopj", "xtb", "xtcsavaoqxtzpjggsnzteozkyaxsi", "dmfxzmoocgvnohvoizbizcyxrf", "okxvifwd", "crovtacyjlgnnhqcir", "jpt", "olstsjdaw", "jdoettwaiht", "rssiqrjlhycazsfwrnjjgonrdfkwap", "qthqibueztoefzxtm", "kp", "wqyfbikuplttprzczfcqua", "nrddnfmybmkbwcsljugzo", "anziifwksnksyxetjyhyhlimj", "zdmmzdpvbcdaqnji", "drkds", "cepxvyotfwjugm", "bcbhcvttbrzmxcxkuemfbewvqe", "nqbsqepfsrwxgg", "ruxjdivunhzsngyvjrsxvk", "ufosyjiyxynmwadttrptbchfjehrp", "gmhbyvvqltj", "pasudfvrhuxrruxnclpc", "nwdmbmlubueulezaakwx", "jtx", "ed", "hszoogblhknjeo", "ypthbhxacdqlw", "gqeominieezrfugattk", "ihrdsegogmjcvnehoorwufxwj", "idkinhuv", "rawashuqzkrihnnzqnkx", "hvwcjfzknphsrcndr", "kkdxvrbvogxcwczexa", "xnflyaqkmtvgwops", "tkermddswloonnkkqlxfazcfnq", "uopljqsjlnaqvuzay", "qnyehjdvxfshxhalr", "gfmpnj", "bowokxwvntbcw", "pm", "igzpqgmarvnhim", "swhnjdobtfkmdsylarniczvlx", "uyt", "witfqagiqyzfurg", "dsexluubuqjuvmusnzijzpk", "iumkifkgwywdcqccnqzj", "nuupabrmirjgpsmm", "qzehbpnc", "gofvcgvsbfazczjvuqpqnif", "wwwosqqagwjdbnlm", "pnzds", "nuztxpahxrcyt", "ovtxwaqrwbc", "mgygqstgnt", "dxr", "omcxijephhatpalmlfd", "o", "ah", "cyfdorakihmcsmekrckkvtcoznh", "usrwjzrwrhhacaq", "uahvkljcbclsmpwmkor", "ibtpmssrrqheqrgjqh", "ejmjgbyrsskchriblbkbfrcnnpuj", "mkystf", "silydqvigfaquypbndetoxsgqo", "rrb", "wyd", "lfohvrpvuufobmippvmwo", "xxmhz", "oibxfpszrfwcnjjzkpdciyj", "dzcyzt", "cdrslo", "lzsmgocxipfdrrcv", "ytfqibwljytkmzczvzaah", "dxlrq", "otwrxbiyxkffhwkqdfi", "zcjrvfzpmdkvwndualljgcawpcm", "lrgzbjmypjdszwdqczawoofpphbhpq", "yefhpgacjaafirmzgpiujhfd", "cvickevukciqigyffgjnrnvfovff", "wodwxwptgjongtxnznlgvupmktoiyr", "aqpvqvmwbybhwulzuj", "sekbtlcwoktlqbpgnlavqozzdj", "oz", "xlaw", "obdubsicaitnywghqrheppqvthm", "pbuyznzkrpivbbubucusrk", "mumkhbbljtgmauwwbdyifyvbwxlb", "hv", "oozlkogzivszdmfcytf", "qdnhwhsehyoxtoufxpwmclwfxhbzm", "hrt", "ynmniy", "hpkudiuw", "fykgctspadqlgpada", "kcptpryhhtg", "rzdwpbgt", "ixavjfuwcprsnmlaqir", "lxphyfaxxocleqmpwyhimufdjemx", "lcs", "vrvrwuakbfzbwaujn", "ntuf", "bbpfcodmnzaneodtkncnip", "xcckeijunlm", "uouaaedvab", "mspdrxohmt", "pymhwcnryfg", "uljljlgzhiqqpyhtsc", "dexemhqtnid", "tzcrwskwaqpkfkewiutblwxcir", "qdidmpqpqfrgudn", "mfpnbsliee", "glicvtli", "dhickwxg", "hdqdahsfrasm", "wmowmj", "mpnwqemhjxeydvoudxjwtksghr", "cswudnsffojihqfmnywvfc", "chhplwipainksdgnzixanc", "dqcmrbyakhwyfjzdet", "detsoppskceydbewvtaxneenlrlmbc", "zemcpqantvzwkfojmdoirozbk", "bikqvcfd", "lmzrbacwuppwd", "voxoxwfoqkmocuwvcavemjrbnys", "glzptxgfxydgdcddmgyplbpjtkvys", "mpojfdn", "cruutpuwshl", "ldlvepnon", "joqtcojmsbvkatshwrxehlmyzxff", "pjauwejwxpghadbyl", "vxtbvsdjgdgazrqpbewciqhpvfbmkv", "xme", "fon", "uyubcbbdgcwsht", "gomlydefucheypokpv", "syhswuuoeuyjasemlrziorxqh", "srylvjlmuiliex", "slmxzyktrmjstqnubrymlctqnflouh", "efjdtyzbbarabn", "esaznwtaxsuauywijaggbjrgxdsyjb", "kdsnlbhcvjwiocelhtvkymiedodtf", "ae", "enjnpqifbbtqtc", "wtwdirjeivjldjxuexpcaobsdxbc", "ybjswkgloqzuouzv", "mdse", "cnmoozzwj", "db", "rydeujjnghimygyqboxljgkuqbuxr", "pkzvufewwkrygj", "ifpynpfnckarerl", "exjsuasvegtjmxaagw", "isaejmanekyundvd", "fcpvgvm", "rfhuxmdfudmxruxsvtps", "pomlbhxbazgfombcdjm", "vuastxislol", "te", "hjdkdaruxexfwunfivrtewco", "pelmjgacmkrt", "sf", "taivxvrxvbyxfnrwfrkpjw", "sqbmztroii", "bdmts", "ou", "bscipyckjlpvunlfvh", "lvwsxofksrhiqlmizbubqwlyi", "olkwrfouhgqwutxrhiowbrxh", "wxajryvztqcrbdvpmbxismzerf", "prrjj", "pbudgtcudiiicsjil", "ukioiustnalsdvhgtbxiqnlkin", "nditxrbqdyhejpettacuynak", "xvss", "diwe", "kymktgrurkaxkjdjokhen", "btovzfsmuuppwt", "ysvgqhpjzzosptbexwuutkyu", "umyskynwqsvucuvoqajibleevjlojr", "iuyfsrifhkyxbhrykvzugnxd", "vihawarywpw", "czijgefubbmzfgrbezjbqyecb", "xvymrqtanewgg", "vtqtmexqczunhnhzukdlhumcjbbw", "fbqymdldwuxmlesqxrvp", "blxerjvqoksvaaz", "ndzldzg", "pdxqa", "zhftqkhrqqggdvhuzeislmsltdw", "qevnhlxeapxizulplu", "vccrysxosll", "zurodqvy", "jcmvtgd", "djwhlsxffex", "lxjoshnrpvjihsk", "hqltizczotkdiwobpzjsmrohqgbtao", "rkerg", "axbvhsytevtwmpku", "rfcu", "tljqunltughpraiweanlqph", "vrmvzcuzeqvucdlgwfjof", "cpzrkg", "pcfqjxfpsszgjwijwstbbgug", "quh", "mllkp", "vbjbdyerqbfeqyjqnjsbwpwd", "scmdecocxcyldowoedbq", "ydcgpgdgnl", "ljownbgcpsjtmo", "lcgwlma", "hotoctmffdjsdlvzgg", "yiszzmczvchtnydwnxl", "efbrouohottxkliqwwqzdejqz", "ycrhglfgssbjgdzadcnmgegkkttizt", "jwznnfmwempxzjjjzwtxtj", "zjciyfkhenuslmwx", "sbvqzxm", "vzufklxe", "hwpoaiukzqiinmw", "kmbbeoqlynzqgs", "en", "xsmphahlluuufqylocztcyg", "yqfsosljtyccmbznrepuiaigf", "uxentrrhfcvkypyexdmaxmr", "jmecyicwcomyxejtovznfwmrmtfpr", "zolyxqbcovsplzggfxcnmmyfa", "aesasrexacvtrx", "ywpnjzcngrzhrurmvurwzfs", "dvnirqobkr", "inbcz", "nihidhiqwmcbpv", "hocxcftjogxricwgxnvckuuqmcecv", "iygfv", "gkwdcelidbsxqcvweijiy", "zxicplguiiwiwpskfilgtw", "aqbqsktojoagnkhkhjabfuhoq", "izcyuaiarptdsh", "psaxw", "uslhnmsqcsmwkmmb", "hwtmgbbkgddlvhzbkmbfkjbjdvajin", "hxydmatmeqpajnqziikbmppzmoi", "xilszseuxwoqhpbphlmnqonlltw", "eztakourusawaeddwxpn", "vusjvnhgfqxwwzqmxqau", "hpbj", "pt", "jfwjyazhccgykziwwalwiok", "upozonpzpguhazinhjlbsbv", "gimzlm", "vmydxbzgcsskl", "gvscmeebtrwuruvub", "izeuvqnvflaeafcitvqq", "xypd", "prxbedbngscuwlndynktbdk", "rjnfzw", "zqkrjwrsgopidoudugsnkuajbf", "bklpz", "llilrjfaaee", "bzumxjeaeeachoc", "kmigobbzwlpbyxj", "tcqzqqnmszjil", "bslgqealhqucbyedgb", "xfejtlfbave", "ffyoqnqnyblvlcwcstt", "cpnsdntjixnqboyfwvulfch", "pbkzarijsvuwvw", "edjd", "lqasion", "grtpifmhthovshriwaxkkjr", "ggoxtqmzfsss", "nhhlaxvybscldou", "duzpfx", "oufxilccqyzwxatbudfdvvrikszl", "fvt", "vtcsekoapabjtunvwvterktqtl", "dalzyjkrcayfzntrlkzcu", "uprywoehaam", "kecxzrtxvurffc", "eavnqtcxsrvlekipu", "uczqucrwrbjfodqyuvftby", "hnsostztgyugxfyqpmwfltfp", "ajholxkbatdza", "ehoubtiuuwllfljgpcrcdcycyrbtld", "juguunmarciz", "tnyiyhghcbega", "psjrslwn", "ccpgasdbmfiai", "zefzvracjdihsenvsajcbffc", "a", "xquswc", "ugoaijqkxgqbjjcclrbgqbqsf", "xqzcimxqmce", "miabgjwpehnmdyeadlfflr", "hufmumwtzhkrjcdpltpbwkld", "jwhtutdmqhgnmveoouy", "xorr", "qfwxtexexolmqszsg", "solfokfowbqobt", "tmdajr", "yrprjtvmecaijekmsiyujp", "kvetztsvxjmnwsi", "tqiyrvagjyozbmugaylrb", "j", "yoduqhvakprclbjchuhy", "ehxkrtrxvodfiiadkyenlepifix", "lzqpjbwtsrlkesgouowcyd", "pbebbeeqgbqufliwkauq", "svxiwzcdtkkt", "iwnppsnwqlcufhkeiyurtdsukmig", "mbwblpzllilpraujwvxq", "hvgwvoddalnuxzuito", "fazlosndjpqfdtdpbjlwatl", "pnjhznb", "cjqufqts", "usvmtrdozcskvwgjml", "b", "jfrkusrmr", "vjbgjiezfzplpfpfpldknp", "qhpkjormpalkdjnsdnaxazujk", "ke", "qbeeaukyhxgkvqnycnlaynf", "snderwm", "wduoxvxtmxjugewsxmfxjfsomzaoib", "jndoopvtbpyhkwyohqsf", "elnllxh", "tphhserqnl", "vuvphclnieieesxrpwdaphown", "eiznvuiuikmzoeewszxclaxcglx", "vwjjoiappdn", "gqoayzkbpnmxecpnk", "evijhcquegptvsrappqxfmzwrqx", "ktactmsgsiswicyx", "lo", "kugvestzbfrndvcbetfgmqcnip", "mkzjsgscxksueih", "ffpnrsrdyofgicivflxhkwn", "zqksbycvguetzulyiablg", "yfnbmdnojwi", "gsnxrezemjgcxhxvdnejaza", "soqvdmezxxptlbwqmiopmspdmf", "kjheggfukwtgappywwqggizs", "xlgrowefqfwesktm", "rhexgeuvbcmjzfqq", "miweq", "isqjnkzjnhh", "upywcwhxvdpr", "giximrggtfmlxdxmpao", "rnlehmudwiqezewwbvw", "sgktzkd", "ywoaaginmemsbmkkhptrqqvwkdgon", "ajmcqpgc", "awwql", "hbmarqcuhlotb", "thdqquwgulgpok", "fxscxogxlasud", "mfluqqtxzsfpvoohdkhomwdmozgazb", "odeptpcwvrtikdvvstkevrjwkt", "hxubdfhalowpprp", "vbjjcntzqydijhnhefzmwmhzgzunnu", "hwxtlwcwnaav", "ifnjnhaqstwyqwjxhdguqoerxjuyi", "njagalsyx", "xpxmxyviqgwficaqzxfxhfzbj", "sfkkforqvieygvjpjqvrxhuplmj", "kohv", "t", "sctpygxijllgnhwmdbpdbfinwsuks", "nilxkbsrrppmhcfof", "byqmexxielzjcaotpqnzx", "ucnctwinwbiqjrnjbd", "ysfkten", "gldjanviphslgfbdat", "ijedbxp", "uif", "bed", "iljllqenir", "nyzaxsvbuely", "faydgubttkip", "kdzbdhhvabiudkc", "ftqbxpniyydwuu", "oib", "tcksbegzifxybcgwqxyobfelcpkkd", "uanlcfaoihlaqnwvytqvue", "ypbwijmllx", "fauoserqnvl", "rwzvgiu", "hyyjcpnrycg", "xidjbotihtrhp", "iwcn", "hacjbhilbal", "yahoiy", "fstbbwmy", "htxofroytipom", "xfxabgoax", "invbmwcqgpr", "pobmljstwqanvhaxxx", "pqcjzlrhpegchxw", "qrfrkphjtw", "frncwpdejwjtk", "rzvfjqepqiqpbxdzlajjixffmg", "mvhpxrxzzbwovqslrdzgndharl", "mawyqtf", "pmv", "zovfqoavvzmpnmrpmlabniaidpr", "wxnnxaxhhqje", "igyhkynatktjikllkpbzuzncaiot", "ztzcgctxuxwklycmsxgkktziovk", "xpabfdrnygpdrjlalaiqfn", "nxuwya", "wjpbfhqloadylrjivfph", "bqbsqoeilohioyolxtnrslozyqoyfa", "swgxqjugrzerangtxktva", "dosaudckkdsviusvpljvuhglcvxjd", "hwbu", "dpsruwavafwwvxap", "uvcfnvdnznltqzqjnvm", "bbdtuhysjqnvrssshwfnfghf", "lvoxfqqeqnvyhyqbhqvrbpsgozkv", "fvoldnekttiripjlk", "ygtx", "dnbrhhcwahitlbikhhbrbpkmbc", "rpoydgtnpaywhkc", "mljnjmslyigkxuxtojoxgvznspft", "drefzghsawaalmslfyayqrdrsi", "qckquzyxxudistzodun", "hnzgki", "zwnnlc", "jbqrceqnk", "atepcvg", "m", "odusiitgkafwdl", "irpzsigzjtjqjzsumlgfdonngs", "ubcmoplveqskcsjcphvofdsmn", "yewmhkzjjc", "rayfzyovlyukxhufkubeywmcetzvan", "rskleghfxggzocalavprx", "ujlyzs", "gkgbvgmcxacuylmvcbtw", "daocvkp", "zgzml", "cfxjwqremswqovkpyjyugeusmjtx", "efjmnddgwvephlvsosncbrfby", "hlppmbbibjsjs", "ovda", "nu", "bogczmixpktescbbeckmwtmbmwnhh", "wdqenrgdzabzmogvvfqrhcoamymnxl", "iigzwpuhybvbygmhgsfxkitwrzfgo", "fdi", "lltfycyfbqcilaugjzcuzzswzcvdmj", "ufpvbzrvscrgoddsqbvwteuzy", "cdrkdwopffylx", "ysulzppouyzgqukmvnthfx", "u", "mmgemyotqsxzgf", "wyyuij", "xboilfvtzmv", "mbqbjlakucxleicgpivzilwqy", "iwuvfpnnytowyitirkrh", "npuhtujmwurebzq", "ibwylecvozglatfsca", "rlpagckxhzfeweuxrqbyrlggo", "yxetmqyuxxqvbsit", "inbfu", "vplgrbstlhojlcejje", "dlkvmrpdxvrtwopmjvmsdezlle", "zwozdkoyhcvhkvbrhdubfqsgrfzhto", "oxchumuiegamacocssufmy", "vxbnttkxstuysmqsdbbiaie", "tsnsftlvuuthtcjyycvwlufivrabb", "zbqshnamqteoifkujfi", "nnmkctfllnthukx", "sykw", "fejilenn", "jalmjarihhoz", "dvhwjsenjhgpzgruocasvlzwqsrod", "qbv", "hwusehs", "dsjlfyyivdlzbufg", "xpcpxegkdifrdeknwqurpfsl", "iu", "gxemroxxhgezojqx", "yxfdrcyfdkqsnpixhzzztsv", "fetus", "fzefj", "vodnruuxotghkkrsou", "tehuynaprcuxhlpgp", "jgyelvto", "ofhcbpcsqsypihrkquyqrlen", "tarpefvawnpcwrjivpxzxman", "otjwfh", "vkzzcdhobgypbyfkweuwaluhniaco", "qahmmjsxmhxijct", "fcjwltwojxnzdnm", "zgapvvhrzejkgx", "eszvdlxhnzuaufaibpjgkbjuu", "dfpatjmlhvc", "jfwwi", "uqpbtzlnfyuyhrtfm", "kmeqpplwkjut", "leikcrgvlbzoro", "xivftczllxdnbzwx", "ty", "ccvvlxzu", "aysghvnxuuroobcwzuiejvhxfns", "rkkbeh", "fxqwmnglfpdktnjtvsdrwktmffsjk", "as", "vyjoehtywohwknmdpvbujmozbqt", "ofbejmbvg", "bhhqioysdtcfosnxvystvsiq", "jckxqfxapxkoojownnikxfjzyxoe", "formwygcaiz", "ahxmtobxvnexgg", "pz", "defmwxvarj", "npwdywerujkydz", "phrozmhbtkjssdconcy", "quppvbnrbejhmruupdcwel", "hrmpfjdodgceuuqstcjzhqphhanvk", "mvahueumzzvvgnx", "jxmjtuhqyrzunduhrlbolcozl", "cmfwpkjdewflgnapogk", "xnspiehpocbtpnoqhnyqnd", "scmyyejzefjoubkkxjkp", "qfjt", "lwzolcbagytjrooost", "cjvgvfvjhqurzlalfjhxluoguh", "tuogoeqcjrugjv", "cegoppwjkcyaiowcmvfgznkud", "ovapflawqtzeidf", "qadnbfateoshkihertxj", "akhxddfigxzycaevnp", "fsemghggodqjhpycpqzcswvdgvfj", "ufcozbowjntxcpyke", "scs", "wxwwyglkvepcxplnsxdt", "abfawgagojyqu", "txjq", "uvoqshxduslfeasnp", "ljl", "ombujoxyoqsm", "cdepvdwvikhfcxrjergekuzvb", "frvjiuekjkgosxmznbmfza", "brtvascdyzbkoylr", "nvujljvsbegadindyszyvnfw", "bzzdvvzvzvqdnhflxuhwvsljzub", "wxfzwotbxfxmvvch", "sngkqpqglxnmdxnbkhnt", "cocbyezkocatppbtl", "vrkrelwzepnyrtqvslubvrz", "otonuntpxkniclakoqipjdwezue", "bfwak", "mvqht", "oabqclppjloykuftz", "ucprrgphxbfekpltcoamsebujdnt", "vknkssdkmkkzpytwfynuhugb", "lxzhnsamqns", "gbaocvwlkczrlknsznehjhnhth", "tesjrimdzrepijyorru", "fd", "uoqjdbbemvgsfhbaftbkh", "tgjysuywpxciisiior", "dluoavkpggdigbdlnf", "rhblrfvhfgkexzzcsiorzeaeyrfixq", "vxoulxpcqephaqybwxz", "rnwqfoceszcoeutpoekvpdchdlqo", "vueuiyztideclwnqcsgubmufdsr", "wzskccydfvaptqbiockrueje", "tmvjqbtspfgz", "vcdewyhkaxmsa", "euhfcnfewu", "fdisqallzdfpte", "jgetetqnzmpxbw", "pljgwhitnljrwwftrn", "uxmvdtvarhmhssjxxrpodvczg", "hzgjba", "npgq", "eucemyih", "vwvbiscuyxjwsfkbzbllutvbqiztu", "iodbhdrqdkzfrigvrsiklxdaeelbv", "dicwnadmpefwu", "ykjqhy", "bgnhnnhxdlsrvjlchlswjias", "ejdjhbaxcg", "qgefotmkapptcx", "vjbwocedgqitrbwjmgkbrnhzczdboh", "zep", "xnn", "jhhzdakxuss", "qwkphqdkpdanp", "srxuqtmzjpmnvtrfwof", "bprmoadcaeagmmfgd", "rsbblsnclhyhrdjxfsnnxrsoah", "ecs", "cqehkrmxvnf", "vavxnlne", "ostcodkkkklgbuwbkizknmdbxbtzv", "gbskcqnxpiahimnzsosxncq", "gqihsqhrmbkalggrrxqqytfjxbj", "ipsblobhgcd", "aisypakhecbrsrnarcpbgjicajnflw", "dibdobftahufcfzjpngoyezjjj", "gwawbsbxoehkymishdswtkwzntjy", "iietanz", "bryrswdilliahkcaqbdezbotq", "pczkkzscdlhvjcwtfcnkezfoypd", "rgxfkaqzijfduuaskoynmvodsydj", "inyeygrwk", "lzbdri", "zdhjnpwzxudwgwnzouue", "jjvpryqyxqcwfaiqf", "ewblpt", "ggboaadb", "osnwsjdpjkqcegqtngpcn", "qaagygsrgdauvkzyz", "fuajxlihpavzgwkyvhinftdmjdt", "xdcuj", "kojbbhefmvjrhkimdfkel", "skcqrhbexmovoy", "hin", "nvorhtyienobxxybkjnwuwusp", "yukuigmsshyglugwxulqjgtplstnw", "thsrauboelzmtonrithrm", "dyazaxyaovpqdbexpc", "zeaasfqgnwzhdmwnca", "drrcic", "qpfjyvgni", "opcgjjifvwhlehzwrrojfoodw", "xhnabhvouhytsmndftneyfkvoqx", "yfmkbsirjsfrjsxbcnisdnfnnvymco", "zzziyswere", "lc", "oxvsupeefs", "krxgbrvxdqkuzvvnrrujtdug", "vjlpmelnuqoycejcxatqibqqqsvt", "mzbtuuzrmzqbhvsseyrm", "ixbugeiqrxqcd", "ehlpihhvenzmychpa", "akmvaiansgkxndpvmzviooiwycmn", "helwsqgibicy", "tqfaysiuuulzlldpnyjcc", "dqaifoeqnoicqmfozdtubsdajo", "ydsbz", "mmaglbiixexgdtjmsa", "tf", "tnckwsgmeosvhniumvpcssexfhf", "wdndcrfljqdmmhnips", "mlvexkyejbswwpvrrliqmhxlnfem", "hkyqpihhsekuvsnzuefcb", "bfezaeiiburhcnizjgifsem", "hxb", "iuwozkevxmrnyennz", "dosdlvyfxaoegc", "qhqsimysmooagzyqoi", "wo", "onboooq", "trnimvuxkrynqiwytigdgvi", "qarxbrrqpbtwtxxfmuarmrmnwv", "htapqoorpkcjbbihppuxd", "fxuhgicrxsjduhqeniuaxgy", "xnecxrelcz", "rjpbvjfcjigrxxhjekcvz", "yaovckkfshatdvfjmjbawjuumfbiel", "lzizggc", "wxrtijcmiejv", "vkpu", "nudsyqgbnnrqsmoukfvfjei", "bstgefstpwnuazzbesxxdohtappyqm", "npbashbxawckllfwzgpq", "nsobcebe", "wlkzmsyrsnbopgobsftwpfkja", "cd", "xvnwbiuva", "ttepnod", "xdr", "uebofplexjqwulruiubyf", "bjavqkrotiqabtudaldrhgzcv", "rjkzr", "qzsxhphawpwmujjstyayv", "rrslnbetywwhubv", "nchvbuousnlxecvsbfreehu", "nmfxwnmgmrjgsyyqcydmbfmy", "jgdvvjktoqslxcznsnufdksogpyyj", "pvjqwnbzezfhruduxjcbq", "gwpxovfmqmioxnwpfhtofvkf", "vqhlarxbhfquhfkfacdkjgzptmuvxu", "owivmfqwdjujtvcms", "yxxnoznvthjhzjjflbblv", "vujuqvlhnpsihmmxphoscmbhbvsmot", "crnyopjieoizfwlgqb", "prhtqurojxzs", "lhnwa", "dch", "jnpgdwefjgcemriqcgiluqdnglkt", "yfmks", "bizdbnhhbfntnip", "yimiivemqbugpqebezqzxt", "ykqtrxbnsdu", "cdcyekcwhtii", "xtamdbvdpabyrgombhy", "hqdckkkivowtbxzihoh", "weswtchigivouwsrvp", "gqkefgqlyxkza", "kakdqinuqlt", "tezdaooxogsmdn", "rqnaetffmpbfsupl", "ggfjelqwygfgjipjxms", "nfacqnnbruiuagaxcrmu", "nfwg", "ire", "wzmtpldnvmacddpzthxdvl", "tmzihonsbbtitrklywcdabakdpyv", "ykzweyqawwilhhttfrwsnmyskpvuht", "kyujcudleimjvovws", "nffbmjvhukidziiscp", "rv", "hmdlgjcsv", "ekbjfunlzjjtjuz", "osooempiphave", "xlebvf"};
        String[] restaurant = findRestaurant(list1, list2);
        String[] restaurant_tree = findRestaurant_map(list1, list2);
        System.out.println(restaurant.length);
        System.out.println(restaurant_tree.length);
        System.out.println(list1.length);
        System.out.println(list2.length);
    }

    public static String[] findRestaurant_map(String[] list1, String[] list2) {

        int min = Integer.MAX_VALUE;
        String[] minArr = list1.length > list2.length ? list1 : list2;
        Map<String, Integer> map = new HashMap<>(minArr.length);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < minArr.length; i++) {
            map.put(minArr[i], i);
        }
        minArr = list1.length <= list2.length ? list1 : list2;
        for (int i = 0; i < minArr.length && i <= min; i++) {
            int j = map.getOrDefault(minArr[i], -1);
            if (j != -1) {
                if (i + j < min) {
                    min = i + j;
                    list.clear();
                    list.add(minArr[i]);
                } else if (i + j == min) {
                    list.add(minArr[i]);
                }
            }
        }
        return list.toArray(new String[1]);
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        List<String> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i].equals(list2[j])) {
                    if (i + j < min) {
                        min = i + j;
                        list.clear();
                        list.add(list1[i]);
                    } else if (i + j == min) {
                        list.add(list1[i]);
                    }
                }
            }
        }
        return list.toArray(new String[1]);
    }

    public static String[] findRestaurant_prefixTree(String[] list1, String[] list2) {
        PrefixTree root = makeTree(list1);
        List<String> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length && i <= min; i++) {
            int weigh = getWeigh(root, list2[i]);
            if (weigh != -1) {
                if (weigh + i < min) {
                    list.clear();
                    list.add(list2[i]);
                    min = weigh + i;
                } else if (weigh + i == min) {
                    list.add(list2[i]);
                }
            }
        }
        return list.toArray(new String[0]);
    }

    private static int getWeigh(PrefixTree root, String s) {
        for (int i = 0; i < s.length() && root != null; i++) {
            char c = s.charAt(i);
            int v = 52;
            if (c >= 97) {
                v = c - 'a' + 26;
            } else if (c >= 65) {
                v = c - 'A';
            }
            root = root.values[v];
        }
        return root != null && root.isEnd ? root.weigh : -1;
    }

    private static PrefixTree makeTree(String[] list) {
        PrefixTree root = new PrefixTree();
        PrefixTree node;

        for (int k = 0; k < list.length; k++) {
            node = root;
            String s = list[k];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int v = 52;
                if (c >= 97) {
                    v = c - 'a' + 26;
                } else if (c >= 65) {
                    v = c - 'A';
                }
                if (node.values[v] == null) {
                    node.values[v] = new PrefixTree();
                }
                node = node.values[v];
            }
            node.isEnd = true;
            node.weigh = k;
        }
        return root;
    }

    static class PrefixTree {
        PrefixTree[] values;
        boolean isEnd;
        int weigh;

        public PrefixTree() {
            this.values = new PrefixTree[53];
            this.isEnd = false;
            this.weigh = -1;
        }
    }
}
