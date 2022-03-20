package leetcode;

import java.util.LinkedList;

/**
 * @description: Given the root of a binary search tree, return a balanced binary search tree with the same node values.
 * If there is more than one answer, return any of them.
 * <p>
 * A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
 * @author: LISHUAI
 * @createDate: 2022/1/8 21:08
 * @version: 1.0
 */

public class LeetCode_1382 {

    private static final int[] oldArr = {88265, 83067, 88942, 1931, 86070, 88632, 97916, 10, 40355, 83240, 87828, 88314, 88935, 93107, 98624, Integer.MIN_VALUE, 445, 19602, 50100, 83231, 85900, 87243, 88165, Integer.MIN_VALUE, 88574, 88726, Integer.MIN_VALUE, 89038, 95508, 98191, 99899, 310, 1273, 3621, 27988, 45914, 65855, 83127, Integer.MIN_VALUE, 84043, 85995, 86540, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 88962, 91698, 95310, 95878, 98106, 98287, 98910, Integer.MIN_VALUE, 200, Integer.MIN_VALUE, 1030, 1731, 2125, 4675, 27601, 31475, 43587, 49227, 55475, 70179, Integer.MIN_VALUE, Integer.MIN_VALUE, 83740, 84534, Integer.MIN_VALUE, Integer.MIN_VALUE, 86196, 86796, Integer.MIN_VALUE, Integer.MIN_VALUE, 89588, 91950, 94022, Integer.MIN_VALUE, 95751, 95893, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 98928, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 1167, 1631, 1874, 2004, 2836, 4649, 17674, 19836, Integer.MIN_VALUE, 28897, 33618, 40903, 45489, 46813, 49677, 54413, 57975, 68558, 76860, 83546, 83905, 84430, 85306, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 89120, 91446, 91780, 92014, 93798, 95100, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 96306, Integer.MIN_VALUE, 99471, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 2435, 3500, 4322, Integer.MIN_VALUE, 5519, 19447, 19795, 24476, 28854, 30868, 31856, 38458, 40672, 42266, 44336, Integer.MIN_VALUE, 46167, 48818, 49228, Integer.MIN_VALUE, 50931, 54818, 57075, 62815, 67703, 69443, 73510, 80225, Integer.MIN_VALUE, 83672, Integer.MIN_VALUE, 84017, Integer.MIN_VALUE, Integer.MIN_VALUE, 85101, 85665, 89040, 89207, 90061, 91565, Integer.MIN_VALUE, 91925, 91991, 92484, 93639, Integer.MIN_VALUE, 94176, Integer.MIN_VALUE, Integer.MIN_VALUE, 96865, 99333, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 2987, Integer.MIN_VALUE, 4135, 4407, 5065, 16993, 19183, 19507, Integer.MIN_VALUE, Integer.MIN_VALUE, 20737, 25949, 28298, Integer.MIN_VALUE, 29504, 31419, 31734, 32891, 34069, 39672, Integer.MIN_VALUE, 40822, 42183, 43332, 44030, 45044, Integer.MIN_VALUE, Integer.MIN_VALUE, 47082, Integer.MIN_VALUE, Integer.MIN_VALUE, 49631, 50439, 52667, 54768, 54975, 55787, 57157, 62661, 65359, 66013, 68271, 68827, 70019, 71891, 75218, 79639, 80319, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 84794, 85300, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 89044, Integer.MIN_VALUE, 89260, Integer.MIN_VALUE, 90548, 91449, 91662, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 92450, 92931, 93371, Integer.MIN_VALUE, 94127, Integer.MIN_VALUE, Integer.MIN_VALUE, 97907, 99301, Integer.MIN_VALUE, 2864, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 4958, 5101, 15176, 17069, 18148, 19184, 19452, 19585, 20109, 21835, 24824, 27163, 28207, 28476, 29242, 30615, 31381, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 32859, Integer.MIN_VALUE, 34036, 36073, 38586, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 41442, Integer.MIN_VALUE, 42510, Integer.MIN_VALUE, 43945, 44182, 44594, Integer.MIN_VALUE, 46997, 47305, 49550, Integer.MIN_VALUE, Integer.MIN_VALUE, 50859, 51618, 53204, 54721, Integer.MIN_VALUE, Integer.MIN_VALUE, 55449, Integer.MIN_VALUE, 56522, Integer.MIN_VALUE, Integer.MIN_VALUE, 61661, 62795, 63069, Integer.MIN_VALUE, Integer.MIN_VALUE, 66664, 68027, 68393, 68757, 69151, Integer.MIN_VALUE, Integer.MIN_VALUE, 71335, 72939, 74595, 76409, 79365, 79972, 80318, 81713, Integer.MIN_VALUE, 85058, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 89061, Integer.MIN_VALUE, 89297, 90249, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 92443, Integer.MIN_VALUE, 92586, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 94125, Integer.MIN_VALUE, 97200, Integer.MIN_VALUE, 99023, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 4770, Integer.MIN_VALUE, Integer.MIN_VALUE, 5401, 8016, 15354, Integer.MIN_VALUE, 17488, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 20404, 20982, 23635, 24642, 25159, 26662, 27243, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 28676, 28945, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 33671, Integer.MIN_VALUE, 35340, 37967, Integer.MIN_VALUE, 39461, 41251, 41715, Integer.MIN_VALUE, 43060, 43701, Integer.MIN_VALUE, Integer.MIN_VALUE, 44239, Integer.MIN_VALUE, 44868, 46992, Integer.MIN_VALUE, Integer.MIN_VALUE, 47402, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 51286, 51747, 52726, 53728, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 55885, Integer.MIN_VALUE, 60390, 62129, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 64826, 66192, Integer.MIN_VALUE, 67896, Integer.MIN_VALUE, 68319, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 68920, 69252, 70858, 71346, 71902, 73479, 73585, 74681, 75219, 76663, 78662, 79474, 79739, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 81406, 82981, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 5330, 5506, 5545, 13304, 15211, 15364, 17108, Integer.MIN_VALUE, 20243, 20435, Integer.MIN_VALUE, 21228, 22207, 23885, Integer.MIN_VALUE, 24710, 24964, 25506, 26020, 27015, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 28975, 33619, 33978, 34199, 35570, 37950, 38055, 39221, 39639, Integer.MIN_VALUE, Integer.MIN_VALUE, 41556, Integer.MIN_VALUE, 42723, Integer.MIN_VALUE, Integer.MIN_VALUE, 43831, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 46841, Integer.MIN_VALUE, Integer.MIN_VALUE, 48278, 50941, 51288, Integer.MIN_VALUE, 52161, Integer.MIN_VALUE, 52845, Integer.MIN_VALUE, 54319, 55854, 55907, 59194, 61633, 61817, 62460, 63205, 64934, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 69400, 70508, 71148, Integer.MIN_VALUE, 71617, Integer.MIN_VALUE, Integer.MIN_VALUE, 73185, Integer.MIN_VALUE, Integer.MIN_VALUE, 73989, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 75982, 76475, 76708, 78223, 78928, Integer.MIN_VALUE, 79618, Integer.MIN_VALUE, 79924, 81344, Integer.MIN_VALUE, 82149, Integer.MIN_VALUE, 5167, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 5929, 8630, 13715, 15206, Integer.MIN_VALUE, Integer.MIN_VALUE, 15585, 17105, 17347, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 20669, 21062, 21831, Integer.MIN_VALUE, 22914, Integer.MIN_VALUE, 24245, Integer.MIN_VALUE, Integer.MIN_VALUE, 24954, Integer.MIN_VALUE, 25207, Integer.MIN_VALUE, 25958, 26130, 26967, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 34097, Integer.MIN_VALUE, 35526, 36008, 36698, Integer.MIN_VALUE, 37988, 38147, Integer.MIN_VALUE, 39381, 39572, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 43920, Integer.MIN_VALUE, Integer.MIN_VALUE, 48090, 48670, Integer.MIN_VALUE, 51277, Integer.MIN_VALUE, Integer.MIN_VALUE, 51996, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 54318, 54359, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 58506, 59373, 61228, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 62339, Integer.MIN_VALUE, 63155, 64814, Integer.MIN_VALUE, 65240, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 73913, 74380, 75492, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 77369, 78462, Integer.MIN_VALUE, 78983, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 81336, Integer.MIN_VALUE, Integer.MIN_VALUE, 82595, 5162, 5326, Integer.MIN_VALUE, 7609, 8152, 12283, Integer.MIN_VALUE, 15120, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 16296, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 21478, Integer.MIN_VALUE, 22346, 23369, 24101, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 26476, Integer.MIN_VALUE, Integer.MIN_VALUE, 34087, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 36492, 37733, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 48130, 48515, 48767, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 58374, 59057, Integer.MIN_VALUE, 60085, 61197, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 64045, Integer.MIN_VALUE, 65155, 65332, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 74469, 75452, Integer.MIN_VALUE, 76956, 78151, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 79165, Integer.MIN_VALUE, Integer.MIN_VALUE, 82266, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 6132, 8001, Integer.MIN_VALUE, 8374, 9289, 12817, 15088, Integer.MIN_VALUE, 15586, 16312, Integer.MIN_VALUE, 21716, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 23539, Integer.MIN_VALUE, Integer.MIN_VALUE, 26436, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 36128, Integer.MIN_VALUE, 36816, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 58798, Integer.MIN_VALUE, 60025, 60119, Integer.MIN_VALUE, Integer.MIN_VALUE, 63760, 64574, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 76950, 77307, 77933, 78183, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 5990, 6326, Integer.MIN_VALUE, 8007, 8370, Integer.MIN_VALUE, 9176, 11394, Integer.MIN_VALUE, 13187, 15075, Integer.MIN_VALUE, Integer.MIN_VALUE, 15689, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 58792, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 63951, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 6535, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 9056, Integer.MIN_VALUE, 10519, 11998, 13029, 13228, 15060, Integer.MIN_VALUE, Integer.MIN_VALUE, 16240, 58738, Integer.MIN_VALUE, 63867, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 8698, Integer.MIN_VALUE, 9434, 11098, 11774, Integer.MIN_VALUE, 12856, 13131, Integer.MIN_VALUE, Integer.MIN_VALUE, 14242, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 10365, 10941, Integer.MIN_VALUE, Integer.MIN_VALUE, 11882, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 13730, Integer.MIN_VALUE, 9699, Integer.MIN_VALUE, 10663, 11095, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 10009, 10581, 10797, 10966};

