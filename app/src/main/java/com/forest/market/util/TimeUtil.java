package com.forest.market.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by guo on 2016/07/28 14:05
 */
public class TimeUtil {
    private static SimpleDateFormat format;

    public static String getCurTime(String template) {
        return getFormatTime(System.currentTimeMillis(), template);
    }

    public static String getFormatTime(long milliseconds, String template) {
        return getFormatTime(new Date(milliseconds), template);
    }

    public static String getFormatTime(Date date, String template) {
        if (format == null) {
            format = new SimpleDateFormat(template, Locale.CHINA);
        }
        return format.format(date);
    }

    public static String getTimeInterval(long time) {
        time = System.currentTimeMillis() / 1000 - time;
        if (time < 60) {
            return "刚刚";
        } else if (time < 60 * 60) {
            return time / 60 + "分钟前";
        } else if (time < 60 * 60 * 24) {
            return time / 3600 + "小时" + (time % 3600) / 24 + "分钟前";
        } else {
            return time / (60 * 60 * 24) + "天前";
        }
    }

    public static String getTimeDis(int time) {
        String t;
        if (time < 60 * 60) {
            t = getTime(time / 60) + ":" + getTime(time % 60);
        } else {
            t = getTime(time / 3600) + ":" + getTime((time % 3600) / 60) + ":" + getTime(time % 60);
        }
        return t;
    }

    private static String getTime(int t) {
        return String.format("%02d", t);
    }

    public static String getFormatTime(int curNum) {
        if (curNum < 10) {
            return "0" + curNum;
        }
        return curNum + "";
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    public static Long getLongTime(String pattent,String date){
        SimpleDateFormat sdf = new SimpleDateFormat(pattent);
        try {
            long time = sdf.parse(date).getTime();
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1L;
    }



}
