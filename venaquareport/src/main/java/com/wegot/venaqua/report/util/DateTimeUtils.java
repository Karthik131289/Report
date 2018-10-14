package com.wegot.venaqua.report.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtils {
    public static Date LocalToUTC(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date utc = new Date(sdf.format(date));
        return utc;
    }

    public static Date UTCToLocal(Date date) {
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        Date local = new Date(date.getTime() + TimeZone.getTimeZone(timeZone).getOffset(date.getTime()));
        return local;
    }

    public static Date addDays(Date date, int count) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, count);
        return c.getTime();
    }

    public static Date subDays(Date date, int count) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -count);
        return c.getTime();
    }

    public static void main(String[] args) {
        Date date = new Date(2018, 9, 1, 6, 00, 00);
        System.out.println(LocalToUTC(date).getTime());
        date = new Date(2018, 9, 1, 7, 00, 00);
        System.out.println(LocalToUTC(date).getTime());
        System.out.println();

        date = new Date(2018, 9, 1, 8, 00, 00);
        System.out.println(LocalToUTC(date).getTime());
        date = new Date(2018, 9, 1, 9, 00, 00);
        System.out.println(LocalToUTC(date).getTime());
        System.out.println();

        date = new Date(2018, 9, 1, 9, 00, 00);
        System.out.println(LocalToUTC(date).getTime());
        date = new Date(2018, 9, 1, 9, 00, 00);
        System.out.println(LocalToUTC(date).getTime());
        System.out.println();

        Date dateutc = new Date(LocalToUTC(date).getTime());
        System.out.println(UTCToLocal(dateutc));
    }
}