    private static final int[] arr = {65855, 40355, 88265, 10519, 50100, 76860, 93107, 5519, 19602, 45914, 59194, 70179, 83067, 91698, 97916, 3621, 8016, 15176, 27988, 43587, 48090, 55475, 62815, 68558, 75218, 80225, 85101, 89588, 91950, 95508, 99301, 1931, 4675, 6132, 8630, 13304, 16993, 24476, 31475, 41715, 44336, 47082, 49227, 52667, 57975, 61661, 64826, 67703, 69151, 73510, 75492, 78662, 81713, 84043, 86070, 88942, 91446, 91780, 92484, 94127, 96865, 98624, 99471, 445, 2125, 4322, 5167, 5929, 7609, 8152, 9289, 12283, 15075, 15354, 17674, 20737, 26476, 29504, 36073, 40903, 42266, 44030, 45044, 46813, 47305, 48670, 49631, 51618, 54413, 55885, 58792, 60390, 62129, 63951, 65240, 66013, 68271, 68827, 69443, 71891, 73989, 75219, 76409, 77369, 79639, 80319, 82595, 83240, 84534, 85900, 87828, 88632, 89120, 90061, 91565, Integer.MIN_VALUE, 91925, 92014, 92931, 94022, 95100, 95878, 97907, 98191, 98928, 99333, 99899, 310, 1273, 2004, 2987, 4135, 4649, 5065, 5401, 5545, 5990, 6326, 8001, Integer.MIN_VALUE, 8374, 9056, 9699, 11394, 13029, 14242, 15120, 15211, 15689, 17108, 19447, 20404, 21835, 25949, 27015, 28897, 30868, 33618, 38458, 40672, 41442, 42183, 43060, 43831, 44182, 44594, 45489, 46167, 46992, Integer.MIN_VALUE, 47402, 48278, 48818, 49228, 49677, 50931, 51747, 53204, 54818, 55787, 57075, 58506, 59057, 60085, 61228, 61817, 62661, 63205, 64045, 64934, 65359, Integer.MIN_VALUE, 66664, 68027, 68393, 68757, 68920, 69252, 70019, 71335, 72939, 73585, 74595, Integer.MIN_VALUE, 75452, 75982, 76663, 76956, 78223, 79365, 79972, 80318, 81344, 82149, 82981, 83231, 83740, 84430, 84794, 85306, 85995, 86540, 88165, 88314, 88935, 89038, 89260, Integer.MIN_VALUE, 90548, 91449, 91662, Integer.MIN_VALUE, Integer.MIN_VALUE, 91991, 92450, 92586, Integer.MIN_VALUE, 93639, 94125, 94176, 95310, 95751, 95893, 97200, Integer.MIN_VALUE, 98106, 98287, 98910, 99023, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 10, Integer.MIN_VALUE, 1030, 1731, Integer.MIN_VALUE, Integer.MIN_VALUE, 2836, 3500, Integer.MIN_VALUE, Integer.MIN_VALUE, 4407, Integer.MIN_VALUE, 4958, 5101, 5330, 5506, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 6535, Integer.MIN_VALUE, 8007, 8370, Integer.MIN_VALUE, 8698, 9176, 9434, 10365, 10941, 11998, 12817, 13187, 13715, 15060, 15088, Integer.MIN_VALUE, 15206, Integer.MIN_VALUE, 15585, 16296, 17069, 17488, 19183, 19507, 19836, 20435, 21478, 23635, 24824, 26020, 26662, 27163, 28476, 29242, 30615, 31419, 31856, 34069, 37967, 39461, Integer.MIN_VALUE, 40822, 41251, 41556, Integer.MIN_VALUE, Integer.MIN_VALUE, 42510, 43332, 43701, 43945, Integer.MIN_VALUE, 44239, Integer.MIN_VALUE, 44868, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 46841, 46997, Integer.MIN_VALUE, Integer.MIN_VALUE, 48130, 48515, 48767, Integer.MIN_VALUE, Integer.MIN_VALUE, 49550, Integer.MIN_VALUE, Integer.MIN_VALUE, 50439, 51286, Integer.MIN_VALUE, 52161, 52726, 54319, 54768, 54975, Integer.MIN_VALUE, 55854, 56522, 57157, 58374, 58738, 58798, Integer.MIN_VALUE, 59373, 60119, 61197, 61633, Integer.MIN_VALUE, Integer.MIN_VALUE, 62460, 62795, 63069, 63760, Integer.MIN_VALUE, 64814, Integer.MIN_VALUE, 65155, 65332, Integer.MIN_VALUE, 66192, Integer.MIN_VALUE, 67896, Integer.MIN_VALUE, 68319, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 69400, Integer.MIN_VALUE, Integer.MIN_VALUE, 70858, 71346, 71902, 73479, Integer.MIN_VALUE, 73913, 74380, 74681, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 76475, 76708, 76950, 77307, 78151, 78462, 78983, 79474, 79739, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 81336, 81406, Integer.MIN_VALUE, 82266, Integer.MIN_VALUE, Integer.MIN_VALUE, 83127, Integer.MIN_VALUE, 83546, 83905, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 85058, 85300, 85665, Integer.MIN_VALUE, Integer.MIN_VALUE, 86196, 87243, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 88574, 88726, Integer.MIN_VALUE, 88962, 89044, 89207, 89297, 90249, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 92443, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 93371, 93798, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 96306, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 200, Integer.MIN_VALUE, 1167, 1631, 1874, 2435, 2864, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 4770, Integer.MIN_VALUE, Integer.MIN_VALUE, 5162, 5326, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 10009, Integer.MIN_VALUE, 10663, 11095, 11774, Integer.MIN_VALUE, Integer.MIN_VALUE, 12856, 13131, 13228, Integer.MIN_VALUE, 13730, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 15364, 15586, 16240, 16312, Integer.MIN_VALUE, 17105, 17347, Integer.MIN_VALUE, 18148, 19184, 19452, 19585, 19795, 20109, Integer.MIN_VALUE, 20669, 21228, 21831, 22914, 23885, 24642, 25159, 25958, 26130, Integer.MIN_VALUE, 26967, Integer.MIN_VALUE, 27601, 28298, 28854, 28945, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 31381, Integer.MIN_VALUE, 31734, 32891, 33671, 35340, 36698, 38055, 39221, 39639, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 42723, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 43920, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 50859, 50941, 51288, 51996, Integer.MIN_VALUE, Integer.MIN_VALUE, 52845, 53728, 54359, 54721, Integer.MIN_VALUE, Integer.MIN_VALUE, 55449, Integer.MIN_VALUE, Integer.MIN_VALUE, 55907, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 60025, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 62339, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 63155, Integer.MIN_VALUE, 63867, 64574, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 70508, 71148, Integer.MIN_VALUE, 71617, Integer.MIN_VALUE, Integer.MIN_VALUE, 73185, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 74469, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 77933, 78183, Integer.MIN_VALUE, Integer.MIN_VALUE, 78928, 79165, Integer.MIN_VALUE, 79618, Integer.MIN_VALUE, 79924, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 83672, Integer.MIN_VALUE, 84017, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 86796, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 89040, 89061, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 10581, 10797, 10966, 11098, Integer.MIN_VALUE, 11882, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 20243, Integer.MIN_VALUE, Integer.MIN_VALUE, 20982, Integer.MIN_VALUE, 21716, Integer.MIN_VALUE, 22207, 23369, Integer.MIN_VALUE, 24245, Integer.MIN_VALUE, 24710, 24964, 25506, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 26436, Integer.MIN_VALUE, Integer.MIN_VALUE, 27243, Integer.MIN_VALUE, 28207, Integer.MIN_VALUE, 28676, Integer.MIN_VALUE, Integer.MIN_VALUE, 28975, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 32859, Integer.MIN_VALUE, 33619, 34036, 34097, 35570, 36492, 37733, 37988, 38147, 38586, 39381, 39572, 39672, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 51277, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 54318, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 21062, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 22346, Integer.MIN_VALUE, 23539, 24101, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 24954, Integer.MIN_VALUE, 25207, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 33978, Integer.MIN_VALUE, 34087, 34199, 35526, 36008, 36128, Integer.MIN_VALUE, 36816, 37950};

