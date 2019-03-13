package com.micro.utils.lang3;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author radioend
 * @version v1.0
 * @{#} DateUtils.java Create on 2015年10月5日 下午3:54:34
 * <p>
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * </p>
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public final static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm"};

    public final static String YMDHMS = "yyyyMMddHHmmss";
    /**
     * 按指定格式将日期字符串转换成日期
     *
     * @param source
     * @param pattern
     * @return
     */
    public static Date parse(String source, String pattern) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(source);
        } catch (ParseException e) {

        }
        return date;
    }

    /**
     * 获取上一个月的第一天
     *
     * @param
     * @return
     */
    public static Date getLastMonthFirstDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
        return cal.getTime();
    }

    /**
     * 获取上一个月的最后一天
     *
     * @param pattern
     * @return
     */
    public static Date getLastMonthLastDate(String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * function 获取两个时间的时间查 如1天2小时30分钟
     *
     * @param endDate
     * @param nowDate
     * @return
     * @author fjf
     * @time 2016年2月17日 上午12:49:42
     */
    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        String dateDiff = "";
        if (day > 0) {
            dateDiff = String.format("%d天%d小时%d分钟%d秒", day, hour, min, sec);
        } else {
            dateDiff = hour + "小时" + min + "分钟" + sec + "秒";
            if (hour == 0) {
                dateDiff = min + "分钟" + sec + "秒";
                if (min == 0) {
                    dateDiff = sec + "秒";
                }
            }

        }
        return dateDiff;
    }

    /**
     * function 获取两个时间的时间查 如1天2小时30分钟
     *
     * @param enterTime 进场时间
     * @param exitTime  出场时间
     * @return
     */
    public static String getStopDateString(Integer enterTime, Integer exitTime) {

        if (exitTime < enterTime) {
            return "";
        }

        long nd = 24 * 60 * 60;
        long nh = 60 * 60;
        long nm = 60;
        // 获得两个时间的毫秒时间差异
        long diff = exitTime - enterTime;
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒
        long second = diff % nd % nh % nm % nm;

        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("小时");
        }
        if (min > 0) {
            sb.append(min).append("分钟");
        }
        if (second > 0) {
            sb.append(second).append("秒");
        }
        return sb.toString();
    }

    /**
     * function 获取两个时间的时间查 如1天2小时30分钟
     *
     * @param enterDate 进场时间
     * @param exitDate  出场时间
     * @return
     */
    public static String getStopDateString(Date enterDate, Date exitDate) {
        Integer enterTime = Math.toIntExact(enterDate.getTime() / 1000);
        Integer exitTime = Math.toIntExact(exitDate.getTime() / 1000);
        return getStopDateString(enterTime, exitTime);
    }


    /**
     * function 获取两个时间的相差天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDifferDays(Date startDate, Date endDate) {
        Integer startTime = 0;
        Integer endTime = 0;
        try {
            startTime = startDate != null ? Math.toIntExact(startDate.getTime() / 1000) : 0;
            endTime = endDate != null ? Math.toIntExact(endDate.getTime() / 1000) : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getDifferDays(startTime, endTime);
    }


    /**
     * function 获取两个时间的相差天数（向上取整 eg:1天1小时=2天）
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDifferDays(Integer startDate, Integer endDate) {

        Integer sDate = startDate;
        Integer eDate = endDate;

        if (startDate >= endDate) {
            return 0;
        }
        int nd = 24 * 60 * 60;

        // 获得两个时间的毫秒时间差异
        int diff = eDate - sDate;
        // 计算差多少天
        int day = diff / nd;
        int residue = diff % nd;
        if (residue > 0) {
            day += 1;
        }
        return day;
    }


    /**
     * function 评论的时间显示
     *
     * @param datetime
     * @return
     * @author fjf
     * @time 2016年2月17日 上午12:50:35
     */
    public static String getCommentDatePoor(long datetime, String pattern) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = System.currentTimeMillis() - datetime * 1000;
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        String result = DateFormatUtils.format(new Date(datetime * 1000), pattern);
        if (day <= 0l && hour <= 0l && min <= 1l) {
            result = "刚刚";
        } else if (day <= 0l && hour <= 0l && min > 1l) {
            result = min + "分钟前";
        } else if (day <= 0l && hour > 0l) {
            result = hour + "小时前";
        } else if (day == 1l) {
            result = "昨天";
        }

        return result;
    }

    public static int getUserAge(long datetime) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 得到当前的年份
        String cYear = sdf.format(new Date()).substring(0, 4);
        // 得到生日年份
        String birthYear = sdf.format(new Date(datetime)).substring(0, 4);
        int age = Integer.parseInt(cYear) - Integer.parseInt(birthYear);
        return age;
    }

    public static Date plusDate(int num, Date newDate) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(newDate);
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        return ca.getTime();
    }

    public static Date plusMonth(int num, Date newDate) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(newDate);
        ca.add(Calendar.MONTH, num);// num为增加的月数，可以改变的
        return ca.getTime();
    }

    public static Date[] plusExpiry(int month, Date newDate) {

        Date[] dates = new Date[2];

        Date startTime = plusDate(0, newDate);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(startTime);
        // 将时分秒,毫秒域清零
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        startTime = cal1.getTime();

        Date endTime = plusDate(-1, plusMonth(month, startTime));
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endTime);
        // 将时分秒,毫秒域清零
        cal2.set(Calendar.HOUR_OF_DAY, 23);
        cal2.set(Calendar.MINUTE, 59);
        cal2.set(Calendar.SECOND, 59);
        cal2.set(Calendar.MILLISECOND, 0);
        endTime = cal2.getTime();

        dates[0] = startTime;
        dates[1] = endTime;

        return dates;

    }

    /**
     * 获取该日期 00:00:00 - 23:59:59 日期
     *
     * @param date
     * @return
     */
    public static Date[] today(Date date) {

        Date[] dates = new Date[2];

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        // 将时分秒,毫秒域清零
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        Date startTime = cal1.getTime();

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date);
        cal2.set(Calendar.HOUR_OF_DAY, 23);
        cal2.set(Calendar.MINUTE, 59);
        cal2.set(Calendar.SECOND, 59);
        cal2.set(Calendar.MILLISECOND, 0);
        Date endTime = cal2.getTime();

        dates[0] = startTime;
        dates[1] = endTime;

        return dates;

    }

    /**
     * 该日期是否是初始化时间, 空也返回是
     *
     * @param time
     * @return
     */
    public static boolean is1970(Date time) {
        if (time == null) {
            return true;
        }
        Date date1970 = new Date(0);
        return time.getTime() == date1970.getTime();
    }


    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDateTime(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return DateFormatUtils.format(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return DateFormatUtils.format(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return DateFormatUtils.format(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return DateFormatUtils.format(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return DateFormatUtils.format(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(DateFormatUtils.format(date, "yyyy-MM-dd") + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param date
     * @return
     */
    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(DateFormatUtils.format(date, "yyyy-MM-dd") + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断字符串是否是日期
     *
     * @param timeString
     * @return
     */
    public static boolean isDate(String timeString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            format.parse(timeString);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 格式化时间
     *
     * @param timestamp
     * @return
     */
    public static String dateFormat(Date timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timestamp);
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        Date time = parseDate("2018-09-22 23:50:00", "yyyy-MM-dd HH:mm:ss");
        Date ntime = addMinutes(time.getTime(), 50);
        System.out.println("ntime:" + formatDateTime(ntime));

        System.out.println(parseDate("0000-00-00 00:00:00"));

        Date s = parseDate("2018-11-24 11:16:37", "yyyy-MM-dd HH:mm:ss");
        Date e = parseDate("2018-11-24 11:17:12", "yyyy-MM-dd HH:mm:ss");

        System.out.println(getDifferDays(s, e));

    }

    //增加n天后的日期
    public static Date addDay(Long oldDate, int n) {
        oldDate += 60 * 60 * 1000 * 24;
        Calendar ca = Calendar.getInstance();
        ca.setTimeInMillis(oldDate);
        ca.add(Calendar.DATE, n);
        Date lastMonth = ca.getTime(); //结果

        return new Date(lastMonth.getTime() - 60 * 60 * 1000 * 24);
    }

    //增加n分钟的日期
    public static Date addMinutes(Long oldDate, int n) {
        oldDate += 60 * 60 * 1000 * 24;
        Calendar ca = Calendar.getInstance();
        ca.setTimeInMillis(oldDate);
        ca.add(Calendar.MINUTE, n);
        Date lastMonth = ca.getTime(); //结果

        return new Date(lastMonth.getTime() - 60 * 60 * 1000 * 24);
    }

    //获取分钟数
    public static int getMinute(Date time) {
        Calendar ca = Calendar.getInstance();
        ca.setTimeInMillis(time.getTime());
        int minute = ca.get(Calendar.MINUTE);

        return minute;
    }
}
