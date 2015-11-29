package com.web.dateutil;

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
}