    private static final int[] arras = {48818, 21835, 76663, 10941, 35570, 62795, 88574, 5326, 16240, 27988, 42510, 55449, 69151, 82266, 92586, 2836, 8152, 13304, 19585, 24964, 31475, 39221, 45489, 51747, 59057, 65332, 73479, 79165, 85058, 90061, 96306, 1273, 4649, 5990, 9434, 11998, 15176, 17488, 20669, 23885, 26436, 28945, 33978, 37733, 40822, 43945, 47305, 50439, 54318, 57075, 61228, 63951, 68027, 71148, 74681, 77933, 80225, 83672, 86196, 89040, 91780, 94127, 98624, 310, 1931, 3621, 5065, 5506, 7609, 8698, 10519, 11098, 13029, 15060, 15364, 17069, 19184, 20109, 21228, 22914, 24642, 25949, 27015, 28476, 30615, 32891, 34097, 36128, 38055, 39639, 41556, 43587, 44336, 46841, 48278, 49550, 51277, 52726, 54721, 55854, 58506, 60085, 62129, 63155, 64826, 66192, 68558, 70019, 71891, 73989, 75492, 76950, 78462, 79639, 81344, 83127, 84043, 85665, 87828, 88935, 89207, 91449, 92014, 93639, 95508, 97916, 99301, 10, 1030, 1731, 2125, 2987, 4322, 4770, 5162, 5330, 5545, 6326, 8007, 8374, 9176, 10009, 10663, 10966, 11774, 12817, 13187, 13730, 15088, 15211, 15586, 16312, 17108, 18148, 19452, 19795, 20404, 20982, 21716, 22207, 23539, 24245, 24824, 25207, 26020, 26662, 27243, 28207, 28854, 29242, 31381, 31856, 33619, 34069, 35340, 36008, 36698, 37967, 38458, 39461, 40355, 41251, 42183, 43060, 43831, 44182, 44868, 46167, 46997, 48090, 48670, 49227, 49677, 50931, 51288, 52161, 53204, 54359, 54818, 55475, 55907, 57975, 58792, 59373, 60390, 61661, 62460, 62815, 63760, 64574, 65155, 65855, 67703, 68319, 68827, 69400, 70508, 71346, 72939, 73585, 74469, 75219, 76409, 76708, 77307, 78183, 78928, 79474, 79924, 80319, 81713, 82981, 83240, 83905, 84534, 85300, 85995, 86796, 88265, 88632, 88962, 89061, 89297, 90548, 91662, 91950, 92450, 93107, 94022, 95100, 95878, 97200, 98191, 98928, 99471, Integer.MIN_VALUE, 200, 445, 1167, 1631, 1874, 2004, 2435, 2864, 3500, 4135, 4407, 4675, 4958, 5101, 5167, Integer.MIN_VALUE, 5401, 5519, 5929, 6132, 6535, 8001, 8016, 8370, 8630, 9056, 9289, 9699, 10365, 10581, 10797, Integer.MIN_VALUE, 11095, 11394, 11882, 12283, 12856, 13131, 13228, 13715, 14242, 15075, 15120, 15206, 15354, 15585, 15689, 16296, 16993, 17105, 17347, 17674, 19183, 19447, 19507, 19602, 19836, 20243, 20435, 20737, 21062, 21478, 21831, Integer.MIN_VALUE, 22346, 23369, 23635, 24101, 24476, 24710, 24954, 25159, 25506, 25958, 26130, 26476, 26967, 27163, 27601, Integer.MIN_VALUE, 28298, 28676, 28897, 28975, 29504, 30868, 31419, 31734, 32859, 33618, 33671, 34036, 34087, 34199, 35526, Integer.MIN_VALUE, 36073, 36492, 36816, 37950, 37988, 38147, 38586, 39381, 39572, 39672, 40672, 40903, 41442, 41715, 42266, 42723, 43332, 43701, 43920, 44030, 44239, 44594, 45044, 45914, 46813, 46992, 47082, 47402, 48130, 48515, 48767, Integer.MIN_VALUE, 49228, 49631, 50100, 50859, 50941, 51286, 51618, 51996, 52667, 52845, 53728, 54319, 54413, 54768, 54975, Integer.MIN_VALUE, 55787, 55885, 56522, 57157, 58374, 58738, 58798, 59194, 60025, 60119, 61197, 61633, 61817, 62339, 62661, Integer.MIN_VALUE, 63069, 63205, 63867, 64045, 64814, 64934, 65240, 65359, 66013, 66664, 67896, 68271, 68393, 68757, 68920, 69252, 69443, 70179, 70858, 71335, 71617, 71902, 73185, 73510, 73913, 74380, 74595, 75218, 75452, 75982, 76475, Integer.MIN_VALUE, 76860, 76956, 77369, 78151, 78223, 78662, 78983, 79365, 79618, 79739, 79972, 80318, 81336, 81406, 82149, 82595, 83067, 83231, 83546, 83740, 84017, 84430, 84794, 85101, 85306, 85900, 86070, 86540, 87243, 88165, 88314, Integer.MIN_VALUE, 88726, 88942, 89038, 89044, 89120, 89260, 89588, 90249, 91446, 91565, 91698, 91925, 91991, 92443, 92484, 92931, 93371, 93798, 94125, 94176, 95310, 95751, 95893, 96865, 97907, 98106, 98287, 98910, 99023, 99333, 99899};

