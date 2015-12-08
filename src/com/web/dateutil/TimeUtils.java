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
     * ��ȡ��ǰʱ���� lastAssistantTime ���������
     * @param beginTime
     * @param endTime
     * @return
     */
    public static int getDayShift(long beginTime,long endTime) {
        GregorianCalendar beginTimeCal = new GregorianCalendar();
        // ��ȡ��Ϸ�������ʱ��
        beginTimeCal.setTimeZone(TimeUtils.TIME_ZONE);
        beginTimeCal.setTimeInMillis(beginTime);

        GregorianCalendar endTimeCal = new GregorianCalendar();
        // ��ȡ��Ϸ�������ʱ��
        endTimeCal.setTimeZone(TimeUtils.TIME_ZONE);
        endTimeCal.setTimeInMillis(endTime);

        int begin=beginTimeCal.get(Calendar.DAY_OF_YEAR);
        int end=endTimeCal.get(Calendar.DAY_OF_YEAR);
        // ��ͬһ��
        if(beginTimeCal.get(Calendar.YEAR) == endTimeCal.get(Calendar.YEAR)) {
            return (end-begin) ;
        }else{
            // ����ͬһ�� �磺2015-12-31 �� 2015-01-01 ʱ����Ϊ1��,015-01-01 ����ʱ�䲻��
            int maxDayBegin=beginTimeCal.getActualMaximum(Calendar.DAY_OF_YEAR);
            int startDays=maxDayBegin-begin;
            // ��Ҫ��ȥ1
            return  (startDays+end-1);
        }
    }

}
