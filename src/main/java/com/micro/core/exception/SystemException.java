package com.micro.core.exception;

/**
 * 系统类异常，
 * 严重级别：高
 */
public class SystemException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String code;

    public SystemException(String arg0) {
        this("555", arg0);
    }

    public SystemException(String code, String des) {
        super(des);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
