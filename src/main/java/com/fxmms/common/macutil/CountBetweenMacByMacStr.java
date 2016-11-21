package com.fxmms.common.macutil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mark on 16/11/8.
 * @usage 计算连续mac地址间包含的mac地址
 */
public class CountBetweenMacByMacStr {
    /**
     * 获取两个MAC区间内的所有MAC地址 并封装至list当中
     */
    public static List<String> countBetweenMacByMacStr(String macStart, String macEnd) {
        List<String> macStrList = new ArrayList<>();
        long start = turnMacToLong(macStart);
        System.out.println(start + "-----");
        long end = turnMacToLong(macEnd);
        System.out.println(end + "-----");
        String prefix = macStart.substring(0, 9);
        System.out.println(prefix);
        String hex = null;
        String suffix = null;
        StringBuffer sb = null;
        int count = 0;
        for (long i = start; i < end + 1; i++) {
            hex = Long.toHexString(i);
            suffix = hex.substring(hex.length() - 6);
            sb = new StringBuffer(suffix);
            sb.insert(2, ":");
            sb.insert(5, ":");
            System.out.println(prefix + sb.toString());
            macStrList.add(prefix + sb.toString());
            count++;
        }
        System.out.println(count+"-----");
        return macStrList;
    }

    /**
     * 将MAC转换成数字
     */
    public static long turnMacToLong(String MAC) {
        String hex = MAC.replaceAll("\\:", "");
        long longMac = Long.parseLong(hex, 16);
        return longMac;
    }

    public static boolean matchMacAddrByregex(String macAddr){
        String pattern = "^[0-9a-fA-F]{2}(:[0-9a-fA-F]{2}){5}$";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(macAddr);
        return m.find();
    }

    public static void main(String[] args) {
        String starMacStr = "8C:7B:6D:43:50:8A";
        System.out.println(matchMacAddrByregex(starMacStr));
       // String endMacStr = "8C:7B:9D:43:50:94";
       // countBetweenMacByMacStr(starMacStr, endMacStr);
    }
}
