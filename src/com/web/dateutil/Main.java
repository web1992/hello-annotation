package com.web.dateutil;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.web.util.Util.print;


/**
 * Created by erniu on 2015/11/29.
 */
public class Main {

    public static void main(String[] a) {
//        long day=1000*60*60*24;
//
//        Date _data=new Date();
//        System.out.println(_data);
//        //日期：2009年3月11号
//        Calendar c1 = Calendar.getInstance();
//        c1.set(2015, 12 - 1, 31);
//        System.out.println("WEEK_OF_YEAR=" + c1.get(Calendar.WEEK_OF_YEAR));
//        System.out.println(c1.getTime());
//        System.out.println("----");
//        Calendar c12 = Calendar.getInstance();
//        c12.set(2016, 1 - 1, 1);
//        System.out.println("WEEK_OF_YEAR=" + c12.get(Calendar.WEEK_OF_YEAR));
//
//        System.out.println(c12.getTime());
//
//        Calendar cal = new GregorianCalendar();
//        System.out.println(cal.getTimeZone());
//        // 获取游戏工具类的时区
//        cal.setTimeZone(TimeUtils.TIME_ZONE);
//        System.out.println(cal.getTimeZone());
//        int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
//        System.out.println(weekOfYear);
//        System.out.println(cal.getFirstDayOfWeek());
//        System.out.println("WEEK_OF_YEAR=" + cal.get(Calendar.WEEK_OF_YEAR));
//
//        //
//        Calendar c1 = Calendar.getInstance();
//        c1.set(2015, 12 - 1, 31);
//        print("2015, 12 - 1, 31");
//        print(c1);

        int year=2015;
        int month=12;
        int date=31;
        for(int i=date;i>0;i--){
            Calendar c1 = Calendar.getInstance();
            c1.setTimeZone(TimeUtils.TIME_ZONE);
            int m=month- 1;
            c1.set(year, m, i);
            int weekOfYear = c1.get(Calendar.WEEK_OF_YEAR);
            int weekFirstDay = (c1.getFirstDayOfWeek());
            System.out.print(" WEEK_OF_YEAR=" + weekOfYear);
            System.out.print(" weekFirstDay=" + weekFirstDay);
            System.out.println(" date=" + String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(i));
        }

        //

//        WEEK_OF_YEAR=1 weekFirstDay=1 date=2015-12-26
//        WEEK_OF_YEAR=52 weekFirstDay=1 date=2015-12-26
        Calendar st = Calendar.getInstance();
        st.setTimeZone(TimeUtils.TIME_ZONE);
        st.set(2015, 12, 25);
        st.setFirstDayOfWeek(Calendar.MONDAY);

        Calendar et = Calendar.getInstance();
        et.setTimeZone(TimeUtils.TIME_ZONE);
        et.setFirstDayOfWeek(Calendar.MONDAY);
        et.set(2015, 12, 23);

        if (st.get(Calendar.WEEK_OF_YEAR) == et.get(Calendar.WEEK_OF_YEAR)) {
            print("true");
        }else{
            print("false");
        }

    }

}
