package com.micro.core.exception;


/**
 * @author radioend
 * @ClassName: EntityException
 * @Description: 参数异常类
 * @date 2014-3-14 下午4:39:25
 */
public class ParamException extends RuntimeException {
    private static final long serialVersionUID = 6874181540669667153L;
    private String code;

    public ParamException(String arg0) {
        this("500", arg0);
    }

    private ParamException(String code, String des) {
        super(des);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
