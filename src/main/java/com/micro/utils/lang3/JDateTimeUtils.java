package com.micro.utils.lang3;

import jodd.datetime.JDateTime;
import org.jetbrains.annotations.NotNull;

public class JDateTimeUtils {
    public static final String YYYYMMDD = "YYYYMMDD";

    /**
     * 一天中的最后一秒。
     * 如：2019-03-07 23:59:59.000
     * 注：毫秒应=0，否则在数据库中可能会四舍五入
     *
     * @param jDateTime
     * @return
     */
    public static JDateTime lastSecondInDate(@NotNull JDateTime jDateTime) {
        jDateTime.setTime(23, 59, 59, 0);
        return jDateTime;
    }

    /**
     * 一天中的第一秒。
     * 如：2019-03-07 00:00:00.000
     * 注：毫秒应=0，否则在数据库中可能会四舍五入
     *
     * @param jDateTime
     * @return
     */
    public static JDateTime firstSecondInDate(@NotNull JDateTime jDateTime) {
        jDateTime.setTime(0, 0, 0, 0);
        return jDateTime;
    }

    /**
     * 下n个月
     * 如：2019-03-07 -> 2019-04-06
     * 续费日期算法：截止日期=起始日期+1天+1个自然月-2天
     * 例如：
     * 2月26日到期，起始日期为2月27日，加1天为2月28日，加一个自然月为3月28日，再减2天，则截止日期为3月26日。
     * 2月27日到期，起始日期为2月28日，加1天为3月1日，加一个自然月为4月1日，再减2天，则截止日期为3月30日。
     * 2月28日到期（平年），起始日期为3月1日，加1天为3月2日，加一个自然月为4月2日，再减2天，则截止日期为3月31日。
     * 2月28日到期（闰年），起始日期为2月29日，加1天为3月1日，加一个自然月为4月1日，再减2天，则截止日期为3月30日。
     * 2月29日到期（闰年），起始日期为3月1日，加1天为3月2日，加一个自然月为4月2日，再减2天，则截止日期为3月31日。
     * 3月30日到期，起始日期为3月31日，加1天为4月1日，加一个自然月为5月1日，再减2天，则截止日期为4月29日。
     * 3月31日到期，起始日期为4月1日，加1天为4月2日，加一个自然月为5月2日，再减2天，则截止日期为4月30日。
     * @param jDateTime
     * @param n
     * @return
     */
    public static JDateTime nextMonth(@NotNull JDateTime jDateTime, int n) {
        jDateTime.addDay(1);
        jDateTime.addMonth(n);
        jDateTime.addDay(-2);
        return jDateTime;
    }

}
