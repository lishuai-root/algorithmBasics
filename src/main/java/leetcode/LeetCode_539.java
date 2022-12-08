package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a list of 24-hour clock time points in "HH:MM" format,
 * return the minimum minutes difference between any two time-points in the list.
 * @author: LISHUAI
 * @createDate: 2022/12/2 0:35                          PM
 * @version: 1.0
 */

public class LeetCode_539 {

    public static void main(String[] args) {
//        String[] ss = {"01:01", "02:01", "03:00", "03:01"};
//        String[] ss = {"12:12", "12:13"};
//        String[] ss = {"11:13", "12:12"};
        String[] ss = {"12:12", "00:13"};
//        String[] ss = {"01:39", "10:26", "21:43"};
        List<String> timePoints = new ArrayList<>(List.of(ss));
//        timePoints.add("12:12");
//        timePoints.add("00:13");
//        timePoints.add("23:59");
//        timePoints.add("00:00");
//        timePoints.add("00:01");
        int minDifference = findMinDifference(timePoints);
        System.out.println(minDifference);
        System.out.println(12 * 60 + 1);
        System.out.println(24 * 60);
    }

    public static int findMinDifference(List<String> timePoints) {
        int ans = Integer.MAX_VALUE;
        timePoints.sort(String::compareTo);
        int index = 0;
        String pre = timePoints.get(timePoints.size() - 1);
        int preH = Integer.parseInt(pre.substring(0, pre.indexOf(":")));
        int preM = Integer.parseInt(pre.substring(pre.indexOf(":") + 1));

        while (index < timePoints.size()) {
            String s = timePoints.get(index);
            if (s.equals(pre)) {
                return 0;
            }
            int curH = Integer.parseInt(s.substring(0, s.indexOf(":")));
            int curM = Integer.parseInt(s.substring(s.indexOf(":") + 1));
            int curTime = Math.abs((curH - preH) * 60 + (curM - preM));
            ans = Math.min(ans, Math.min(curTime, 1440 - curTime));
            pre = s;
            preH = curH;
            preM = curM;
            index++;
        }
        return ans;
    }
}
