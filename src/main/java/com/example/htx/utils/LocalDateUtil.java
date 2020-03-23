package com.example.htx.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 张宏斌
 * @Date: 2019/5/10 14:04
 */
public class LocalDateUtil {

    /**
     * 获取两个日期之间的所有月份
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<String> allMonth(LocalDate beginDate, LocalDate endDate) {
        List<String> result = new ArrayList<>();
        result.add(beginDate.format(DateTimeFormatter.ofPattern("yyyy-MM")));
        while (beginDate.isBefore(endDate)) {
            beginDate = beginDate.plusMonths(1);
            result.add(beginDate.format(DateTimeFormatter.ofPattern("yyyy-MM")));
        }
        return result;
    }

    /**
     * 获取两个日期之间的所有月份(倒序)
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<String> allMonthDesc(LocalDate beginDate, LocalDate endDate) {
        List<String> result = new ArrayList<>();
        result.add(endDate.format(DateTimeFormatter.ofPattern("yyyy-MM")));
        while (endDate.isAfter(beginDate)) {
            endDate = endDate.plusMonths(-1);
            result.add(endDate.format(DateTimeFormatter.ofPattern("yyyy-MM")));
        }
        return result;
    }

    /**
     * 获取某月的最后一天
     * @param month
     * @return
     */
    public static String monthEndDay(String month){
        LocalDate parse = LocalDate.parse(month + "-01");
        LocalDate with = parse.with(TemporalAdjusters.lastDayOfMonth());
        return with.toString();
    }
    public static void main(String[] args) {
        String s = monthEndDay("2019-05");
        System.out.println(s);
    }
}
