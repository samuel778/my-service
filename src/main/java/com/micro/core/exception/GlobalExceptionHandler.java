package com.micro.core.exception;

import com.micro.query.JsonResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常处理，比如：404,500
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult handlerException(HttpServletRequest req, Exception e) {
        JsonResult r = JsonResult.failureResult(e.getMessage());//TODO 正式环境，请注释，避免系统异常引起系统代码结构暴露
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            r.setCode("404");
        } else if (e instanceof LoginTimeoutException) {
            r.setCode("30000");
            r.setMsg("登陆超时");
        } else if (e instanceof SecurityException) {
            r.setCode("30001");
            r.setMsg("权限异常");
        } else if (e instanceof ServiceException) {
            r.setCode("500");
            r.setMsg(e.getMessage());
        } else if (e instanceof SystemException) {
            r.setCode("555");
            r.setMsg(e.getMessage());
        } else if (e instanceof ParamException) {
            r.setCode("500");
            r.setMsg(e.getMessage());
        }
         else if(e instanceof UnauthorizedException){
            r.setCode("30001");
            r.setMsg("权限异常");
        }
        else if(e instanceof AuthenticationException){
            r.setCode("30000");
            r.setMsg("登陆超时");
        }

        else {
            r.setCode("500");
        }
        logger.error("", e);
        return r;
    }

}