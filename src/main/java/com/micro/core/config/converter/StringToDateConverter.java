package com.micro.core.config.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description：//TODO description
 * @author：zhenghj
 * @create：2019/3/1 16:53.
 */

@Component
@Slf4j
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(source.trim(), "yyyy-MM-dd");
        }
        if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source.trim(), "yyyy-MM-dd HH:mm:ss");
        }
        throw new IllegalArgumentException("Invalid value '" + source + "'");
    }

    public Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(dateStr);
        }
        catch (ParseException e) {
            log.warn("转换{}为日期(pattern={})错误！", dateStr, format);
        }
        return date;
    }

}

