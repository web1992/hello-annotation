package com.web.dateutil;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by erniu on 2015/11/29.
 */
public class TimeUtils {
    static {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        TIME_ZONE = TimeZone.getDefault();
    }
    public static  final TimeZone TIME_ZONE;

    /**
     * 获取当前时间与 lastAssistantTime 间隔的天数
     * @param beginTime
     * @param endTime
     * @return
     */
    public static int getDayShift(long beginTime,long endTime) {
        GregorianCalendar beginTimeCal = new GregorianCalendar();
        // 获取游戏工具类的时区
        beginTimeCal.setTimeZone(TimeUtils.TIME_ZONE);
        beginTimeCal.setTimeInMillis(beginTime);

        GregorianCalendar endTimeCal = new GregorianCalendar();
        // 获取游戏工具类的时区
        endTimeCal.setTimeZone(TimeUtils.TIME_ZONE);
        endTimeCal.setTimeInMillis(endTime);

        int begin=beginTimeCal.get(Calendar.DAY_OF_YEAR);
        int end=endTimeCal.get(Calendar.DAY_OF_YEAR);
        // 是同一年
        if(beginTimeCal.get(Calendar.YEAR) == endTimeCal.get(Calendar.YEAR)) {
            return (end-begin) ;
        }else{
            // 不是同一年 如：2015-12-31 与 2015-01-01 时间间隔为1天,015-01-01 当天时间不算
            int maxDayBegin=beginTimeCal.getActualMaximum(Calendar.DAY_OF_YEAR);
            int startDays=maxDayBegin-begin;
            // 需要减去1
            return  (startDays+end-1);
        }
    }

}
