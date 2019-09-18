package com.zoyo.core.common.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/8.
 */
public class TimeUtil {

    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String timeLogic(String dateStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_MONTH);
        long now = calendar.getTimeInMillis();
        Date date = strToDate(dateStr);
        calendar.setTime(date);
        long past = calendar.getTimeInMillis();

        // 相差的秒数
        long time = (now - past) / 1000;

        StringBuffer sb = new StringBuffer();
        if (time > 0 && time < 60) { // 1小时内
            return sb.append(time + "秒前").toString();
        } else if (time > 60 && time < 3600) {
            return sb.append(time / 60+"分钟前").toString();
        } else if (time >= 3600 && time < 3600 * 24) {
            return sb.append(time / 3600 +"小时前").toString();
        }else if (time >= 3600 * 24 && time < 3600 * 48) {
            return sb.append("昨天").toString();
        }else if (time >= 3600 * 48 && time < 3600 * 72) {
            return sb.append("前天").toString();
        }else if (time >= 3600 * 72) {
            return (int)(time/3600/24)+"天前";
        }
        return "";
    }

    /**
     * 日期字符串转换为Date
     * @param dateStr
     * @return
     */
    public static Date strToDate(String dateStr) {
        Date date = null;

        if (!TextUtils.isEmpty(dateStr)) {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            try {
                date = df.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 将10位或者13位时间戳转换为日期格式：2016-07-18
     * @param times
     * @return
     */
    public static String dateFormat(String times){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(times.length() == 13){
            return sdf.format(new Date(Long.parseLong(times)));
        }else if(times.length() == 10){
            return sdf.format(new Date(Long.parseLong(times)*1000L));
        }
        return  null;
    }

    /**
     * 将10位或者13位时间戳转换为日期格式：2016年07月18日
     * @param times
     * @return
     */
    public static String dateFormat2(String times){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        if(times.length() == 13){
            return sdf.format(new Date(Long.parseLong(times)));
        }else if(times.length() == 10){
            return sdf.format(new Date(Long.parseLong(times)*1000L));
        }
        return  null;
    }

    /**
     * 将10位或者13位时间戳转换为日期格式：2016-07-18 12:44
     * @param times
     * @return
     */
    public static String dateFormat3(String times){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(times.length() == 13){
            return sdf.format(new Date(Long.parseLong(times)));
        }else if(times.length() == 10){
            return sdf.format(new Date(Long.parseLong(times)*1000L));
        }
        return  null;
    }

    /**
     * 将yyyy-MM-dd HH:mm转换为时间戳
     * @param times
     * @return
     */
    public static Long dateFormat4(String times){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = sdf.parse(times);
            return  date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 计算两个日期之间相差的天数
     * @param currentTime yyyy-MM-dd HH:mm
     * @param publishTime yyyy-MM-dd HH:mm
     * @return 返回整型的天数
     */
    public static int betweenDays(String currentTime, String publishTime){
        double dayss = -(dateFormat4(currentTime) - dateFormat4(publishTime)) / (1000*3600*24);
        String[] ss = (dayss+"").split("\\.");
        int days = Integer.valueOf(ss[0]);
        return days;
    }

    /**
     * 计算两个日期之间相差的小时
     * @param currentTime yyyy-MM-dd HH:mm
     * @param publishTime yyyy-MM-dd HH:mm
     * @return
     */
    public static int betweenHours(String currentTime, String publishTime){
        double dayss = -(dateFormat4(currentTime) - dateFormat4(publishTime)) / (1000*3600);
        String[] ss = (dayss+"").split("\\.");
        int hours = Integer.valueOf(ss[0]);
        return hours;
    }

    /**
     * 将10位或者13位时间戳转换为日期格式：2016-07-18 12:44:22
     * @param times
     * @return
     */
    public static String dateFormat5(String times){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(times.length() == 13){
            return sdf.format(new Date(Long.parseLong(times)));
        }else if(times.length() == 10){
            return sdf.format(new Date(Long.parseLong(times)*1000L));
        }
        return  null;
    }

    /**
     * 将10位或者13位时间戳转换为日期格式：12:44
     * @param times
     * @return
     */
    public static String dateStampToDate(String times){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        if(times.length() == 13){
            return sdf.format(new Date(Long.parseLong(times)));
        }else if(times.length() == 10){
            return sdf.format(new Date(Long.parseLong(times)*1000L));
        }
        return  null;
    }
}
