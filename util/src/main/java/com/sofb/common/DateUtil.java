package com.sofb.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger("DateUtil");

    public static final String DATE_FORMAT_FOR_YMD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_FOR_YMDHM = "yyyy-MM-dd hh:mm";

    public static final String DATE_FORMAT_FOR_YMDH = "yyyy-MM-dd hh";

    public static final int WEEKS = 7;

    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public final static String TIME_PATTERN = "yyyy-MM-dd hh:mm:ss";
    public final static String TIME_PATTERN_24 = "yyyy-MM-dd HH:mm:ss";
    public final static String TIME_PATTERN_24_YMDHM = "yyyy年MM月dd日 HH:mm";
    public final static String TIME_PATTERN_CN = "yyyy年MM月dd日";

    public final static String TIME_NUMBER_PATTERN = "yyyyMMddhhmmss";
    public final static String TIME_NUMBER_PATTERN_YMD = "yyyyMMdd";

    public final static String TIME_PATTERN_SIMPLE_CN = "MM月dd日 HH:mm";


    protected static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static String format(Date date) {

        DateFormat df = new SimpleDateFormat(TIME_PATTERN_24);
        return df.format(date);
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String formatYMD(Date date) {
        if (date == null)
            return "";

        return format.format(date);
    }

    public static Date strToDate(String s, String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = df.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException("转换成" + format + "日期格式，异常");
        }
        return d;
    }

    public static Date strToDate(String s, String format, Date defaultDate) {
        DateFormat df = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = df.parse(s);
        } catch (ParseException e) {
            return defaultDate;
        }
        return d;
    }


    /**
     * 计算两个日期之间相差的月数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getMonths(Date date1, Date date2) {
        int iMonth = 0;
        int flag = 0;
        try {
            Calendar objCalendarDate1 = Calendar.getInstance();
            objCalendarDate1.setTime(date1);

            Calendar objCalendarDate2 = Calendar.getInstance();
            objCalendarDate2.setTime(date2);

            if (objCalendarDate2.equals(objCalendarDate1))
                return 0;
            if (objCalendarDate1.after(objCalendarDate2)) {
                Calendar temp = objCalendarDate1;
                objCalendarDate1 = objCalendarDate2;
                objCalendarDate2 = temp;
            }
            if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))
                flag = 1;

            if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
                iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))
                        * 12 + objCalendarDate2.get(Calendar.MONTH) - flag)
                        - objCalendarDate1.get(Calendar.MONTH);
            else
                iMonth = objCalendarDate2.get(Calendar.MONTH)
                        - objCalendarDate1.get(Calendar.MONTH) - flag;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return iMonth;
    }

    /**
     * 计算日期 added by taking.wang
     *
     * @param date  日期
     * @param month 月份
     * @return
     */
    public static Date calcDate(Date date, int month) throws Exception {
        if (null != date) { // 先判断当前日期和试用期是否都有数据

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            int vYear = cal.get(Calendar.YEAR); // 得到年
            int vMonth = cal.get(Calendar.MONTH) + 1; // 得到月
            int vDay = cal.get(Calendar.DATE); // 得到日

            vMonth += month;
            if (vMonth > 12) { // 如果日期加起来大于12的话，则年份要加1
                vYear++;
                vMonth -= 12;
            }

            return strToDate(vYear + "-" + ((String.valueOf(vMonth).length() == 1) ? "0" + vMonth : vMonth) + "-" + vDay, "yyyy-MM-dd");
        }
        return null;
    }


    /**
     * 计算7个工作日后日期
     *
     * @param date
     * @return
     */
    public static Date get7WorkDays(Date date) {
        return getNWorkDays(date, 7);
    }

    /**
     * 获取N个工作日以后的日期
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getNWorkDays(Date date, int n) {
        int week = n / 5;
        int day = n % 5;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;// 获取这个星期的第几天
        int plusDay = 0;
        if (day_of_week != 0) {// 周日
            int tmp = day_of_week >> 2;// 除以4
            if (tmp == 0) {// 周一到周三，周数乘以7+剩余天数
                plusDay = week * WEEKS + day;
            } else {// 周四到周六，周数乘以7+剩余天数+当周的周六周日两天
                plusDay = week * WEEKS + day + 2;
            }
        } else {
            plusDay = week * WEEKS + day + 1;// 跳过周日本身
        }
        c.add(Calendar.DAY_OF_MONTH, plusDay);

        return c.getTime();
    }


    /**
     * @param date
     * @param n
     * @return
     */
    public static Date getNWorkDaysAgo(Date date, int n) {
        int week = n / 5;// 周数
        int day = n % 5;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;// 获取这个星期的第几天
        int substactDay = WEEKS * week;
        if (day_of_week > 0 && day_of_week <= 2) {// 周一周二,再去除周六日
            substactDay += day + 2;
        } else if (day_of_week == 0 || day_of_week == 6) {
            substactDay += day + 1;
        } else {
            substactDay += day;
        }
        // 减去相应的时间
        c.add(Calendar.DAY_OF_MONTH, -substactDay);
        return c.getTime();
    }

    /**
     * 获取7个工作日以前的日期
     *
     * @param date
     * @return
     */
    public static Date get7WorkDaysAgo(Date date) {
        return getNWorkDaysAgo(date, 7);
    }

    public static Date parseToCommonDate(String str) throws Exception {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        return format.parse(str);
    }

    /**
     * 求两个时间区间的交集天数
     *
     * @param from_1      区间1开始日期
     * @param to_1        区间1结束日期
     * @param from_2      区间2开始日期
     * @param to_2        区间2结束日期
     * @param withWeekend 是否包括周末
     * @return
     */
    public static int getDateAreaMix(Date from_1, Date to_1, Date from_2, Date to_2, boolean withWeekend) {
        Calendar start = Calendar.getInstance();
        if (from_1.before(from_2)) {
            start.setTime(from_2);
        } else {
            start.setTime(from_1);
        }
        Calendar end = Calendar.getInstance();
        if (to_1.before(to_2)) {
            end.setTime(to_1);
        } else {
            end.setTime(to_2);
        }
        if (end.before(start)) {
            return 0;
        }
        int count = 0;
        while (end.after(start) || end.equals(start)) {
            if (withWeekend) {
                count++;
            } else {
                if (start.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    count++;
                }
            }
            start.add(Calendar.DATE, 1);
        }
        return count;
    }

    /**
     * 得到date-day
     *
     * @param date
     * @param day
     * @return
     */
    public static Date getDateAfterDay(Date date, int day) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date.getTime()));
        c.add(Calendar.DAY_OF_YEAR, day);
        return c.getTime();
    }

    /**
     * 得到一天开始时间
     *
     * @param date
     * @return
     */
    public static Date getStartDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date.getTime()));
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getEndDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date.getTime()));
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 23, 59, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 得到一天的开始
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date getStartDate(String dateStr, String format) {
        if (dateStr == null) {
            return null;
        }
        Date date = null;
        try {
            date = getStartDate(strToDate(dateStr, format));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return date;
    }

    /**
     * 得到一天的结束
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date getEndDate(String dateStr, String format) {
        if (dateStr == null) {
            return null;
        }
        Date date = null;
        try {
            date = getEndDate(strToDate(dateStr, format));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return date;
    }

    /**
     * 获取某月的最后一天
     *
     * @throws
     * @Title:getLastDayOfMonth
     * @Description:
     * @param:@param year
     * @param:@param month
     * @param:@return
     * @return:String
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    public static String getCurrentDateStr() {
        return DateUtil.format(Calendar.getInstance().getTime(), TIME_PATTERN_24);
    }

    public static String getCurrentDateStr(String format) {
        return DateUtil.format(Calendar.getInstance().getTime(), format);
    }

    //获取两个date间的相差
    public static long getDateGap(Date d1, Date d2) {
        return Math.abs(d1.getTime() - d2.getTime());
    }

    //返回相差的表示，用天、时、分、秒
    public static long[] getGapShow(long diff) {
        long[] l = new long[4];
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (60 * 1000)) / 1000;
        l[0] = days;
        l[1] = hours;
        l[2] = minutes;
        l[3] = seconds;
        return l;
    }

    /**
     * @param obj 秒数
     * @return * 小时 * 分 * 秒
     * @MethodName convertSecond
     * @Description 秒数转换，转换成 * 小时 * 分 * 秒
     * @author zhangliancai
     * @date 2015年12月22日 上午10:03:28
     */
    public static String convertSecond(Object obj) {
        if (!NumberUtil.isNumber(obj)) {
            return "";
        }
        int time = NumberUtil.toInteger(obj, 0);

        if (time < 60) {
            return time + "秒";
        }

        int second = time % 60;
        int minute = time / 60;
        if (minute < 60) {
            return minute + "分" + second + "秒";
        }

        int hour = minute / 60;
        minute = minute % 60;
        return hour + "小时" + minute + "分" + second + "秒";
    }

    public static final int daysBetween(Date early, Date late) {

        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }

    /**
     * 比较两个时间的分钟数
     *
     * @param start
     * @param end
     * @return
     */
    public static int getMinuteDiff(Date start, Date end) {

        return Long.valueOf((end.getTime() - start.getTime()) / (60 * 1000)).intValue();
    }

    /**
     * 比较两个时间的秒数
     *
     * @param start
     * @param end
     * @return
     */
    public static long getSecondDiff(Date start, Date end) {
        if (start == null && end == null) {
            return Long.valueOf(0);
        }
        if (end == null) {
            return -Long.valueOf(start.getTime() / 1000);
        }
        if (start == null) {
            return Long.valueOf(end.getTime() / 1000);
        }


        return Long.valueOf((end.getTime() - start.getTime()) / 1000);
    }

    /**
     * 获得当天剩下的秒数
     **/
    public static int getTodayLeftSeconds() {
        Calendar now = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return Long.valueOf(
                (calendar.getTime().getTime() - now.getTime().getTime()) / 1000)
                .intValue();
    }

    public static Date comparisonDate(Date date1, Date date2) {
        if (date1 == null) return date2;
        if (date2 == null) return date1;
        return date2.getTime() - date1.getTime() >= 0 ? date2 : date1;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {

        if (null == dt) return "";

        String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static int getHourOfDate(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static Date getStartDateByHourOfDate(Date date, int hour) {
        if (date == null || hour < 0) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Date getStartDateByHourOfDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Date getEndDateByHourOfDate(Date date, int hour) {
        if (date == null || hour < 0) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTime();
    }

    public static Date getEndDateByHourOfDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTime();
    }

    /**
     * @param date
     * @param preHours
     * @return
     * @MethodName preHours
     * @Description 获取preHours小时前的时间
     * @author zhangliancai
     * @date 2017年5月17日 下午3:49:58
     */
    public static Date preHours(Date date, int preHours) {
        if (date == null) {
            return null;
        }
        GregorianCalendar c1 = new GregorianCalendar();
        c1.setTime(date);
        GregorianCalendar cloneCalendar = (GregorianCalendar) c1.clone();
        cloneCalendar.add(Calendar.HOUR, -preHours);
        return cloneCalendar.getTime();
    }

    /**
     * @param date
     * @param preMinutes
     * @return
     * @MethodName preHours
     * @Description 获取preHours小时前的时间
     * @author zhangliancai
     * @date 2017年5月17日 下午3:49:58
     */
    public static Date preMinutes(Date date, int preMinutes) {
        if (date == null) {
            return null;
        }
        GregorianCalendar c1 = new GregorianCalendar();
        c1.setTime(date);
        GregorianCalendar cloneCalendar = (GregorianCalendar) c1.clone();
        cloneCalendar.add(Calendar.MINUTE, -preMinutes);
        return cloneCalendar.getTime();
    }

    public static Date getYesterday(String format,Date date) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        String dates = dateFormat.format(date);
        try {
            date = dateFormat.parse(dates);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        Calendar begin = Calendar.getInstance();
        begin.setTime(date);
        begin.add(Calendar.DAY_OF_MONTH, -1);
        return begin.getTime();
    }

    public static Date getDate(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        String dates = dateFormat.format(date);
        try {
            return dateFormat.parse(dates);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(DateUtil.format(DateUtil.getDateAfterDay(new Date(), -90), "yyyy-MM-dd HH:mm:ss"));

        System.out.println(DateUtil.format(DateUtil.getDateAfterDay(new Date(), -2), "yyyy-MM-dd HH:mm:ss"));

        System.out.println(Math.abs(-10));
        System.out.println(Math.abs(10));

        System.out.println(getWeekOfDate(getCurrentDate()));
    }
}
