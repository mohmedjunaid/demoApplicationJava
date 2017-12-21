package com.example.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by babu.kannar@indianic.com on 10/17/2016.
 */
public class DateTimeUtils {
    private static String UTC_TIME_ZONE = "ETC-UTC";
    private static String DATE_TIME_UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    private static String DATE_UTC_FORMAT = "yyyy-MM-dd";

    public static LocalDateTime getNow() {
        return  LocalDateTime.now(TimeZone.getTimeZone(UTC_TIME_ZONE).toZoneId());
    }

    public static Date getCurrenUtctDateTime() {
        LocalDateTime ldt = getNow();
        Date dt = parseDateTimeFromUtcFromat(ldt.toString());
        return dt;
    }

    public static String getUtcFromatDateTime(Date date) {
        if(date == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_UTC_FORMAT);
        return df.format(date);
    }

    public static String getUtcFromatDate(Date date) {
        if(date == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(DATE_UTC_FORMAT);
        return df.format(date);
    }

    public static Date parseDateTimeFromUtcFromat(String strDate){
        if(strDate == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_UTC_FORMAT);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseDateFromUtcFromat(String strDate){
        if(strDate == null) {
            return null;
        }
        Date dt = null;
        SimpleDateFormat df = new SimpleDateFormat(DATE_UTC_FORMAT);
        try {
            dt = df.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
        return dt;
    }

}
