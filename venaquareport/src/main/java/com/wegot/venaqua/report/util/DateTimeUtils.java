package com.wegot.venaqua.report.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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
		c.set(Calendar.HOUR_OF_DAY, 0);  
        c.set(Calendar.MINUTE, 0);  
        c.set(Calendar.SECOND, 0);  
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date subDays(Date date, int count) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -count);
		c.set(Calendar.HOUR_OF_DAY, 23);  
        c.set(Calendar.MINUTE, 59);  
        c.set(Calendar.SECOND, 59);  
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    public static Date adjustToDayStart(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 00);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND, 00);
        c.set(Calendar.MILLISECOND, 000);
        return c.getTime();
    }

    public static Date adjustToDayEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    public static boolean isWithinRange(Date date, Date from, Date to) {
        return !(date.before(from) || date.after(to));
    }

    public static Date getStartDateOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getStartDateOfWeek(cal);
    }

    public static Date getStartDateOfWeek(Calendar cal) {
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMinimum(Calendar.DAY_OF_WEEK));
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 000);
        return cal.getTime();
    }

    public static Date getEndDateOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getEndDateOfWeek(cal);
    }

    public static Date getEndDateOfWeek(Calendar cal) {
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static long findDateDiff(Date from, Date to) {
        long duration  = to.getTime() - from.getTime();
        return TimeUnit.MILLISECONDS.toDays(duration);
    }

    public static long getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
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
