package com.example.mqttfactorydemo.util;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateUtil {

    private DateUtil() {
    }
    /**
     * 日期格式 年 如2009
     */
    public static final String DATE_FORMAT_YEAR = "yyyy";

    /**
     * 日期格式 年 月  如 2009-02
     */
    public static final String DATE_FORMAT_MONTH = "yyyy-MM";

    /**
     * 日期格式 年 月 日 如2009-02-26
     */
    public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";

    /**
     * 日期格式年 月 日 时 分 秒 如 20090226154000
     */
    public static final String DATE_FORMAT_SECOND_COMPRESS = "yyyyMMddHHmmss";

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final Object LOCK_OBJ = new Object();
    private static Map<String, ThreadLocal<SimpleDateFormat>> simpleDateFormatMap = new HashMap();

    public static LocalDateTime getCurrentLocalDateTime(){
        return LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
    }

    public static Date fromLocalDateTime(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date plusSeconds(long senconds){
        LocalDateTime localDateTime = getCurrentLocalDateTime();
        localDateTime = localDateTime.plusSeconds(senconds);
        return fromLocalDateTime(localDateTime);
    }

    /**
     * 获得当前时间的零点时间
     *
     * @param date
     * @return
     */
    public static LocalDateTime getDayStart(LocalDateTime date) {
        return date.withNano(0).withSecond(0).withMinute(0).withHour(0);
    }

    /**
     * 得到当前时间周的第一天，从周一开始
     *
     * @return
     */
    public static LocalDateTime getWeekStart(LocalDateTime date) {
        TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
        return date.with(fieldISO, 1).toLocalDate().atStartOfDay().plusDays(1);

    }

    /**
     * 得到当前周的最后一天，周日为结束
     *
     * @return
     */
    public static LocalDateTime getWeekEnd(LocalDateTime date) {
        TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
        return date.with(fieldISO, 7).toLocalDate().atStartOfDay().with(LocalTime.MAX).plusDays(1);

    }

    /**
     * 得到该日期是本年度的第几周
     *
     * @return
     */
    public static int getWeekNumberOfYear(LocalDateTime date) {
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return date.get(woy);
    }

    /**
     * 得到当前月的第一天
     *
     * @return
     */
    public static LocalDateTime getMonthStart(LocalDateTime date) {
        return LocalDate.of(date.getYear(), date.getMonth(), 1).atStartOfDay().with(LocalTime.MIN);

    }

    /**
     * 得到当前月的最后一天
     *
     * @return
     */
    public static LocalDateTime getMonthEnd(LocalDateTime date) {
        return date.with(TemporalAdjusters.lastDayOfMonth());

    }

    /**
     * 得到当前季度的第一天
     *
     * @return
     */
    public static LocalDateTime getQuarterStart(LocalDateTime date) {
        Month monthType = Month.JANUARY;
        switch (getQuarterNumberOfYear(date)) {
            case 1:
                monthType = Month.JANUARY;
                break;
            case 2:
                monthType = Month.APRIL;
                break;
            case 3:
                monthType = Month.JULY;
                break;
            case 4:
                monthType = Month.OCTOBER;
                break;
            default:
                break;
        }
        return LocalDate.of(date.getYear(), monthType, 1).atStartOfDay().with(LocalTime.MIN);
    }

    /**
     * 得到当前季度的最后一天
     *
     * @return
     */
    public static LocalDateTime getQuarterEnd(LocalDateTime date) {
        return getQuarterStart(date).plusMonths(2).with(TemporalAdjusters.lastDayOfMonth());

    }

    /**
     * 得到该日期是本年度的第几个季度
     * @param date
     * @return
     */
    public static int getQuarterNumberOfYear(LocalDateTime date) {
        int month = date.getMonth().getValue();
        int quarterNumber = 0;
        if (month >= 1 && month <= 3) {
            quarterNumber = 1;
        } else if (month >= 4 && month <= 6) {
            quarterNumber = 2;
        } else if (month >= 7 && month <= 9) {
            quarterNumber = 3;
        } else {
            quarterNumber = 4;
        }
        return quarterNumber;
    }

    /**
     * 得到本年度的第一天
     *
     * @return
     */
    public static LocalDateTime getYearStart(LocalDateTime date) {
        return LocalDate.of(date.getYear(), Month.JANUARY, 1).atStartOfDay().with(LocalTime.MIN);
    }

    /**
     * 得到本年度最后一天
     *
     * @return
     */
    public static LocalDateTime getYearEnd(LocalDateTime date) {
        return LocalDate.of(date.getYear(), Month.DECEMBER, 31).atStartOfDay().with(LocalTime.MAX);
    }

    /**
     * 将date转换成localdatetime
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static SimpleDateFormat getSimpleDateFormat(final String pattern) {
        ThreadLocal<SimpleDateFormat> threadLocalSimpleDateFormat = (ThreadLocal)simpleDateFormatMap.get(pattern);
        if (threadLocalSimpleDateFormat == null) {
            synchronized(LOCK_OBJ) {
                threadLocalSimpleDateFormat = (ThreadLocal)simpleDateFormatMap.get(pattern);
                if (threadLocalSimpleDateFormat == null) {
                    threadLocalSimpleDateFormat = new ThreadLocal<SimpleDateFormat>() {
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    simpleDateFormatMap.put(pattern, threadLocalSimpleDateFormat);
                }
            }
        }

        return (SimpleDateFormat)threadLocalSimpleDateFormat.get();
    }

    public static Date strToDate(String dateStr, String pattern) {
        try {
            if (dateStr != null && dateStr.length() != 0) {
                if (pattern == null) {
                    pattern = "yyyy-MM-dd";
                }

                SimpleDateFormat simpleDateFormat = getSimpleDateFormat(pattern);
                return simpleDateFormat.parse(dateStr);
            } else {
                return null;
            }
        } catch (Exception var3) {
            return null;
        }
    }

    public static Date strToDateTime(String dateStr) {
        try {
            return strToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        } catch (Exception var2) {
            return null;
        }
    }

}
