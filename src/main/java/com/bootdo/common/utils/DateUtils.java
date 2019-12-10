package com.bootdo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 */
public class DateUtils {
    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        } else if (hour > 0) {
            r += hour + "小时";
        } else if (min > 0) {
            r += min + "分";
        } else if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    public static int getYears(String oldDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date nowDate = new Date();
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(oldDate));
        aft.setTime(sdf.parse(sdf.format(nowDate)));
        int surplus = aft.get(Calendar.DATE) - bef.get(Calendar.DATE);
        int year = aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR);
        if(year < 0){
            year = 1;
        }else if(year == 0){
            year = surplus <= 0 ? 1 : 0;
        }
        return year;
    }

    public static void main(String[] args) throws ParseException {
        /*int years = getYears("2012/06/18");
        System.out.println(years);*/
       /* String t1 = "21:43";
        String t2 = "23:59";
        //Boolean result = DateUtils.largerTime(t1, t2);
        BigDecimal result = DateUtils.timeDifference(t1, t2);
        System.out.println(result);*/
       /* String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Date date = getDate("2019-12-01");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int w = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (w < 0){
            w = 0;
        }
        System.out.println(weekDays[w]);*/
       String date = "2019-2-1";
        System.out.println(getMaxDate(date));
    }

    /**
     * t1是否晚于t2（小时数跟分钟数比较）
     * @param t1
     * @param t2
     * @return
     */
    public static Boolean largerTime(String t1,String t2) {
        boolean flag = false;
        Date date1, date2;
        DateFormat formart = new SimpleDateFormat("hh:mm");
        try {
            date1 = formart.parse(t1);
            date2 = formart.parse(t2);
            if (date1.compareTo(date2) < 0) {//date1早于date2
                return flag;
            } else {//date1晚于或等于date2
                flag = true;
                return flag;
            }
        } catch (ParseException e) {
            System.out.println("date init fail!");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * t1与t2相差(小时数，保留一位小数)
     * @param t1
     * @param t2
     * @return
     */
    public static BigDecimal timeDifference(String t1, String t2) {
        Date date1, date2;
        DateFormat formart = new SimpleDateFormat("hh:mm");
        try {
            date1 = formart.parse(t1);
            date2 = formart.parse(t2);
            long m = (date2.getHours()*60 + date2.getMinutes()) - (date1.getHours()*60 + date1.getMinutes());
            return new BigDecimal(m/60.0).setScale(1,BigDecimal.ROUND_HALF_UP);
        } catch (ParseException e) {
            System.out.println("date init fail!");
            e.printStackTrace();
            return null;
        }
    }

    public static Date getDate(String dateString) throws ParseException {
        DateFormat formart = new SimpleDateFormat(DATE_PATTERN);
        Date date = formart.parse(dateString);
        return date;
    }

    public static String getWeekDay(String dateString) throws ParseException {
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Date date = getDate(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int w = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }

    public static int getMaxDate(String dateString) throws ParseException {
        Date date = getDate(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

}