    private static final int[] littleArr = {1, Integer.MIN_VALUE, 2, Integer.MIN_VALUE, 3, Integer.MIN_VALUE, 4, Integer.MIN_VALUE, Integer.MIN_VALUE};

    private static int sum = 0, index;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        TreeNode treeNode = makeTree(oldArr);
        long end = System.currentTimeMillis();

        System.out.println(isBalanceBST(treeNode));

        System.out.println("times : " + (end - start));

        System.out.println(treeSize(treeNode));

//        TreeNode node = makeTree(arras);

        System.out.println(isBST(treeNode));

//        System.out.println(isBST(node));
//
        TreeNode treeNode1 = balanceBST(treeNode);
//
        System.out.println(isBST(treeNode1));
        System.out.println(isBalanceBST(treeNode1));

        TreeNode treeNode2 = makeTree(arras);

        System.out.println(isBalanceBST(treeNode2));

//        TreeNode t = new TreeNode(5);
//
//        t.left = new TreeNode(3);
//
//        t.right = new TreeNode(6);
//
//        t.right.right = new TreeNode(7);
//
//        t.right.right.right = new TreeNode(8);
//
//        System.out.println(isBalanceBST(t));
//
//        System.out.println(treeLen(t));
    }

    public static boolean isBalanceBST(TreeNode root) {

        return isBalanceBSTProcess(root);
    }

    private static boolean isBalanceBSTProcess(TreeNode root) {

        if (root == null) {

            return true;
        }

        return isBST(root) && Math.abs(treeLen(root.left) - treeLen(root.right)) <= 1;
    }


    public static int treeLen(TreeNode root) {

        return treeLenProcess(root);
    }

    private static int treeLenProcess(TreeNode root) {

        if (root == null) {

            return 0;
        }

        return Math.max(treeLenProcess(root.left), treeLenProcess(root.right)) + 1;
    }

    public static int treeSize(TreeNode root) {

        return treeSizeProcess(root);
    }

    private static int treeSizeProcess(TreeNode root) {

        if (root == null) {

            return 0;
        }

        return treeSizeProcess(root.left) + treeSizeProcess(root.right) + 1;
    }

    public static boolean isBST(TreeNode root) {

        return isBSTProcess(root);
    }

    private static boolean isBSTProcess(TreeNode root) {

        if (root == null) {

            return true;
        }

        boolean b = isBSTProcess(root.left) & isBSTProcess(root.right);

        int left = root.left == null ? Integer.MIN_VALUE : root.left.val;

        int right = root.right == null ? Integer.MAX_VALUE : root.right.val;

        b = b & root.val > left & root.val < right;

//        if (!b) {
//
//            System.out.print(root.val + "   ");
//
//            if (root.left != null) {
//
//                System.out.print("  left : " + root.left.val);
//            }
//
//            if (root.right != null) {
//
//                System.out.print("  right : " + root.right.val);
//            }
//            System.out.println();
//        }

//        return b & root.val > left & root.val < right;

        return b;
    }

    public static TreeNode makeTree(int[] arr) {

        if (arr == null || arr.length == 0) {

            return null;
        }

        return makeTreeProcess(arr, 0);
    }

    private static TreeNode makeTreeProcess(int[] arr, int cur) {

        int index = cur;

        TreeNode root = new TreeNode(arr[cur]), node;

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {

            cur = index << 1;

            node = queue.pop();

            if (cur + 1 < arr.length && arr[cur + 1] != Integer.MIN_VALUE) {

                node.left = new TreeNode(arr[cur + 1]);

                queue.offer(node.left);
            }

            if (cur + 2 < arr.length && arr[cur + 2] != Integer.MIN_VALUE) {

                node.right = new TreeNode(arr[cur + 2]);

                queue.offer(node.right);
            }

            index++;
        }

        return root;
    }

    public static TreeNode balanceBST(TreeNode root) {


        root = process(root).node;

        return root;
    }

    private static Info process(TreeNode root) {

        if (root == null) {

            return new Info();
        }

        Info left = process(root.left);

        Info right = process(root.right);

        root.left = left.node;

        root.right = right.node;

        Info info = new Info();

        info.leftSize = left.size;

        info.rightSize = right.size;

        info.node = root;

        if (right.size - left.size > 1) {

//            if (right.leftSize > right.rightSize) {
//
//                root.right = rotateRight(right.node);
//            }

//            sum++;

            info.node = rotateLeft(info.node);

            info.leftSize = sizeProcess(info.node.left);

            info.rightSize = sizeProcess(root.right);
        } else if (left.size - right.size > 1) {

//            if (left.rightSize > left.leftSize) {
//
//                root.left = rotateLeft(left.node);
//            }

//            sum++;

            info.node = rotateRight(root);

            info.leftSize = sizeProcess(info.node.left);

            info.rightSize = sizeProcess(info.node.right);
        }

        info.size = Math.max(info.leftSize, info.rightSize) + 1;

//        System.out.println(info.node.val + " : " + info.leftSize + "  " + info.rightSize);
        return info;
    }

    private static int sizeProcess(TreeNode root) {

        if (root == null) {

            return 0;
        }

        return Math.max(sizeProcess(root.left), sizeProcess(root.right)) + 1;
    }

    /**
     * 左旋转
     *
     * @param h
     * @return
     */
    private static TreeNode rotateLeft(TreeNode h) {

        TreeNode x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }

    /**
     * 右旋转
     *
     * @param h
     * @return
     */
    private static TreeNode rotateRight(TreeNode h) {
        TreeNode x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    public static class Info {

        int size, leftSize, rightSize, minLen, maxLen;

        TreeNode node;

        public Info() {
        }

        public Info(TreeNode node) {

            this.node = node;
        }

        public Info(int size, TreeNode node) {
            this.size = size;
            this.node = node;
        }

        public Info(int leftSize, int rightSize, TreeNode node) {
            this.size = leftSize + rightSize + 1;
            this.leftSize = leftSize;
            this.rightSize = rightSize;
            this.node = node;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
