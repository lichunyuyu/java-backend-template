package com.fxmms.common.util;

/**
 * Created by mark on 16/11/4.
 */
public class EncodeUtil {
    public static String encodeStr(String str) {
        String newStr = str;
        try {
            newStr = new String(str.getBytes("iso-8859-1"), "utf-8");
        }catch (Exception e) {
             e.printStackTrace();
        }finally {
            return newStr;
        }
    }
}
