package com.fxmms.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mark on 16/11/10.
 */
public class DateUtil {
    /**
     *
     * @param date
     * @return
     * @usage 转化时间为相应格式的字符串
     */
    public static String doChangeDateToStr(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = formatter.format(date);
        return dateStr;
    }

    /**
     * @param n
     * @usage 计算当前时间的前n个小时
     */
    public static String beforeNHourToNowDate(int n) {
        Calendar calendar = Calendar.getInstance();
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - n);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    /**
     *
     * @param dateStr
     * @return
     * @usage 将字符串转化为 java.util.date 形似 Mon Nov 14 00:00:00 CST 2016
     */
    public static Date changeStrToDate(String dateStr){
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try{
            date = fmt.parse(dateStr);
            fmt.format(date);
        }catch (ParseException e){
            //e.printStackTrace();
        }finally {
            return date;
        }
    }

    /**
     *
     * @param dateStr
     * @return
     * @usage 将dateStr 转化为sqlDate
     */
    public static java.sql.Date changeDateStrToSqlDate(String dateStr) {
        java.sql.Date sdt = null;
        try{
            sdt = java.sql.Date.valueOf(dateStr);
        }catch (Exception e){
            //do nothing
        }finally {
            return sdt;
        }
    }

    /**
     * @param args
     * @throws ParseException 测试桩
     */
    public static void main(String[] args) throws ParseException {
        beforeNHourToNowDate(1);
        System.out.println(changeDateStrToSqlDate("2016-11-14"));

    }

}
