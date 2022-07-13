package cn.golaxy.zstp.projecttemplatejava.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：yaojianlin(yaojianlin@golaxy.cn)
 * @date ：Created in 18:56 2021/7/19
 * @description： 日期工具类
 */
public class DateUtils {

    /**
     * 获取当前时间的秒级时间戳
     *
     * @return 返回结果
     */
    public static long getTimestamp10() {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return Integer.valueOf(timestamp);
    }

    /**
     * 获取当前时间的 毫秒级时间戳
     *
     * @return 返回结果
     */
    public static long getTimestamp13() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     *
     * @return 返回date类型的时间
     */
    public static Date getDate() {
        return new Date();
    }

    /**
     * 根据日期格式获取日期字符串
     *
     * @param pattern 日期格式，如：yyyy-MM-dd HH:mm:SS
     * @return 返回日期字符串
     */
    public static String getStrDateByPattern(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 日期字符串相互转换
     *
     * @param strDate    日期字符串
     * @param oldPattern 日期字符串的格式
     * @param newPattern 需要转换的格式
     * @return 返回结果
     */
    public static String dateStringToDateStringByPattern(String strDate, String oldPattern, String newPattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(oldPattern);
        return DateFormatUtils.format(dateFormat.parse(strDate), newPattern);
    }

    /**
     * 获取指定日期，类型 增加或减少数量
     *
     * @param date   指定日期
     * @param cal    模式：按天增加为5
     * @param amount 新增的天数
     * @return 返回结果
     */
    public static Date getDateByDateAndAddDays(Date date, int cal, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(cal, amount);
        return calendar.getTime();
    }

    /**
     * 根据日期格式 生成 日期 路径.
     *
     * @param pattern 日期格式
     * @return 返回值
     */
    public static String createPath(String pattern) {
        return DateFormatUtils.format(getDate(), pattern);
    }

    /**
     * 根据指定日期，类型 增加或减少数量
     *
     * @param date   date
     * @param cal    cal
     * @param amount amount
     * @return 返回值
     */
    public static Date add(Date date, int cal, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(cal, amount);
        return calendar.getTime();
    }

    /**
     * 获取两个日期之间所有日期的列表
     *
     * @param startYearMonthDay 开始日期
     * @param endYearMonthDay   结束日期
     * @return 返回集合
     */
    public static void getDayMiddle(String startYearMonthDay, String endYearMonthDay, List<String> list) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        list.add(startYearMonthDay);
        while (true) {
            // 获取下一天
            try {
                Date end = DateUtils.add(sdf.parse(startYearMonthDay), Calendar.DATE, 1);
                String endDay = sdf.format(end);
                list.add(endDay);
                if (endDay.compareTo(endYearMonthDay) >= 0) {
                    break;
                }
                startYearMonthDay = endDay;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取两个月份之间所有月份的列表
     *
     * @param startYearMonth 开始月份
     * @param endYearMonth   结束月份
     * @return 返回集合
     */
    public static void getMonthMiddle(String startYearMonth, String endYearMonth, List<String> list) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        list.add(startYearMonth);
        while (true) {
            // 获取下一月
            try {
                Date end = DateUtils.add(sdf.parse(startYearMonth), Calendar.MONTH, 1);
                String endMonth = sdf.format(end);
                list.add(endMonth);
                if (endMonth.compareTo(endYearMonth) >= 0) {
                    break;
                }
                startYearMonth = endMonth;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取两个年份之间所有年份的列表
     *
     * @param startYear 开始年份
     * @param endYear   结束年份
     * @return 返回集合
     */
    public static void getYearMiddle(String startYear, String endYear, List<String> list) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        list.add(startYear);
        int ks = Integer.parseInt(startYear);
        int js = Integer.parseInt(endYear);
        while (ks < js) {
            ks = ks + 1;
            list.add(ks + "");
        }
    }

    /**
     * 获取两个季度之间所有季度的列表
     *
     * @param startJd 开始季度
     * @param endJd   结束季度
     * @return 返回集合
     */
    public static void getJdMiddle(String startJd, String endJd, List<String> list) {
        //拆分开始季度的年和季度
        String[] start = startJd.split("-");
        String[] end = endJd.split("-");
        int startYear = Integer.parseInt(start[0]);
        int startJdTemp = Integer.parseInt(start[1]);
        int endYear = Integer.parseInt(end[0]);
        int endJdTemp = Integer.parseInt(end[1]);
        // 循环查询
        while (startYear <= endYear) {
            list.add(startYear + "-" + startJdTemp);
            if (startYear == endYear && startJdTemp >= endJdTemp) {
                break;
            } else {
                if (startJdTemp == 4) {
                    startJdTemp = 1;
                    startYear += 1;
                } else {
                    startJdTemp++;
                }
            }
        }
    }

    /**
     * 根据 年、月 获取对应的月份 的 天数
     *
     * @param year  年
     * @param month 月
     * @return 返回天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 根据年份获取天数
     *
     * @param year 年
     * @return 返回天数
     */
    public static int getDaysByYear(int year) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        return a.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    /**
     * 根据年 季度 获取天数
     *
     * @param year 年
     * @param jd   季度
     * @return 天数
     */
    public static int getDaysJd(int year, int jd) {
        List<Integer> listMonth = new ArrayList<>();
        if (jd == 1) {
            listMonth.add(1);
            listMonth.add(2);
            listMonth.add(3);
        } else if (jd == 2) {
            listMonth.add(4);
            listMonth.add(5);
            listMonth.add(6);
        } else if (jd == 3) {
            listMonth.add(7);
            listMonth.add(8);
            listMonth.add(9);
        } else if (jd == 4) {
            listMonth.add(10);
            listMonth.add(11);
            listMonth.add(12);
        }
        int maxDays = 0;
        for (Integer i : listMonth) {
            maxDays = maxDays + getDaysByYearMonth(year, i);
        }
        return maxDays;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param startRq 开始日期
     * @param endRq   结束日期
     * @return 天数
     */
    public static long getDaysByBetweenRq(String startRq, String endRq) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long days = 0;
        try {
            Date startDate = sdf.parse(startRq);
            Date endDate = sdf.parse(endRq);
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(endDate);
            long time2 = cal.getTimeInMillis();
            days = (time2 - time1) / (1000 * 3600 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 获取今天和昨天列表，第一个值为今天，第二个值为昨天
     *
     * @return 返回值
     */
    public static List<String> getDayOrYesterday() {
        List<String> list = new ArrayList<>(2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        // 当前日期
        list.add(0, DateFormatUtils.format(date, "yyyy-MM-dd"));
        // 前一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        String yesterday = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd");
        list.add(1, yesterday);
        return list;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        getMonthMiddle("2021-01", "2021-08", list);
        System.out.println(list.toString());
    }
}
