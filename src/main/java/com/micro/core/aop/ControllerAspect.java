package com.micro.core.aop;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.micro.core.annotation.NotLog;
import com.micro.core.annotation.Validators;
import com.micro.core.config.shiro.LoginUser;
import com.micro.core.exception.ParamException;
import com.micro.utils.lang3.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;

@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Pointcut("execution( public * com.micro.controller..*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object service(ProceedingJoinPoint call) throws Throwable {
        LoginUser operateUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Object object = null;
        Signature signature = call.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String servletPath = request.getServletPath();
        String ip = queryClientIp(request);
        MDC.put("ip", ip);
        MDC.put("user", operateUser == null ? "unAuth" : operateUser.getUser().getLoginName());
        MDC.put("path", servletPath);

        log.info("/*===========================REQUEST============================*/");
        log.info("/* requestPath :{} ", servletPath);

        // 判断是否输出日志
        boolean notLog = method.isAnnotationPresent(NotLog.class);
        // 记录下请求内容
        Enumeration<?> pa = request.getParameterNames();
        StringBuffer bodyBuffer = new StringBuffer("body=");
        while (pa.hasMoreElements()) {
            String key = pa.nextElement().toString();
            String value = request.getParameter(key);
            bodyBuffer.append(key + ":" + value + ",");
        }
        log.info("/* requestParam :{}", bodyBuffer.toString());

        // 接口参数效验
        if (method.isAnnotationPresent(Validators.class)) {
            String[] params = method.getAnnotation(Validators.class).value();
            for (String param : params) {
                if (StringUtils.isEmpty(request.getParameter(param))) {
                    throw new ParamException(param + " is null");
                }
            }
        }


        log.info("/*---------------------------SERVICE---------------------------*/");
        Date startTime = new Date();
        object = call.proceed();
        log.info("/*-------------------------------------------------------------*/");
        //if (method.isAnnotationPresent(ResponseBody.class)){
        String json = JSONObject.toJSONString(object,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullListAsEmpty);
        log.info("/*---------------------------RESPONSE---------------------------*/");
        log.info("/* repository = " + json);
        Date endTime = new Date();
        long cost = endTime.getTime() - startTime.getTime();
        log.info("/* cost:{}ms", cost);
        log.info("/*==============================================================*/");
        MDC.clear();
        return object;
    }


    @Around("execution(* com.micro.domain.service..*.*(..))")
    public Object logForService(ProceedingJoinPoint call) throws Throwable {
        Object object = null;
        Date startTime = new Date();
        object = call.proceed();
        Date endTime = new Date();
        long cost = endTime.getTime() - startTime.getTime();

        try {
            log.info(buildCommonMessageForService(call, cost));
        } catch (Exception e) {

        }
        return object;
    }


    private String buildCommonMessageForService(ProceedingJoinPoint call, long cost) {
        String className = call.getTarget().getClass().getSimpleName();
        String methodName = call.getSignature().getName();
        String argsText = CollectionUtils.toDelimitedString(CollectionUtils.makeList(call.getArgs()), new CollectionUtils.KeyPropertyGetter<Object>() {
            @Override
            public Object get(Object item) {
                if (item instanceof String) {
                    return "'" + item + "'";
                }
                return item;
            }
        }, ", ");
        return String.format("%s.%s(%s)@Cost %dms", className, methodName, argsText, cost);
    }

    private String queryClientIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}