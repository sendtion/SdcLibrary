package com.fb.sdclibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TimeUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    /**
     * 时间戳转换为Date
     *
     * @param time 时间戳
     * @return 时间
     */
    public static Date toDate(long time) {
        String date = format.format(time);
        try {
            return format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转换为Date
     *
     * @param time 时间戳
     * @return 时间
     */
    public static Date toDate(String time, String formatString) {
        try {
            return new SimpleDateFormat(formatString, Locale.CHINA).parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间戳转换为String
     *
     * @param time 时间戳
     * @return 时间
     */
    public static String toString(long time, String formatString) {
        return new SimpleDateFormat(formatString, Locale.CHINA).format(time);
    }

    /**
     * 将String装换成时间戳
     *
     * @param time 时间
     * @return 时间戳
     */
    public static long toLong(Date time) {
        try {
            return time.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将String装换成时间戳
     *
     * @param time 时间
     * @return 时间戳
     */
    public static long toLong(String time) {
        try {
            return format.parse(time).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将String装换成时间戳后再转成String格式
     *
     * @param time 时间
     * @return 时间戳
     */
    public static String toLongToString(String formatFrom, String time, String formatTo) {
        try {
            return toString(new SimpleDateFormat(formatFrom, Locale.CHINA).parse(time).getTime(), formatTo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将String装换成时间戳
     *
     * @param time 时间
     * @return 时间戳
     */
    public static long toLong(String time, String formatString) {
        try {
            return new SimpleDateFormat(formatString, Locale.CHINA).parse(time).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取本周第一天
     *
     * @return 第一天的时间戳
     */
    public static long getWeekStart() {
        Calendar currentDate = new GregorianCalendar();
        currentDate.setFirstDayOfWeek(Calendar.SUNDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return currentDate.getTimeInMillis();
    }

    /**
     * 获取本周最后一天
     *
     * @return 最后一天的时间戳
     */
    public static long getWeekEnd() {
        Calendar currentDate = new GregorianCalendar();
        currentDate.setFirstDayOfWeek(Calendar.SUNDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return currentDate.getTimeInMillis();
    }

    /**
     * 获取当前月第一天
     *
     * @return 第一天的时间戳
     */
    public static long getMonthStart() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MONTH, 0);
        instance.set(Calendar.DAY_OF_MONTH, 1);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        return instance.getTimeInMillis();
    }

    /**
     * 获取当前月最后一天
     *
     * @return 最后一天的时间戳
     */
    public static long getMonthEnd() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DAY_OF_MONTH, instance.getActualMaximum(Calendar.DAY_OF_MONTH));
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        return instance.getTimeInMillis();
    }

    /**
     * 获取当天的开始
     *
     * @return 时间戳
     */
    public static Long getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

    /**
     * 获取当天的结束
     *
     * @return 时间戳
     */
    public static Long getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }

    /**
     * 获取几天前的时间
     *
     * @return 时间戳
     */
    public static Long getDayInNumStart(int count) {

        return getStartTime() - 1000 * 60 * 60 * 24 * (count - 1);
    }

    /**
     * 判断输入的时间是否在当天
     *
     * @param currentTime 当前时间毫秒值
     * @return true 在今天范围内
     */
    public static boolean isBelongToday(long currentTime) {
        if (currentTime <= getEndTime() && currentTime >= getStartTime()) {
            return true;
        }
        return false;

    }
}
