package com.web.dateutil;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.web.util.Util.print;


/**
 * Created by erniu on 2015/11/29.
 */
public class Main {

    public static void main(String[] a) {

        // 打印12月的日期
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

//       WEEK_OF_YEAR=1 weekFirstDay=1 date=2015-12-27
//       WEEK_OF_YEAR=52 weekFirstDay=1 date=2015-12-26
        // 测试日期是否是同一周
        Calendar st = Calendar.getInstance();
        st.setTimeZone(TimeUtils.TIME_ZONE);
        st.set(2015, 12, 31);
        st.setFirstDayOfWeek(Calendar.MONDAY);

        Calendar et = Calendar.getInstance();
        et.setTimeZone(TimeUtils.TIME_ZONE);
        et.setFirstDayOfWeek(Calendar.MONDAY);
        et.set(2016, 01, 01);

        if (st.get(Calendar.WEEK_OF_YEAR) == et.get(Calendar.WEEK_OF_YEAR)) {
            print("true");
        }else{
            print("false");
        }
        int dayShift=TimeUtils.getDayShift(st.getTimeInMillis(),et.getTimeInMillis());

        print("dayShift="+dayShift);
    }

}
