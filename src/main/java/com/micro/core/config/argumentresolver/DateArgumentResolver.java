package com.micro.core.config.argumentresolver;

import com.alibaba.fastjson.util.TypeUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @description：日期类型格式处理
 * @author：zhenghj
 * @create：2019/2/22 11:39.
 */


public class DateArgumentResolver implements HandlerMethodArgumentResolver {
    public DateArgumentResolver() {
    }


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Date.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String value = nativeWebRequest.getParameter(methodParameter.getParameterName());
        if (StringUtils.isEmpty(value)) {
            return null;
        } else {
            try {
                return TypeUtils.castToDate(value);
            } catch (Exception var7) {
                return null;
            }
        }
    }
}