package com.micro.query;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;

/**
 * Json数据结果的封装
 *
 * @author zhonglong
 * 2019/2/22 17:19
 */
@SuppressWarnings("unused")
public class JsonResult<T> implements Serializable {

    /**
     * 状态码：成功
     */
    public static final String CODE_SUCCESS = "200";

    public static final String CODE_FAIL = "000";

    private static final long serialVersionUID = 6111848860016623706L;
    private String msg;
    private String code;
    /**
     * 数据
     */
    private T data;

    public JsonResult(T data, String code, String message) {
        if (data != null) {
            this.data = data;
        }
        this.code = code;
        this.msg = message;
    }

    public static long getSerialVersionId() {
        return serialVersionUID;
    }

    public static <T> JsonResult<T> result(String code, String message) {
        return new JsonResult<>(null, CODE_SUCCESS, "success");
    }

    public static <T> JsonResult<T> successResult() {
        return new JsonResult<>(null, CODE_SUCCESS, "success");
    }

    public static <T> JsonResult<T> failureResult(String code, String message) {
        return new JsonResult<>(null, code, message);
    }

    public static <T> JsonResult<T> failureResult() {
        return new JsonResult<>(null, CODE_FAIL, "failure");
    }

    public static <T> JsonResult<T> successResult(String message) {
        return new JsonResult<>(null, CODE_SUCCESS, message);
    }

    public static <T> JsonResult<T> failureResult(String message) {
        return new JsonResult<>(null, CODE_FAIL, message);
    }

    public static <T> JsonResult<T> successResult(T data) {
        return new JsonResult<>(data, CODE_SUCCESS, "success");
    }

    /**
     * 构建带分页结果的JsonResult
     *
     * @param pageData PageInfo类型的分页数据
     * @param <T>      数据类型
     * @return 带分页结果的JsonResult
     */
    public static <T> JsonResult<RealPager<T>> successPageResult(PageInfo<T> pageData) {
        RealPager<T> realPager = new RealPager<>(pageData);
        return successPageResult(realPager);
    }

    /**
     * 构建带分页结果的JsonResult
     *
     * @param pageData 分页数据
     * @param <T>      数据类型
     * @return 带分页结果的JsonResult
     */
    public static <T> JsonResult<RealPager<T>> successPageResult(RealPager<T> pageData) {
        return new JsonResult<>(pageData, CODE_SUCCESS, "success");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
