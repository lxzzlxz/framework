
package com.liuzemin.server.framework.model.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.IntStream;

public class DateUtils {

    public static final String LONG_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SHORT_FORMAT = "yyyy-MM-dd";
    public static final String SHORT_FORMAT_MONTH = "yyyy-MM";
    public static final String SHORT_FORMAT_YEAR = "yyyy";
    public static final String ORDER_FORM_FORMAT = "yyyyMMddHHmmss";
    public static final String ORDER_SHORT_FORMAT = "yyyyMMdd";
    public static SimpleDateFormat SIMPLE_LONG_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat SIMPLE_SHORT_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static final String DateTimeUnit_yyyy_MM_dd = "0";

    public static final String DateTimeUnit_yyyy_MM = "1";

    public static final String DateTimeUnit_yyyy = "2";
    public static final ZoneId CHINA_ZONE = ZoneId.systemDefault();


    /**
     * 格式化日期
     *
     * @param dateStr String 字符型日期
     * @param format  String 格式
     * @return Date 日期
     */
    public static Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {

        }
        return date;
    }

    public static String convert2String(Date date, String format) {
        if (date != null && StringUtils.isNotBlank(format)) {
            return new SimpleDateFormat(format).format(date);
        }
        return StringUtils.EMPTY;
    }

    public static Date format(Date date, SimpleDateFormat format) {
        Date result = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            result = format.parse(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE) + " "
                    + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @return String
     * @description 返回系统时间的字符串 (yyyy-MM-dd HH:mm:ss)
     * @date
     * @author
     */
    public static String getTime() {
        return dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @return String
     * @description 返回系统日期的字符串 (yyyy-MM-dd)
     * @date
     * @author
     */
    public static String getDate() {
        return dateToString(new Date(), "yyyy-MM-dd");
    }

    /**
     * @return String
     * @description 返回系统日期的字符串 (yyyy-MM-dd)
     * @date
     * @author
     */
    public static String getNowMonth() {
        return dateToString(new Date(), SHORT_FORMAT_MONTH);
    }


    /**
     * @param pattern pattern -格式 <br>
     *                Date date -日期对象
     * @return String -日期字符串
     * @description 将日期对象date转化成格式pattern的日期字符串
     * @date
     * @author
     */
    public static String dateToString(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * @param date date
     * @return String
     * @description 返回指定时间的字符串 (yyyy-MM-dd HH:mm:ss)
     * @date
     * @author
     */
    public static String timeToString(Date date) {
        return dateToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @param date 日期对象
     * @return String 日期的字符串
     * @description 返回指定日期的字符串 (yyyy-MM-dd)
     * @date
     * @author
     */
    public static String dateToString(Date date) {
        return dateToString(date, "yyyy-MM-dd");
    }

    /**
     * @param dateStr dateStr -日期字符串 <br>
     *                String pattern -转化格式
     * @return Date -转化成功返回该格式的日期对象,失败返回null
     * @description -按格式pattern将字符串dateStr转化为日期
     * @date
     * @author
     */
    public static Date stringToDate(String dateStr, String pattern) {
        Date date = null;
        try {
            date = new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param timeStr -日期字符串
     * @return Date
     * @description -将日期字符串timeStr转化为日期对象 (yyyy-MM-dd HH:mm:ss)
     * @date
     * @author
     */
    public static Date stringToTime(String timeStr) {
        return stringToDate(timeStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @param dateStr -日期字符串
     * @return Date
     * @description -将日期字符串dateStr转化为日期对象 (yyyy-MM-dd)
     * @date
     * @author
     */
    public static Date stringToDate(String dateStr) {
        return stringToDate(dateStr, "yyyy-MM-dd");
    }

    /**
     * @param dateString -日期字符串 <br>
     *                   Stirng pattern -格式
     * @return String
     * @description -将日期字符按pattern串格式化
     * @date
     * @author
     */
    public static String format(String dateString, String pattern) {
        String result = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            result = dateFormat.format(dateFormat.parse(dateString));
        } catch (ParseException e) {
            return getSysTime(pattern);
        }
        return result;
    }


    public static String getPcsDate() {
        String orderFormFormat = DateUtils.ORDER_FORM_FORMAT;
        String date = DateUtils.convert2String(new Date(), orderFormFormat);
        return date;
    }

    /**
     * 两个时间之间相差距离多少天
     *
     * @param begin 开始时间 1：
     * @param end   结束时间 2：
     * @return 相差天数，
     */
    public static Double getDistanceDays(Date begin, Date end) {
        double days;
        long time1 = begin.getTime();
        long time2 = end.getTime();
        long diff;
        if (time1 > time2) {
            diff = 0;
        } else {
            diff = time2 - time1;
        }
        days = (double) diff / (1000 * 60 * 60 * 24);
        return days;
    }

    public static String getDistanceHours(Date begin, Date end) {
        String descript = "";
        long time = end.getTime() - begin.getTime();
        long days = time / (1000 * 60 * 60 * 24);
        long hours = (time - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        if (days > 0) {
            descript = descript + (days + "天");
        }
        if (hours > 0) {
            descript = descript + (hours + "小时");
        }
        return descript;
    }

    /**
     * @param number 需要获取几年后时间
     * @return
     */
    public static Date getYearTime(Integer number, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, number);
        Date time = calendar.getTime();
        return time;
    }

    /**
     * @param dateString
     * @return String
     * @description-将日期字符按"yyyy-MM-dd"串格式化
     * @author
     * @date :
     */
    public static String format(String dateString) {
        try {
            return format(dateString, "yyyy-MM-dd");
        } catch (Exception e) {
            return getSysTime("yyyy-MM-dd");
        }
    }

    /**
     * 取得系统时间
     *
     * @param pattern 按指定格式输出如'yyyy-MM-dd'
     * @return
     */
    public static String getSysTime(String pattern) {
        if (pattern == null || "".equals(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        Properties p = System.getProperties();
        p.setProperty("user.timezone", "GMT+8");
        String tt = new SimpleDateFormat(pattern).format(new Date());
        return tt;
    }


    /**
     * @return 未来7天的开始时间
     */
    public static String getSevenDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 7);
        String day = format.format(c.getTime()) + " 00:00:00";
        return day;
    }


    /**
     * @return 未来10天的开始时间
     */
    public static String getTenDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 10);
        String day = format.format(c.getTime()) + " 00:00:00";
        return day;
    }

    /**
     * @return 过去3天的开始时间
     */
    public static String getLast3Days() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -2);
        String day = format.format(c.getTime()) + " 00:00:00";
        return day;
    }

    /**
     * @return 过去7天的开始时间
     */
    public static String getLastWeek() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -6);
        String day = format.format(c.getTime()) + " 00:00:00";
        return day;
    }

    /**
     * @return 过去一个月的开始时间
     */
    public static String getLastMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        String day = format.format(c.getTime()) + " 00:00:00";
        return day;

    }

    /**
     * @return 过去6个月的开始时间
     */
    public static String getLastSixMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -6);
        String day = format.format(c.getTime());
        return day;

    }

    /**
     * @return 过去一个年的开始时间
     */
    public static String getLastYear() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        String year = format.format(c.getTime()) + " 00:00:00";
        return year;

    }

    public static List<String> getEveryDateUnit(String sdateTimeParam, String edateTimeParam, String dateTimeUnit) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        int unit = 0;
        switch (dateTimeUnit) {
            case DateTimeUnit_yyyy_MM_dd:
                sdf.applyPattern("yyyy-MM-dd");
                unit = Calendar.DAY_OF_MONTH;
                break;// 日期范围是日
            case DateTimeUnit_yyyy_MM:
                sdf.applyPattern("yyyy-MM");
                unit = Calendar.MONTH;
                break;// 日期范围是月
            case DateTimeUnit_yyyy:
                sdf.applyPattern("yyyy");
                unit = Calendar.YEAR;
                break;// 日期范围是年
            default:
                throw new RuntimeException("没有传入符合标准的时间单位 0日 1月 2年");
        }
        List<String> result = new ArrayList<String>();// 一个存放结果的集合
        try {
            Date startDate = sdf.parse(sdateTimeParam);
            Date endDate = sdf.parse(edateTimeParam);
            List<Date> dateList = getEveryDateUnit(startDate, endDate, unit);
            dateList.forEach(d -> {
                result.add(sdf.format(d));
            });
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * 遍历，并获得从开始到结束的每一个时间点
     *
     * @param startDate    开始日期
     * @param endDate      结束日期
     * @param dateTimeUnit {@link Calendar} 使用Calendar类中的属性，目前支持传入年月日的遍历
     * @return
     */
    public static List<Date> getEveryDateUnit(Date startDate, Date endDate, int dateTimeUnit) {
        startDate = getActualMinimum(startDate, getZeroFidlds(dateTimeUnit));
        endDate = getActualMinimum(endDate, getZeroFidlds(dateTimeUnit));
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        List<Date> dateList = new ArrayList<>();
        /*while (start.compareTo(end) <= 0) {
            dateList.add(start.getTime());
            start.add(dateTimeUnit, 1);
        }*/
        //降序排列
        while (start.compareTo(end) <= 0) {
            dateList.add(end.getTime());
            //start.add(dateTimeUnit, 1);
            end.add(dateTimeUnit, -1);
        }
        return dateList;
    }

    public static Date getActualMinimum(Date date, int... zeroFields) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setActualMinimum(calendar, Calendar.YEAR, zeroFields);
        setActualMinimum(calendar, Calendar.MONTH, zeroFields);
        setActualMinimum(calendar, Calendar.DAY_OF_MONTH, zeroFields);
        setActualMinimum(calendar, Calendar.HOUR_OF_DAY, zeroFields);
        setActualMinimum(calendar, Calendar.MINUTE, zeroFields);
        setActualMinimum(calendar, Calendar.SECOND, zeroFields);
        setActualMinimum(calendar, Calendar.MILLISECOND, zeroFields);
        Date date2 = calendar.getTime();
        return date2;
    }

    private static void setActualMinimum(Calendar calendar, int dateTimeUnit, int... zeroFields) {
        if (IntStream.of(zeroFields).anyMatch(x -> x == dateTimeUnit)) {
            calendar.set(dateTimeUnit, calendar.getActualMinimum(dateTimeUnit));
        } else if (calendar.get(dateTimeUnit) == 0) {
            calendar.set(dateTimeUnit, calendar.getActualMinimum(dateTimeUnit));
        }
    }

    private static int[] getZeroFidlds(int dateTimeUnit) {
        switch (dateTimeUnit) {
            case Calendar.YEAR:
                return DateFormatterPattrenEnum.yyyy.getZeroFields();
            case Calendar.MONTH:
                return DateFormatterPattrenEnum.yyyyMM.getZeroFields();
            case Calendar.DAY_OF_MONTH:
                return DateFormatterPattrenEnum.yyyyMMdd.getZeroFields();
            default:
                return null;
        }
    }

    /**
     * 将毫秒数转换为日期格式
     *
     * @param dateTime 毫秒数
     * @return 日期格式
     */
    public static String long2Str(Long dateTime) {
        Date date = new Date(dateTime);
        return date2StrByPattern(date, LONG_FORMAT);
    }


    /**
     * 获取近n 天时间列表
     *
     * @param days 毫秒数
     * @return 日期格式
     */
    public static List<String> getDateList(int days) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String date = fmt.format(today);
        String maxDateStr = date;
        String minDateStr = "";
        Calendar calc = Calendar.getInstance();
        List<String> dateforDaysList = new ArrayList<String>();
        try {
            for (int i = days; i > 0; i--) {
                calc.setTime(fmt.parse(maxDateStr));
                calc.add(calc.DATE, -i);
                Date minDate = calc.getTime();
                minDateStr = fmt.format(minDate);
                dateforDaysList.add(minDateStr);

            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        System.out.println(dateforDaysList);
        return dateforDaysList;
    }


    public static void main(String[] args) throws ParseException {
        Date date = DateUtils.stringToDate("20210625", DateUtils.ORDER_SHORT_FORMAT);
        System.out.println(date);
    }

    /**
     * 格式化日期
     *
     * @param dateTime 日期
     */
    public static String date2StrByPattern(Date dateTime, String pattern) {
        Instant instantDate = dateTime.toInstant();
        LocalDateTime localDate = instantDate.atZone(CHINA_ZONE).toLocalDateTime();
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化日期
     */
    public static Date oneYearDate() {
        Date date = new Date();//取时间
        System.out.println(date.toString());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.YEAR, 1);//把日期往后增加一年.整数往后推,负数往前移动
//        calendar.add(calendar.DAY_OF_MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动
//        calendar.add(calendar.DATE, 1);//把日期往后增加一天.整数往后推,负数往前移动
//        calendar.add(calendar.WEEK_OF_MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        System.out.println(date.toString());
        return date;

    }

    public static String getLongStringDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return year + "年" + month + "月" + day + "日" + hour + "时" + minute + "分" + second + "秒";

    }


    public static Date addDays(int days) {
        Date date = new Date();//取时间
        System.out.println(date.toString());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, days);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        System.out.println(date.toString());
        return date;

    }

    public static Date addDaysTo(Date now, int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(calendar.DATE, days);//把日期往后增加一天.整数往后推,负数往前移动
        Date date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }


    public static Date addHoursTo(Date now, int hours) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(calendar.HOUR, hours);//把日期往后增加一天.整数往后推,负数往前移动
        Date date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }

    public static Date addSecondsTo(Date now, int seconds) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(calendar.SECOND, seconds);//把日期往后增加一妙.整数往后推,负数往前移动
        Date date = calendar.getTime();   //这个时间就是日期往后推一妙的结果
        return date;

    }

    /**
     * 判断日期是否是当月
     *
     * @param givenDate
     * @return
     */
    public static boolean inCurrentMonth(Date givenDate) {
        Date today = new Date();
        return givenDate.getMonth() == today.getMonth() && givenDate.getYear() == today.getYear();

    }
}
