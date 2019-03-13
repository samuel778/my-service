package com.micro.core.exception;

/**
 * 服务类异常，
 * 严重级别：中
 */
public class ServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String code;

    public ServiceException(String arg0) {
        this("500", arg0);
    }

    public ServiceException(String code, String des) {
        super(des);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
