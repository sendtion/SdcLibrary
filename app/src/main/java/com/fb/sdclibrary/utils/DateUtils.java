package com.fb.sdclibrary.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Description: 时间处理，获取当月的天数，获取当月的第一天和最后一天
 * CreateTime: 2018/8/24 16:38
 * Author: ShengDecheng
 */

public class DateUtils {

    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static final DateFormat FILE_NAME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault());

    public static void main(String[] args) {
        int currentMaxDays = getCurrentMonthDay();
        int maxDaysByDate = getDaysByYearMonth(2017, 9);
        System.out.println("本月天数：" + currentMaxDays);
        System.out.println("2017年9月天数：" + maxDaysByDate);

        System.out.println("2018年8月第一天：" + getFirstDayOfMonth(2018, 2));
        System.out.println("2018年8月最后一天：" + getLastDayOfMonth(2018, 2));
    }

    /**
     * 获取当月的 天数
     * https://blog.csdn.net/h4241778/article/details/77849439
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    /**
     * 获得该月第一天
     * https://blog.csdn.net/u011287511/article/details/54344402
     * @param year 年份
     * @param month 月份
     * @return 返回年月日
     */
    public static String getFirstDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获得该月最后一天
     * @param year 年份
     * @param month 月份
     * @return 返回年月日
     */
    public static String getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 根据日期字符串获取年份
     */
    public static int getYearFromString(String timeStr) {
        //DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
        Date date = null;
        try {
            date = DEFAULT_FORMAT.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        //System.out.println(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        //System.out.println((date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + (date.getDay() + 12));
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 根据日期字符串获取月份
     */
    public static int getMonthFromString(String timeStr) {
        //DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
        Date date = null;
        try {
            date = DEFAULT_FORMAT.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        //System.out.println(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        //System.out.println((date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + (date.getDay() + 12));
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 根据日期字符串获取日期
     */
    public static int getDayFromString(String timeStr) {
        //DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
        Date date = null;
        try {
            date = DEFAULT_FORMAT.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        //System.out.println(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        //System.out.println((date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + (date.getDay() + 12));
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 格式化时间为 MM/dd HH:mm  yyyy-MM-dd  yyyy-MM-dd HH:mm
     *
     */
    public static String getFormatTime(String dateStr, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String result = null;
        try {
            Date date = sdf.parse(dateStr);
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr, Locale.CHINA);
            result = formatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
